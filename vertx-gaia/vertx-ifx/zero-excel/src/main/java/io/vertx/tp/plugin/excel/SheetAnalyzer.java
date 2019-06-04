package io.vertx.tp.plugin.excel;

import io.vertx.tp.plugin.excel.atom.ExKey;
import io.vertx.tp.plugin.excel.atom.ExRecord;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.tp.plugin.excel.cell.ExValue;
import io.vertx.tp.plugin.excel.ranger.ColBound;
import io.vertx.tp.plugin.excel.ranger.ExBound;
import io.vertx.tp.plugin.excel.ranger.RowBound;
import io.vertx.tp.plugin.excel.tool.ExFn;
import io.vertx.up.log.Annal;
import io.zero.epic.container.RxHod;
import org.apache.poi.ss.usermodel.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
 * Wrapper Sheet object to store data, this object could help to
 * build ExTable here.
 */
public class SheetAnalyzer implements Serializable {
    private static final Annal LOGGER = Annal.get(SheetAnalyzer.class);
    private final transient Sheet sheet;
    private transient FormulaEvaluator evaluator;

    public SheetAnalyzer(final Sheet sheet) {
        this.sheet = sheet;
    }

    public SheetAnalyzer on(final FormulaEvaluator evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    /*
     * Scan sheet to find all the data and definition part
     */
    public Set<ExTable> analyzed(final ExBound bound) {
        LOGGER.info("[ Excel ] Scan Range: {0}", bound);
        /* Sheet scanning */
        final Set<ExTable> tables = new HashSet<>();
        ExFn.itSheet(this.sheet, bound, (row, index) -> {
            /* Row scanning */
            final Set<Cell> tableCell = new HashSet<>();
            final ExBound colBound = new ColBound(row);
            ExFn.itRow(row, colBound,
                    /* {Table} Cell */
                    (cell, colIndex) -> tableCell.add(cell),
                    /* Predicate Here */
                    cell -> CellType.STRING == cell.getCellType()
                            && ExKey.EXPR_TABLE.endsWith(cell.getStringCellValue()));
            if (!tableCell.isEmpty()) {
                LOGGER.info("[ Excel ] Scanned sheet: {0}, tables = {1}",
                        this.sheet.getSheetName(), String.valueOf(tableCell.size()));
                /* Table Found */
                tableCell.stream().map(cell -> this.analyzed(row, cell))
                        .forEach(tables::add);
            }
        });
        return tables;
    }

    /*
     * Scan sheet from row to cell to build each table.
     */
    private ExTable analyzed(final Row row, final Cell cell) {
        /* Build ExTable */
        final ExTable table = this.create(row, cell);

        /* Scan Field, Once scanning */
        final RxHod hod = new RxHod();
        ExFn.onRow(this.sheet, row.getRowNum() + 2, foundRow -> {
            /* Build Field Col */
            final ExBound bound = new ColBound(cell.getColumnIndex(), row.getLastCellNum());
            ExFn.itRow(foundRow, bound, (foundCell, colIndex) ->
                    table.add(foundCell.getStringCellValue()));
            /* Build Value Row Range */
            hod.add(new RowBound(foundRow.getRowNum() + 1, this.sheet.getLastRowNum()));
        });

        /* Data Range */
        final ExBound dataRange = hod.get();
        ExFn.itSheet(this.sheet, dataRange, (dataRow, dataIndex) -> {
            /* Build Data Col Range */
            final ExBound bound = new ColBound(cell.getColumnIndex(),
                    cell.getColumnIndex() + table.size());
            /* Each row should be record */
            final ExRecord record = this.create(dataRow, bound, table);
            /* Not Empty to add */
            table.add(record);
        });
        return table;
    }

    private ExRecord create(final Row row, final ExBound bound, final ExTable table) {
        final ExRecord record = new ExRecord();
        ExFn.itRow(row, bound, (dataCell, dataIndex) -> {
            /* Field / Value */
            final String field = table.field(dataIndex);
            final Object value = ExValue.getValue(dataCell, this.evaluator);
            /* Stored into record */
            record.put(field, value);
        });
        return record;
    }

    private ExTable create(final Row row, final Cell cell) {
        /* Sheet Name */
        final ExTable table = new ExTable(this.sheet.getSheetName());
        /* Name, Dao, Description - Cell */
        ExFn.onCell(row, cell.getColumnIndex() + 1,
                found -> table.setName(found.getStringCellValue()));
        ExFn.onCell(row, cell.getColumnIndex() + 2,
                found -> table.setDaoCls(found.getStringCellValue()));
        ExFn.onCell(row, cell.getColumnIndex() + 3,
                found -> table.setDescription(found.getStringCellValue()));
        return table;
    }
}

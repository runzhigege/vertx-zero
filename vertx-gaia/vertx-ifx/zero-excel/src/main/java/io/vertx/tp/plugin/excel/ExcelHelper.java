package io.vertx.tp.plugin.excel;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.core.json.JsonArray;
import io.vertx.tp.error._404ExcelFileNullException;
import io.vertx.tp.plugin.excel.atom.ExConnect;
import io.vertx.tp.plugin.excel.atom.ExTable;
import io.vertx.tp.plugin.excel.ranger.ExBound;
import io.vertx.tp.plugin.excel.ranger.RowBound;
import io.vertx.zero.eon.FileSuffix;
import io.vertx.zero.epic.Ut;
import io.vertx.zero.epic.fn.Fn;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

/*
 * Excel Helper to help ExcelClient to do some object building
 */
class ExcelHelper {

    private transient final Class<?> target;

    private ExcelHelper(final Class<?> target) {
        this.target = target;
    }

    static ExcelHelper helper(final Class<?> target) {
        return Fn.pool(Pool.HELPERS, target.getName(), () -> new ExcelHelper(target));
    }

    /*
     * Read file from path to build Excel Workbook object.
     * If this function get null pointer file or file object, zero system
     * will throw exception out.
     */
    @SuppressWarnings("all")
    Workbook getWorkbook(final String filename) {
        Fn.outWeb(null == filename, _404ExcelFileNullException.class, this.target, filename);
        final InputStream in = Ut.ioStream(filename);
        Fn.outWeb(null == in, _404ExcelFileNullException.class, this.target, filename);
        final Workbook workbook;
        if (filename.endsWith(FileSuffix.EXCEL_2003)) {
            workbook = Fn.pool(Pool.WORKBOOKS, filename,
                    () -> Fn.getJvm(() -> new HSSFWorkbook(in)));
        } else {
            workbook = Fn.pool(Pool.WORKBOOKS, filename,
                    () -> Fn.getJvm(() -> new XSSFWorkbook(in)));
        }
        return workbook;
    }

    @SuppressWarnings("all")
    Workbook getWorkbook(final InputStream in, final boolean isXlsx) {
        Fn.outWeb(null == in, _404ExcelFileNullException.class, this.target, "Stream");
        final Workbook workbook;
        if (isXlsx) {
            workbook = Fn.pool(Pool.WORKBOOKS_STREAM, in.hashCode(),
                    () -> Fn.getJvm(() -> new XSSFWorkbook(in)));
        } else {
            workbook = Fn.pool(Pool.WORKBOOKS_STREAM, in.hashCode(),
                    () -> Fn.getJvm(() -> new HSSFWorkbook(in)));
        }
        return workbook;
    }

    /*
     * Get Set<ExSheet> collection based on workbook
     */
    Set<ExTable> getExTables(final Workbook workbook) {
        return Fn.getNull(new HashSet<>(), () -> {
            /* FormulaEvaluator reference */
            final FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            final Iterator<Sheet> it = workbook.sheetIterator();
            final Set<ExTable> sheets = new HashSet<>();
            while (it.hasNext()) {
                /* Build temp ExSheet */
                final Sheet sheet = it.next();
                /* Build Range ( Row Start - End ) */
                final ExBound range = new RowBound(sheet);

                final SheetAnalyzer exSheet = new SheetAnalyzer(sheet).on(evaluator);
                /* Build Set */
                sheets.addAll(exSheet.analyzed(range));
            }
            return sheets;
        }, workbook);
    }

    void initConnect(final JsonArray connects) {
        /* JsonArray serialization */
        if (Pool.CONNECTS.isEmpty()) {
            final List<ExConnect> connectList = Ut.deserialize(connects, new TypeReference<List<ExConnect>>() {
            });
            connectList.stream().filter(Objects::nonNull)
                    .filter(connect -> Objects.nonNull(connect.getTable()))
                    .forEach(connect -> Pool.CONNECTS.put(connect.getTable(), connect));
        }
    }
}

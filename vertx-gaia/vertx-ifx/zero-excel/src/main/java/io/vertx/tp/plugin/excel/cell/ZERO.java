package io.vertx.tp.plugin.excel.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

interface Pool {

    ConcurrentMap<String, ExValue> VALUE_MAP =
            new ConcurrentHashMap<String, ExValue>() {
                {
                    this.put(Literal.UUID, new UuidValue());
                }
            };

    ConcurrentMap<CellType, Function<Cell, Object>> FUNS
            = new ConcurrentHashMap<CellType, Function<Cell, Object>>() {
        {
            this.put(CellType.STRING, Cell::getStringCellValue);
            this.put(CellType.BOOLEAN, Cell::getBooleanCellValue);
            this.put(CellType.NUMERIC, Cell::getNumericCellValue);
        }
    };
}

interface Literal {
    String UUID = "{UUID}";
}

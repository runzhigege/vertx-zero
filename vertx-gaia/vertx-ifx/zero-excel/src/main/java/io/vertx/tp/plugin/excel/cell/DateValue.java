package io.vertx.tp.plugin.excel.cell;

import io.vertx.up.util.Ut;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

class DateValue {

    static Object toNumeric(final Cell cell) {
        if (CellType.NUMERIC == cell.getCellType()) {
            if (DateUtil.isCellDateFormatted(cell)) {
                final Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                final LocalDateTime dateTime = Ut.toDateTime(date);
                if (dateTime.getYear() < 1900) {
                    /*
                     * Default time should be 1899 of year
                     */
                    return dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
                } else {
                    return date.toInstant();
                }
            } else {
                return cell.getNumericCellValue();
            }
        } else return null;
    }
}

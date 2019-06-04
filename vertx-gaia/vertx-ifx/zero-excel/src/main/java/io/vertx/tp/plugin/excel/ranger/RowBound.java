package io.vertx.tp.plugin.excel.ranger;

import org.apache.poi.ss.usermodel.Sheet;

public class RowBound implements ExBound {
    private final transient int start;
    private final transient int end;

    public RowBound(final Sheet sheet) {
        this(sheet.getFirstRowNum(), sheet.getLastRowNum());
    }

    public RowBound(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "RowBound{" +
                "start=" + this.start +
                ", end=" + this.end +
                '}';
    }

    @Override
    public int getStart() {
        return this.start;
    }

    @Override
    public int getEnd() {
        return this.end;
    }
}

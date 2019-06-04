package io.vertx.tp.plugin.excel.cell;

import io.vertx.tp.plugin.excel.atom.ExKey;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.util.Objects;
import java.util.function.Function;

/*
 * Cell Processing for value
 */
@FunctionalInterface
public interface ExValue {
    /*
     * Get value reference to process value
     */
    @SuppressWarnings("all")
    static ExValue get(final Object value) {
        if (Objects.isNull(value)) {
            return PureValue.create();
        }
        final ExValue reference = Pool.VALUE_MAP.get(value);
        if (Objects.isNull(reference)) {
            return PureValue.create();
        } else {
            return reference;
        }
    }

    static Object getValue(final Cell cell, final FormulaEvaluator evaluator) {
        final Function<Cell, Object> fun = Pool.FUNS.get(cell.getCellType());
        if (null == fun) {
            if (CellType.FORMULA == cell.getCellType()) {
                final CellValue value = evaluator.evaluate(cell);
                final String literal = value.getStringValue();
                return ExKey.VALUE_NULL.endsWith(literal.trim()) ? null : literal;
            } else {
                return null;
            }
        } else {
            return fun.apply(cell);
        }
    }

    <T> T to(Object value);
}

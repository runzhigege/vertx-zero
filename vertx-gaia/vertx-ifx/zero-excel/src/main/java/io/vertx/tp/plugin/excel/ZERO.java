package io.vertx.tp.plugin.excel;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    ConcurrentMap<String, Workbook> WORKBOOKS
            = new ConcurrentHashMap<>();

    ConcurrentMap<String, ExcelHelper> HELPERS
            = new ConcurrentHashMap<>();
}

package com.biboheart.demos.collect;

public interface HomeDataCollector {
    boolean collectRecordFromExcel(String filePath, Integer headRowNumber);
    boolean collectPatientFromExcel(String filePath, Integer headRowNumber);
}

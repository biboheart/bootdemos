package com.biboheart.dexcel.service.impl;

import com.biboheart.brick.utils.CheckUtils;
import com.biboheart.dexcel.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;

@Slf4j
//@Service
public class ExcelServiceImpl implements ExcelService {
    private final String path = "E:/test";

    @Override
    public void readExcel() throws IOException, InvalidFormatException {
        File file = new File(path, "ICD10.xlsx");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        //2.读取excel文件,创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //3.读取sheet对象
        Sheet sheet = workbook.getSheetAt(1);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("sheet name:" + sheet.getSheetName());
        System.out.println("last row num:" + lastRowNum);
        //4.定义一些可复用的对象
        Row row = null;
        Cell cell = null;
        //5.读取大标题行
        row = sheet.getRow(0);
        int lastCellNum = row.getLastCellNum();
        System.out.println("last cell num:" + lastCellNum);
        cell = row.createCell(lastCellNum);
        cell.setCellValue("诊断类型");
        cell = row.createCell(lastCellNum + 1);
        cell.setCellValue("发行方");
        cell = row.createCell(lastCellNum + 2);
        cell.setCellValue("版本号");
        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex ++) {
            row = sheet.getRow(rowIndex);
            cell = row.getCell(lastCellNum - 1);
            String name = getCellContent(cell);
            if (CheckUtils.isEmpty(name)) {
                cell.setCellValue(getCellContent(row.getCell(lastCellNum - 3)));
            }
            cell = row.getCell(lastCellNum - 2);
            String icd = getCellContent(cell);
            if (CheckUtils.isEmpty(icd)) {
                cell.setCellValue(getCellContent(row.getCell(lastCellNum - 4)));
            }
            String chapterSn = getCellContent(row.getCell(1));
            String type = "1";
            if ("S00-T98".equals(chapterSn) || "V01-Y98".equals(chapterSn)) {
                type = "2";
            } else if ("C00-D48".equals(chapterSn)) {
                type = "3";
            }
            cell = row.createCell(lastCellNum);
            cell.setCellValue(type);
            cell = row.createCell(lastCellNum + 1);
            cell.setCellValue("医保版");
            cell = row.createCell(lastCellNum + 2);
            cell.setCellValue("v2.0");
        }
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        inputStream.close();
        workbook.write(out);
        out.flush();
        out.close();
        System.out.println("完成");
    }

    @Override
    public void readExcelOperation() throws IOException, InvalidFormatException {
        File file = new File(path, "ICD9.xlsx");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        //2.读取excel文件,创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //3.读取sheet对象
        Sheet sheet = workbook.getSheetAt(1);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("sheet name:" + sheet.getSheetName());
        System.out.println("last row num:" + lastRowNum);
        //4.定义一些可复用的对象
        Row row = null;
        Cell cell = null;
        //5.读取大标题行
        row = sheet.getRow(0);
        int lastCellNum = row.getLastCellNum();
        System.out.println("last cell num:" + lastCellNum);
        cell = row.createCell(lastCellNum);
        cell.setCellValue("发行方");
        cell = row.createCell(lastCellNum + 1);
        cell.setCellValue("版本号");
        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex ++) {
            row = sheet.getRow(rowIndex);
            cell = row.createCell(lastCellNum);
            cell.setCellValue("医保版");
            cell = row.createCell(lastCellNum + 1);
            cell.setCellValue("v2.0");
        }
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        inputStream.close();
        workbook.write(out);
        out.flush();
        out.close();
        System.out.println("完成");
    }

    @Override
    public void readExcelOther() throws IOException, InvalidFormatException {
        File file = new File(path, "GICD9.xlsx");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        ZipSecureFile.setMinInflateRatio(0);
        //2.读取excel文件,创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //3.读取sheet对象
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("sheet name:" + sheet.getSheetName());
        System.out.println("last row num:" + lastRowNum);
        //4.定义一些可复用的对象
        Row row = null;
        Cell cell = null;
        //5.读取大标题行
        row = sheet.getRow(0);
        int lastCellNum = row.getLastCellNum();
        System.out.println("last cell num:" + lastCellNum);
        cell = row.createCell(lastCellNum);
        cell.setCellValue("发行方");
        cell = row.createCell(lastCellNum + 1);
        cell.setCellValue("版本号");
        cell = row.createCell(lastCellNum + 2);
        cell.setCellValue("类型");
        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex ++) {
            row = sheet.getRow(rowIndex);
            cell = row.createCell(lastCellNum);
            cell.setCellValue("国临版");
            cell = row.createCell(lastCellNum + 1);
            cell.setCellValue("v2.0");
            String type = "1";
            // 诊断判断
            /*String sn = getCellContent(row.getCell(1));
            if ('S' == sn.charAt(0) || 'T' == sn.charAt(0) || 'V' == sn.charAt(0) || 'W' == sn.charAt(0) || 'X' == sn.charAt(0) || 'Y' == sn.charAt(0)) {
                type = "2";
            }*/
            String st = getCellContent(row.getCell(2));
            if ("治疗性操作".equals(st)) {
                type = "3";
            } else if ("诊断性操作".equals(st)) {
                type = "2";
            } else if ("介入治疗".equals(st)) {
                type = "4";
            }
            cell = row.createCell(lastCellNum + 2);
            cell.setCellValue(type);
        }
        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
        inputStream.close();
        workbook.write(out);
        out.flush();
        out.close();
        System.out.println("完成");
    }

    @Override
    public void readExcelDrug() throws IOException, InvalidFormatException {
        File file = new File(path, "DRUG.xlsx");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        ZipSecureFile.setMinInflateRatio(0);
        //2.读取excel文件,创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //3.读取sheet对象
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("sheet name:" + sheet.getSheetName());
        System.out.println("last row num:" + lastRowNum);
        Row row = sheet.getRow(0);
        int lastCellNum = row.getLastCellNum();
        System.out.println("last cell num:" + lastCellNum);
        Cell cell = row.createCell(lastCellNum);
        cell.setCellValue("类型编号");
        int rowNum = 1;
        while (rowNum <= lastRowNum) {
            rowNum = write(file.getAbsolutePath(), workbook, rowNum, lastCellNum);
        }
        inputStream.close();
        System.out.println("完成");
    }

    @Override
    public void readExcelDrugFull() throws IOException, InvalidFormatException {

    }

    // 向excel中写入10000行
    private int write(String filePath, XSSFWorkbook workbook, int rowStart, int lastCellNum) throws IOException {
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        int rowEnd = Math.min(lastRowNum, rowStart + 10000);
        log.info("rowStart:{};rowEnd:{};lastRowNum:{}", rowStart, rowEnd, lastRowNum);
        Row row;
        Cell cell;
        for (int rowIndex = rowStart; rowIndex <= rowEnd; rowIndex ++) {
            row = sheet.getRow(rowIndex);
            cell = row.createCell(lastCellNum);
            String type = "11";
            String sn = getCellContent(row.getCell(0));
            if ('Z' == sn.charAt(0)) {
                type = "13";
            }
            cell.setCellValue(type);
        }
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
        return rowEnd;
    }

    private String getCellContent(Cell cell) {
        CellType type = cell.getCellType();
        String content = "";
        if (CellType.NUMERIC.equals(type)) {
            content = new BigDecimal(String.valueOf(cell.getNumericCellValue())).stripTrailingZeros().toPlainString();;
        } else if (CellType.STRING.equals(type)) {
            content = cell.getStringCellValue();
        }
        return content;
    }
}

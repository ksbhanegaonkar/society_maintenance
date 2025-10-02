package com.bhan.ked.parser;

import com.bhan.ked.model.NoBrokerHoodStatementRow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NoBrokerHoodExcelParser {

    public List<NoBrokerHoodStatementRow> parseNoBrokerTransactions(String filePath) throws IOException {
        String excelFilePath = "statement.xlsx"; // path to your file
        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        List<NoBrokerHoodStatementRow> rows = new ArrayList<>();

        Iterator<Row> iterator = sheet.iterator();
        boolean firstRow = true;

        while (iterator.hasNext()) {
            Row row = iterator.next();

            // Skip header row
            if (firstRow) {
                firstRow = false;
                continue;
            }

            if (Objects.nonNull(getCellValueAsString(row.getCell(1)))) {

                NoBrokerHoodStatementRow record = new NoBrokerHoodStatementRow();

                Optional.ofNullable(getCellValueAsString(row.getCell(1)))
                        .ifPresent(record::setAccountName);

                Optional.ofNullable(getCellValueAsString(row.getCell(2)))
                        .ifPresent(record::setBillHead);

                Optional.ofNullable(getCellValueAsString(row.getCell(3)))
                        .ifPresent(record::setTower);

                Optional.ofNullable(getCellValueAsString(row.getCell(4)))
                        .ifPresent(record::setFlat);

                Optional.ofNullable(getCellValueAsString(row.getCell(5)))
                        .ifPresent(record::setEmail);

                Optional.ofNullable(getCellValueAsString(row.getCell(6)))
                        .ifPresent(record::setVoucherNo);

                Optional.ofNullable(getCellValueAsString(row.getCell(7)))
                        .ifPresent(record::setReferenceNo);

                Optional.ofNullable(getCellValueAsString(row.getCell(8)))
                        .ifPresent(record::setChequeNo);

                Optional.ofNullable(getCellValueAsString(row.getCell(9)))
                        .ifPresent(record::setSettlementId);

                Optional.ofNullable(getCellValueAsString(row.getCell(10)))
                        .ifPresent(record::setSettlementIdLast8);

                Optional.ofNullable(getCellValueAsString(row.getCell(11)))
                        .map(this::getFormattedDate)
                        .ifPresent(record::setDate);

                Optional.ofNullable(getCellValueAsString(row.getCell(12)))
                        .ifPresent(record::setReceipts);

                Optional.ofNullable(getCellValueAsString(row.getCell(13)))
                        .ifPresent(record::setPayments);

                Optional.ofNullable(getCellValueAsString(row.getCell(14)))
                        .ifPresent(record::setVoucherType);

                Optional.ofNullable(getCellValueAsString(row.getCell(15)))
                        .ifPresent(record::setNarration);

                Optional.ofNullable(getCellValueAsString(row.getCell(16)))
                        .ifPresent(record::setBankName);


                rows.add(record);
            }
        }

        workbook.close();
        fis.close();

        return rows;
    }


    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // or format date
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            case ERROR:
            default:
                return null;
        }
    }

    private LocalDate getFormattedDate(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy")
                .withLocale(java.util.Locale.ENGLISH);
        ZonedDateTime zdt = ZonedDateTime.parse(dateStr, formatter);
        return zdt.toLocalDate();
    }
}

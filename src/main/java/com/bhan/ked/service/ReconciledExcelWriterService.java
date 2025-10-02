package com.bhan.ked.service;

import com.bhan.ked.model.ReconciledRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class ReconciledExcelWriterService {

    public static void writeExcel(List<ReconciledRecord> records, String filePath) throws IOException {
        // Create workbook
        Workbook workbook = new XSSFWorkbook();

        // Formatter for LocalDate
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Group records by month of bankTransactionDate (1-12)
        Map<Integer, List<ReconciledRecord>> recordsByMonth = records.stream()
                .filter(r -> r.getBankTransactionDate() != null)
                .collect(Collectors.groupingBy(r -> r.getBankTransactionDate().getMonthValue()));

        // Create 10 sheets, one for each month (you can adjust which months)
        for (int month = 1; month <= 12; month++) {
            String sheetName = getMonthName(month);
            Sheet sheet = workbook.createSheet(sheetName);

            // Create header row
            Row header = sheet.createRow(0);
            String[] headers = {"Flat", "Name", "Amount", "Bank Transaction Date",
                    "NoBroker Transaction Date", "Late Payment", "Settlement Id NoBroker", "Bank Statement Transaction Id"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Write records for this month
            List<ReconciledRecord> monthRecords = recordsByMonth.getOrDefault(month, Collections.emptyList());

            int rowIdx = 1;
            for (ReconciledRecord r : monthRecords) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(r.getFlat());
                row.createCell(1).setCellValue(r.getName());
                row.createCell(2).setCellValue(r.getAmount());
                row.createCell(3).setCellValue(r.getBankTransactionDate() != null ? r.getBankTransactionDate().format(dateFormatter) : "");
                row.createCell(4).setCellValue(r.getNoBrokerTransactionDate() != null ? r.getNoBrokerTransactionDate().format(dateFormatter) : "");
                row.createCell(5).setCellValue(r.getLatePayment());
                row.createCell(6).setCellValue(r.getSettlementIdNoBroker());
                row.createCell(7).setCellValue(r.getBankStatementTransactionId());
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
        }

        // ===== Create Summary Sheet =====
        Sheet summarySheet = workbook.createSheet("SUMMARY");

        // Distinct flats with their latest name
        Map<String, String> flatToName = records.stream()
                .filter(r -> r.getFlat() != null)
                .collect(Collectors.toMap(
                        ReconciledRecord::getFlat,
                        ReconciledRecord::getName,
                        (name1, name2) -> name1 != null ? name1 : name2 // pick first non-null
                ));

        // Header row (Flat, Name, Jan … Dec)
        Row header = summarySheet.createRow(0);
        header.createCell(0).setCellValue("Flat");
        header.createCell(1).setCellValue("Name");
        for (int month = 1; month <= 12; month++) {
            String monthName = java.time.Month.of(month).getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            header.createCell(month + 1).setCellValue(monthName);
        }

        // Fill rows
        List<Integer> flatNumbersSorted = flatToName.keySet().stream()
                .map(Integer::parseInt)
                .sorted()
                .toList();
        int rowIdx = 1;
        List<String> sortedFlatNumbers = flatToName.keySet().stream()
                .sorted(Comparator.comparing(Integer::parseInt))
                .toList();
        for (String flat : sortedFlatNumbers) {
            Row row = summarySheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(flat);
            row.createCell(1).setCellValue(flatToName.get(flat));

            for (int month = 1; month <= 12; month++) {
                final int m = month; // <-- make it effectively final

                double total = records.stream()
                        .filter(r -> flat.equals(r.getFlat()))
                        .filter(r -> r.getBankTransactionDate() != null && r.getBankTransactionDate().getMonthValue() == m)
                        .mapToDouble(ReconciledRecord::getAmount)
                        .sum();

                row.createCell(month + 1).setCellValue(total);
            }
        }

        // Auto-size columns
        for (int i = 0; i <= 13; i++) {
            summarySheet.autoSizeColumn(i);
        }

        // Write workbook to file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
        System.out.println("Excel file created at: " + filePath);
    }

    private static String getMonthName(int month) {
        // Adjust month mapping to your preference
        return java.time.Month.of(month).name(); // returns JANUARY, FEBRUARY etc.
    }

}

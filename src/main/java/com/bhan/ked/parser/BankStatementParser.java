package com.bhan.ked.parser;

import com.bhan.ked.model.BankTransaction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class BankStatementParser {

    private static final Pattern COL_PATTERN = Pattern.compile("(?<=\\|)[^|]*(?=\\|)");
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public static void main(String[] args) throws Exception {
        File file = new File("statement.pdf");
        PDDocument document = PDDocument.load(file);

        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        List<BankTransaction> transactions = parseTransactions(text);
        transactions.forEach(System.out::println);

    }

    public List<BankTransaction> getAllBankTransactionFromPdf(String path) throws IOException {
        File file = new File(path);
        PDDocument document = PDDocument.load(file);

        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        return parseTransactions(text);
    }

    static List<BankTransaction> parseTransactions(String text) {
        String[] lines = text.split("\\r?\\n");
        StringBuilder currentRow = new StringBuilder();

        return Stream.of(lines)
                .map(BankStatementParser::extractColumns)
                .filter(list -> list.size() == 8)
                .map(cols -> {
                    try {
                        LocalDate date = LocalDate.parse(cols.get(0).trim(), DATE_FMT);
                        return new BankTransaction(date, cols.get(1), cols.get(2),
                                Objects.nonNull(cols.get(3)) ? cols.get(3) : cols.get(4),
                                Objects.nonNull(cols.get(5)) ? cols.get(5) : cols.get(6),
                                cols.get(7)
                        );
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    // Extracts all columns between pipes, normalizes NBSP -> space and trims
    public static List<String> extractColumns(String line) {
        Matcher m = COL_PATTERN.matcher(line);
        List<String> cols = new ArrayList<>();
        while (m.find()) {
            String col = m.group().replace('\u00A0', ' ').trim();
            cols.add(col);
        }
        return cols;
    }

    // Parse a date string like 06-07-2025 -> LocalDate (returns null on parse failure)
    public static LocalDate parseDate(String s) {
        if (s == null || s.isEmpty()) return null;
        try {
            return LocalDate.parse(s.trim(), DATE_FMT);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    // Parse an amount string like "6,02,167.22 Cr" or "(1,234.56)" or "2500.00"
    // Returns null if not parseable. Does not infer sign from Cr/Dr (caller may use isCredit/isDebit helper).
    public static Double parseAmount(String s) {
        if (s == null) return null;
        String t = s.replace('\u00A0', ' ').trim();
        if (t.isEmpty()) return null;

        boolean negative = false;
        // parentheses indicate negative in many bank statements
        if (t.startsWith("(") && t.endsWith(")")) {
            negative = true;
            t = t.substring(1, t.length() - 1).trim();
        }

        // remove Cr/Dr etc.
        t = t.replaceAll("(?i)cr|dr|credit|debit", "");
        // remove commas and spaces
        t = t.replaceAll("[,\\s]", "");
        // keep only digits, dot and minus
        t = t.replaceAll("[^0-9.\\-]", "");

        if (t.isEmpty()) return null;
        Double val = Double.parseDouble(t);
        if (negative) val = val * (-1);
        return val;
    }

}

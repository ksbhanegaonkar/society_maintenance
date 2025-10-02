package com.bhan.ked.service;

import com.bhan.ked.model.BankTransaction;
import com.bhan.ked.model.NoBrokerHoodStatementRow;
import com.bhan.ked.model.ReconciledRecord;
import com.bhan.ked.parser.BankStatementParser;
import com.bhan.ked.parser.NoBrokerHoodExcelParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionValidationService {
    public static final Set<String> validTransactionKeywords = Set.of("IMPS", "UPI", "NEFT");
    public static void main(String[] args) throws IOException {
        NoBrokerHoodExcelParser noBrokerHoodExcelParser = new NoBrokerHoodExcelParser();
        BankStatementParser bankStatementParser = new BankStatementParser();

        List<NoBrokerHoodStatementRow> noBrokerHoodStatementRows = noBrokerHoodExcelParser.parseNoBrokerTransactions("statement.xlsx");

        Map<String, List<NoBrokerHoodStatementRow>> noBrokerEntriesByFlatNumber = noBrokerHoodStatementRows.stream()
                .filter(row -> Objects.nonNull(row.getFlat()))
                .filter(row -> Objects.nonNull(row.getSettlementId()))
                .collect(Collectors.groupingBy(NoBrokerHoodStatementRow::getSettlementId));

        List<BankTransaction> allBankTransactionFromPdf = bankStatementParser.getAllBankTransactionFromPdf("statement.pdf");

        Map<String, List<BankTransaction>> bankTransactionsByParticulars = allBankTransactionFromPdf.stream()
                .collect(Collectors.groupingBy(BankTransaction::getParticulars));

        Map<String, List<Pair<BankTransaction, NoBrokerHoodStatementRow>>> bankTransactionNoBrokerTransactionMapByBankTransactionId = bankTransactionsByParticulars.entrySet().stream()
                .filter(entry -> validTransactionKeywords.stream().anyMatch(keyword -> entry.getKey().contains(keyword)))
                .map(entry -> mapToTransactionaPair(entry.getKey(), entry.getValue(), noBrokerEntriesByFlatNumber))
                .collect(Collectors.groupingBy(pair -> pair.getLeft().getParticulars()));

        List<ReconciledRecord> reconciledRecords = mapToReconciledRecords(bankTransactionNoBrokerTransactionMapByBankTransactionId);

        ReconciledExcelWriterService.writeExcel(reconciledRecords, "Output.xlsx");

    }

    private static List<ReconciledRecord> mapToReconciledRecords(Map<String, List<Pair<BankTransaction, NoBrokerHoodStatementRow>>> bankTransactionNoBrokerTransactionMapByBankTransactionId) {
        return bankTransactionNoBrokerTransactionMapByBankTransactionId.values().stream()
                .map(TransactionValidationService::mapToReconciledRecord)
                .filter(Objects::nonNull)
                .toList();
    }

    private static ReconciledRecord mapToReconciledRecord(List<Pair<BankTransaction, NoBrokerHoodStatementRow>> pairs) {
        Optional<Pair<BankTransaction, NoBrokerHoodStatementRow>> pair = pairs.stream().findFirst();
        if(pair.isPresent() && StringUtils.isNotBlank(pair.get().getLeft().getCredit())) {
            BankTransaction bankTransaction = pair.get().getLeft();
            NoBrokerHoodStatementRow noBrokerHoodStatementRow = pair.get().getRight();
            ReconciledRecord record = new ReconciledRecord();

            record.setFlat(noBrokerHoodStatementRow.getFlat());
            record.setName(noBrokerHoodStatementRow.getAccountName());
            record.setAmount(Double.parseDouble(bankTransaction.getCredit()));
            record.setBankTransactionDate(bankTransaction.getDate());
            record.setNoBrokerTransactionDate(noBrokerHoodStatementRow.getDate());
            record.setLatePayment(bankTransaction.getDate().getDayOfMonth() > 10 ? "Yes" : "No");
            record.setSettlementIdNoBroker(noBrokerHoodStatementRow.getSettlementId());
            record.setBankStatementTransactionId(bankTransaction.getParticulars());
            return record;
        }
        return null;
    }

    private static Pair<BankTransaction, NoBrokerHoodStatementRow> mapToTransactionaPair(String bankParticular, List<BankTransaction> bankTransactions, Map<String, List<NoBrokerHoodStatementRow>> noBrokerEntriesByFlatNumber) {
        BankTransaction bankTransaction = bankTransactions.stream().findFirst().orElse(new BankTransaction());
        Optional<String> foundTransaction = noBrokerEntriesByFlatNumber.keySet().stream()
                .filter(bankParticular::contains)
                .findFirst();
        if(foundTransaction.isPresent()){
            NoBrokerHoodStatementRow noBrokerHoodStatementRow = noBrokerEntriesByFlatNumber.get(foundTransaction.get()).stream()
                    .findFirst()
                    .orElse(new NoBrokerHoodStatementRow());
            return Pair.of(bankTransaction, noBrokerHoodStatementRow);
        }else {
            return Pair.of(bankTransaction, new NoBrokerHoodStatementRow());
        }
    }
}

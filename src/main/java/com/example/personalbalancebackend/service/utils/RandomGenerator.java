package com.example.personalbalancebackend.service.utils;

import com.example.personalbalancebackend.entity.TxSourceEnum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private static final String[] INCOME_ADJECTIVES = {
            "annual", "bountiful", "cash", "double", "earnest", "fiscal", "generous", "hefty", "invested", "joint",
            "killing", "lucrative", "monetary", "net", "overdue", "profitable", "quick", "revenue", "surplus", "taxable",
            "upfront", "valuable", "wealthy", "yielding", "zero-risk"
    };

    private static final String[] INCOME_NOUNS = {
            "account", "bonus", "compensation", "dividend", "earnings", "funds", "gross", "income", "job", "kicker",
            "livelihood", "money", "net", "paycheck", "quid", "revenue", "salary", "take-home", "upside", "victory",
            "wages", "x-factor", "yield", "zenith"
    };

    private static final String[] EXPENSE_ADJECTIVES = {
            "basic", "capital", "daily", "emergency", "fixed", "general", "housing", "indirect", "joint", "large",
            "miscellaneous", "necessary", "operating", "personal", "quick", "recurring", "specific", "travel", "unforeseen", "variable",
            "wasteful", "x-treme", "yearly", "zero-sum"
    };

    private static final String[] EXPENSE_NOUNS = {
            "bills", "car expenses", "child care", "clothing", "credit card payments", "education", "entertainment", "food",
            "gifts", "health care", "household items", "insurance", "investments", "memberships", "mortgage", "office supplies",
            "personal care", "pets", "rent", "subscriptions", "taxes", "transportation", "utilities", "vacation", "water bill"
    };

    private static final String[] CATEGORIES = {
            "Banking", "Investing", "Insurance", "Retirement", "Credit", "Taxes", "Estate Planning", "Budgeting", "Savings", "Debt"
    };

    public static BigDecimal bigDecimalBetween(BigDecimal min, BigDecimal MAX) {
        int digitCount = Math.max(min.precision(), MAX.precision());
        int bitCount = (int)(digitCount / Math.log10(2.0));

        // convert Random BigInteger to a BigDecimal between 0 and 1
        BigDecimal alpha = new BigDecimal(
                new BigInteger( bitCount, new Random() )
        ).movePointLeft(digitCount);

        return min.add(MAX.subtract(min).multiply(alpha, new MathContext(digitCount)));
    }

    public static String generateRandomTerm(boolean isIncomeTerm) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        int numAdjectives = rand.nextInt(2) + 1;  // generate 1-3 adjectives
        for (int i = 0; i < numAdjectives; i++) {
            int adjIndex = rand.nextInt(isIncomeTerm ? INCOME_ADJECTIVES.length : EXPENSE_ADJECTIVES.length);
            String adj = isIncomeTerm ? INCOME_ADJECTIVES[adjIndex] : EXPENSE_ADJECTIVES[adjIndex];
            if (i > 0) {
                sb.append("_");
            }
            sb.append(adj);
        }
        int nounIndex = rand.nextInt(isIncomeTerm ? INCOME_NOUNS.length : EXPENSE_NOUNS.length);
        String noun = isIncomeTerm ? INCOME_NOUNS[nounIndex] : EXPENSE_NOUNS[nounIndex];
        sb.append("_");
        sb.append(noun);
        return sb.toString();
    }

    public static TxSourceEnum randomTxSource(boolean isIncome) {
        List<TxSourceEnum> sourceList = new ArrayList<>();
        if (isIncome) {
            sourceList.add(TxSourceEnum.OTHERS);
            sourceList.add(TxSourceEnum.SALARY);
            sourceList.add(TxSourceEnum.BONUS);
            sourceList.add(TxSourceEnum.INTEREST);
        } else {
            sourceList.add(TxSourceEnum.CREDIT_CARD);
            sourceList.add(TxSourceEnum.CASH);
            sourceList.add(TxSourceEnum.DEBIT_CARD);
        }


        Random rand = new Random();
        int size = sourceList.size() - 1;

        return sourceList.get(rand.nextInt(size));
    }

    public static String generateRandomCategoryTerm() {
        Random rand = new Random();
        int categoryIndex = rand.nextInt(CATEGORIES.length);
        return CATEGORIES[categoryIndex];
    }

}

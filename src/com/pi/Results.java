package com.pi;

import java.math.BigDecimal;
import java.util.Vector;

class Results {

    static Vector<BigDecimal> threadResults = new Vector<>();

    private static BigDecimal getSum() {

        BigDecimal sum = BigDecimal.ZERO;

        for (Object threadResult : threadResults) {
            sum = sum.add((BigDecimal) threadResult);
        }
        return sum;
    }

    static BigDecimal getResult() {

        BigDecimal result = getSum();

        // 1/882
        BigDecimal coefficient = BigDecimal.ONE.divide(BigDecimal.valueOf(882), Constants.PRECISION, Constants.MODE);

        // *1/882
        result = result.multiply(coefficient);
        result = BigDecimal.valueOf(4).divide(result, Constants.PRECISION, Constants.MODE);

        return result;
    }
}

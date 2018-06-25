package com.pi;

import com.pi.utils.Constants;
import com.pi.utils.MathFunctions;

import java.math.BigDecimal;
import java.math.BigInteger;

class SeriesMember {

    private BigDecimal nominator;
    private BigDecimal denominator;

    SeriesMember(int memberNumber) {
        this.nominator = new BigDecimal(computeNominator(memberNumber));
        this.denominator = new BigDecimal(computeDenominator(memberNumber));
    }
    
    /**
     * Compute the nominator of the series of Ramanujan.
     *
     * @param n the n-th member of the series
     */
    private BigInteger computeNominator(int n) {

        BigInteger result = BigInteger.ONE;

        // (-1)^n
        if (n % 2 != 0) {
            result = result.multiply(BigInteger.valueOf(-1));
        }

        // (4n)!
        result = result.multiply(MathFunctions.factorial(BigInteger.valueOf(n).multiply(BigInteger.valueOf(4))));

        // 1123 + 21460n
        result = result.
                multiply((BigInteger.valueOf(1123).add(BigInteger.valueOf(21460).multiply(BigInteger.valueOf(n)))));

        return result;
    }

    /**
     * Compute the denominator of the series of Ramanujan.
     *
     * @param n the n-th member of the series
     */
    private BigInteger computeDenominator(int n) {

        BigInteger result = BigInteger.ONE;

        // *4^n
        result = result.multiply(MathFunctions.power(BigInteger.valueOf(4), BigInteger.valueOf(n)));

        // *n!
        result = result.multiply(MathFunctions.factorial(BigInteger.valueOf(n)));

        // ^4
        result = MathFunctions.power(result, BigInteger.valueOf(4));

        // *882^(2n)
        result = result.multiply(MathFunctions.power(BigInteger.valueOf(882), BigInteger.valueOf(2 * n)));

        return result;
    }

    /**
     * Compute series member
     *
     * @return BigDecimal result
     */
    BigDecimal compute() {

        return (this.nominator).divide(this.denominator, Constants.PRECISION, Constants.MODE);
    }
}
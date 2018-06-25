package com.pi.utils;

import java.math.BigInteger;

public class MathFunctions {

    /**
     * Calculate the power bitwise
     * http://en.wikipedia.org/w/index.php?title=Modular_exponentiation&oldid=517653653#Right-to-left_binary_method
     *
     * @param base     BigInteger
     * @param exponent BigInteger
     * @return BigInteger result
     */
    public static BigInteger power(BigInteger base, BigInteger exponent) {

        BigInteger result = BigInteger.ONE;

        if (exponent.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }

        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            boolean isOdd = exponent.testBit(0);
            if (isOdd) {
                result = result.multiply(base);
            }
            exponent = exponent.shiftRight(1);
            base = (base.multiply(base));
        }

        return result;
    }

    /**
     * Calculate n! (n-th factorial)
     *
     * @param n BigInteger
     * @return BigInteger result
     */
    public static BigInteger factorial(BigInteger n) {
        BigInteger result = BigInteger.ONE;

        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }

        return result;
    }
}
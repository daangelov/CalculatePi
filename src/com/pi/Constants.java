package com.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Constants {

    // Define a rounding mode
    public static final RoundingMode MODE = RoundingMode.HALF_EVEN;

    // The precision accurate for dividing operations.
    public static final int PRECISION = 20;

    // The final residual for Pi Ramanujan formula.
    public static final BigDecimal RESIDUAL_CONSTANT =
            BigDecimal.valueOf(1).divide(BigDecimal.valueOf(882), PRECISION, MODE);

    public static final String DEFAULT_OUTPUT_FILE = "result.txt";
}

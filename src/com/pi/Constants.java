package com.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Constants {

    // Define a rounding mode
    static final RoundingMode MODE = RoundingMode.HALF_EVEN;

    // The precision accurate for dividing operations.
    static final int PRECISION = 20;

    // The final residual for Pi Ramanujan formula.
    static final BigDecimal RESIDUAL_CONSTANT =
            BigDecimal.valueOf(1).divide(BigDecimal.valueOf(882), PRECISION, MODE);

    static final String DEFAULT_OUTPUT_FILE = "result.txt";
}

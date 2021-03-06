package com.pi;

import java.math.BigDecimal;
import java.util.Calendar;

public class RunnableThread implements Runnable {

    private int threadId;
    private int from;
    private int to;

    RunnableThread(int threadId, int from, int to) {
        this.threadId = threadId;
        this.from = from;
        this.to = to;
    }

    /**
     * Compute the series from start to end
     *
     * @return BigDecimal result from computing to-from members from the series
     */
    private BigDecimal computePartialSeries() {

        BigDecimal result = BigDecimal.ZERO;

        for (int i = this.from; i < this.to; i++) {
            SeriesMember seriesMember = new SeriesMember(i);
            BigDecimal seriesMemberComputed = seriesMember.compute();

            result = result.add(seriesMemberComputed);
        }

        return result;
    }

    @Override
    public void run() {
        Logger.threadStartedMessage(this.threadId);
        final long startTime = Calendar.getInstance().getTimeInMillis();

        try {
            BigDecimal result = computePartialSeries();
            Results.threadResults.add(result);

        } finally {
            final long endTime = Calendar.getInstance().getTimeInMillis();
            final long executionTime = endTime - startTime;

            Logger.threadEndedMessage(this.threadId, executionTime);
        }
    }
}
package com.pi;

class ThreadPool {

    private int precision;
    private int threads;

    private Thread threadPool[];
    private int chunkSize;

    ThreadPool(int precision, int threads) {
        this.precision = precision;
        this.threads = threads;

        initiate();
    }

    private void initiate() {
        this.threadPool = new Thread[this.threads];
        this.chunkSize = this.precision / this.threads;
    }

    void startThreads() {

        int lastTo = 0;
        for (int threadId = 0; threadId < this.threads; threadId++) {

            int threadsLeft = this.threads - (threadId + 1);
            int from = lastTo;
            int to = from + this.chunkSize;
            lastTo = to;

            if (threadsLeft == 0) {
                to = this.precision;
            }

            // Create thread to sum from, to
            Runnable runnableThread = new RunnableThread(threadId, from, to);

            // Save a ref to the thread and start it
            Thread currentThread = new Thread(runnableThread);
            this.threadPool[threadId] = currentThread;
            currentThread.start();
        }
    }

    void joinThreads() {

        for (int i = 0; i < this.threads; i++) {
            try {
                this.threadPool[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

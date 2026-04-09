public class ResourceManager {

    private final Object gradeLock = new Object();
    private final Object reportLock = new Object();

    public void safeOperation() {
        synchronized (gradeLock) {
            synchronized (reportLock) {
                System.out.println("Safe execution");
            }
        }
    }

    /*
    // Deadlock example (DO NOT RUN)
    public void deadlockExample() {

        Thread t1 = new Thread(() -> {
            synchronized (gradeLock) {
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (reportLock) {
                    System.out.println("Thread 1 done");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (reportLock) {
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (gradeLock) {
                    System.out.println("Thread 2 done");
                }
            }
        });

        t1.start();
        t2.start();
    }
    */
}
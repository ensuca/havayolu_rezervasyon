import java.util.Random;
import java.util.concurrent.locks.Lock;


public class WWL implements Runnable {

    private final Ucak ucak;
    private final String koltukNo;
    private final Lock lock;


    public WWL(Ucak ucak, String koltukNo, Lock lock) {
        this.ucak = ucak;
        this.koltukNo = koltukNo;
        this.lock = lock;
    }
    
    @Override
    public void run() {

        lock.lock();

        try {
            Random random = new Random();

            boolean draw = random.nextBoolean();

            if (draw) {
                rezervasyon();
            } else {
                rezervasyonIptali();
            }
        } finally {
            lock.unlock();
        }
    }

    private void rezervasyon() {
        ucak.biletAlım(koltukNo, (int) Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName() + " booked seat " + koltukNo + " successfully.");
    }

    private void rezervasyonIptali() {
        ucak.biletIptali(koltukNo);
        System.out.println(Thread.currentThread().getName() + " cancel seat " + koltukNo + " successfully. ");
    }
}

import java.util.concurrent.locks.Lock;

public class RWL implements Runnable {

    private final Ucak ucak;

    private final Lock lock;

    public RWL(Ucak ucak, Lock lock) {
        this.ucak = ucak;
        this.lock = lock;
    }

    @Override
    public void run() {

        lock.lock();

        try {
            queryReservation();

        } finally {
            lock.unlock();
        }

    }

    private void queryReservation() {
        System.out.println(Thread.currentThread().getName() + " looks for available seats.");
        System.out.println(ucak.tumkoltuklar());
    }
}

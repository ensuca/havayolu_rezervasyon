import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        Ucak ucak = new Ucak();
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 12; i++) {
            Random random = new Random();
            int sayı = random.nextInt(1, 4);
            String koltuk = Integer.toString(sayı) + randomChar();
            threads.add(new Thread(new Writer(ucak, koltuk), "Writer " + i));
            threads.add(new Thread(new Reader(ucak), "Reader " + i));
        }

        for (int i = 0; i < 24; i++) {
            threads.get(i).start();
        }

    }

    public static char randomChar() {
        String koltukHarf = "ABC";
        Random random = new Random();

        char c = koltukHarf.charAt(random.nextInt(koltukHarf.length()));

        return c;
    }
}

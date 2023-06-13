import java.util.Random;

public class Writer implements Runnable {

    private final String koltukNo;

    private final Ucak ucak;

    
    public Writer(Ucak ucak, String koltukNo) {
        this.ucak = ucak;
        this.koltukNo = koltukNo;
    }
    //Thread calistiginda yapilacak isler.
    @Override
    public void run() {
        Random random = new Random();
        boolean draw = random.nextBoolean();

        rezervasyon();
    }
    //Rezervasyon fonksiyonumuz.
    private void rezervasyon() {
        ucak.biletAlım(koltukNo, (int) Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName() + " booked seat " + koltukNo +  " successfully. ");
    }
    
    //Rezervasyon iptali fonksiyonumuz.
    private void rezervasyonIptali() {
        ucak.biletIptali(koltukNo);
        System.out.println(Thread.currentThread().getName() + " cancel seat " + koltukNo + " successfully. ");
    }
}

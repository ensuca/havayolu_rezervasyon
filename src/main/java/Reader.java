public class Reader implements Runnable {

    private final Ucak ucak;

    public Reader(Ucak ucak) {
        this.ucak = ucak;
    }
    //Thread calistiginda yapilacak isler.
    @Override
    public void run() {
        queryReservation();
    }

    private void queryReservation() {
        System.out.println(Thread.currentThread().getName() + " looks for available seats.");
        System.out.println(ucak.tumkoltuklar());
    }


}

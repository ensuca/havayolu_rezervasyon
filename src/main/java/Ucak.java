import java.util.HashMap;
import java.util.Map;
//ucak icin koltuk plani olusturuyoruz.
public class Ucak {

    private static Map<String, String> Koltuk_Plani;

    public Ucak() {
        Koltuk_Plani = new HashMap<>();

        Koltuk_Plani.put("1A", "bos");
        Koltuk_Plani.put("1B", "bos");
        Koltuk_Plani.put("1C", "bos");
        Koltuk_Plani.put("2A", "bos");
        Koltuk_Plani.put("2B", "bos");
        Koltuk_Plani.put("2C", "bos");
        Koltuk_Plani.put("3A", "bos");
        Koltuk_Plani.put("3B", "bos");
        Koltuk_Plani.put("3C", "bos");
        Koltuk_Plani.put("4A", "bos");
        Koltuk_Plani.put("4B", "bos");
        Koltuk_Plani.put("4C", "bos");
    }
    //Tum koltuklari goruntuleyen fonksiyon
    public String tumkoltuklar() {
        return Koltuk_Plani.toString();
    }
    //Koltuk durumunu gosteren fonskiyon
    public String koltukBilgi(String koltukNo) {
        return Koltuk_Plani.get(koltukNo);
    }
    //Bos olan koltugun alımı.
    public boolean biletAlım(String koltukNo, int musteriNo) {
        if (Koltuk_Plani.get(koltukNo).equals("bos")) {

            Koltuk_Plani.put(koltukNo, Integer.toString(musteriNo));
            return true;
        }
        return false;
    }
    // Bilet iptali.
    public void biletIptali(String koltukNo) {
        Koltuk_Plani.put(koltukNo, "bos");
    }
}

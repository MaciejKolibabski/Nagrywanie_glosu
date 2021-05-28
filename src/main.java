import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class main {

    public static void wcisnij() {
        try {
            System.in.read();
        } catch (Exception ignored) {
            System.out.println("Wyjątek !!!");
        }
    }

    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, InterruptedException, IOException {
        AudioON record = new AudioON();
        System.out.println("Kliknij by rozpocząć nagrywanie dźwięku ");
        wcisnij();
        record.zacznij();
        System.out.println("Kliknij by zakończyć nagrywanie ");
        wcisnij();
        record.zatrzymaj();
        System.out.println("Kliknij by odsłuchaj nagrania");
        wcisnij();
        record.odtworz();
    }
}

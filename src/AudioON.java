import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioON {

    private AudioFormat audio; //określenie konkretnego układu danych w strumieniu (bity binarnych danych dzwiekowych)
    private TargetDataLine target; //odczyt danych audio



    class CaptureThread extends Thread {

        //funkcja run() inicjuje operacje nagrywania dzwieku

        public void run() {
            AudioFileFormat.Type pliczek = AudioFileFormat.Type.WAVE;
            File pliknagrania = new File("nagranie"); //plik z
            try {
                target.open(audio); //Otwiera wiersz o określonym formacie, powodując, że wiersz pobiera wszystkie wymagane zasoby systemowe i zaczyna działać.
                target.start(); //rozpoczescie odczytu
                AudioSystem.write(new AudioInputStream(target), pliczek, pliknagrania); //zapis do plików
            } catch (Exception e) { //rzucanie wyjatkow
                e.printStackTrace();
            }
        }
    }


    public void zacznij() {
        try {
            audio = new AudioFormat(8000.0F, 16, 1, true, false); //parametry
            // sampleRate - częstotliwość próbkowania
            // sampleSizeInBits - rozmiar próbki
            // channels - liczba kanałow
            // signed
            // bigEndian - wskazuje, czy dane dla pojedynczej próbki są przechowywane w kolejności bajtów big-endian ( falseczyli little-endian)
            DataLine.Info datainfo = new DataLine.Info(TargetDataLine.class, audio);
            target = (TargetDataLine) AudioSystem.getLine(datainfo);
            CaptureThread captureThread = new CaptureThread();
            captureThread.start();
        } catch (NullPointerException e) {
            System.out.println("Wyjątek !!!");
        } catch (IllegalArgumentException | LineUnavailableException e) {
            System.out.println("Wyjątek !!!");
            System.exit(0);
        }
    }


    public void zatrzymaj() {
        target.stop(); //zakończenie odczytu danych
        target.close(); //zamknięcie
    }

    public void odtworz() {
        try {
            Clip clip = AudioSystem.getClip();
            File record = new File("nagranie");
            clip.open(AudioSystem.getAudioInputStream(record));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception exc) {
            System.out.println("Wyjątek !!!");
        }
    }
}

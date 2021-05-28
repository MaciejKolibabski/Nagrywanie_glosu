import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioON {

    private AudioFormat audio; //określenie konkretnego układu danych w strumieniu (bity binarnych danych dzwiekowych)
    private TargetDataLine target; //odczyt danych audio

    class CaptureThread extends Thread {
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

    public void zacznij() throws LineUnavailableException {
            audio = new AudioFormat(8000.0F, 16, 1, true, false); //parametry
            // sampleRate - częstotliwość próbkowania
            // sampleSizeInBits - rozmiar próbki
            // channels - liczba kanałow
            // signed - nie wiem
            // bigEndian - wskazuje, czy dane dla pojedynczej próbki są przechowywane w kolejności bajtów big-endian ( falseczyli little-endian)
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audio); //zawiera dodatkowe informacje specyficzne dla linii danych.
            target = (TargetDataLine) AudioSystem.getLine(info); //konwersja audio
            CaptureThread ct = new CaptureThread();
            ct.start();
    }

    public void zatrzymaj() {
        target.stop(); //zakończenie odczytu danych
        target.close(); //zamknięcie
    }

    public void odtworz() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
            Clip clip = AudioSystem.getClip(); //dane audio mogą być ładowane przed odtwarzania, zamiast być transmitowane w czasie rzeczywistym.
            File record = new File("nagranie"); //plik z nagraniem
            clip.open(AudioSystem.getAudioInputStream(record)); //załadowanie clipu
            clip.start(); //start odtwarzania
            Thread.sleep(clip.getMicrosecondLength() / 1000);
    }
}

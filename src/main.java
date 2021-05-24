public class main {

    public static void main(String[] args) {
        AudioON record = new AudioON();
        System.out.println("Rozpocznij nagrywanie dźwięku ");
        record.click();
        record.captureAudio();
        System.out.println("Zakończ ");
        record.click();
        record.stopAudio();
        record.click();
        System.out.println("Odsłuchaj nagrania");
        record.playRecord();
    }
}

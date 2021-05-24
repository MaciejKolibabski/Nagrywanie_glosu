public class main {

    public static void wcisnij() {
        try {
            System.in.read();
        } catch (Exception ignored) {
            System.out.println("Wyjątek !!!");
        }
    }


    public static void main(String[] args) {
        AudioON record = new AudioON();
        System.out.println("Kliknij by rozpocząć nagrywanie dźwięku ");
        wcisnij();
        record.zacznij();
        System.out.println("Kliknij by zakończyć nagrywanie ");
        wcisnij();
        record.zatrzymaj();
        wcisnij();
        System.out.println("Odsłuchaj nagrania");
        record.odtworz();
    }
}

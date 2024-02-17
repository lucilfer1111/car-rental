package src;

public class Main {
    public static void main(String[] args) {
        MashinTurees mTurees = new MashinTurees();
        Mashin mashin1 = new Mashin("1", "Toyato", "Mark2", 200000.00);
        Mashin mashin2 = new Mashin("2", "Toyato", "Camry", 180000.00);
        Mashin mashin3 = new Mashin("3", "Lexus", "570", 250000.00);
        mTurees.mashinNemeh(mashin1);
        mTurees.mashinNemeh(mashin2);
        mTurees.mashinNemeh(mashin3);

        mTurees.menu();
    }
}
package src;

public class Mashin {
    private String mashinID;
    private String brand;
    private String zagvar;
    private double tureesUneOdort;
    private Boolean bolomjtoiEseh;

    public Mashin(String mashinID, String brand, String zagvar, double tureesUneOdort) {
        this.mashinID = mashinID;
        this.brand = brand;
        this.bolomjtoiEseh = true;
        this.zagvar = zagvar;
        this.tureesUneOdort = tureesUneOdort;
    }

    public String getMashinID() {
        return mashinID;
    }

    public String getBrand() {
        return brand;
    }

    public String getZagvar() {
        return zagvar;
    }

    public double tureesUneTootsoh(int tureesHonog) {
        return tureesUneOdort * tureesHonog;
    }

    public Boolean bolomjtoiEseh() {
        return bolomjtoiEseh;
    }

    public void turees() {
        bolomjtoiEseh = false;
    }

    public void returnMashin() {
        bolomjtoiEseh = true;
    }
}

package lhdweb2.entity;


public class Lhd {
    private int lhda;
    private float lhdb;
    private String lhdc;

    public int getLhda() {
        return lhda;
    }

    public void setLhda(int lhda) {
        this.lhda = lhda;
    }

    public float getLhdb() {
        return lhdb;
    }

    public void setLhdb(float lhdb) {
        this.lhdb = lhdb;
    }

    public String getLhdc() {
        return lhdc;
    }

    public void setLhdc(String lhdc) {
        this.lhdc = lhdc;
    }

    public Lhd(int lhda, float lhdb, String lhdc) {
        this.lhda = lhda;
        this.lhdb = lhdb;
        this.lhdc = lhdc;
    }

    public Lhd() {
    }

    @Override
    public String toString() {

        return "{\"lhda\":\"" + lhda + "\",\"lhdb\":\""+lhdb+"\",\"lhdc\":\""+lhdc+"\"}";
    }
}

import java.util.ArrayList;

public class Merchant {
    private String name, picture;
    private short coin;
    private ArrayList<String> weapons, magicWeapons;

    public Merchant(String name, String picture, short coin, ArrayList<String> weapons, ArrayList<String> magicWeapons) {
        this.name = name;
        this.picture = picture;
        this.coin = coin;
        this.weapons = weapons;
        this.magicWeapons = magicWeapons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public short getCoin() {
        return coin;
    }

    public void setCoin(short coin) {
        this.coin = coin;
    }

    public ArrayList<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<String> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<String> getMagicWeapons() {
        return magicWeapons;
    }

    public void setMagicWeapons(ArrayList<String> magicWeapons) {
        this.magicWeapons = magicWeapons;
    }
}

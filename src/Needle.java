public class Needle extends Weapon{
    int healing;
    String name;
    String owner;

    public Needle(String name) {
        this.healing  = 10;
        this.name = "Needle";
        this.owner = "Healer";
    }

    public int getHealing() {
        return healing;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
}

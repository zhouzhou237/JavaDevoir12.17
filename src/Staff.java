public class Staff extends Weapon{
    int manacost;
    int damage;
    String name;
    String owner;

    public Staff(String name) {
        this.manacost = 20;
        this.damage = 10;
        this.name = "Staff";
        this.owner = "Mage";
    }

    public int getManacost() {
        return manacost;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public void add_manacost(int add_manacost) {
        manacost = manacost + add_manacost;
    }

    public void loose_manacost(int loose_manacost) {
        manacost = manacost - loose_manacost;
    }
}

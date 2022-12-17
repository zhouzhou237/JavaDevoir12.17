public class Sword extends Weapon{
    int damage;
    String name;
    String owner;

    public Sword(String name) {
        this.damage = 20;
        this.name = "Sword";
        this.owner = "Warrior";
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

    
}

public class Bow extends Weapon{
    int amount;     //箭的数量
    int damage;
    String owner;
    String name;

    public Bow(String name) {
        this.damage = 30;
        this.amount = 5;
        this.owner = "Hunter";
        this.name = "Bow";
    }


    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int addAmount) {
        amount = amount + addAmount;
    }

    public void looseAmount(int looseAmount) {
        amount = amount - looseAmount;
    }
}

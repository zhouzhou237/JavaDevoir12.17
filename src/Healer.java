public class Healer extends Hero{
    int hp;
    int attack;
    int armor;
    String classtype;
    Weapon weapon;
    String option;
    String food;
    boolean isLive;

    public Healer(String classtype) {
        this.hp = 50;
        this.attack = 10;
        this.armor = 5;
        this.classtype = "Healer";
        this.weapon = new Needle("Archangel's Staff");
        this.option = "";
        this.food = "";
        this.isLive = true;
    }

    //查看属性
    public StringBuilder showProper() {
        StringBuilder properStringBuilder = new StringBuilder();
        properStringBuilder.append(classtype + "'s hp is " + hp + "\n");
        properStringBuilder.append(classtype + "'s attack is " + attack + "\n");
        properStringBuilder.append(classtype + "'s armor is " + armor + "\n");

        return properStringBuilder;
    }

    //查看物品
    public StringBuilder showItem() {
        StringBuilder itemBuilder = new StringBuilder();
        itemBuilder.append(classtype + "'s weapon is " + weapon + "\n");
        itemBuilder.append(classtype + "'s option is "+ option + "\n");
        itemBuilder.append(classtype + "'s food is " + food +"\n");

        return itemBuilder;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getOption() {
        return option;
    }    
    
    public String getFood() {
        return food;
    }
    
    public void gainHp(int gainHp) {
        hp = hp + gainHp;
    }

    public void looseHp(int looseHp) {
        hp = hp - looseHp;
    }

    public void gainAttack(int gainAttack) {
        attack = attack + gainAttack;
    }

    public void looseAttack(int looseAttack) {
        attack = attack - looseAttack;
    }

    public void gainArmor(int gainArmor) {
        armor = armor + gainArmor;
    }

    public void looseArmor(int looseArmor) {
        armor = armor - looseArmor;
    }

    public void haveOption(String option) {
        this.option = option;
    }

    public void haveFood(String food) {
        this.food = food;
    }

    public void gainOption(String gainOption) {
        option = option + gainOption;
    }

    public void looseOption(String looseOption) {
        option = "";
    }

    public void gainFood(String gainFood) {
        food = food + gainFood;
    }

    public void looseFood(String looseFood) {
        food = "";
    }

    public void die() {
        this.hp = 0;
        isLive = false;
        System.out.println(classtype + " is dead.");
    }

//---------------------------------------------------------//
    @Override
    public void attack() {
        System.out.println("Warrior attack");
        
    }

    @Override
    public void defend() {
        System.out.println("Warrior defend");
        
    }

    @Override
    public void consumable() {
        System.out.println("Warrior use consumable");
    }
    
}

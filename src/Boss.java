public class Boss extends Enermy{
    String classType;
    String name;
    int hp;
    int attack;
    boolean isLive;

    public Boss(String name){
        this.name = name;
        this.classType = "Boss";
        this.hp = 300;
        this.attack = 40;
        isLive = true;
    }

    public void die(){
        this.hp = 0;
        isLive = false;
        System.out.println(name + " is dead.");
    }

    public void attack(){
        System.out.println(name + " attack");
    }


    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public int getAttack(){
        return attack;
    }

    public void looseHp(int looseHp){
        hp = hp - looseHp;
    }
}

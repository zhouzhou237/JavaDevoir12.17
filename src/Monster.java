public class Monster extends Enermy{
    String classType;
    String name;
    int hp ;
    int attack ;
    boolean isLive;

    //怪兽创建
    public Monster(String name){
        this.name = name;
        this.classType = "Monster";
        this.hp = 150;
        this.attack = 20;
        isLive = true;
    }

    //怪兽死亡
    public void die(){
        this.hp = 0;
        isLive = false;
        System.out.println(name + " is dead");
    }

    //怪兽攻击
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

public class Food extends Consumable{
    String classType;       //食物
    int amount;             //创建数量
    int effect;
    //创建消耗品属性
    public Food(String classType, int amount,int effect){
        this.classType = classType;
        this.amount = amount;
        this.effect = effect;
    }

    public String getClassType(){
        return classType;
    }

    public int getAmount(){
        return amount;
    }

    public void gainAmount(int gainAmount){
        amount = amount + gainAmount;
    }

    public void looseAmount(int looseAmount){
        amount = amount - looseAmount;
    }

    public void gainEffect(int gainEffect){
        effect = effect + gainEffect;
    }

    public void looseEffect(int looseEffect){
        effect = effect - looseEffect;
    }
}

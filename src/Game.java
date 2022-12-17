import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;

public class Game extends javafx.application.Application{
    
        static boolean isStart = false;     //判断是否开始
        Button buttonItem,buttonProperties;        //查看装备，查看属性
        Button buttonRound,buttonStartround;         //控制英雄行动，控制开始
        Button buttonHeroChoose;        //英雄选择
        Button buttonEnter;     //确认按钮
        
        static TextArea gameArea;       //游戏区域
    

        ArrayList<String> heroList = new ArrayList<>();        //英雄列表
        ArrayList<String> monsterList = new ArrayList<>();    //怪兽列表
        ArrayList<String> allList = new ArrayList<>();       //总共列表

        //设置英雄
        Hunter hunter = new Hunter("Hunter");
        Warrior warrior = new Warrior("Warrior");
        Mage mage = new Mage("Mage");
        Healer healer = new Healer("Healer");
    
        //设置怪兽
        List<Monster> monsters;
        Monster Monster1 = null;
        Monster Monster2 = null;
        Monster Monster3 = null;
        Monster Monster4 = null;

        //设置Boss
        Boss boss = new Boss("Medusa");

        //设置轮次胜利
        Boolean round_one_win = false;
        Boolean round_two_win = false;
        Boolean round_three_win = false;
        Boolean round_four_win = false;
        Boolean round_Boss_win = false;
        
        //设置食物和药品
        Food apple = new Food("Apple", 1, 10);
        Option red = new Option("Red", 1, 10);
    
        //设置武器
        Bow bow = new Bow("Hero Bow");
        Needle needle = new Needle("Archangel's Staff");
        Sword sword = new Sword("Excalibur");
        Staff staff = new Staff("Flame Staff");
        
        public static void main(String[] args) {
            Application.launch(args);	// 启动独立的JavaFx程序
        }

        @Override
    public void start(Stage primaryStage) throws Exception {

        //设置游戏主题区域(画面上方)
        BorderPane rootPane = new BorderPane();     //边界布局面板
        rootPane.setPadding(new Insets(10)); // 边缘内侧空白距离
		gameArea = new TextArea();
		gameArea.setEditable(false);	// 不可修改
		gameArea.setPrefSize(800, 350); // 游戏画面大小
		gameArea.setStyle("-fx-background-color:red;-fx-text-fill:blue;");
		gameArea.setWrapText(true);	// 自动换行
        Font font = new Font("Cambria", 40);
        gameArea.setFont(font);
        gameArea.appendText("\n\t Welcome to the Mini RPG Lite 3000");
        gameArea.appendText("\n\t ");
        gameArea.appendText("\n\t ");
        gameArea.appendText("\n\t Press ENTER to start game!");
        rootPane.setTop(gameArea);


        //设置左侧按钮(控制按钮)
        GridPane controlPane = new GridPane();
        controlPane.setAlignment(Pos.CENTER);
        controlPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		controlPane.setHgap(5.5);
		controlPane.setVgap(5.5);

        //设置左侧按钮的位置参数
        int helpRowNumNum = 4;
        int buttonWidth = 100;       //按钮的宽
        int buttonHeight = 80;      //按钮的高

        //ENTER 键设置
        buttonEnter = new Button("ENTER");
        buttonEnter.setPrefSize(buttonWidth, buttonHeight);
        controlPane.add(buttonEnter, helpRowNumNum+1, 1);

        //Hero_Choose 键设置
        buttonHeroChoose = new Button("HERO_CHOOSE");
        buttonHeroChoose.setPrefSize(buttonWidth, buttonHeight);
        controlPane.add(buttonHeroChoose, helpRowNumNum+2,1);


        rootPane.setLeft(controlPane);      //确认控制面板在rootPane的左边


        //设置右侧按钮（操作按钮）
        GridPane operationPane = new GridPane();
        operationPane.setAlignment(Pos.CENTER);
        operationPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		operationPane.setHgap(5.5);
		operationPane.setVgap(5.5);

        //设置右侧按钮的位置参数
        int helpRowNumNum2 = 14;
        int buttonWidth2 = 160;
        int buttonHeight2 = 50;

        //装备按钮
        buttonItem = new Button("show item");
        buttonItem.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonItem,helpRowNumNum2+0,1);

        //属性按钮
        buttonProperties = new Button("show properties");
        buttonProperties.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonProperties,helpRowNumNum2+3,1);

        //看第几轮按钮
        buttonRound = new Button("ROUND NUMBER");
        buttonRound.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonRound, helpRowNumNum2+0,3);

        //开始轮次按钮
        buttonStartround = new Button("START GAME");
        buttonStartround.setPrefSize(buttonWidth2, buttonHeight2);
        operationPane.add(buttonStartround,helpRowNumNum2+3,3);

        rootPane.setCenter(operationPane);      //确认操作面板在rootPane的右边





        //点击确认开始游戏
        buttonEnter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartGame();        //开始游戏
            }
        });


        //点击进入英雄选择界面
        buttonHeroChoose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isStart) {
                    System.out.println("Hero choose");       //检查器
                    StringBuilder herochooBuilder1 = new StringBuilder();
                    herochooBuilder1.append("\n\n How many heros do you want to choose? (1 to 4) ");
                    gameArea.appendText(herochooBuilder1+"");
                    TextInputalwaysDialog herochooseDialog = new TextInputalwaysDialog();

                    StringBuilder herochooBuilder11 = new StringBuilder();
                    herochooBuilder11.append("\n" + herochooseDialog.getText() + "\n");
                    gameArea.appendText(herochooBuilder11 + "");

                    int HCD1 = Integer.parseInt(herochooseDialog.getText());
                    if (HCD1 == 1) {
                        System.out.println("You choose 1 hero.");
                        monsterList.add("Monster1");
                        allList.add("Monster1");
                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                    }else if (HCD1 == 2) {
                        System.out.println("You choose to have 2 heros.");
                        monsterList.add("Monster1");
                        monsterList.add("Monster2");
                        allList.add("Monster1");
                        allList.add("Monster2");

                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                        //第二个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass2Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass2Builder = new StringBuilder();
                        int HClass2 = Integer.parseInt(heroclass2Dialog.getText());
                        
                        if (HClass2 == 1) {
                            heroclass2Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass2 == 2) {
                            heroclass2Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass2 == 3) {
                            heroclass2Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass2 == 4) {
                            heroclass2Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass2Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass2Builder+"");                        

                    }else if (HCD1 == 3) {
                        System.out.println("You choose to have 3 heros.");
                        monsterList.add("Monster1");
                        monsterList.add("Monster2");
                        monsterList.add("Monster3");
                        allList.add("Monster1");
                        allList.add("Monster2");
                        allList.add("Monster3");

                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                        //第二个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass2Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass2Builder = new StringBuilder();
                        int HClass2 = Integer.parseInt(heroclass2Dialog.getText());
                        
                        if (HClass2 == 1) {
                            heroclass2Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass2 == 2) {
                            heroclass2Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass2 == 3) {
                            heroclass2Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass2 == 4) {
                            heroclass2Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass2Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass2Builder+"");  

                        //第三个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass3Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass3Builder = new StringBuilder();
                        int HClass3 = Integer.parseInt(heroclass3Dialog.getText());
                        
                        if (HClass3 == 1) {
                            heroclass3Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass3 == 2) {
                            heroclass3Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass3 == 3) {
                            heroclass3Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass3 == 4) {
                            heroclass3Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass3Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass3Builder+"");                        

                    }else if (HCD1 == 4) {
                        System.out.println("You choose to have 4 heros.");
                        monsterList.add("Monster1");
                        monsterList.add("Monster2");
                        monsterList.add("Monster3");
                        monsterList.add("Monster4");
                        allList.add("Monster1");
                        allList.add("Monster2");
                        allList.add("Monster3");
                        allList.add("Monster4");

                        //第一个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass1Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass1Builder = new StringBuilder();
                        int HClass1 = Integer.parseInt(heroclass1Dialog.getText());
                        
                        if (HClass1 == 1) {
                            heroclass1Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass1 == 2) {
                            heroclass1Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass1 == 3) {
                            heroclass1Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass1 == 4) {
                            heroclass1Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass1Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass1Builder+"");

                        //第二个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass2Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass2Builder = new StringBuilder();
                        int HClass2 = Integer.parseInt(heroclass2Dialog.getText());
                        
                        if (HClass2 == 1) {
                            heroclass2Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass2 == 2) {
                            heroclass2Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass2 == 3) {
                            heroclass2Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass2 == 4) {
                            heroclass2Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass2Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass2Builder+"");  
                        
                        //第三个英雄
                        heroclass1();
                        TextInputalwaysDialog heroclass3Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass3Builder = new StringBuilder();
                        int HClass3 = Integer.parseInt(heroclass3Dialog.getText());
                        
                        if (HClass3 == 1) {
                            heroclass3Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass3 == 2) {
                            heroclass3Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass3 == 3) {
                            heroclass3Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass3 == 4) {
                            heroclass3Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass3Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass3Builder+"");

                        //第四个英雄
                        heroclass2();
                        TextInputalwaysDialog heroclass4Dialog = new TextInputalwaysDialog();
                        StringBuilder heroclass4Builder = new StringBuilder();
                        int HClass4 = Integer.parseInt(heroclass4Dialog.getText());
                        
                        if (HClass4 == 1) {
                            heroclass4Builder.append("\n You have chosen Warrior");
                            heroList.add("Warrior");
                            allList.add("Warrior");
                        }else if (HClass4 == 2) {
                            heroclass4Builder.append("\n You have chosen Hunter");
                            heroList.add("Hunter");
                            allList.add("Hunter");
                        }else if (HClass4 == 3) {
                            heroclass4Builder.append("\n You have chosen Mage");
                            heroList.add("Mage");
                            allList.add("Mage");
                        }else if (HClass4 == 4) {
                            heroclass4Builder.append("\n You have chosen Healer");
                            heroList.add("Healer");
                            allList.add("Healer");
                        }else {
                            heroclass4Builder.append("\n ERROR");
                        }
                        gameArea.appendText(heroclass4Builder+"");  

                    }else {
                        System.out.println("ERROR");
                    }
                
                }else {
                    System.out.println("ERROR");
                    primaryStage.close();
                }
                

            }
        });

        //点击进入轮次
        buttonStartround.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //创建4个怪兽
                monsters = new ArrayList<>();
                for(int i = 1; i <= 4; i++){
                    monsters.add(new Monster("Monster" + i));
                }
                Monster1 = new Monster("Monster1");
                Monster2 = new Monster("Monster2");
                Monster3 = new Monster("Monster3");
                Monster4 = new Monster("Monster4");

                //判断英雄是否穿戴护甲
                Boolean Hunter_armor = false;
                Boolean Warrior_armor = false;
                Boolean Mage_armor = false;
                Boolean Healer_armor = false;

                //创造第一回合的list
                ArrayList<String> RoundOneAllList = new ArrayList<>();
                for(int i = 0; i<allList.size();i++){
                    RoundOneAllList.add(allList.get(i));
                }
                ArrayList<String> RoundOneMonsterList = new ArrayList<>();
                for(int i = 0; i<monsterList.size();i++){
                    RoundOneMonsterList.add(monsterList.get(i));
                }
                ArrayList<String> RoundOneHeroList = new ArrayList<>();
                for(int i = 0; i<heroList.size();i++){
                    RoundOneHeroList.add(heroList.get(i));
                }





                //第一回合
                String round_one_start = "";
                gameArea.setText("PLAY NOW!");
                round_one_start += "\n";
                round_one_start += "There are 5 rounds at all, the last round you will encounter the boss. \n";
                round_one_start += "For the first four round, you will meet the monsters. \n";
                round_one_start += "The number of monsters is depends on the number of heros that you choose. \n";
                round_one_start += "For example, if you choose 2 heros, you will meet 2 monsters each round. \n";
                round_one_start += "Here is your first round. \n";
                round_one_start += "-------------------- FIRST ROUND ---------------------------- \n";

                gameArea.appendText(round_one_start);
                
                while(RoundOneAllList.size() > 0) {
                    if(RoundOneHeroList.size() != 0 && RoundOneMonsterList.size() != 0){
                        int randomIndex = (int)(Math.random()*RoundOneAllList.size());
                        String element_RoundOneAllList = RoundOneAllList.get(randomIndex);
                        Boolean checkway = RoundOneHeroList.contains(element_RoundOneAllList);
                        String round_one1 = "";
                        if (checkway == true){
                            int indexRoundOneMonster = (int)(Math.random()*RoundOneMonsterList.size());
                            String element_RoundOnemonsterList = RoundOneMonsterList.get(indexRoundOneMonster);
                            round_one1 += "\n Here is your hero's round : " + element_RoundOneAllList + "\n";
                            round_one1 += "\n You have 4 options : \n";
                            round_one1 += "\n 1) attack  2) defense  3) use weapon  4) use option 5) use food \n";
                            gameArea.appendText(round_one1);
                            TextInputalwaysDialog heroActionDialog = new TextInputalwaysDialog();
                            int HA = Integer.parseInt(heroActionDialog.getText());
                            gameArea.appendText("\n You choose the option :" + HA);
                            if (HA == 1) {
                                String round_one_HA = "";
                                round_one_HA += "\n " + element_RoundOneAllList + "attack" + element_RoundOnemonsterList + "\n";
                                if (element_RoundOneAllList == "Hunter"){
                                    System.out.println("Hunter attack");
                                    round_one_HA += "\n and deal " + hunter.getAttack() +"damage";
                                    if (element_RoundOnemonsterList == "Monster1"){
                                        round_one_HA += "\n " + element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster1.looseHp(hunter.getAttack());
                                        if (Monster1.getHp() <= 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster2.looseHp(hunter.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster3.looseHp(hunter.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + hunter.getAttack() + "hp.";
                                        Monster4.looseHp(hunter.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }else {
                                        System.out.println("round_one_HA");
                                    }
                                    gameArea.appendText(round_one_HA);

                                }else if(element_RoundOneAllList == "Warrior"){
                                    System.out.println("Warrior attack");
                                    round_one_HA += "and deal " + warrior.getAttack() +" damage ";
                                    if (element_RoundOnemonsterList =="Monster1"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster1.looseHp(warrior.getAttack());
                                        if (Monster1.getHp() < 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster2.looseHp(warrior.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster3.looseHp(warrior.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + warrior.getAttack() + "hp.";
                                        Monster4.looseHp(warrior.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }
                                    gameArea.appendText(round_one_HA);

                                }else if (element_RoundOneAllList == "Mage"){
                                    System.out.println("Mage attack");
                                    round_one_HA += "\n and deal " + mage.getAttack() +"damage";
                                    if (element_RoundOnemonsterList =="Monster1"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster1.looseHp(mage.getAttack());
                                        if (Monster1.getHp() < 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster2.looseHp(mage.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster3.looseHp(mage.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + mage.getAttack() + "hp.";
                                        Monster4.looseHp(mage.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }    
                                    gameArea.appendText(round_one_HA);

                                }else if (element_RoundOneAllList == "Healer"){
                                    System.out.println("Healer attack");
                                    round_one_HA += "\n and deal " + healer.getAttack() +"damage";
                                    if (element_RoundOnemonsterList =="Monster1"){
                                        round_one_HA +="\n " + element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster1.looseHp(healer.getAttack());
                                        if (Monster1.getHp() < 0){
                                            Monster1.die();
                                            RoundOneAllList.remove("Monster1");
                                            RoundOneMonsterList.remove("Monster1");
                                        }
                                    }else if (element_RoundOnemonsterList == "Monster2"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster2.looseHp(healer.getAttack());
                                        if (Monster2.getHp() < 0){
                                            Monster2.die();
                                            RoundOneAllList.remove("Monster2");
                                            RoundOneMonsterList.remove("Monster2");
                                        }                                    
                                    }else if (element_RoundOnemonsterList == "Monster3"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster3.looseHp(healer.getAttack());
                                        if (Monster3.getHp() < 0){
                                            Monster3.die();
                                            RoundOneAllList.remove("Monster3");
                                            RoundOneMonsterList.remove("Monster3");
                                        }                                        
                                    }else if (element_RoundOnemonsterList == "Monster4"){
                                        round_one_HA += "\n " +element_RoundOnemonsterList + " loose " + healer.getAttack() + "hp.";
                                        Monster4.looseHp(healer.getAttack());
                                        if (Monster4.getHp() < 0){
                                            Monster4.die();
                                            RoundOneAllList.remove("Monster4");
                                            RoundOneMonsterList.remove("Monster4");
                                        }                                        
                                    }
                                    gameArea.appendText(round_one_HA);  
                                                                       
                                }
                                
                            } else if (HA == 2){
                                String round_one_option2 = "";
                                round_one_option2 += "\n " +element_RoundOneAllList + " use the armor";
                                if (element_RoundOneAllList == "Hunter"){
                                    Hunter_armor = true;
                                }else if (element_RoundOneAllList == "Warrior"){
                                    Warrior_armor = true;
                                }else if (element_RoundOneAllList == "Mage"){
                                    Mage_armor = true;
                                }else if (element_RoundOneAllList == "Healer"){
                                    Healer_armor = true;
                                }
                                gameArea.appendText(round_one_option2);

                            } else if (HA == 3){
                                String round_one_option3 = "";
                                if (element_RoundOneAllList == "Hunter"){
                                    System.out.println("Hunter use the Bow");
                                    int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                    String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                    round_one_option3 += "\n Hunter use the Bow to attack one monster \n";
                                    round_one_option3 += "\n and deal" + bow.getDamage() + "to" + element_MonsterList_Hunter;
                                    bow.looseAmount(1);
                                    if (element_MonsterList_Hunter == "Monster1"){
                                        Monster1.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster1 loose " + bow.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster2"){
                                        Monster2.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster2 loose " + bow.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster3"){
                                        Monster3.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster3 loose " + bow.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster4"){
                                        Monster4.looseHp(bow.getDamage());
                                        round_one_option3 += "\n Monster4 loose " + bow.getDamage() + "hp";
                                    }
                                    gameArea.appendText(round_one_option3);

                                }else if (element_RoundOneAllList == "Warrior"){
                                    System.out.println("Warrior use the sword");
                                    int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                    String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                    round_one_option3 += "\n Warrior use the Sword to attack one monster \n";
                                    round_one_option3 += "\n and deal" + sword.getDamage() + "to" + element_MonsterList_Hunter;
                                    if (element_MonsterList_Hunter == "Monster1"){
                                        Monster1.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster1 loose " + sword.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster2"){
                                        Monster2.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster2 loose " + sword.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster3"){
                                        Monster3.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster3 loose " + sword.getDamage() + "hp";
                                    }else if (element_MonsterList_Hunter == "Monster4"){
                                        Monster4.looseHp(sword.getDamage());
                                        round_one_option3 += "\n Monster4 loose " + sword.getDamage() + "hp";
                                    } 
                                    gameArea.appendText(round_one_option3);

                                }else if (element_RoundOneAllList == "Mage"){
                                    System.out.println("Mage use the Staff");
                                    round_one_option3 += "\n Mage use the Staff to attack all the monster";
                                    for (int i = 0; i<RoundOneMonsterList.size();i++){
                                        if (RoundOneMonsterList.get(i) == "Monster1"){
                                            Monster1.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster1 loose " + staff.getDamage() + "hp";
                                        }else if (RoundOneMonsterList.get(i) == "Monster2"){
                                            Monster2.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster2 loose " + staff.getDamage() + "hp";
                                        }else if (RoundOneMonsterList.get(i) == "Monster3"){
                                            Monster3.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster3 loose " + staff.getDamage() + "hp";
                                        }else if (RoundOneMonsterList.get(i) == "Monster4"){
                                            Monster4.looseHp(staff.getDamage());
                                            round_one_option3 += "\n Monster4 loose " + staff.getDamage() + "hp";
                                        } 
                                    }
                                    gameArea.appendText(round_one_option3);

                                }else if (element_RoundOneAllList == "Healer"){
                                    System.out.println("Healer use the Needle");
                                    round_one_option3 += "\n You can choose one hero to heal";
                                    gameArea.appendText(round_one_option3);
                                    TextInputalwaysDialog ChooseHeroHeal = new TextInputalwaysDialog();
                                    String CH = "";
                                    CH = CH + ChooseHeroHeal;
                                    String healingheroString = "";
                                    if (CH == "Hunter"){
                                        healingheroString += "\n Hunter is healed";
                                        hunter.gainHp(needle.getHealing());
                                    }else if (CH == "Warrior"){
                                        healingheroString += "\n Warrior is healed";
                                        warrior.gainHp(needle.getHealing());
                                    }else if (CH == "Mage"){
                                        healingheroString += "\n Mage is healed";
                                        mage.gainHp(needle.getHealing());                                    
                                    }else if (CH == "Healer"){
                                        healingheroString += "\n Healer is healed";
                                        healer.gainHp(needle.getHealing());                                    
                                    }

                                    gameArea.appendText(healingheroString);
                                }
                            } else if (HA == 4){
                                
                                if (element_RoundOneAllList == "Hunter"){
                                    if(hunter.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Hunter use the option";
                                        gameArea.appendText(round_one_option4);
                                        hunter.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Hunter dont have option";
                                        gameArea.appendText(round_one_option4);
                                    }
                                }else if (element_RoundOneAllList == "Warrior"){
                                    if(warrior.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior use the option";
                                        gameArea.appendText(round_one_option4);
                                        warrior.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior dont have option";
                                        gameArea.appendText(round_one_option4);
                                    }                                
                                }else if (element_RoundOneAllList == "Mage"){
                                    if(mage.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Mage use the option";
                                        gameArea.appendText(round_one_option4);
                                        mage.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Mage dont have option";
                                        gameArea.appendText(round_one_option4);
                                    } 
                                }else if (element_RoundOneAllList == "Healer"){
                                    if(healer.getOption() != null){
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior use the option";
                                        gameArea.appendText(round_one_option4);
                                        healer.gainAttack(5);
                                    }else{
                                        String round_one_option4 = "";
                                        round_one_option4 += "\n Warrior dont have option";
                                        gameArea.appendText(round_one_option4);
                                    } 
                                }
                                

                            } else if(HA == 5){
                                String round_one_option5 = "";
                                if (element_RoundOneAllList == "Hunter"){
                                    if(hunter.getFood() != null){
                                        round_one_option5 += "\n Hunter use the food";
                                        hunter.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Hunter dont have food";
                                    }
                                }else if (element_RoundOneAllList == "Warrior"){
                                    if(warrior.getFood() != null){
                                        round_one_option5 += "\n Warrior use the food";
                                        warrior.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Warrior dont have food";
                                    }                                
                                }else if (element_RoundOneAllList == "Mage"){
                                    if(mage.getFood() != null){
                                        round_one_option5 += "\n Mage use the food";
                                        mage.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Mage dont have food";
                                    } 
                                }else if (element_RoundOneAllList == "Healer"){
                                    if(healer.getFood() != null){
                                        round_one_option5 += "\n Warrior use the food";
                                        healer.gainHp(15);
                                    }else{
                                        round_one_option5 += "\n Warrior dont have food";
                                    } 
                                }
                                gameArea.appendText(round_one_option5);                            
                            } else {
                                System.out.println("ERREUR2");
                            }
                        }else {
                            System.out.println("Monster attack");
                            int indexHero = (int)(Math.random()*RoundOneHeroList.size());
                            String element_RoundOneHeroList = RoundOneHeroList.get(indexHero);
                            String monsterattack1 = "";
                            monsterattack1 += "\n" + element_RoundOneAllList + " attack " + element_RoundOneHeroList;
                            gameArea.appendText(monsterattack1);
                            if(element_RoundOneHeroList == "Hunter"){
                                if(Hunter_armor == false){
                                    StringBuilder monsterattack2 = new StringBuilder();
                                    monsterattack2.append("\n and deal 20 damage to Hunter") ;
                                    hunter.looseHp(20);
                                    if (hunter.getHp() <= 0){
                                        RoundOneAllList.remove("Hunter");
                                        RoundOneHeroList.remove("Hunter");
                                    }
                                    gameArea.appendText(monsterattack2 + "");
                                }else{
                                    StringBuilder monsterattack3 = new StringBuilder();
                                    monsterattack3.append("\n and deal " + (20-hunter.getArmor()) + " damage to Hunter") ;
                                    hunter.looseHp(20-hunter.getArmor());
                                    if (hunter.getHp() <= 0){
                                        RoundOneAllList.remove("Hunter");
                                        RoundOneHeroList.remove("Hunter");
                                    }
                                    gameArea.appendText(monsterattack3 + "");
                                }
                            }else if (element_RoundOneHeroList == "Warrior") {
                                if(Warrior_armor == false){
                                    StringBuilder monsterattack4 = new StringBuilder();
                                    monsterattack4.append("\n and deal 20 damage to Warrior") ;
                                    warrior.looseHp(20);
                                    if (warrior.getHp() <= 0){
                                        RoundOneAllList.remove("Warrior");
                                        RoundOneHeroList.remove("Warrior");
                                    }
                                    gameArea.appendText(monsterattack4 + "");
                                }else{
                                    StringBuilder monsterattack5 = new StringBuilder();
                                    monsterattack5.append("\n and deal " + (20-warrior.getArmor()) + " damage to Warrior") ;
                                    warrior.looseHp(20-warrior.getArmor());
                                    if (warrior.getHp() <= 0){
                                        RoundOneAllList.remove("Warrior");
                                        RoundOneHeroList.remove("Warrior");
                                    }
                                    gameArea.appendText(monsterattack5 + "");
                                }                          
                            }else if (element_RoundOneHeroList == "Mage") {
                                if(Mage_armor == false){
                                    StringBuilder monsterattack6 = new StringBuilder();
                                    monsterattack6.append("\n and deal 20 damage to Mage") ;
                                    mage.looseHp(20);
                                    if (mage.getHp() <= 0){
                                        RoundOneAllList.remove("Mage");
                                        RoundOneHeroList.remove("Mage");
                                    }
                                    gameArea.appendText(monsterattack6 + "");
                                }else{
                                    StringBuilder monsterattack7 = new StringBuilder();
                                    monsterattack7.append("\n and deal " + (20-mage.getArmor()) + " damage to Mage") ;
                                    mage.looseHp(20-mage.getArmor());
                                    if (mage.getHp() <= 0){
                                        RoundOneAllList.remove("Mage");
                                        RoundOneHeroList.remove("Mage");
                                    }
                                    gameArea.appendText(monsterattack7 + "");
                                }                         
                            }else if (element_RoundOneHeroList == "Healer") {
                                if(Healer_armor == false){
                                    StringBuilder monsterattack8 = new StringBuilder();
                                    monsterattack8.append("\n and deal 20 damage to Healer") ;
                                    healer.looseHp(20);
                                    if (healer.getHp() <= 0){
                                        RoundOneAllList.remove("Healer");
                                        RoundOneHeroList.remove("Healer");
                                    }
                                    gameArea.appendText(monsterattack8 + "");
                                }else{
                                    StringBuilder monsterattack9 = new StringBuilder();
                                    monsterattack9.append("\n and deal " + (20-healer.getArmor()) + " damage to Healer") ;
                                    healer.looseHp(20-healer.getArmor());
                                    if (healer.getHp() <= 0){
                                        RoundOneAllList.remove("Healer");
                                        RoundOneHeroList.remove("Healer");
                                    }
                                    gameArea.appendText(monsterattack9 + "");
                                }
                            }
                        }
                    }else if (RoundOneHeroList.size() == 0){
                        String GameOver = "";
                        GameOver += "\n Game Over!";
                        gameArea.appendText(GameOver);
                        isStart = false;
                        break;
                        
                    }else if (RoundOneMonsterList.size() == 0){
                        String FirstRoundWin = "";
                        FirstRoundWin += "\n You win the first round";
                        gameArea.appendText(FirstRoundWin);
                        round_one_win = true;
                        String bonus = "";
                        bonus += "\n You can choose one bonus";
                        bonus += "\n 1)Increase the damage";
                        bonus += "\n 2)Increase the defense";
                        bonus += "\n 3)Increase the effectiveness of potions and foods";
                        bonus += "\n 4)Increase the amount of potions and foods";
                        bonus += "\n 5)Increase the number of arrows (for the Hunter), decrease the mana cost for the sorcerers or the effectiveness of their spells";
                        gameArea.appendText(bonus);
                        TextInputalwaysDialog bonus1Dialog = new TextInputalwaysDialog();
                        int bonus1 = Integer.parseInt(bonus1Dialog.getText());
                        if(bonus1 == 1){
                            for(int i = 0; i<heroList.size();i++){
                                if(heroList.get(i) == "Hunter"){
                                    hunter.gainAttack(5);
                                }
                                if(heroList.get(i) == "Warrior"){
                                    warrior.gainAttack(5);
                                }
                                if(heroList.get(i) == "Mage"){
                                    mage.gainAttack(5);
                                }
                                if(heroList.get(i) == "Healer"){
                                    healer.gainAttack(5);
                                }
                            }
                        }else if (bonus1 == 2){
                            for(int i = 0; i<heroList.size();i++){
                                if(heroList.get(i) == "Hunter"){
                                    hunter.gainArmor(5);
                                }
                                if(heroList.get(i) == "Warrior"){
                                    warrior.gainArmor(5);
                                }
                                if(heroList.get(i) == "Mage"){
                                    mage.gainArmor(5);
                                }
                                if(heroList.get(i) == "Healer"){
                                    healer.gainArmor(5);
                                }
                            }
                        }else if (bonus1 == 3){
                            apple.gainEffect(5);
                            red.gainEffect(5);
                        }else if (bonus1 == 4){
                            apple.gainAmount(1);
                            red.gainAmount(1);
                        }else if (bonus1 == 5){
                            bow.addAmount(1);
                            mage.gainMana(20);
                            staff.loose_manacost(5);
                        }
                        gameArea.appendText("\n You choose the optiom: "+bonus1);
                        break;
                    }
                }

                //创建第二回合的list
                ArrayList<String> RoundTwoMonsterList = new ArrayList<>();
                for(int i = 0; i<monsterList.size();i++){
                    RoundTwoMonsterList.add(monsterList.get(i));
                }
                ArrayList<String> RoundTwoHeroList = new ArrayList<>();
                for(int i = 0; i<RoundOneHeroList.size();i++){
                    RoundTwoHeroList.add(RoundOneHeroList.get(i));
                }
                ArrayList<String> RoundTwoAllList = new ArrayList<>();
                RoundTwoAllList.addAll(RoundTwoMonsterList);
                RoundTwoAllList.addAll(RoundTwoHeroList);
                
                //System.out.println(monsterList.size());
                //System.out.println(heroList.size());
                //System.out.println(RoundTwoMonsterList.size());

                //第二回合
                if(round_one_win == true){
                    gameArea.appendText("\n-------------------- Second ROUND ----------------------------");
                    while(RoundTwoAllList.size()>0){
                        if(RoundTwoHeroList.size() != 0 && RoundTwoMonsterList.size() != 0){
                            int round2Index = (int)(Math.random()*RoundTwoAllList.size());
                            String element_RoundTwoAllist = RoundTwoAllList.get(round2Index);
                            Boolean Round2Check = RoundTwoHeroList.contains(element_RoundTwoAllist);
                            String round_two1 = "";
                            if (Round2Check == true){
                                int indexRoundTwoMonster = (int)(Math.random()*RoundTwoMonsterList.size());
                                String element_RoundTwomonsterList = RoundTwoMonsterList.get(indexRoundTwoMonster);
                                round_two1 += "\n Here is your hero's round : " + element_RoundTwoAllist + "\n";
                                round_two1 += "\n You have 4 options : \n";
                                round_two1 += "\n 1) attack  2) defense  3) use weapon  4) use option 5) use food \n";
                                gameArea.appendText(round_two1);
                                TextInputalwaysDialog heroActionDialog = new TextInputalwaysDialog();
                                int HA = Integer.parseInt(heroActionDialog.getText());
                                gameArea.appendText("\n You choose the option :" + HA);
                                if (HA == 1) {
                                    String round_two_HA = "";
                                    round_two_HA += "\n " + element_RoundTwoAllist + "attack" + element_RoundTwomonsterList + "\n";
                                    if (element_RoundTwoAllist == "Hunter"){
                                        System.out.println("Hunter attack");
                                        round_two_HA += "\n and deal " + hunter.getAttack() +"damage";
                                        if (element_RoundTwomonsterList == "Monster1"){
                                            round_two_HA += "\n " + element_RoundTwomonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster1.looseHp(hunter.getAttack());
                                            if (Monster1.getHp() <= 0){
                                                Monster1.die();
                                                RoundTwoAllList.remove("Monster1");
                                                RoundTwoMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundTwomonsterList == "Monster2"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster2.looseHp(hunter.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundTwoAllList.remove("Monster2");
                                                RoundTwoMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundTwomonsterList == "Monster3"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster3.looseHp(hunter.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundTwoAllList.remove("Monster3");
                                                RoundTwoMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundTwomonsterList == "Monster4"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster4.looseHp(hunter.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundTwoAllList.remove("Monster4");
                                                RoundTwoMonsterList.remove("Monster4");
                                            }                                        
                                        }else {
                                            System.out.println("round_one_HA");
                                        }
                                        gameArea.appendText(round_two_HA);
    
                                    }else if(element_RoundTwoAllist == "Warrior"){
                                        System.out.println("Warrior attack");
                                        round_two_HA += "and deal " + warrior.getAttack() +" damage ";
                                        if (element_RoundTwomonsterList =="Monster1"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster1.looseHp(warrior.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundTwoAllList.remove("Monster1");
                                                RoundTwoMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundTwomonsterList == "Monster2"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster2.looseHp(warrior.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundTwoAllList.remove("Monster2");
                                                RoundTwoMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundTwomonsterList == "Monster3"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster3.looseHp(warrior.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundTwoAllList.remove("Monster3");
                                                RoundTwoMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundTwomonsterList == "Monster4"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster4.looseHp(warrior.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundTwoAllList.remove("Monster4");
                                                RoundTwoMonsterList.remove("Monster4");
                                            }                                        
                                        }
                                        gameArea.appendText(round_two_HA);
    
                                    }else if (element_RoundTwoAllist == "Mage"){
                                        System.out.println("Mage attack");
                                        round_two_HA += "\n and deal " + mage.getAttack() +"damage";
                                        if (element_RoundTwomonsterList =="Monster1"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster1.looseHp(mage.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundTwoAllList.remove("Monster1");
                                                RoundTwoMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundTwomonsterList == "Monster2"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster2.looseHp(mage.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundTwoAllList.remove("Monster2");
                                                RoundTwoMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundTwomonsterList == "Monster3"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster3.looseHp(mage.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundTwoAllList.remove("Monster3");
                                                RoundTwoMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundTwomonsterList == "Monster4"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster4.looseHp(mage.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundTwoAllList.remove("Monster4");
                                                RoundTwoMonsterList.remove("Monster4");
                                            }                                        
                                        }    
                                        gameArea.appendText(round_two_HA);
    
                                    }else if (element_RoundTwoAllist == "Healer"){
                                        System.out.println("Healer attack");
                                        round_two_HA += "\n and deal " + healer.getAttack() +"damage";
                                        if (element_RoundTwomonsterList =="Monster1"){
                                            round_two_HA +="\n " + element_RoundTwomonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster1.looseHp(healer.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundTwoAllList.remove("Monster1");
                                                RoundTwoMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundTwomonsterList == "Monster2"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster2.looseHp(healer.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundTwoAllList.remove("Monster2");
                                                RoundTwoMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundTwomonsterList == "Monster3"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster3.looseHp(healer.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundTwoAllList.remove("Monster3");
                                                RoundTwoMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundTwomonsterList == "Monster4"){
                                            round_two_HA += "\n " +element_RoundTwomonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster4.looseHp(healer.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundTwoAllList.remove("Monster4");
                                                RoundTwoMonsterList.remove("Monster4");
                                            }                                        
                                        }
                                        gameArea.appendText(round_two_HA);  
                                                                           
                                    }
                                    
                                } else if (HA == 2){
                                    String round_two_option2 = "";
                                    round_two_option2 += "\n " +element_RoundTwoAllist + " use the armor";
                                    if (element_RoundTwoAllist == "Hunter"){
                                        Hunter_armor = true;
                                    }else if (element_RoundTwoAllist == "Warrior"){
                                        Warrior_armor = true;
                                    }else if (element_RoundTwoAllist == "Mage"){
                                        Mage_armor = true;
                                    }else if (element_RoundTwoAllist == "Healer"){
                                        Healer_armor = true;
                                    }
                                    gameArea.appendText(round_two_option2);
    
                                } else if (HA == 3){
                                    String round_two_option3 = "";
                                    if (element_RoundTwoAllist == "Hunter"){
                                        System.out.println("Hunter use the Bow");
                                        int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                        String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                        round_two_option3 += "\n Hunter use the Bow to attack one monster \n";
                                        round_two_option3 += "\n and deal" + bow.getDamage() + "to" + element_MonsterList_Hunter;
                                        bow.looseAmount(1);
                                        if (element_MonsterList_Hunter == "Monster1"){
                                            Monster1.looseHp(bow.getDamage());
                                            round_two_option3 += "\n Monster1 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster2"){
                                            Monster2.looseHp(bow.getDamage());
                                            round_two_option3 += "\n Monster2 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster3"){
                                            Monster3.looseHp(bow.getDamage());
                                            round_two_option3 += "\n Monster3 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster4"){
                                            Monster4.looseHp(bow.getDamage());
                                            round_two_option3 += "\n Monster4 loose " + bow.getDamage() + "hp";
                                        }
                                        gameArea.appendText(round_two_option3);
    
                                    }else if (element_RoundTwoAllist == "Warrior"){
                                        System.out.println("Warrior use the sword");
                                        int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                        String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                        round_two_option3 += "\n Warrior use the Sword to attack one monster \n";
                                        round_two_option3 += "\n and deal" + sword.getDamage() + "to" + element_MonsterList_Hunter;
                                        if (element_MonsterList_Hunter == "Monster1"){
                                            Monster1.looseHp(sword.getDamage());
                                            round_two_option3 += "\n Monster1 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster2"){
                                            Monster2.looseHp(sword.getDamage());
                                            round_two_option3 += "\n Monster2 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster3"){
                                            Monster3.looseHp(sword.getDamage());
                                            round_two_option3 += "\n Monster3 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster4"){
                                            Monster4.looseHp(sword.getDamage());
                                            round_two_option3 += "\n Monster4 loose " + sword.getDamage() + "hp";
                                        } 
                                        gameArea.appendText(round_two_option3);
    
                                    }else if (element_RoundTwoAllist == "Mage"){
                                        System.out.println("Mage use the Staff");
                                        round_two_option3 += "\n Mage use the Staff to attack all the monster";
                                        for (int i = 0; i<RoundOneMonsterList.size();i++){
                                            if (RoundOneMonsterList.get(i) == "Monster1"){
                                                Monster1.looseHp(staff.getDamage());
                                                round_two_option3 += "\n Monster1 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster2"){
                                                Monster2.looseHp(staff.getDamage());
                                                round_two_option3 += "\n Monster2 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster3"){
                                                Monster3.looseHp(staff.getDamage());
                                                round_two_option3 += "\n Monster3 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster4"){
                                                Monster4.looseHp(staff.getDamage());
                                                round_two_option3 += "\n Monster4 loose " + staff.getDamage() + "hp";
                                            } 
                                        }
                                        gameArea.appendText(round_two_option3);
    
                                    }else if (element_RoundTwoAllist == "Healer"){
                                        System.out.println("Healer use the Needle");
                                        round_two_option3 += "\n You can choose one hero to heal";
                                        gameArea.appendText(round_two_option3);
                                        TextInputalwaysDialog ChooseHeroHeal = new TextInputalwaysDialog();
                                        String CH = "";
                                        CH = CH + ChooseHeroHeal;
                                        String healingheroString = "";
                                        if (CH == "Hunter"){
                                            healingheroString += "\n Hunter is healed";
                                            hunter.gainHp(needle.getHealing());
                                        }else if (CH == "Warrior"){
                                            healingheroString += "\n Warrior is healed";
                                            warrior.gainHp(needle.getHealing());
                                        }else if (CH == "Mage"){
                                            healingheroString += "\n Mage is healed";
                                            mage.gainHp(needle.getHealing());                                    
                                        }else if (CH == "Healer"){
                                            healingheroString += "\n Healer is healed";
                                            healer.gainHp(needle.getHealing());                                    
                                        }
    
                                        gameArea.appendText(healingheroString);
                                    }
                                } else if (HA == 4){
                                    
                                    if (element_RoundTwoAllist == "Hunter"){
                                        if(hunter.getOption() != null){
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Hunter use the option";
                                            gameArea.appendText(round_two_option4);
                                            hunter.gainAttack(5);
                                        }else{
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Hunter dont have option";
                                            gameArea.appendText(round_two_option4);
                                        }
                                    }else if (element_RoundTwoAllist == "Warrior"){
                                        if(warrior.getOption() != null){
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_two_option4);
                                            warrior.gainAttack(5);
                                        }else{
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_two_option4);
                                        }                                
                                    }else if (element_RoundTwoAllist == "Mage"){
                                        if(mage.getOption() != null){
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Mage use the option";
                                            gameArea.appendText(round_two_option4);
                                            mage.gainAttack(5);
                                        }else{
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Mage dont have option";
                                            gameArea.appendText(round_two_option4);
                                        } 
                                    }else if (element_RoundTwoAllist == "Healer"){
                                        if(healer.getOption() != null){
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_two_option4);
                                            healer.gainAttack(5);
                                        }else{
                                            String round_two_option4 = "";
                                            round_two_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_two_option4);
                                        } 
                                    }
                                    
    
                                } else if(HA == 5){
                                    String round_two_option5 = "";
                                    if (element_RoundTwoAllist == "Hunter"){
                                        if(hunter.getFood() != null){
                                            round_two_option5 += "\n Hunter use the food";
                                            hunter.gainHp(15);
                                        }else{
                                            round_two_option5 += "\n Hunter dont have food";
                                        }
                                    }else if (element_RoundTwoAllist == "Warrior"){
                                        if(warrior.getFood() != null){
                                            round_two_option5 += "\n Warrior use the food";
                                            warrior.gainHp(15);
                                        }else{
                                            round_two_option5 += "\n Warrior dont have food";
                                        }                                
                                    }else if (element_RoundTwoAllist == "Mage"){
                                        if(mage.getFood() != null){
                                            round_two_option5 += "\n Mage use the food";
                                            mage.gainHp(15);
                                        }else{
                                            round_two_option5 += "\n Mage dont have food";
                                        } 
                                    }else if (element_RoundTwoAllist == "Healer"){
                                        if(healer.getFood() != null){
                                            round_two_option5 += "\n Warrior use the food";
                                            healer.gainHp(15);
                                        }else{
                                            round_two_option5 += "\n Warrior dont have food";
                                        } 
                                    }
                                    gameArea.appendText(round_two_option5);                            
                                } else {
                                    System.out.println("ERREUR2");
                                }
                            }else {
                                System.out.println("Monster attack");
                                int indexHero = (int)(Math.random()*RoundTwoHeroList.size());
                                String element_RoundTwoHeroList = RoundTwoHeroList.get(indexHero);
                                String monsterattack1 = "";
                                monsterattack1 += "\n" + element_RoundTwoAllist + " attack " + element_RoundTwoHeroList;
                                gameArea.appendText(monsterattack1);
                                if(element_RoundTwoHeroList == "Hunter"){
                                    if(Hunter_armor == false){
                                        StringBuilder monsterattack2 = new StringBuilder();
                                        monsterattack2.append("\n and deal 20 damage to Hunter") ;
                                        hunter.looseHp(20);
                                        if (hunter.getHp() <= 0){
                                            RoundTwoAllList.remove("Hunter");
                                            RoundTwoHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack2 + "");
                                    }else{
                                        StringBuilder monsterattack3 = new StringBuilder();
                                        monsterattack3.append("\n and deal " + (20-hunter.getArmor()) + " damage to Hunter") ;
                                        hunter.looseHp(20-hunter.getArmor());
                                        if (hunter.getHp() <= 0){
                                            RoundTwoAllList.remove("Hunter");
                                            RoundTwoHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack3 + "");
                                    }
                                }else if (element_RoundTwoHeroList == "Warrior") {
                                    if(Warrior_armor == false){
                                        StringBuilder monsterattack4 = new StringBuilder();
                                        monsterattack4.append("\n and deal 20 damage to Warrior") ;
                                        warrior.looseHp(20);
                                        if (warrior.getHp() <= 0){
                                            RoundTwoAllList.remove("Warrior");
                                            RoundTwoHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack4 + "");
                                    }else{
                                        StringBuilder monsterattack5 = new StringBuilder();
                                        monsterattack5.append("\n and deal " + (20-warrior.getArmor()) + " damage to Warrior") ;
                                        warrior.looseHp(20-warrior.getArmor());
                                        if (warrior.getHp() <= 0){
                                            RoundTwoAllList.remove("Warrior");
                                            RoundTwoHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack5 + "");
                                    }                          
                                }else if (element_RoundTwoHeroList == "Mage") {
                                    if(Mage_armor == false){
                                        StringBuilder monsterattack6 = new StringBuilder();
                                        monsterattack6.append("\n and deal 20 damage to Mage") ;
                                        mage.looseHp(20);
                                        if (mage.getHp() <= 0){
                                            RoundTwoAllList.remove("Mage");
                                            RoundTwoHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack6 + "");
                                    }else{
                                        StringBuilder monsterattack7 = new StringBuilder();
                                        monsterattack7.append("\n and deal " + (20-mage.getArmor()) + " damage to Mage") ;
                                        mage.looseHp(20-mage.getArmor());
                                        if (mage.getHp() <= 0){
                                            RoundTwoAllList.remove("Mage");
                                            RoundTwoHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack7 + "");
                                    }                         
                                }else if (element_RoundTwoHeroList == "Healer") {
                                    if(Healer_armor == false){
                                        StringBuilder monsterattack8 = new StringBuilder();
                                        monsterattack8.append("\n and deal 20 damage to Healer") ;
                                        healer.looseHp(20);
                                        if (healer.getHp() <= 0){
                                            RoundTwoAllList.remove("Healer");
                                            RoundTwoHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack8 + "");
                                    }else{
                                        StringBuilder monsterattack9 = new StringBuilder();
                                        monsterattack9.append("\n and deal " + (20-healer.getArmor()) + " damage to Healer") ;
                                        healer.looseHp(20-healer.getArmor());
                                        if (healer.getHp() <= 0){
                                            RoundTwoAllList.remove("Healer");
                                            RoundTwoHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack9 + "");
                                    }
                                }
                            }                    
                        }else if (RoundTwoHeroList.size() == 0){
                                String GameOver = "";
                                GameOver += "\n Game Over!";
                                gameArea.appendText(GameOver);
                                isStart = false;
                                break;
                                
                        }else if (RoundTwoMonsterList.size() == 0){
                            String SecondRoundWin = "";
                            SecondRoundWin += "\n You win the Second round";
                            gameArea.appendText(SecondRoundWin);
                            round_two_win = true;
                            String bonus = "";
                            bonus += "\n You can choose one bonus";
                            bonus += "\n 1)Increase the damage";
                            bonus += "\n 2)Increase the defense";
                            bonus += "\n 3)Increase the effectiveness of potions and foods";
                            bonus += "\n 4)Increase the amount of potions and foods";
                            bonus += "\n 5)Increase the number of arrows (for the Hunter), decrease the mana cost for the sorcerers or the effectiveness of their spells";
                            gameArea.appendText(bonus);
                            TextInputalwaysDialog bonus2Dialog = new TextInputalwaysDialog();
                            int bonus2 = Integer.parseInt(bonus2Dialog.getText());
                            if(bonus2 == 1){
                                for(int i = 0; i<heroList.size();i++){
                                    if(heroList.get(i) == "Hunter"){
                                        hunter.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Warrior"){
                                        warrior.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Mage"){
                                        mage.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Healer"){
                                        healer.gainAttack(5);
                                    }
                                }
                            }else if (bonus2 == 2){
                                for(int i = 0; i<heroList.size();i++){
                                    if(heroList.get(i) == "Hunter"){
                                        hunter.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Warrior"){
                                        warrior.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Mage"){
                                        mage.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Healer"){
                                        healer.gainArmor(5);
                                    }
                                }
                            }else if (bonus2 == 3){
                                apple.gainEffect(5);
                                red.gainEffect(5);
                            }else if (bonus2 == 4){
                                apple.gainAmount(1);
                                red.gainAmount(1);
                            }else if (bonus2 == 5){
                                bow.addAmount(1);
                                mage.gainMana(20);
                                staff.loose_manacost(5);
                            }
                            gameArea.appendText("\n You choose the optiom: "+bonus2);

                            break;
                        }
                    }
                }

                //创建第三回合的list
                ArrayList<String> RoundThreeMonsterList = new ArrayList<>();
                for(int i = 0; i<monsterList.size();i++){
                    RoundThreeMonsterList.add(monsterList.get(i));
                }
                ArrayList<String> RoundThreeHeroList = new ArrayList<>();
                for(int i = 0; i<RoundTwoHeroList.size();i++){
                    RoundThreeHeroList.add(RoundTwoHeroList.get(i));
                }
                ArrayList<String> RoundThreeAllList = new ArrayList<>();
                RoundThreeAllList.addAll(RoundThreeMonsterList);
                RoundThreeAllList.addAll(RoundThreeHeroList);

                //第三回合
                if(round_two_win == true){
                    gameArea.appendText("\n-------------------- Third ROUND ----------------------------");
                    while(RoundThreeAllList.size()>0){
                        if(RoundThreeHeroList.size() != 0 &&RoundThreeAllList.size() != 0){
                            int round3Index = (int)(Math.random()*RoundThreeMonsterList.size());
                            String element_RoundThreeAllist = RoundThreeAllList.get(round3Index);
                            Boolean Round3Check = RoundThreeHeroList.contains(element_RoundThreeAllist);
                            String round_three1 = "";
                            if (Round3Check == true){
                                int indexRoundThreeMonster = (int)(Math.random()*RoundThreeMonsterList.size());
                                String element_RoundThreemonsterList = RoundThreeMonsterList.get(indexRoundThreeMonster);
                                round_three1 += "\n Here is your hero's round : " + element_RoundThreeAllist + "\n";
                                round_three1 += "\n You have 4 options : \n";
                                round_three1 += "\n 1) attack  2) defense  3) use weapon  4) use option 5) use food \n";
                                gameArea.appendText(round_three1);
                                TextInputalwaysDialog heroActionDialog = new TextInputalwaysDialog();
                                int HA = Integer.parseInt(heroActionDialog.getText());
                                gameArea.appendText("\n You choose the option :" + HA);
                                if (HA == 1) {
                                    String round_three_HA = "";
                                    round_three_HA += "\n " + element_RoundThreeAllist + "attack" + element_RoundThreemonsterList + "\n";
                                    if (element_RoundThreeAllist == "Hunter"){
                                        System.out.println("Hunter attack");
                                        round_three_HA += "\n and deal " + hunter.getAttack() +"damage";
                                        if (element_RoundThreemonsterList == "Monster1"){
                                            round_three_HA += "\n " + element_RoundThreemonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster1.looseHp(hunter.getAttack());
                                            if (Monster1.getHp() <= 0){
                                                Monster1.die();
                                                RoundThreeAllList.remove("Monster1");
                                                RoundThreeMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundThreemonsterList == "Monster2"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster2.looseHp(hunter.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundThreeAllList.remove("Monster2");
                                                RoundThreeMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundThreemonsterList == "Monster3"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster3.looseHp(hunter.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundThreeAllList.remove("Monster3");
                                                RoundThreeMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundThreemonsterList == "Monster4"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster4.looseHp(hunter.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundThreeAllList.remove("Monster4");
                                                RoundThreeMonsterList.remove("Monster4");
                                            }                                        
                                        }else {
                                            System.out.println("round_one_HA");
                                        }
                                        gameArea.appendText(round_three_HA);
    
                                    }else if(element_RoundThreeAllist == "Warrior"){
                                        System.out.println("Warrior attack");
                                        round_three_HA += "and deal " + warrior.getAttack() +" damage ";
                                        if (element_RoundThreemonsterList =="Monster1"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster1.looseHp(warrior.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundThreeAllList.remove("Monster1");
                                                RoundThreeMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundThreemonsterList == "Monster2"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster2.looseHp(warrior.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundThreeAllList.remove("Monster2");
                                                RoundThreeMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundThreemonsterList == "Monster3"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster3.looseHp(warrior.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundThreeAllList.remove("Monster3");
                                                RoundThreeMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundThreemonsterList == "Monster4"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster4.looseHp(warrior.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundThreeAllList.remove("Monster4");
                                                RoundThreeMonsterList.remove("Monster4");
                                            }                                        
                                        }
                                        gameArea.appendText(round_three_HA);
    
                                    }else if (element_RoundThreeAllist == "Mage"){
                                        System.out.println("Mage attack");
                                        round_three_HA += "\n and deal " + mage.getAttack() +"damage";
                                        if (element_RoundThreemonsterList =="Monster1"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster1.looseHp(mage.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundThreeAllList.remove("Monster1");
                                                RoundThreeMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundThreemonsterList == "Monster2"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster2.looseHp(mage.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundThreeAllList.remove("Monster2");
                                                RoundThreeMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundThreemonsterList == "Monster3"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster3.looseHp(mage.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundThreeAllList.remove("Monster3");
                                                RoundThreeMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundThreemonsterList == "Monster4"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster4.looseHp(mage.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundThreeAllList.remove("Monster4");
                                                RoundThreeMonsterList.remove("Monster4");
                                            }                                        
                                        }    
                                        gameArea.appendText(round_three_HA);
    
                                    }else if (element_RoundThreeAllist == "Healer"){
                                        System.out.println("Healer attack");
                                        round_three_HA += "\n and deal " + healer.getAttack() +"damage";
                                        if (element_RoundThreemonsterList =="Monster1"){
                                            round_three_HA +="\n " + element_RoundThreemonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster1.looseHp(healer.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundThreeAllList.remove("Monster1");
                                                RoundThreeMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundThreemonsterList == "Monster2"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster2.looseHp(healer.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundThreeAllList.remove("Monster2");
                                                RoundThreeMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundThreemonsterList == "Monster3"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster3.looseHp(healer.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundThreeAllList.remove("Monster3");
                                                RoundThreeMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundThreemonsterList == "Monster4"){
                                            round_three_HA += "\n " +element_RoundThreemonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster4.looseHp(healer.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundThreeAllList.remove("Monster4");
                                                RoundThreeMonsterList.remove("Monster4");
                                            }                                        
                                        }
                                        gameArea.appendText(round_three_HA);  
                                                                           
                                    }
                                    
                                } else if (HA == 2){
                                    String round_three_option2 = "";
                                    round_three_option2 += "\n " +element_RoundThreeAllist + " use the armor";
                                    if (element_RoundThreeAllist == "Hunter"){
                                        Hunter_armor = true;
                                    }else if (element_RoundThreeAllist == "Warrior"){
                                        Warrior_armor = true;
                                    }else if (element_RoundThreeAllist == "Mage"){
                                        Mage_armor = true;
                                    }else if (element_RoundThreeAllist == "Healer"){
                                        Healer_armor = true;
                                    }
                                    gameArea.appendText(round_three_option2);
    
                                } else if (HA == 3){
                                    String round_three_option3 = "";
                                    if (element_RoundThreeAllist == "Hunter"){
                                        System.out.println("Hunter use the Bow");
                                        int indexMonster_Hunter = (int)(Math.random()*RoundThreeMonsterList.size());
                                        String element_MonsterList_Hunter = RoundThreeMonsterList.get(indexMonster_Hunter);
                                        round_three_option3 += "\n Hunter use the Bow to attack one monster \n";
                                        round_three_option3 += "\n and deal" + bow.getDamage() + "to" + element_MonsterList_Hunter;
                                        bow.looseAmount(1);
                                        if (element_MonsterList_Hunter == "Monster1"){
                                            Monster1.looseHp(bow.getDamage());
                                            round_three_option3 += "\n Monster1 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster2"){
                                            Monster2.looseHp(bow.getDamage());
                                            round_three_option3 += "\n Monster2 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster3"){
                                            Monster3.looseHp(bow.getDamage());
                                            round_three_option3 += "\n Monster3 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster4"){
                                            Monster4.looseHp(bow.getDamage());
                                            round_three_option3 += "\n Monster4 loose " + bow.getDamage() + "hp";
                                        }
                                        gameArea.appendText(round_three_option3);
    
                                    }else if (element_RoundThreeAllist == "Warrior"){
                                        System.out.println("Warrior use the sword");
                                        int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                        String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                        round_three_option3 += "\n Warrior use the Sword to attack one monster \n";
                                        round_three_option3 += "\n and deal" + sword.getDamage() + "to" + element_MonsterList_Hunter;
                                        if (element_MonsterList_Hunter == "Monster1"){
                                            Monster1.looseHp(sword.getDamage());
                                            round_three_option3 += "\n Monster1 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster2"){
                                            Monster2.looseHp(sword.getDamage());
                                            round_three_option3 += "\n Monster2 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster3"){
                                            Monster3.looseHp(sword.getDamage());
                                            round_three_option3 += "\n Monster3 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster4"){
                                            Monster4.looseHp(sword.getDamage());
                                            round_three_option3 += "\n Monster4 loose " + sword.getDamage() + "hp";
                                        } 
                                        gameArea.appendText(round_three_option3);
    
                                    }else if (element_RoundThreeAllist == "Mage"){
                                        System.out.println("Mage use the Staff");
                                        round_three_option3 += "\n Mage use the Staff to attack all the monster";
                                        for (int i = 0; i<RoundOneMonsterList.size();i++){
                                            if (RoundOneMonsterList.get(i) == "Monster1"){
                                                Monster1.looseHp(staff.getDamage());
                                                round_three_option3 += "\n Monster1 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster2"){
                                                Monster2.looseHp(staff.getDamage());
                                                round_three_option3 += "\n Monster2 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster3"){
                                                Monster3.looseHp(staff.getDamage());
                                                round_three_option3 += "\n Monster3 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster4"){
                                                Monster4.looseHp(staff.getDamage());
                                                round_three_option3 += "\n Monster4 loose " + staff.getDamage() + "hp";
                                            } 
                                        }
                                        gameArea.appendText(round_three_option3);
    
                                    }else if (element_RoundThreeAllist == "Healer"){
                                        System.out.println("Healer use the Needle");
                                        round_three_option3 += "\n You can choose one hero to heal";
                                        gameArea.appendText(round_three_option3);
                                        TextInputalwaysDialog ChooseHeroHeal = new TextInputalwaysDialog();
                                        String CH = "";
                                        CH = CH + ChooseHeroHeal;
                                        String healingheroString = "";
                                        if (CH == "Hunter"){
                                            healingheroString += "\n Hunter is healed";
                                            hunter.gainHp(needle.getHealing());
                                        }else if (CH == "Warrior"){
                                            healingheroString += "\n Warrior is healed";
                                            warrior.gainHp(needle.getHealing());
                                        }else if (CH == "Mage"){
                                            healingheroString += "\n Mage is healed";
                                            mage.gainHp(needle.getHealing());                                    
                                        }else if (CH == "Healer"){
                                            healingheroString += "\n Healer is healed";
                                            healer.gainHp(needle.getHealing());                                    
                                        }
    
                                        gameArea.appendText(healingheroString);
                                    }
                                } else if (HA == 4){
                                    
                                    if (element_RoundThreeAllist == "Hunter"){
                                        if(hunter.getOption() != null){
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Hunter use the option";
                                            gameArea.appendText(round_three_option4);
                                            hunter.gainAttack(5);
                                        }else{
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Hunter dont have option";
                                            gameArea.appendText(round_three_option4);
                                        }
                                    }else if (element_RoundThreeAllist == "Warrior"){
                                        if(warrior.getOption() != null){
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_three_option4);
                                            warrior.gainAttack(5);
                                        }else{
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_three_option4);
                                        }                                
                                    }else if (element_RoundThreeAllist == "Mage"){
                                        if(mage.getOption() != null){
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Mage use the option";
                                            gameArea.appendText(round_three_option4);
                                            mage.gainAttack(5);
                                        }else{
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Mage dont have option";
                                            gameArea.appendText(round_three_option4);
                                        } 
                                    }else if (element_RoundThreeAllist == "Healer"){
                                        if(healer.getOption() != null){
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_three_option4);
                                            healer.gainAttack(5);
                                        }else{
                                            String round_three_option4 = "";
                                            round_three_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_three_option4);
                                        } 
                                    }
                                    
    
                                } else if(HA == 5){
                                    String round_three_option5 = "";
                                    if (element_RoundThreeAllist == "Hunter"){
                                        if(hunter.getFood() != null){
                                            round_three_option5 += "\n Hunter use the food";
                                            hunter.gainHp(15);
                                        }else{
                                            round_three_option5 += "\n Hunter dont have food";
                                        }
                                    }else if (element_RoundThreeAllist == "Warrior"){
                                        if(warrior.getFood() != null){
                                            round_three_option5 += "\n Warrior use the food";
                                            warrior.gainHp(15);
                                        }else{
                                            round_three_option5 += "\n Warrior dont have food";
                                        }                                
                                    }else if (element_RoundThreeAllist == "Mage"){
                                        if(mage.getFood() != null){
                                            round_three_option5 += "\n Mage use the food";
                                            mage.gainHp(15);
                                        }else{
                                            round_three_option5 += "\n Mage dont have food";
                                        } 
                                    }else if (element_RoundThreeAllist == "Healer"){
                                        if(healer.getFood() != null){
                                            round_three_option5 += "\n Warrior use the food";
                                            healer.gainHp(15);
                                        }else{
                                            round_three_option5 += "\n Warrior dont have food";
                                        } 
                                    }
                                    gameArea.appendText(round_three_option5);                            
                                } else {
                                    System.out.println("ERREUR2");
                                }
                            }else {
                                System.out.println("Monster attack");
                                int indexHero = (int)(Math.random()*RoundThreeHeroList.size());
                                String element_RoundThreeHeroList = RoundThreeHeroList.get(indexHero);
                                String monsterattack1 = "";
                                monsterattack1 += "\n" + element_RoundThreeAllist + " attack " + element_RoundThreeHeroList;
                                gameArea.appendText(monsterattack1);
                                if(element_RoundThreeHeroList == "Hunter"){
                                    if(Hunter_armor == false){
                                        StringBuilder monsterattack2 = new StringBuilder();
                                        monsterattack2.append("\n and deal 20 damage to Hunter") ;
                                        hunter.looseHp(20);
                                        if (hunter.getHp() <= 0){
                                            RoundThreeAllList.remove("Hunter");
                                            RoundThreeHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack2 + "");
                                    }else{
                                        StringBuilder monsterattack3 = new StringBuilder();
                                        monsterattack3.append("\n and deal " + (20-hunter.getArmor()) + " damage to Hunter") ;
                                        hunter.looseHp(20-hunter.getArmor());
                                        if (hunter.getHp() <= 0){
                                            RoundThreeAllList.remove("Hunter");
                                            RoundThreeHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack3 + "");
                                    }
                                }else if (element_RoundThreeHeroList == "Warrior") {
                                    if(Warrior_armor == false){
                                        StringBuilder monsterattack4 = new StringBuilder();
                                        monsterattack4.append("\n and deal 20 damage to Warrior") ;
                                        warrior.looseHp(20);
                                        if (warrior.getHp() <= 0){
                                            RoundThreeAllList.remove("Warrior");
                                            RoundThreeHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack4 + "");
                                    }else{
                                        StringBuilder monsterattack5 = new StringBuilder();
                                        monsterattack5.append("\n and deal " + (20-warrior.getArmor()) + " damage to Warrior") ;
                                        warrior.looseHp(20-warrior.getArmor());
                                        if (warrior.getHp() <= 0){
                                            RoundThreeAllList.remove("Warrior");
                                            RoundThreeHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack5 + "");
                                    }                          
                                }else if (element_RoundThreeHeroList == "Mage") {
                                    if(Mage_armor == false){
                                        StringBuilder monsterattack6 = new StringBuilder();
                                        monsterattack6.append("\n and deal 20 damage to Mage") ;
                                        mage.looseHp(20);
                                        if (mage.getHp() <= 0){
                                            RoundThreeAllList.remove("Mage");
                                            RoundThreeHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack6 + "");
                                    }else{
                                        StringBuilder monsterattack7 = new StringBuilder();
                                        monsterattack7.append("\n and deal " + (20-mage.getArmor()) + " damage to Mage") ;
                                        mage.looseHp(20-mage.getArmor());
                                        if (mage.getHp() <= 0){
                                            RoundThreeAllList.remove("Mage");
                                            RoundThreeHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack7 + "");
                                    }                         
                                }else if (element_RoundThreeHeroList == "Healer") {
                                    if(Healer_armor == false){
                                        StringBuilder monsterattack8 = new StringBuilder();
                                        monsterattack8.append("\n and deal 20 damage to Healer") ;
                                        healer.looseHp(20);
                                        if (healer.getHp() <= 0){
                                            RoundThreeAllList.remove("Healer");
                                            RoundThreeHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack8 + "");
                                    }else{
                                        StringBuilder monsterattack9 = new StringBuilder();
                                        monsterattack9.append("\n and deal " + (20-healer.getArmor()) + " damage to Healer") ;
                                        healer.looseHp(20-healer.getArmor());
                                        if (healer.getHp() <= 0){
                                            RoundThreeAllList.remove("Healer");
                                            RoundThreeHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack9 + "");
                                    }
                                }
                            }                    
                        }else if (RoundThreeHeroList.size() == 0){
                                String GameOver = "";
                                GameOver += "\n Game Over!";
                                gameArea.appendText(GameOver);
                                isStart = false;
                                break;
                                
                        }else if (RoundThreeMonsterList.size() == 0){
                            String ThirdRoundWin = "";
                            ThirdRoundWin += "\n You win the Third round";
                            gameArea.appendText(ThirdRoundWin);
                            round_three_win = true;
                            String bonus = "";
                            bonus += "\n You can choose one bonus";
                            bonus += "\n 1)Increase the damage";
                            bonus += "\n 2)Increase the defense";
                            bonus += "\n 3)Increase the effectiveness of potions and foods";
                            bonus += "\n 4)Increase the amount of potions and foods";
                            bonus += "\n 5)Increase the number of arrows (for the Hunter), decrease the mana cost for the sorcerers or the effectiveness of their spells";
                            gameArea.appendText(bonus);
                            TextInputalwaysDialog bonus3Dialog = new TextInputalwaysDialog();
                            int bonus3 = Integer.parseInt(bonus3Dialog.getText());
                            if(bonus3 == 1){
                                for(int i = 0; i<heroList.size();i++){
                                    if(heroList.get(i) == "Hunter"){
                                        hunter.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Warrior"){
                                        warrior.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Mage"){
                                        mage.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Healer"){
                                        healer.gainAttack(5);
                                    }
                                }
                            }else if (bonus3 == 2){
                                for(int i = 0; i<heroList.size();i++){
                                    if(heroList.get(i) == "Hunter"){
                                        hunter.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Warrior"){
                                        warrior.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Mage"){
                                        mage.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Healer"){
                                        healer.gainArmor(5);
                                    }
                                }
                            }else if (bonus3 == 3){
                                apple.gainEffect(5);
                                red.gainEffect(5);
                            }else if (bonus3 == 4){
                                apple.gainAmount(1);
                                red.gainAmount(1);
                            }else if (bonus3 == 5){
                                bow.addAmount(1);
                                mage.gainMana(20);
                                staff.loose_manacost(5);
                            }
                            gameArea.appendText("\n You choose the optiom: "+bonus3);
                            break;
                        }
                    }
                }
                    
                //创建第四回合的list
                ArrayList<String> RoundFourMonsterList = new ArrayList<>();
                for(int i = 0; i<monsterList.size();i++){
                    RoundFourMonsterList.add(monsterList.get(i));
                }
                ArrayList<String> RoundFourHeroList = new ArrayList<>();
                for(int i = 0; i<RoundThreeHeroList.size();i++){
                    RoundFourHeroList.add(RoundThreeHeroList.get(i));
                }                
                ArrayList<String> RoundFourAllList = new ArrayList<>();
                RoundFourAllList.addAll(RoundFourMonsterList);
                RoundFourAllList.addAll(RoundFourHeroList);

                //第四回合
                if(round_three_win == true){
                    gameArea.appendText("\n-------------------- Fourth ROUND ----------------------------");
                    while(RoundFourAllList.size()>0){
                        if(RoundFourHeroList.size() != 0 &&RoundFourMonsterList.size() != 0){
                            int round4Index = (int)(Math.random()*RoundFourAllList.size());
                            String element_RoundFourAllist = RoundFourAllList.get(round4Index);
                            Boolean Round4Check = RoundFourHeroList.contains(element_RoundFourAllist);
                            String round_four1 = "";
                            if (Round4Check == true){
                                int indexRoundFourMonster = (int)(Math.random()*RoundFourMonsterList.size());
                                String element_RoundFourmonsterList = RoundFourMonsterList.get(indexRoundFourMonster);
                                round_four1 += "\n Here is your hero's round : " + element_RoundFourAllist + "\n";
                                round_four1 += "\n You have 4 options : \n";
                                round_four1 += "\n 1) attack  2) defense  3) use weapon  4) use option 5) use food \n";
                                gameArea.appendText(round_four1);
                                TextInputalwaysDialog heroActionDialog = new TextInputalwaysDialog();
                                int HA = Integer.parseInt(heroActionDialog.getText());
                                gameArea.appendText("\n You choose the option :" + HA);
                                if (HA == 1) {
                                    String round_four_HA = "";
                                    round_four_HA += "\n " + element_RoundFourAllist + "attack" + element_RoundFourmonsterList + "\n";
                                    if (element_RoundFourAllist == "Hunter"){
                                        System.out.println("Hunter attack");
                                        round_four_HA += "\n and deal " + hunter.getAttack() +"damage";
                                        if (element_RoundFourmonsterList == "Monster1"){
                                            round_four_HA += "\n " + element_RoundFourmonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster1.looseHp(hunter.getAttack());
                                            if (Monster1.getHp() <= 0){
                                                Monster1.die();
                                                RoundFourAllList.remove("Monster1");
                                                RoundFourMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundFourmonsterList == "Monster2"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster2.looseHp(hunter.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundFourAllList.remove("Monster2");
                                                RoundFourMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundFourmonsterList == "Monster3"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster3.looseHp(hunter.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundFourAllList.remove("Monster3");
                                                RoundFourMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundFourmonsterList == "Monster4"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + hunter.getAttack() + "hp.";
                                            Monster4.looseHp(hunter.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundFourAllList.remove("Monster4");
                                                RoundFourMonsterList.remove("Monster4");
                                            }                                        
                                        }else {
                                            System.out.println("round_one_HA");
                                        }
                                        gameArea.appendText(round_four_HA);
    
                                    }else if(element_RoundFourAllist == "Warrior"){
                                        System.out.println("Warrior attack");
                                        round_four_HA += "and deal " + warrior.getAttack() +" damage ";
                                        if (element_RoundFourmonsterList =="Monster1"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster1.looseHp(warrior.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundFourAllList.remove("Monster1");
                                                RoundFourMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundFourmonsterList == "Monster2"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster2.looseHp(warrior.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundFourAllList.remove("Monster2");
                                                RoundFourMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundFourmonsterList == "Monster3"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster3.looseHp(warrior.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundFourAllList.remove("Monster3");
                                                RoundFourMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundFourmonsterList == "Monster4"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + warrior.getAttack() + "hp.";
                                            Monster4.looseHp(warrior.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundFourAllList.remove("Monster4");
                                                RoundFourMonsterList.remove("Monster4");
                                            }                                        
                                        }
                                        gameArea.appendText(round_four_HA);
    
                                    }else if (element_RoundFourAllist == "Mage"){
                                        System.out.println("Mage attack");
                                        round_four_HA += "\n and deal " + mage.getAttack() +"damage";
                                        if (element_RoundFourmonsterList =="Monster1"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster1.looseHp(mage.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundFourAllList.remove("Monster1");
                                                RoundFourMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundFourmonsterList == "Monster2"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster2.looseHp(mage.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundFourAllList.remove("Monster2");
                                                RoundFourMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundFourmonsterList == "Monster3"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster3.looseHp(mage.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundFourAllList.remove("Monster3");
                                                RoundFourMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundFourmonsterList == "Monster4"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + mage.getAttack() + "hp.";
                                            Monster4.looseHp(mage.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundFourAllList.remove("Monster4");
                                                RoundFourMonsterList.remove("Monster4");
                                            }                                        
                                        }    
                                        gameArea.appendText(round_four_HA);
    
                                    }else if (element_RoundFourAllist == "Healer"){
                                        System.out.println("Healer attack");
                                        round_four_HA += "\n and deal " + healer.getAttack() +"damage";
                                        if (element_RoundFourmonsterList =="Monster1"){
                                            round_four_HA +="\n " + element_RoundFourmonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster1.looseHp(healer.getAttack());
                                            if (Monster1.getHp() < 0){
                                                Monster1.die();
                                                RoundFourAllList.remove("Monster1");
                                                RoundFourMonsterList.remove("Monster1");
                                            }
                                        }else if (element_RoundFourmonsterList == "Monster2"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster2.looseHp(healer.getAttack());
                                            if (Monster2.getHp() < 0){
                                                Monster2.die();
                                                RoundFourAllList.remove("Monster2");
                                                RoundFourMonsterList.remove("Monster2");
                                            }                                    
                                        }else if (element_RoundFourmonsterList == "Monster3"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster3.looseHp(healer.getAttack());
                                            if (Monster3.getHp() < 0){
                                                Monster3.die();
                                                RoundFourAllList.remove("Monster3");
                                                RoundThreeMonsterList.remove("Monster3");
                                            }                                        
                                        }else if (element_RoundFourmonsterList == "Monster4"){
                                            round_four_HA += "\n " +element_RoundFourmonsterList + " loose " + healer.getAttack() + "hp.";
                                            Monster4.looseHp(healer.getAttack());
                                            if (Monster4.getHp() < 0){
                                                Monster4.die();
                                                RoundFourAllList.remove("Monster4");
                                                RoundFourMonsterList.remove("Monster4");
                                            }                                        
                                        }
                                        gameArea.appendText(round_four_HA);  
                                                                           
                                    }
                                    
                                } else if (HA == 2){
                                    String round_four_option2 = "";
                                    round_four_option2 += "\n " +element_RoundFourAllist + " use the armor";
                                    if (element_RoundFourAllist == "Hunter"){
                                        Hunter_armor = true;
                                    }else if (element_RoundFourAllist == "Warrior"){
                                        Warrior_armor = true;
                                    }else if (element_RoundFourAllist == "Mage"){
                                        Mage_armor = true;
                                    }else if (element_RoundFourAllist == "Healer"){
                                        Healer_armor = true;
                                    }
                                    gameArea.appendText(round_four_option2);
    
                                } else if (HA == 3){
                                    String round_four_option3 = "";
                                    if (element_RoundFourAllist == "Hunter"){
                                        System.out.println("Hunter use the Bow");
                                        int indexMonster_Hunter = (int)(Math.random()*RoundThreeMonsterList.size());
                                        String element_MonsterList_Hunter = RoundThreeMonsterList.get(indexMonster_Hunter);
                                        round_four_option3 += "\n Hunter use the Bow to attack one monster \n";
                                        round_four_option3 += "\n and deal" + bow.getDamage() + "to" + element_MonsterList_Hunter;
                                        bow.looseAmount(1);
                                        if (element_MonsterList_Hunter == "Monster1"){
                                            Monster1.looseHp(bow.getDamage());
                                            round_four_option3 += "\n Monster1 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster2"){
                                            Monster2.looseHp(bow.getDamage());
                                            round_four_option3 += "\n Monster2 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster3"){
                                            Monster3.looseHp(bow.getDamage());
                                            round_four_option3 += "\n Monster3 loose " + bow.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster4"){
                                            Monster4.looseHp(bow.getDamage());
                                            round_four_option3 += "\n Monster4 loose " + bow.getDamage() + "hp";
                                        }
                                        gameArea.appendText(round_four_option3);
    
                                    }else if (element_RoundFourAllist == "Warrior"){
                                        System.out.println("Warrior use the sword");
                                        int indexMonster_Hunter = (int)(Math.random()*RoundOneMonsterList.size());
                                        String element_MonsterList_Hunter = RoundOneMonsterList.get(indexMonster_Hunter);
                                        round_four_option3 += "\n Warrior use the Sword to attack one monster \n";
                                        round_four_option3 += "\n and deal" + sword.getDamage() + "to" + element_MonsterList_Hunter;
                                        if (element_MonsterList_Hunter == "Monster1"){
                                            Monster1.looseHp(sword.getDamage());
                                            round_four_option3 += "\n Monster1 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster2"){
                                            Monster2.looseHp(sword.getDamage());
                                            round_four_option3 += "\n Monster2 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster3"){
                                            Monster3.looseHp(sword.getDamage());
                                            round_four_option3 += "\n Monster3 loose " + sword.getDamage() + "hp";
                                        }else if (element_MonsterList_Hunter == "Monster4"){
                                            Monster4.looseHp(sword.getDamage());
                                            round_four_option3 += "\n Monster4 loose " + sword.getDamage() + "hp";
                                        } 
                                        gameArea.appendText(round_four_option3);
    
                                    }else if (element_RoundFourAllist == "Mage"){
                                        System.out.println("Mage use the Staff");
                                        round_four_option3 += "\n Mage use the Staff to attack all the monster";
                                        for (int i = 0; i<RoundOneMonsterList.size();i++){
                                            if (RoundOneMonsterList.get(i) == "Monster1"){
                                                Monster1.looseHp(staff.getDamage());
                                                round_four_option3 += "\n Monster1 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster2"){
                                                Monster2.looseHp(staff.getDamage());
                                                round_four_option3 += "\n Monster2 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster3"){
                                                Monster3.looseHp(staff.getDamage());
                                                round_four_option3 += "\n Monster3 loose " + staff.getDamage() + "hp";
                                            }else if (RoundOneMonsterList.get(i) == "Monster4"){
                                                Monster4.looseHp(staff.getDamage());
                                                round_four_option3 += "\n Monster4 loose " + staff.getDamage() + "hp";
                                            } 
                                        }
                                        gameArea.appendText(round_four_option3);
    
                                    }else if (element_RoundFourAllist == "Healer"){
                                        System.out.println("Healer use the Needle");
                                        round_four_option3 += "\n You can choose one hero to heal";
                                        gameArea.appendText(round_four_option3);
                                        TextInputalwaysDialog ChooseHeroHeal = new TextInputalwaysDialog();
                                        String CH = "";
                                        CH = CH + ChooseHeroHeal;
                                        String healingheroString = "";
                                        if (CH == "Hunter"){
                                            healingheroString += "\n Hunter is healed";
                                            hunter.gainHp(needle.getHealing());
                                        }else if (CH == "Warrior"){
                                            healingheroString += "\n Warrior is healed";
                                            warrior.gainHp(needle.getHealing());
                                        }else if (CH == "Mage"){
                                            healingheroString += "\n Mage is healed";
                                            mage.gainHp(needle.getHealing());                                    
                                        }else if (CH == "Healer"){
                                            healingheroString += "\n Healer is healed";
                                            healer.gainHp(needle.getHealing());                                    
                                        }
    
                                        gameArea.appendText(healingheroString);
                                    }
                                } else if (HA == 4){
                                    
                                    if (element_RoundFourAllist == "Hunter"){
                                        if(hunter.getOption() != null){
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Hunter use the option";
                                            gameArea.appendText(round_four_option4);
                                            hunter.gainAttack(5);
                                        }else{
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Hunter dont have option";
                                            gameArea.appendText(round_four_option4);
                                        }
                                    }else if (element_RoundFourAllist == "Warrior"){
                                        if(warrior.getOption() != null){
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_four_option4);
                                            warrior.gainAttack(5);
                                        }else{
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_four_option4);
                                        }                                
                                    }else if (element_RoundFourAllist == "Mage"){
                                        if(mage.getOption() != null){
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Mage use the option";
                                            gameArea.appendText(round_four_option4);
                                            mage.gainAttack(5);
                                        }else{
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Mage dont have option";
                                            gameArea.appendText(round_four_option4);
                                        } 
                                    }else if (element_RoundFourAllist == "Healer"){
                                        if(healer.getOption() != null){
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_four_option4);
                                            healer.gainAttack(5);
                                        }else{
                                            String round_four_option4 = "";
                                            round_four_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_four_option4);
                                        } 
                                    }
                                    
    
                                } else if(HA == 5){
                                    String round_four_option5 = "";
                                    if (element_RoundFourAllist == "Hunter"){
                                        if(hunter.getFood() != null){
                                            round_four_option5 += "\n Hunter use the food";
                                            hunter.gainHp(15);
                                        }else{
                                            round_four_option5 += "\n Hunter dont have food";
                                        }
                                    }else if (element_RoundFourAllist == "Warrior"){
                                        if(warrior.getFood() != null){
                                            round_four_option5 += "\n Warrior use the food";
                                            warrior.gainHp(15);
                                        }else{
                                            round_four_option5 += "\n Warrior dont have food";
                                        }                                
                                    }else if (element_RoundFourAllist == "Mage"){
                                        if(mage.getFood() != null){
                                            round_four_option5 += "\n Mage use the food";
                                            mage.gainHp(15);
                                        }else{
                                            round_four_option5 += "\n Mage dont have food";
                                        } 
                                    }else if (element_RoundFourAllist == "Healer"){
                                        if(healer.getFood() != null){
                                            round_four_option5 += "\n Warrior use the food";
                                            healer.gainHp(15);
                                        }else{
                                            round_four_option5 += "\n Warrior dont have food";
                                        } 
                                    }
                                    gameArea.appendText(round_four_option5);                            
                                } else {
                                    System.out.println("ERREUR2");
                                }
                            }else {
                                System.out.println("Monster attack");
                                int indexHero = (int)(Math.random()*RoundFourHeroList.size());
                                String element_RoundFourHeroList = RoundFourHeroList.get(indexHero);
                                String monsterattack1 = "";
                                monsterattack1 += "\n" + element_RoundFourAllist + " attack " + element_RoundFourHeroList;
                                gameArea.appendText(monsterattack1);
                                if(element_RoundFourHeroList == "Hunter"){
                                    if(Hunter_armor == false){
                                        StringBuilder monsterattack2 = new StringBuilder();
                                        monsterattack2.append("\n and deal 20 damage to Hunter") ;
                                        hunter.looseHp(20);
                                        if (hunter.getHp() <= 0){
                                            RoundFourAllList.remove("Hunter");
                                            RoundFourHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack2 + "");
                                    }else{
                                        StringBuilder monsterattack3 = new StringBuilder();
                                        monsterattack3.append("\n and deal " + (20-hunter.getArmor()) + " damage to Hunter") ;
                                        hunter.looseHp(20-hunter.getArmor());
                                        if (hunter.getHp() <= 0){
                                            RoundFourAllList.remove("Hunter");
                                            RoundFourHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack3 + "");
                                    }
                                }else if (element_RoundFourHeroList == "Warrior") {
                                    if(Warrior_armor == false){
                                        StringBuilder monsterattack4 = new StringBuilder();
                                        monsterattack4.append("\n and deal 20 damage to Warrior") ;
                                        warrior.looseHp(20);
                                        if (warrior.getHp() <= 0){
                                            RoundFourAllList.remove("Warrior");
                                            RoundFourHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack4 + "");
                                    }else{
                                        StringBuilder monsterattack5 = new StringBuilder();
                                        monsterattack5.append("\n and deal " + (20-warrior.getArmor()) + " damage to Warrior") ;
                                        warrior.looseHp(20-warrior.getArmor());
                                        if (warrior.getHp() <= 0){
                                            RoundFourAllList.remove("Warrior");
                                            RoundFourHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack5 + "");
                                    }                          
                                }else if (element_RoundFourHeroList == "Mage") {
                                    if(Mage_armor == false){
                                        StringBuilder monsterattack6 = new StringBuilder();
                                        monsterattack6.append("\n and deal 20 damage to Mage") ;
                                        mage.looseHp(20);
                                        if (mage.getHp() <= 0){
                                            RoundFourAllList.remove("Mage");
                                            RoundFourHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack6 + "");
                                    }else{
                                        StringBuilder monsterattack7 = new StringBuilder();
                                        monsterattack7.append("\n and deal " + (20-mage.getArmor()) + " damage to Mage") ;
                                        mage.looseHp(20-mage.getArmor());
                                        if (mage.getHp() <= 0){
                                            RoundFourAllList.remove("Mage");
                                            RoundFourHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack7 + "");
                                    }                         
                                }else if (element_RoundFourHeroList == "Healer") {
                                    if(Healer_armor == false){
                                        StringBuilder monsterattack8 = new StringBuilder();
                                        monsterattack8.append("\n and deal 20 damage to Healer") ;
                                        healer.looseHp(20);
                                        if (healer.getHp() <= 0){
                                            RoundFourAllList.remove("Healer");
                                            RoundFourHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack8 + "");
                                    }else{
                                        StringBuilder monsterattack9 = new StringBuilder();
                                        monsterattack9.append("\n and deal " + (20-healer.getArmor()) + " damage to Healer") ;
                                        healer.looseHp(20-healer.getArmor());
                                        if (healer.getHp() <= 0){
                                            RoundFourAllList.remove("Healer");
                                            RoundFourHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack9 + "");
                                    }
                                }
                            }                    
                        }else if (RoundFourHeroList.size() == 0){
                                String GameOver = "";
                                GameOver += "\n Game Over!";
                                gameArea.appendText(GameOver);
                                isStart = false;
                                break;
                                
                        }else if (RoundFourMonsterList.size() == 0){
                            String FourthRoundWin = "";
                            FourthRoundWin += "\n You win the Fourth round";
                            gameArea.appendText(FourthRoundWin);
                            round_four_win = true;
                            String bonus = "";
                            bonus += "\n You can choose one bonus";
                            bonus += "\n 1)Increase the damage";
                            bonus += "\n 2)Increase the defense";
                            bonus += "\n 3)Increase the effectiveness of potions and foods";
                            bonus += "\n 4)Increase the amount of potions and foods";
                            bonus += "\n 5)Increase the number of arrows (for the Hunter), decrease the mana cost for the sorcerers or the effectiveness of their spells";
                            gameArea.appendText(bonus);
                            TextInputalwaysDialog bonus4Dialog = new TextInputalwaysDialog();
                            int bonus4 = Integer.parseInt(bonus4Dialog.getText());
                            if(bonus4 == 1){
                                for(int i = 0; i<heroList.size();i++){
                                    if(heroList.get(i) == "Hunter"){
                                        hunter.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Warrior"){
                                        warrior.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Mage"){
                                        mage.gainAttack(5);
                                    }
                                    if(heroList.get(i) == "Healer"){
                                        healer.gainAttack(5);
                                    }
                                }
                            }else if (bonus4 == 2){
                                for(int i = 0; i<heroList.size();i++){
                                    if(heroList.get(i) == "Hunter"){
                                        hunter.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Warrior"){
                                        warrior.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Mage"){
                                        mage.gainArmor(5);
                                    }
                                    if(heroList.get(i) == "Healer"){
                                        healer.gainArmor(5);
                                    }
                                }
                            }else if (bonus4 == 3){
                                apple.gainEffect(5);
                                red.gainEffect(5);
                            }else if (bonus4 == 4){
                                apple.gainAmount(1);
                                red.gainAmount(1);
                            }else if (bonus4 == 5){
                                bow.addAmount(1);
                                mage.gainMana(20);
                                staff.loose_manacost(5);
                            }
                            gameArea.appendText("\n You choose the optiom: "+bonus4);
                            break;
        
                        }
                    }
                }

                
                //创建boss回合的list
                ArrayList<String> RoundBossMonsterList = new ArrayList<>();
                RoundBossMonsterList.add("Boss");
                ArrayList<String> RoundBossHeroList = new ArrayList<>();
                for(int i = 0; i<RoundFourHeroList.size();i++){
                    RoundBossHeroList.add(RoundFourHeroList.get(i));
                }  
                ArrayList<String> RoundBossAllList = new ArrayList<>();
                RoundBossAllList.addAll(RoundBossMonsterList);
                RoundBossAllList.addAll(RoundBossHeroList);

                //第五回合
                if(round_four_win == true){
                    gameArea.appendText("\n-------------------- Fifth ROUND ----------------------------");
                    gameArea.appendText("This is the final round!");
                    while(RoundBossAllList.size()>0){
                        if(RoundBossHeroList.size() != 0 &&RoundBossMonsterList.size() != 0){
                            int round5Index = (int)(Math.random()*RoundBossAllList.size());
                            String element_RoundBossAllist = RoundFourAllList.get(round5Index);
                            Boolean Round5Check = RoundBossHeroList.contains(element_RoundBossAllist);
                            String round_boss1 = "";
                            if (Round5Check == true){
                                round_boss1 += "\n Here is your hero's round : " + element_RoundBossAllist + "\n";
                                round_boss1 += "\n You have 4 options : \n";
                                round_boss1 += "\n 1) attack  2) defense  3) use weapon  4) use option 5) use food \n";
                                gameArea.appendText(round_boss1);
                                TextInputalwaysDialog heroActionDialog = new TextInputalwaysDialog();
                                int HA = Integer.parseInt(heroActionDialog.getText());
                                gameArea.appendText("\n You choose the option :" + HA);
                                if (HA == 1) {
                                    String round_five_HA = "";
                                    round_five_HA += "\n " + element_RoundBossAllist + "attack Boss" + "\n";
                                    if (element_RoundBossAllist == "Hunter"){
                                        System.out.println("Hunter attack");
                                        round_five_HA += "\n and deal " + hunter.getAttack() +"damage";
                                        boss.looseHp(hunter.getAttack());
                                        gameArea.appendText(round_five_HA);
    
                                    }else if(element_RoundBossAllist == "Warrior"){
                                        System.out.println("Warrior attack");
                                        round_five_HA += "and deal " + warrior.getAttack() +" damage ";
                                        int sumattack = warrior.getAttack() + sword.getDamage();
                                        boss.looseHp(sumattack);
                                        gameArea.appendText(round_five_HA);
    
                                    }else if (element_RoundBossAllist == "Mage"){
                                        System.out.println("Mage attack");
                                        round_five_HA += "\n and deal " + mage.getAttack() +"damage";
                                        boss.looseHp(mage.getAttack());
                                        gameArea.appendText(round_five_HA);
    
                                    }else if (element_RoundBossAllist == "Healer"){
                                        System.out.println("Healer attack");
                                        round_five_HA += "\n and deal " + healer.getAttack() +"damage";
                                        boss.looseHp(healer.getAttack());
                                        gameArea.appendText(round_five_HA);  
                                                                           
                                    }
                                    
                                } else if (HA == 2){
                                    String round_five_option2 = "";
                                    round_five_option2 += "\n " +element_RoundBossAllist + " use the armor";
                                    if (element_RoundBossAllist == "Hunter"){
                                        Hunter_armor = true;
                                    }else if (element_RoundBossAllist == "Warrior"){
                                        Warrior_armor = true;
                                    }else if (element_RoundBossAllist == "Mage"){
                                        Mage_armor = true;
                                    }else if (element_RoundBossAllist == "Healer"){
                                        Healer_armor = true;
                                    }
                                    gameArea.appendText(round_five_option2);
    
                                } else if (HA == 3){
                                    String round_five_option3 = "";
                                    if (element_RoundBossAllist == "Hunter"){
                                        System.out.println("Hunter use the Bow");
                                        int indexMonster_Hunter = (int)(Math.random()*RoundThreeMonsterList.size());
                                        String element_MonsterList_Hunter = RoundThreeMonsterList.get(indexMonster_Hunter);
                                        round_five_option3 += "\n Hunter use the Bow to attack one monster \n";
                                        round_five_option3 += "\n and deal" + bow.getDamage() + "to" + element_MonsterList_Hunter;
                                        bow.looseAmount(1);
                                        boss.looseHp(bow.getDamage());
                                        round_five_option3 += "\n Boss loose " + bow.getDamage() + " hp";
                                        
                                        gameArea.appendText(round_five_option3);
    
                                    }else if (element_RoundBossAllist == "Warrior"){
                                        System.out.println("Warrior use the sword");
                                        round_five_option3 += "\n Warrior use the Sword to attack the boss \n";
                                        round_five_option3 += "\n and deal" + sword.getDamage() + "to boss";
                                        round_five_option3 += "\n Boss loose " + sword.getDamage() + "hp";
                                        gameArea.appendText(round_five_option3);
    
                                    }else if (element_RoundBossAllist == "Mage"){
                                        System.out.println("Mage use the Staff");
                                        round_five_option3 += "\n Mage use the Staff to attack the Boss";
                                        round_five_option3 += "\n Boss loose " + staff.getDamage() + "hp";
                                        gameArea.appendText(round_five_option3);
    
                                    }else if (element_RoundBossAllist == "Healer"){
                                        System.out.println("Healer use the Needle");
                                        round_five_option3 += "\n You can choose one hero to heal";
                                        gameArea.appendText(round_five_option3);
                                        TextInputalwaysDialog ChooseHeroHeal = new TextInputalwaysDialog();
                                        String CH = "";
                                        CH = CH + ChooseHeroHeal;
                                        String healingheroString = "";
                                        if (CH == "Hunter"){
                                            healingheroString += "\n Hunter is healed";
                                            hunter.gainHp(needle.getHealing());
                                        }else if (CH == "Warrior"){
                                            healingheroString += "\n Warrior is healed";
                                            warrior.gainHp(needle.getHealing());
                                        }else if (CH == "Mage"){
                                            healingheroString += "\n Mage is healed";
                                            mage.gainHp(needle.getHealing());                                    
                                        }else if (CH == "Healer"){
                                            healingheroString += "\n Healer is healed";
                                            healer.gainHp(needle.getHealing());                                    
                                        }
    
                                        gameArea.appendText(healingheroString);
                                    }
                                } else if (HA == 4){
                                    
                                    if (element_RoundBossAllist == "Hunter"){
                                        if(hunter.getOption() != null){
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Hunter use the option";
                                            gameArea.appendText(round_five_option4);
                                            hunter.gainAttack(5);
                                        }else{
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Hunter dont have option";
                                            gameArea.appendText(round_five_option4);
                                        }
                                    }else if (element_RoundBossAllist == "Warrior"){
                                        if(warrior.getOption() != null){
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_five_option4);
                                            warrior.gainAttack(5);
                                        }else{
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_five_option4);
                                        }                                
                                    }else if (element_RoundBossAllist == "Mage"){
                                        if(mage.getOption() != null){
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Mage use the option";
                                            gameArea.appendText(round_five_option4);
                                            mage.gainAttack(5);
                                        }else{
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Mage dont have option";
                                            gameArea.appendText(round_five_option4);
                                        } 
                                    }else if (element_RoundBossAllist == "Healer"){
                                        if(healer.getOption() != null){
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Warrior use the option";
                                            gameArea.appendText(round_five_option4);
                                            healer.gainAttack(5);
                                        }else{
                                            String round_five_option4 = "";
                                            round_five_option4 += "\n Warrior dont have option";
                                            gameArea.appendText(round_five_option4);
                                        } 
                                    }
                                    
    
                                } else if(HA == 5){
                                    String round_five_option5 = "";
                                    if (element_RoundBossAllist == "Hunter"){
                                        if(hunter.getFood() != null){
                                            round_five_option5 += "\n Hunter use the food";
                                            hunter.gainHp(15);
                                        }else{
                                            round_five_option5 += "\n Hunter dont have food";
                                        }
                                    }else if (element_RoundBossAllist == "Warrior"){
                                        if(warrior.getFood() != null){
                                            round_five_option5 += "\n Warrior use the food";
                                            warrior.gainHp(15);
                                        }else{
                                            round_five_option5 += "\n Warrior dont have food";
                                        }                                
                                    }else if (element_RoundBossAllist == "Mage"){
                                        if(mage.getFood() != null){
                                            round_five_option5 += "\n Mage use the food";
                                            mage.gainHp(15);
                                        }else{
                                            round_five_option5 += "\n Mage dont have food";
                                        } 
                                    }else if (element_RoundBossAllist == "Healer"){
                                        if(healer.getFood() != null){
                                            round_five_option5 += "\n Warrior use the food";
                                            healer.gainHp(15);
                                        }else{
                                            round_five_option5 += "\n Warrior dont have food";
                                        } 
                                    }
                                    gameArea.appendText(round_five_option5);                            
                                } else {
                                    System.out.println("ERREUR2");
                                }
                            }else {
                                System.out.println("Monster attack");
                                int indexHero = (int)(Math.random()*RoundBossHeroList.size());
                                String element_RoundFiveHeroList = RoundBossHeroList.get(indexHero);
                                String monsterattack1 = "";
                                monsterattack1 += "\n" + "Boss " + " attack " + element_RoundFiveHeroList;
                                gameArea.appendText(monsterattack1);
                                if(element_RoundFiveHeroList == "Hunter"){
                                    if(Hunter_armor == false){
                                        StringBuilder monsterattack2 = new StringBuilder();
                                        monsterattack2.append("\n and deal " + boss.getAttack() + " damage to Hunter") ;
                                        hunter.looseHp(boss.getAttack());
                                        if (hunter.getHp() <= 0){
                                            RoundBossAllList.remove("Hunter");
                                            RoundBossHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack2 + "");
                                    }else{
                                        StringBuilder monsterattack3 = new StringBuilder();
                                        monsterattack3.append("\n and deal " + (boss.getAttack()-hunter.getArmor()) + " damage to Hunter") ;
                                        hunter.looseHp(boss.getAttack()-hunter.getArmor());
                                        if (hunter.getHp() <= 0){
                                            RoundBossAllList.remove("Hunter");
                                            RoundBossHeroList.remove("Hunter");
                                        }
                                        gameArea.appendText(monsterattack3 + "");
                                    }
                                }else if (element_RoundFiveHeroList == "Warrior") {
                                    if(Warrior_armor == false){
                                        StringBuilder monsterattack4 = new StringBuilder();
                                        monsterattack4.append("\n and deal " + boss.getAttack() + " damage to Warrior") ;
                                        warrior.looseHp(boss.getAttack());
                                        if (warrior.getHp() <= 0){
                                            RoundBossAllList.remove("Warrior");
                                            RoundBossHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack4 + "");
                                    }else{
                                        StringBuilder monsterattack5 = new StringBuilder();
                                        monsterattack5.append("\n and deal " + (boss.getAttack()-warrior.getArmor()) + " damage to Warrior") ;
                                        warrior.looseHp(boss.getAttack()-warrior.getArmor());
                                        if (warrior.getHp() <= 0){
                                            RoundBossAllList.remove("Warrior");
                                            RoundBossHeroList.remove("Warrior");
                                        }
                                        gameArea.appendText(monsterattack5 + "");
                                    }                          
                                }else if (element_RoundFiveHeroList == "Mage") {
                                    if(Mage_armor == false){
                                        StringBuilder monsterattack6 = new StringBuilder();
                                        monsterattack6.append("\n and deal " + boss.getAttack() + " damage to Mage") ;
                                        mage.looseHp(boss.getAttack());
                                        if (mage.getHp() <= 0){
                                            RoundBossAllList.remove("Mage");
                                            RoundBossHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack6 + "");
                                    }else{
                                        StringBuilder monsterattack7 = new StringBuilder();
                                        monsterattack7.append("\n and deal " + (boss.getAttack()-mage.getArmor()) + " damage to Mage") ;
                                        mage.looseHp(boss.getAttack()-mage.getArmor());
                                        if (mage.getHp() <= 0){
                                            RoundBossAllList.remove("Mage");
                                            RoundBossHeroList.remove("Mage");
                                        }
                                        gameArea.appendText(monsterattack7 + "");
                                    }                         
                                }else if (element_RoundFiveHeroList == "Healer") {
                                    if(Healer_armor == false){
                                        StringBuilder monsterattack8 = new StringBuilder();
                                        monsterattack8.append("\n and deal " + boss.getAttack() + " damage to Healer") ;
                                        healer.looseHp(boss.getAttack());
                                        if (healer.getHp() <= 0){
                                            RoundBossAllList.remove("Healer");
                                            RoundBossHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack8 + "");
                                    }else{
                                        StringBuilder monsterattack9 = new StringBuilder();
                                        monsterattack9.append("\n and deal " + (boss.getAttack()-healer.getArmor()) + " damage to Healer") ;
                                        healer.looseHp(boss.getAttack()-healer.getArmor());
                                        if (healer.getHp() <= 0){
                                            RoundBossAllList.remove("Healer");
                                            RoundBossHeroList.remove("Healer");
                                        }
                                        gameArea.appendText(monsterattack9 + "");
                                    }
                                }
                            }                    
                        }else if (RoundBossHeroList.size() == 0){
                                String GameOver = "";
                                GameOver += "\n Game Over!";
                                gameArea.appendText(GameOver);
                                isStart = false;
                                break;
                                
                        }else if (RoundBossMonsterList.size() == 0){
                            String FifthRoundWin = "";
                            FifthRoundWin += "\n You win the Fifth round";
                            FifthRoundWin += "\n This is the final round";
                            FifthRoundWin += "\n Congratulations";
                            gameArea.appendText(FifthRoundWin);
                            break;
                        }
                    }
                }
            }
        });

        //点击查看属性
        buttonProperties.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("View Properties");      //检查器
                String showproString = "";
                
                //设置每个英雄属性
                hunter = new Hunter("Hunter");
                warrior = new Warrior("Warrior");
                mage = new Mage("Mage");
                healer = new Healer("Healer");


                for (int i = 0; i<heroList.size(); i++) {
                    if (heroList.get(i) == "Hunter") {
                        showproString += "\n";
                        showproString += hunter.showProper();
                        showproString += "\n";
                    }else if (heroList.get(i) == "Warrior") {
                        showproString += "\n";
                        showproString += warrior.showProper();
                        showproString += "\n";
                    }else if (heroList.get(i) == "Mage") {
                        showproString += "\n";
                        showproString += mage.showProper();
                        showproString += "\n";                        
                    }else if (heroList.get(i) == "Healer") {
                        showproString += "\n";
                        showproString += healer.showProper();
                        showproString += "\n";                        
                    }
                }
                gameArea.appendText(showproString);
            }
        });


        //点击查看物品
        buttonItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("View Item");        //检查器
                String showItem = "";
                
                //设置每个英雄属性
                hunter = new Hunter("Hunter");
                warrior = new Warrior("Warrior");
                mage = new Mage("Mage");
                healer = new Healer("Healer");

                for (int i = 0; i<heroList.size(); i++) {
                    if (heroList.get(i) == "Hunter") {
                        showItem += "\n";
                        showItem += hunter.showItem();
                        showItem += "\n";
                    }else if (heroList.get(i) == "Warrior") {
                        showItem += "\n";
                        showItem += warrior.showItem();
                        showItem += "\n";
                    }else if (heroList.get(i) == "Mage") {
                        showItem += "\n";
                        showItem += mage.showItem();
                        showItem += "\n";                        
                    }else if (heroList.get(i) == "Healer") {
                        showItem += "\n";
                        showItem += healer.showItem();
                        showItem += "\n";                        
                    }
                }
                gameArea.appendText(showItem);
                
            }        
        });


        //英雄行动
        buttonRound.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ROUND NUMBER");
                String round_number = "";
                if(round_four_win == true && round_three_win == true && round_two_win == true && round_one_win == true){
                    round_number = "Round 5";
                }else if(round_three_win == true && round_two_win == true && round_one_win == true){
                    round_number = "Round 4";
                }else if (round_two_win == true && round_one_win == true){
                    round_number = "Round 3";
                }else if (round_one_win == true){
                    round_number = "Round 2";
                }
                gameArea.appendText(round_number);
            }
        });


        //设置画面展示
		Scene scene = new Scene(rootPane, 800, 550); // 游戏机整体大小
		primaryStage.setTitle("Mini RPG Lite 3000");
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    //-----------------------------------控制事件----------------------------------//
        //游戏开始
        public static void StartGame(){
            if(isStart){
                return;
            }
            
            Font startnameFont = new Font("Cambria",15);     //字体设置
            gameArea.setFont(startnameFont);
            gameArea.setText("\n\n\t\t\t    Start Game!");
            gameArea.setStyle("-fx-background-color:red;-fx-text-fill:green;");

            TextInputDialog playername = new TextInputDialog("Player");
            playername.setTitle("What is your name?");
            playername.setHeaderText(null);
            playername.setContentText("What is your name?");
            Optional<String> result1 = playername.showAndWait();
            String name = "";
            if (result1.isPresent()) { 
                name = result1.get();        //获取玩家名字
            }else{
                return;
            }
            
            Player player = null;
            player = new Player(name);
            isStart = true;     //开始游戏
            String player_name = "You name is : " + player.getName() + "\n";
            gameArea.setText(player_name);
        }



        //设置文本输入框，之后将一直调用为了判定选择
        class TextInputalwaysDialog {
            private TextField textBox;
            private TextArea textArea;

            public TextInputalwaysDialog() {
                textBox = new TextField();
                BorderPane root = new BorderPane();
                root.setCenter(textBox);

                textArea = new TextArea(); 
                textArea.setEditable(false);
                textArea.setPrefSize(300, 80);
                textArea.setWrapText(true);
                Font textAreaFont = new Font("Cambria", 15);
                textArea.setFont(textAreaFont);
                textArea.appendText("\n\t After you input the text , you need to close the stage"); 
                root.setTop(textArea);

                Scene scene = new Scene(root, 300, 250);

                Stage stage = new Stage();
                stage.setTitle("Input Box");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
            public String getText() {
                return textBox.getText();
            }
        }

        //第一个英雄类型选择
        public static void heroclass1() { 
            String heroclass1Builder = "";
            heroclass1Builder += "\n First Class:";
            heroclass1Builder +="\n 1) Warrior";
            heroclass1Builder +="\n 2) Hunter";
            heroclass1Builder +="\n 3) Mage";
            heroclass1Builder +="\n 4) Healer";
            gameArea.appendText(heroclass1Builder);
        }

        //第二个英雄类型选择
        public static void heroclass2() { 
            String heroclass2Builder = "";
            heroclass2Builder += "\n Second Class:";
            heroclass2Builder +="\n 1) Warrior";
            heroclass2Builder +="\n 2) Hunter";
            heroclass2Builder +="\n 3) Mage";
            heroclass2Builder +="\n 4) Healer";
            gameArea.appendText(heroclass2Builder);
        }

        //第三个英雄类型选择
        public static void heroclass3() { 
            String heroclass3Builder = "";
            heroclass3Builder += "\n Third Class:";
            heroclass3Builder +="\n 1) Warrior";
            heroclass3Builder +="\n 2) Hunter";
            heroclass3Builder +="\n 3) Mage";
            heroclass3Builder +="\n 4) Healer";
            gameArea.appendText(heroclass3Builder);
        }

        //第四个英雄类型选择
        public static void heroclass4() { 
            String heroclass4Builder = "";
            heroclass4Builder += "\n Fourth Class:";
            heroclass4Builder +="\n 1) Warrior";
            heroclass4Builder +="\n 2) Hunter";
            heroclass4Builder +="\n 3) Mage";
            heroclass4Builder +="\n 4) Healer";
            gameArea.appendText(heroclass4Builder);
        }        
}






        



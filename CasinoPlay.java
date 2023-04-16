import java.util.*;
import java.lang.Math;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CasinoPlay {

    static boolean gameOver=false;
    public static void createSlots(String [][] slots, int a,int b){


        ArrayList<String> fruits  = new ArrayList<>();
        Random random = new Random();
        fruits.add("orange");
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("grapes");
        fruits.add("melon");
        fruits.add("pineapple");
        fruits.add("lemon");
        fruits.add("cherry");
        fruits.add("star");


        int indexWord = 0;

        for(int i=0;i<a;i++){


            for(int j=0;j<b;j++){

                indexWord = random.nextInt(9);
                slots[i][j] = fruits.get(indexWord);

            }
        }
    }

    public static void printBoard(String [][] slots,int a,int b){

        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){

                System.out.print(slots[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double calculateWin(String [][] slots,float currentBalance,float gamble){


        double win = 0.0;
        boolean firstLine  =(slots[0][0].equals(slots[0][1])  && slots[0][1].equals(slots[0][2]));
        boolean secondLine = (slots[1][0].equals(slots[1][1]) && slots[1][1].equals(slots[1][2]));
        boolean thirdLine = (slots[2][0].equals(slots[2][1]) && slots[2][1].equals(slots[2][2]));
        boolean firstTwo = firstLine && secondLine;
        boolean firstAndLast = firstLine && thirdLine;
        boolean lastTwo = secondLine && thirdLine;
        boolean allLines = firstLine && secondLine && thirdLine;

        HashMap<String,Integer> map = new HashMap<>();
        map.put("orange",25);
        map.put("banana",30);
        map.put("apple",30);
        map.put("grapes",15);
        map.put("watermelon",35);
        map.put("pineapple",30);
        map.put("lemon",30);
        map.put("cherry",40);
        map.put("star",10);

        if(firstLine){
            for(String key: map.keySet()) {
                if (slots[0][0].equals(key)) {
                    win = gamble * map.get(key);
                }
            }
        }

        if(secondLine){

            for(String key: map.keySet()){
                if(slots[1][0].equals(key)){
                    win = gamble * map.get(key);
                }
            }
        }
        if(thirdLine){
            for(String key: map.keySet()){
                if(slots[2][0].equals(key)){
                    win = gamble * map.get(key);
                }
            }
        }
        if(firstTwo){

            for(String key: map.keySet()){
                if(slots[0][0].equals(key) && slots[1][0].equals(key)){
                    win = gamble * map.get(key)*2;
                }
            }
        }
        else if(firstAndLast){

            for(String key: map.keySet()){
                if(slots[0][0]==key && slots[2][0] == key){
                    win = gamble * map.get(key)*2;
                }
            }
        }
        else if(lastTwo){

            for(String key: map.keySet()){
                if(slots[2][0].equals(key) && slots[3][0].equals(key)){
                    win = gamble * map.get(key)*2;
                }
            }
        }

        else{
            win = 0.0;
        }



        currentBalance+=win;
        return currentBalance;
    }

    public static void printData(float currentMoney,float gamble,int spinsRemaining){

        System.out.println("Current balance + " + currentMoney);
        System.out.println("Gamble amount" + gamble);
        System.out.println("Spins remaining" + spinsRemaining);
    }
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        int a = 3;
        int b = 3;
        List<Float> gambles = (Arrays.asList(0.20f,0.40f,0.50f,0.60f,0.80f,1f,2f,4f,5f));

        Scanner in = new Scanner(System.in);
        System.out.println("Enter money to get in(in lev)");
        float currentMoney = in.nextFloat();

        System.out.println("Select gamble.");
        for(float gamble: gambles){
            System.out.print(gamble + "  ");
        }
        int  gambleIndex = in.nextInt();
        float gamble = 0.0f;
        int spinsRemaining =50;
        if(gambleIndex>8){

            System.out.println("You have to play with a lower gamble");
            gambleIndex = in.nextInt();
        }
        else {
            gamble = gambles.get(gambleIndex);
        }
        String [][] slots  = new String[a][b];


        while(!gameOver){

            createSlots(slots,a,b);
            System.out.println("Change gamble?y/n");
            char choice1 = in.next().charAt(0);

            if(choice1=='y') {

                System.out.println("Choose an appropriate amount to gamble");
                int choiceGamble = (int)(Math.random()*gambles.size());
                float  amountGamble = gambles.get(choiceGamble);

                gamble = amountGamble;
            }

            else if(choice1=='n'){

                System.out.println("The game continues with the same gamble");

            }

            System.out.println("Spin the machine?");
            char choice2 = in.next().charAt(0);

            if(choice2=='y'){

                printBoard(slots,a,b);
                currentMoney-=gamble;
                double win = calculateWin(slots,currentMoney,gamble);
                System.out.println("Your win is : " + win );
                currentMoney+=win;
                printData(currentMoney,gamble,spinsRemaining);
            }

            else if(choice2=='n'){
                System.out.println("End of game");
                break;
            }

            else{

                System.err.println("Please enter a valid input!");
            }
        }

    }
}
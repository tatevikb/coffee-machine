import java.util.Scanner;

public class CoffeeMachineActions {
    private static final int espresso = 0;
    private static final int latte = 1;
    private static final int cappuccino = 2;

    private static int[] waterForOneCup = new int[3];
    private static int[] milkForOneCup = new int[3];
    private static int[] coffeeForOneCup = new int[3];
    private static int[] moneyForOneCup = new int[3];

    private static int amountOfWater;
    private static int amountOfMilk;
    private static int amountOfCoffee;
    private static int amountOfMoney;
    private static int amountOfCups;

    private static Scanner scan = new Scanner(System.in);

    // set values for water, milk, coffee beans and price for each type of coffee
    private static void setupMachine()
    {
        waterForOneCup[espresso] = 250;
        coffeeForOneCup[espresso] = 16;
        milkForOneCup[espresso] = 0;
        moneyForOneCup[espresso] = 4;

        waterForOneCup[latte] = 350;
        coffeeForOneCup[latte] = 20;
        milkForOneCup[latte] = 75;
        moneyForOneCup[latte] = 7;

        waterForOneCup[cappuccino] = 200;
        coffeeForOneCup[cappuccino] = 12;
        milkForOneCup[cappuccino] = 100;
        moneyForOneCup[cappuccino] = 6;

        // machine resources at this moment
        amountOfWater = 400;
        amountOfMilk = 540;
        amountOfCoffee = 120;
        amountOfCups = 9;
        amountOfMoney = 550;
    }

    // prints the state of the coffee-machine
    private static void printState()
    {
        System.out.println("The coffee machine has:");

        System.out.printf("%d of water\n%d of milk\n%d coffee beans\n%d of disposable cups\n%d of money\n",
                amountOfWater, amountOfMilk, amountOfCoffee, amountOfCups, amountOfMoney);
    }

    private static void buy()
    {
        int coffeeType = readIntegerValue("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino");
        --coffeeType;

        amountOfWater -=  waterForOneCup[coffeeType];
        amountOfMilk -= milkForOneCup[coffeeType];
        amountOfCoffee -= coffeeForOneCup[coffeeType];
        amountOfMoney += moneyForOneCup[coffeeType];

        amountOfCups--;
    }

    private static void fill()
   {
        amountOfWater += readIntegerValue("Write how many ml of water do you want to add");
        amountOfMilk += readIntegerValue("Write how many ml of milk do you want to add");
        amountOfCoffee += readIntegerValue("Write how many grams of coffee beans do you want to add");
        amountOfCups += readIntegerValue("Write how many disposable cups of coffee do you want to add");
   }

    private static void take()
    {
        System.out.println("I gave you $" + amountOfMoney);
        amountOfMoney = 0;
    }


    private static int readIntegerValue(String msg)
    {
        System.out.print(msg + ":\n> ");
        return scan.nextInt();
    }

    private static String readStringValue(String msg)
    {
        System.out.print(msg + ":\n> ");
        return scan.nextLine();
    }

    public static void main(String[] args)
    {
        setupMachine();
        printState();

        String action = readStringValue("Write action (buy, fill, take)");
        if(action.equals("buy")) {
           buy();
        }
        else if(action.equals("fill")){
            fill();
        }
        else if(action.equals("take")){
            take();
        }
        printState();
    }
}

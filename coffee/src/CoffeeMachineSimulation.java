import java.util.Scanner;

public class CoffeeMachineSimulation {
    private final int ESPRESSO = 0;
    private final int LATTE = 1;
    private final int CAPPUCCINO = 2;

    private int[] waterPerCup = new int[3];
    private int[] milkPerCup = new int[3];
    private int[] coffeePerCup = new int[3];
    private int[] moneyPerCup = new int[3];

    private int water = 400;
    private int milk = 540;
    private int coffee = 120;
    private int money = 550;
    private int cups = 9;

    private String state = "ready";

    private CoffeeMachineSimulation()
    {
        waterPerCup[ESPRESSO] = 250;
        coffeePerCup[ESPRESSO] = 16;
        milkPerCup[ESPRESSO] = 0;
        moneyPerCup[ESPRESSO] = 4;

        waterPerCup[LATTE] = 350;
        coffeePerCup[LATTE] = 20;
        milkPerCup[LATTE] = 75;
        moneyPerCup[LATTE] = 7;

        waterPerCup[CAPPUCCINO] = 200;
        coffeePerCup[CAPPUCCINO] = 12;
        milkPerCup[CAPPUCCINO] = 100;
        moneyPerCup[CAPPUCCINO] = 6;

    }

    /**
     * Prints the state of the coffee-machine.
     */
    private void remaining()
    {
        System.out.println("The coffee machine has:");

        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffee + " coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");

    }
    /**
     * Check for resources to make at list one cup of coffee.
     *
     * @param coffeeType Coffee type code (1 - espresso, 2 - latte, 3 - cappuccino).
     *
     * @return Has resources or not.
     */
    private boolean hasResources(int coffeeType)
    {
        boolean has = true;

        if(water < waterPerCup[coffeeType]) {
            System.out.println("Sorry, not enough water!");
            has = false;
        }

        if(coffee < coffeePerCup[coffeeType]) {
            System.out.println("Sorry, not enough coffee!");
            has = false;
        }

        if(milk < milkPerCup[coffeeType]) {
            System.out.println("Sorry, not enough milk!");
            has = false;
        }

        if(cups == 0) {
            System.out.println("Sorry, not enough cups!");
            has = false;
        }

        return has;
    }

    private void buy(String coffeeType)
    {
        int index = Integer.parseInt(coffeeType);
        --index;


        if (hasResources(index)) {
            System.out.println("I have enough resources, making you a coffee!");

            water -= waterPerCup[index];
            milk -= milkPerCup[index];
            coffee -= coffeePerCup[index];
            money += moneyPerCup[index];

            cups--;
        }
    }

    private void take()
    {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void action(String input)
    {
        // check to see if the input is integer representation
        boolean isInt = true;
        for(int i = 0; i < input.length(); ++i)
            if(!Character.isDigit(input.charAt(i))) {
                isInt = false;
                break;
            }

        if(!isInt) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
        }

        System.out.println("> " + input);


        if(input.equals("remaining")) {
            remaining();
        }
        else if(input.equals("buy")) {
            state = "select-coffee";
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        }
        else if(isInt && state.equals("select-coffee")) {
            buy(input);
            state = "ready";
        }
        else if(input.equals("fill")) {
            state = "fill-water";
            System.out.println("Write how many ml of water do you want to add:");
        }
        else if(isInt && state.equals("fill-water")) {
            water += Integer.parseInt(input);
            state = "fill-milk";
            System.out.println("Write how many ml of milk do you want to add:");
        }
        else if(isInt && state.equals("fill-milk")) {
            milk += Integer.parseInt(input);
            state = "fill-coffee";
            System.out.println("Write how many grams of coffee beans do you want to add:");
        }
        else if(isInt && state.equals("fill-coffee")) {
            coffee += Integer.parseInt(input);
            state = "fill-cups";
            System.out.println("Write how many disposable cups of coffee do you want to add:");
        }
        else if(isInt && state.equals("fill-cups")) {
            cups += Integer.parseInt(input);
            state = "ready";
        }
        else if(input.equals("take")) {
            take();
        }
        else if(input.equals("exit")) {
            state = "off";
        }
    }

    public static void main(String[] args)
    {
        CoffeeMachineSimulation cm = new CoffeeMachineSimulation();

//        cm.action("remaining");
//        cm.action("buy");
//        cm.action("2");
//        cm.action("remaining");
//        cm.action("buy");
//        cm.action("2");
        cm.action("fill");
        cm.action("1000");
        cm.action("0");
        cm.action("0");
        cm.action("0");
//        cm.action("remaining");
//        cm.action("remaining");
//        cm.action("buy");
//        cm.action("2");
//        cm.action("remaining");
//        cm.action("take");
    }
}

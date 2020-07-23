import java.util.Scanner;

public class CoffeeMachineSimulation {

    private Scanner scan = new Scanner(System.in);

   // private final int ESPRESSO = 0;
  //  private final int LATTE = 1;
  //  private final int CAPPUCCINO = 2;

    private int[] waterPerCup = new int[3];
    private int[] milkPerCup = new int[3];
    private int[] coffeePerCup = new int[3];
    private int[] moneyPerCup = new int[3];

    private int water = 400;
    private int milk = 540;
    private int coffee = 120;
    private int money = 550;
    private int cups = 9;

    private State state = State.Ready;

    private CoffeeMachineSimulation()
    {
        waterPerCup[CoffeeType.ESPRESSO.ordinal()] = 250;
        coffeePerCup[CoffeeType.ESPRESSO.ordinal()] = 16;
        milkPerCup[CoffeeType.ESPRESSO.ordinal()] = 0;
        moneyPerCup[CoffeeType.ESPRESSO.ordinal()] = 4;

        waterPerCup[CoffeeType.LATTE.ordinal()] = 350;
        coffeePerCup[CoffeeType.LATTE.ordinal()] = 20;
        milkPerCup[CoffeeType.LATTE.ordinal()] = 75;
        moneyPerCup[CoffeeType.LATTE.ordinal()] = 7;

        waterPerCup[CoffeeType.CAPPUCCINO.ordinal()] = 200;
        coffeePerCup[CoffeeType.CAPPUCCINO.ordinal()] = 12;
        milkPerCup[CoffeeType.CAPPUCCINO.ordinal()] = 100;
        moneyPerCup[CoffeeType.CAPPUCCINO.ordinal()] = 6;

        setReady();
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

    private void buy(int index)
    {
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

    private void setReady()
    {
        state = State.Ready;
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        System.out.print("> ");
    }

    private void askForNumber(String prompt)
    {
        System.out.println(prompt);
        System.out.print("> ");
    }

    /**
     *
     */
    private void numericInput(int number)
    {
        switch (state) {
            case SelectCoffee:
                buy(number);
                setReady();
                break;
            case FillWater:
                water += number;
                state = State.FillMilk;
                askForNumber("Write how many ml of milk do you want to add:");
                break;
            case FillMilk:
                milk += number;
                state = State.FillCoffee;
                askForNumber("Write how many grams of coffee beans do you want to add:");
                break;
            case FillCoffee:
                coffee += number;
                state = State.FillCups;
                askForNumber("Write how many disposable cups of coffee do you want to add:");
                break;
            case FillCups:
                cups += number;
                setReady();
                break;
            }
    }

    /**
     * Represents all actions that coffee machine can do․
     */
    private void processAction(String input)
    {
        switch (ActionType.valueOf(input.toUpperCase())) {
            case BUY:
                state = State.SelectCoffee;
                askForNumber("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                break;
            case FILL:
                state = State.FillWater;
                askForNumber("Write how many ml of water do you want to add:");
                break;
            case REMAINING:
                remaining();
                setReady();
                break;
            case TAKE:
                take();
                setReady();
                break;
            case EXIT:
                state = State.Off;
                break;
            default:
                System.out.println("Invalid action '" + input + "'. Try again.");
                break;
        }
    }

    /**
     * Determines which kind of input is inserted.
     */
    private void actionOrNumeric(String input)
    {
        int number = 0;
        // determine if the input is a number
        boolean isInt = true;
        try {
            number = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            isInt = false;
        }

        if(isInt) {
            numericInput(number);
        }
        else {
            processAction(input);
        }
    }

    public boolean isOff()
    {
        return state == State.Off;
    }

    public static void main(String[] args)
    {
        CoffeeMachineSimulation cm = new CoffeeMachineSimulation();

        while(!cm.isOff()) {
            String input = cm.scan.nextLine();
            cm.actionOrNumeric(input);
        }
    }
}

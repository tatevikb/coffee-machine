import java.util.Scanner;

public class CoffeeMachineSimulation {

    private enum State {
        Ready,
        Off,
        SelectCoffee,
        FillWater,
        FillCoffee,
        FillMilk,
        FillCups
    }
    private Scanner scan = new Scanner(System.in);

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

    private State state = State.Ready;

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
     *check to see if the input is integer representation
     */
    private boolean isInteger(String input)
    {
        for (int i = 0; i < input.length(); ++i) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private void action(String input)
    {
        boolean isInt = isInteger(input);

        if(input.equals("remaining")) {
            remaining();
            setReady();
        }
        else if(input.equals("buy")) {
            state = State.SelectCoffee;
            askForNumber("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        }
        else if(isInt && state == State.SelectCoffee) {
            buy(Integer.parseInt(input));
            setReady();
        }
        else if(input.equals("fill")) {
            state = State.FillWater;
            askForNumber("Write how many ml of water do you want to add:");
        }
        else if(isInt && state == State.FillWater) {
            water += Integer.parseInt(input);
            state = State.FillMilk;
            askForNumber("Write how many ml of milk do you want to add:");
        }
        else if(isInt && state == State.FillMilk) {
            milk += Integer.parseInt(input);
            state = State.FillCoffee;
            askForNumber("Write how many grams of coffee beans do you want to add:");
        }
        else if(isInt && state == State.FillCoffee) {
            coffee += Integer.parseInt(input);
            state = State.FillCups;
            askForNumber("Write how many disposable cups of coffee do you want to add:");
        }
        else if(isInt && state == State.FillCups) {
            cups += Integer.parseInt(input);
            setReady();
        }
        else if(input.equals("take")) {
            take();
            setReady();
        }
        else if(input.equals("exit")) {
            state = State.Off;
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
            cm.action(input);
        }
    }
}

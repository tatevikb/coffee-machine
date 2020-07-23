import java.util.Scanner;

public class CoffeeMachineLoop {
    private static final int ESPRESSO = 0;
    private static final int LATTE = 1;
    private static final int CAPPUCCINO = 2;

    private static int[] WATER_FOR_ONE_CUP = new int[3];
    private static int[] MILK_FOR_ONE_CUP = new int[3];
    private static int[] COFFEE_FOR_ONE_CUP = new int[3];
    private static int[] MONEY_FOR_ONE_CUP = new int[3];

    private static int AMOUNT_OF_WATER = 400;
    private static int AMOUNT_OF_MILK = 540;
    private static int AMOUNT_OF_COFFEE = 120;
    private static int AMOUNT_OF_MONEY = 550;
    private static int AMOUNT_OF_CUPS = 9;

    private static Scanner scan = new Scanner(System.in);

    /**
     *  Sets values for water, milk, coffee beans
     * and price for each type of coffee.
     */
    private static void setupMachine()
    {
        WATER_FOR_ONE_CUP[ESPRESSO] = 250;
        COFFEE_FOR_ONE_CUP[ESPRESSO] = 16;
        MILK_FOR_ONE_CUP[ESPRESSO] = 0;
        MONEY_FOR_ONE_CUP[ESPRESSO] = 4;

        WATER_FOR_ONE_CUP[LATTE] = 350;
        COFFEE_FOR_ONE_CUP[LATTE] = 20;
        MILK_FOR_ONE_CUP[LATTE] = 75;
        MONEY_FOR_ONE_CUP[LATTE] = 7;

        WATER_FOR_ONE_CUP[CAPPUCCINO] = 200;
        COFFEE_FOR_ONE_CUP[CAPPUCCINO] = 12;
        MILK_FOR_ONE_CUP[CAPPUCCINO] = 100;
        MONEY_FOR_ONE_CUP[CAPPUCCINO] = 6;

    }

    /**
     * Prints the state of the coffee-machine.
     */
    private static void remaining()
    {
        System.out.println("The coffee machine has:");

        System.out.println(AMOUNT_OF_WATER + " of water");
        System.out.println(AMOUNT_OF_MILK + " of milk");
        System.out.println(AMOUNT_OF_COFFEE + " coffee beans");
        System.out.println(AMOUNT_OF_CUPS + " of disposable cups");
        System.out.println("$" + AMOUNT_OF_MONEY + " of money");

    }

    /**
     * Check for resources to make at list one cup of coffee.
     *
     * @param coffeeType Coffee type code (1 - espresso, 2 - latte, 3 - cappuccino).
     *
     * @return Has resources or not.
     */
    private static boolean hasResources(int coffeeType)
    {
        boolean has = true;

        if(AMOUNT_OF_WATER < WATER_FOR_ONE_CUP[coffeeType]) {
            System.out.println("Sorry, not enough water!");
            has = false;
        }

        if(AMOUNT_OF_COFFEE < COFFEE_FOR_ONE_CUP[coffeeType]) {
            System.out.println("Sorry, not enough coffee!");
            has = false;
        }

        if(AMOUNT_OF_MILK < MILK_FOR_ONE_CUP[coffeeType]) {
            System.out.println("Sorry, not enough milk!");
            has = false;
        }

        if(AMOUNT_OF_CUPS == 0) {
            System.out.println("Sorry, not enough cups!");
            has = false;
        }

        return has;
    }

    private static void buy()
    {
        int coffeeType = readIntegerValue("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        --coffeeType;

        if (hasResources(coffeeType)) {
            System.out.println("I have enough resources, making you a coffee!");

            AMOUNT_OF_WATER -= WATER_FOR_ONE_CUP[coffeeType];
            AMOUNT_OF_MILK -= MILK_FOR_ONE_CUP[coffeeType];
            AMOUNT_OF_COFFEE -= COFFEE_FOR_ONE_CUP[coffeeType];
            AMOUNT_OF_MONEY += MONEY_FOR_ONE_CUP[coffeeType];

            AMOUNT_OF_CUPS--;
        }
    }

    private static void fill()
    {
        AMOUNT_OF_WATER += readIntegerValue("Write how many ml of water do you want to add:");
        AMOUNT_OF_MILK += readIntegerValue("Write how many ml of milk do you want to add:");
        AMOUNT_OF_COFFEE += readIntegerValue("Write how many grams of coffee beans do you want to add:");
        AMOUNT_OF_MONEY += readIntegerValue("Write how many disposable cups of coffee do you want to add:");
    }

    private static void take()
    {
        System.out.println("I gave you $" + AMOUNT_OF_MONEY);
        AMOUNT_OF_MONEY = 0;
    }

    /**
     * Reads an integer from standard input.
     *
     * @param msg Prompt message.
     *
     * @return Integer value.
     */
    private static int readIntegerValue(String msg)
    {
        System.out.println(msg);
        System.out.print("> ");
        return scan.nextInt();
    }

    /**
     * Reads text from standard input.
     *
     * @param msg Prompt message.
     *
     * @return String value.
     */
    private static String readStringValue(String msg)
    {
        System.out.println(msg);
        System.out.print("> ");
        String val = scan.nextLine();

        // ignores empty inputs
        while(val.isEmpty()) {
            val = scan.nextLine();
        }
        return val;
    }

    private enum ActionType {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT
    }

    public static void main(String[] args)
    {
        setupMachine();
        String action = "";

        while (!action.equals("exit")) {
            action = readStringValue("Write action (buy, fill, take, remaining, exit)");

            switch (ActionType.valueOf(action.toUpperCase())) {
                case BUY:
                    buy();
                    break;
                case FILL:
                    fill();
                    break;
                case REMAINING:
                    remaining();
                    break;
                case TAKE:
                    take();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Invalid action '" + action + "'. Try again.");
                    break;
            }
        }

    }
}

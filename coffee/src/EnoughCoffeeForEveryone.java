import java.util.Scanner;

public class EnoughCoffeeForEveryone {

    private static final int WATER_FOR_ONE_CUP = 200;
    private static final int MILK_FOR_ONE_CUP = 50;
    private static final int COFFEE_FOR_ONE_CUP = 15;

    private static Scanner scan = new Scanner(System.in);

    // Prints message and reads the value from command line
    private static int readValueFor(String msg)
    {
        System.out.println(msg);
        System.out.print("> ");

        return scan.nextInt();
    }

    // Counts how many cups you will have with the available resources of milk, water and coffee
    private static int countAmountOfCups(int amountOfWater, int amountOfMilk, int amountOfCoffee)
    {
        int w = amountOfWater / WATER_FOR_ONE_CUP;
        int m = amountOfMilk / MILK_FOR_ONE_CUP;
        int c = amountOfCoffee / COFFEE_FOR_ONE_CUP;

        return Math.min(w, Math.min(m, c));
    }

    /* The method checks, has the coffee machine enough supplies to make the specified amount of coffee?
    And prints the result */
    private static String canMakeRequiredAmount(int cups, int amountOfWater, int amountOfMilk, int amountOfCoffee)
    {
        int canMake = countAmountOfCups(amountOfWater, amountOfMilk, amountOfCoffee);
        int diffCups = canMake - cups;

        if(diffCups == 0)
            return "Yes, I can make that amount of coffee";
        else if(diffCups < 0)
            return "No, I can make only " +  canMake + " cup(s) of coffee";
       return "Yes, I can make that amount of coffee (and even " +  diffCups + " more than that)";
    }

    public static void main(String[] args)
    {
        int availableWater = readValueFor("Write how many ml of water the coffee machine has");
        int availableMilk = readValueFor("Write how many ml of milk the coffee machine has");
        int availableCoffee = readValueFor("Write how many ml of coffee beans the coffee machine has");
        int numberOfCups = readValueFor("Write how many cups of coffee you will need");

        String msg = canMakeRequiredAmount(numberOfCups, availableWater, availableMilk, availableCoffee);
        System.out.println(msg);
    }
}


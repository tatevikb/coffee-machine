import java.util.Scanner;

public class EnoughCoffeeForEveryone {

    private static final int waterForOneCup = 200;
    private static final int milkForOneCup = 50;
    private static final int coffeeForOneCup = 15;

    private static Scanner scan = new Scanner(System.in);

    // Prints message and reads the value from command line
    private static int readValueFor(String msg)
    {
        System.out.print(msg + ":\n> ");
        return scan.nextInt();
    }

    // Counts how many cups you will have with the available resources of milk, water and coffee
    private static int countAmountOfCups(int aw, int am, int ac)
    {
        int w = aw / waterForOneCup;
        int m = am / milkForOneCup;
        int c = ac / coffeeForOneCup;

        return Math.min(w, Math.min(m, c));
    }

    /* The method checks, has the coffee machine enough supplies to make the specified amount of coffee?
    And prints the result */
    private static String canMakeRequiredAmount(int cups, int aw, int am, int ac)
    {
        int canMake = countAmountOfCups(aw, am, ac);
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


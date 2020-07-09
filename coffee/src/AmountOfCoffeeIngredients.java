import java.util.Scanner;

public class AmountOfCoffeeIngredients {
    private static final int waterForOneCup = 200;
    private static final int milkForOneCup = 50;
    private static final int coffeeForOneCup = 15;

    public static void main(String[] args)
    {
        System.out.print("Write how many cups of coffee you will need:\n> ");
        Scanner scan = new Scanner(System.in);
        int cups = scan.nextInt();

        System.out.println("For " + cups + " cups of coffee you will need:");
        int water = amountOfWater(cups);
        int milk = amountOfMilk(cups);
        int coffee = amountOfCoffee(cups);

        System.out.printf("%d ml of water\n%d ml of milk\n%d g of coffee beans\n",
                water, milk, coffee);
    }

    // This method counts the amount of water
    public static int amountOfWater(int num)
    {
        return num * waterForOneCup;
    }

    // This method counts the amount of milk
    public static int amountOfMilk(int num)
    {
        return num * milkForOneCup;
    }

    // This method counts the amount of coffee
    public static int amountOfCoffee(int num)
    {
        return num * coffeeForOneCup;
    }
}

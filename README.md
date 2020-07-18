# coffee-machine

## Amount of coffee Ingredients

The program should calculate how much _water_, _coffee_, 
and _milk_ are necessary to make the specified amount 
of coffee. One cup of coffee made on this coffee machine 
contains 200 ml of _water_, 50 ml of _milk_, and 15 g of 
_coffee beans_.

## Enough coffee for everyone

A real coffee machine never has an infinite supply of water, 
milk, or coffee beans. And if you input a really big number, 
it's almost certain that a real coffee machine wouldn't have 
the supplies needed to make all that coffee.

In this stage, you need to improve the previous program. Now 
you need to input amounts of water, milk, and coffee beans 
that your coffee machine has at the moment.

If the coffee machine has enough supplies to make the specified 
amount of coffee, the program should print "Yes, I can make that 
amount of coffee". If the coffee machine can make more than that, 
the program should output "Yes, I can make that amount of coffee 
(and even N more than that)", where N is the number of additional 
cups of coffee that the coffee machine can make. If the amount 
of resources is not enough to make the specified amount of coffee, 
the program should output "No, I can make only N cup(s) of coffee".

Like in the previous stage, the coffee machine needs 200 ml of 
water, 50 ml of milk, and 15 g of coffee beans to make one cup 
of coffee.

## On a coffee loop

But just one action isn't interesting. Let's improve the program
so it can do multiple actions, one after another. The program 
should repeatedly ask what the user wants to do. If the user types 
"buy", "fill" or "take", then just do what the program did in
the previous step. However, if the user wants to switch off 
the coffee machine, he should type "exit". Then the program 
should terminate. Also, when the user types "remaining", the 
program should output all the resources that the coffee machine has.

Also, do not forget that you can be out of resources for making
coffee. If the coffee machine doesn't have enough resources to 
make coffee, the program should output a message that says it 
can't make a cup of coffee.

And the last improvement to the program at this step—if the user 
types "buy" to buy a cup of coffee and then changes his mind, he 
should be able to type "back" to return into the main cycle.

Remember, that:
* For the espresso, the coffee machine needs 250 ml of water and 
  16 g of coffee beans. It costs $4.
* For the latte, the coffee machine needs 350 ml of water, 75 ml 
  of milk, and 20 g of coffee beans. It costs $7.
* And for the cappuccino, the coffee machine needs 200 ml of water,
  100 ml of milk, and 12 g of coffee. It costs $6.
 
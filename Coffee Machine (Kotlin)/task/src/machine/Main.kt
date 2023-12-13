package machine

class Coffee(
        val name: String,
        val water: Int,
        val milk: Int,
        val coffeeBeans: Int,
        val price: Int)


class CoffeeMachine {
    private var waterAvailable = 400
    private var milkAvailable = 540
    private var coffeeBeansAvailable = 120
    private var cupsAvailable = 9
    private var money = 550

    private val espresso = Coffee("espresso", 250, 0, 16, 4)
    private val latte = Coffee("latte", 350, 75, 20, 7)
    private val cappuccino = Coffee("cappuccino", 200, 100, 12, 6)

    /**
     * Let the user chose what does it want to do :
     * buy a coffee
     * refill the machine
     * take the money
     * see the machine's information
     * exit the machine
     */
    fun chooseAction() {
        // Get the user action
        println("Write action (buy, fill, take, remaining, exit)")
        var choice = readln()

        while (choice != "exit") {
            when (choice) {
                "buy" -> buyCoffee()
                "fill" -> fillMachine()
                "take" -> takeMoney()
                "remaining" -> displayInformation()
            }

            println("Write action (buy, fill, take, remaining, exit)")
            choice = readln()
        }
    }

    /**
     * Display all the information about the machine :
     * The amount of water, milk, coffee beans
     * How many cups it has
     * How much money is stored in the machine
     */
    private fun displayInformation() {
        println("")
        println("""The coffee machine has:
$waterAvailable ml of water
$milkAvailable ml of milk
$coffeeBeansAvailable g of coffee beans
$cupsAvailable disposable cups
$$money of money""")
        println("")
    }

    /**
     * The user chose what king of coffee he wants (Espresso, Latte or Cappuccino)
     * If the machine has enough resources, it will update them
     */
    private fun buyCoffee() {
        println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
        val coffeeChoice = readln()

        when (coffeeChoice) {
            "1" -> {
                if (checkResource(espresso)) {
                    updateResource(espresso)
                }
            }
            "2" -> {
                if (checkResource(latte)) {
                    updateResource(latte)
                }
            }
            "3" -> {
                if (checkResource(cappuccino)) {
                    updateResource(cappuccino)
                }
            }
            "back" -> return
        }
    }

    /**
     * Check if the machine has enough resources to make a coffee
     * @param coffee the coffee to make
     * @return true if it has enough resources
     */
    private fun checkResource(coffee: Coffee) : Boolean {
        val enoughWater = waterAvailable - coffee.water > 0
        val enoughMilk = milkAvailable - coffee.milk > 0
        val enoughCoffeeBeans = coffeeBeansAvailable - coffee.coffeeBeans > 0
        val enoughCups = cupsAvailable > 0

        when {
            !enoughWater -> {
                println("Sorry, not enough water!")
                return false
            }
            !enoughMilk -> {
                println("Sorry, not enough milk!")
                return false
            }
            !enoughCoffeeBeans -> {
                println("Sorry, not enough coffee beans!")
                return false
            }
            !enoughCups -> {
                println("Sorry, not enough cups!")
                return false
            }
            else -> return true
        }
    }

    /**
     * Update the resources (water, milk, coffee beans and cups)
     * @param coffee the coffee it's making
     */
    private fun updateResource(coffee: Coffee) {
        waterAvailable -= coffee.water
        milkAvailable -= coffee.milk
        coffeeBeansAvailable -= coffee.coffeeBeans
        money += coffee.price
        cupsAvailable--
        println("I have enough resources, making you a coffee\n")
    }

    /**
     * Refill the machine :
     * the user enter one number for each resource (water, milk, coffee beans and cups)
     */
    private fun fillMachine() {
        // Get the amount of water, milk and coffee beans for the machine
        println("\nWrite how many ml of water you want to add:")
        val amountWater: Int = readln().toInt()
        println("Write how many ml of milk you want to add:")
        val amountMilk: Int = readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        val amountCoffee: Int = readln().toInt()
        println("Write how many disposable cups you want to add:")
        val amountCups: Int = readln().toInt()

        // Refill the machine
        waterAvailable += amountWater
        milkAvailable += amountMilk
        coffeeBeansAvailable += amountCoffee
        cupsAvailable += amountCups
        println("")
    }

    /**
     * Take all the money stored inside the machine
     */
    private fun takeMoney() {
        println("I gave you \$$money\n")
        money = 0
    }
}

fun main() {
    val myCoffeeMachine = CoffeeMachine()
    myCoffeeMachine.chooseAction()
}
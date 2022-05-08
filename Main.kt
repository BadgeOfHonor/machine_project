package machine

fun main() {
    val cm = Machine(400, 540, 120, 9, 550)

    while (true)
    {
        print("\nWrite action (buy, fill, take, remaining, exit): ")
        when (readln()) {
            "buy" -> {
                print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                when (readln()) {
                    "1" -> { if (cm.isPos(Coffee.Espresso.res)) cm.buy(Coffee.Espresso.res) }
                    "2" -> { if (cm.isPos(Coffee.Latte.res)) cm.buy(Coffee.Latte.res) }
                    "3" -> { if (cm.isPos(Coffee.Cappuccino.res)) cm.buy(Coffee.Cappuccino.res) }
                    "back" -> continue
                }
            }
            "fill" -> {
                print("Write how many ml of water do you want to add: ")
                cm.addWater(readln().toInt())
                print("Write how many ml of milk do you want to add: ")
                cm.addMilk(readln().toInt())
                print("Write how many grams of coffee beans do you want to add: ")
                cm.addBeans(readln().toInt())
                print("Write how many disposable cups of coffee do you want to add: ")
                cm.addCups(readln().toInt())
            }
            "take" -> { println("I gave you \$${cm.machine[4]}"); cm.takeMoney() }
            "remaining" -> { println(cm.toString()) }
            "exit" -> return
        }
    }

}

enum class Coffee ( val res: List<Int>) {
    Espresso(listOf(250, 0, 16, 1, 4)),
    Latte(listOf(350, 75, 20, 1, 7)),
    Cappuccino(listOf(200, 100, 12, 1, 6))
}

class Machine(val water: Int, val milk: Int, val beans: Int, val cups: Int, val money: Int) {

    val machine = mutableListOf(water, milk, beans, cups, money)

    fun buy(coffee: List<Int>) { for (i in 0..3) { machine[i] -= coffee[i] }; machine[4] += coffee[4] }
    fun addWater(n: Int) = run { machine[0] += n }
    fun addMilk(n: Int) = run { machine[1] += n }
    fun addBeans(n: Int) = run { machine[2] += n }
    fun addCups(n: Int) = run { machine[3] += n }
    fun takeMoney() = run { machine[4] = 0 }
    fun isPos(coffee: List<Int>): Boolean {
        var r = true
        for (i in 0..3) {
            if (machine[i] - coffee[i] < 0) {
                println("Sorry, not enough ${
                    when (i) {
                        0 -> "water"
                        1 -> "milk"
                        2 -> "beans"
                        else -> "cups"
                    }
                }!")
                r = false
            }
        }
        if (r) println("I have enough resources, making you a coffee!")
        return r
    }
    override fun toString(): String {
       return "The coffee machine has:" +
               "\n${machine[0]} ml of water" +
               "\n${machine[1]} ml of milk" +
               "\n${machine[2]} g of coffee beans" +
               "\n${machine[3]} disposable cups" +
               "\n\$${machine[4]} of money"
    }
}
package chapter6.exercises

import arrow.core.Tuple2
import arrow.mtl.State
import arrow.mtl.runS
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//tag::init1[]
sealed class Input

object Coin : Input()
object Turn : Input()

data class Machine(val locked: Boolean, val candies: Int, val coins: Int)
//end::init1[]

//tag::init2[]
val update: (Input) -> (Machine) -> Machine = TODO()

fun simulateMachine(inputs: List<Input>):
        State<Machine, Tuple2<Int, Int>> = TODO()
//end::init2[]

/**
 * TODO: Re-enable tests by removing `!` prefix!
 */
class Solution_6_11 : WordSpec({
    "simulateMachine" should {
        "!allow the purchase of a single candy" {
            val actions = listOf(Coin)
            val before = Machine(locked = true, candies = 1, coins = 0)
            val after = Machine(locked = false, candies = 1, coins = 1)
            simulateMachine(actions).runS(before) shouldBe after
        }
        "!allow the redemption of a single candy" {
            val actions = listOf(Turn)
            val before = Machine(locked = false, candies = 1, coins = 1)
            val after = Machine(locked = true, candies = 0, coins = 1)
            simulateMachine(actions).runS(before) shouldBe after
        }
        "!allow purchase and redemption of a candy" {
            val actions = listOf(Coin, Turn)
            val before = Machine(locked = true, candies = 1, coins = 0)
            val after = Machine(locked = true, candies = 0, coins = 1)
            simulateMachine(actions).runS(before) shouldBe after
        }
    }

    "inserting a coin into a locked machine" should {
        "!unlock the machine if there is some candy" {
            val actions = listOf(Coin)
            val before = Machine(locked = true, candies = 1, coins = 0)
            val after = Machine(locked = false, candies = 1, coins = 1)
            simulateMachine(actions).runS(before) shouldBe after
        }
    }
    "inserting a coin into an unlocked machine" should {
        "!do nothing" {
            val actions = listOf(Coin)
            val before = Machine(locked = false, candies = 1, coins = 1)
            val after = Machine(locked = false, candies = 1, coins = 1)
            simulateMachine(actions).runS(before) shouldBe after
        }
    }
    "turning the knob on an unlocked machine" should {
        "!cause it to dispense candy and lock" {
            val actions = listOf(Turn)
            val before = Machine(locked = false, candies = 1, coins = 1)
            val after = Machine(locked = true, candies = 0, coins = 1)
            simulateMachine(actions).runS(before) shouldBe after
        }
    }
    "turning the knob on a locked machine" should {
        "!do nothing" {
            val actions = listOf(Turn)
            val before = Machine(locked = true, candies = 1, coins = 1)
            val after = Machine(locked = true, candies = 1, coins = 1)
            simulateMachine(actions).runS(before) shouldBe after
        }
    }
    "a machine that is out of candy" should {
        "!ignore the turn of a knob" {
            val actions = listOf(Turn)
            val before = Machine(locked = true, candies = 0, coins = 0)
            val after = Machine(locked = true, candies = 0, coins = 0)
            simulateMachine(actions).runS(before) shouldBe after
        }
        "!ignore the insertion of a coin" {
            val actions = listOf(Coin)
            val before = Machine(locked = true, candies = 0, coins = 0)
            val after = Machine(locked = true, candies = 0, coins = 0)
            simulateMachine(actions).runS(before) shouldBe after
        }
    }
})
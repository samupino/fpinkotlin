package chapter2.exercises.ex1

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.collections.immutable.persistentMapOf

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    //tag::init[]
    fun fib(i: Int): Int {
        // iterative version
        // var nums = Pair(0, 1)
        // for (j in i downTo 1) {
        //     nums = Pair(nums.second, nums.first + nums.second)
        // }
        // return nums.first

        tailrec fun helper(counter: Int, p: Pair<Int, Int>): Int =
            if (counter >= 1) helper(
                counter - 1,
                Pair(p.second, p.first + p.second)
            )
            else p.first

        return helper(i, Pair(0, 1))
    }
    //end::init[]

    "fib" should {
        "return the nth fibonacci number" {
            persistentMapOf(
                0 to 0,
                1 to 1,
                2 to 1,
                3 to 2,
                4 to 3,
                5 to 5,
                6 to 8,
                7 to 13,
                8 to 21
            ).forEach { (n, num) ->
                fib(n) shouldBe num
            }
        }
    }
})

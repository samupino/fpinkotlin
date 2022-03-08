package chapter3.exercises.ex21

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun add(xa: List<Int>, xb: List<Int>): List<Int> =
    // the solution implementation is better
    foldRight(
        xa,
        Pair(List.empty<Int>(), xb.reverse())
    ) { a: Int, b: Pair<List<Int>, List<Int>> ->
        val acc = b.first
        val other = b.second
        when (other) {
            Nil -> Pair(Cons(a, acc), Nil)
            is Cons -> Pair(Cons(a + other.head, acc), other.tail)
        }
    }.first
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise21 : WordSpec({
    "list add" should {
        "add elements of two corresponding lists" {
            add(List.of(1, 2, 3), List.of(4, 5, 6)) shouldBe
                List.of(5, 7, 9)
        }
    }
})

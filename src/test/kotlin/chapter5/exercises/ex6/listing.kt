package chapter5.exercises.ex6

import chapter4.None
import chapter4.Option
import chapter4.Some
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise6 : WordSpec({

    //tag::init[]
    fun <A> Stream<A>.headOrNullSimple(): A? = when(this) {
        Empty -> null
        is Cons<A> -> this.head()
    }

    fun <A> Stream<A>.headOrNull(): A? =
        foldRight({ null }) { a: A, tailEvaluator: () -> A? -> a }
    //end::init[]

    "Stream.headOption" should {
        "return some first element from the stream if it is not empty" {
            val s = Stream.of(1, 2, 3, 4)
            s.headOrNull() shouldBe 1
        }

        "return none if the stream is empty" {
            Stream.empty<Int>().headOrNull() shouldBe null
        }
    }
})

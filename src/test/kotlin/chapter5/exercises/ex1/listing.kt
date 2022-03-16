package chapter5.exercises.ex1

import chapter3.List
import chapter3.Nil
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import chapter3.Cons as ConsL

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    //tag::init[]
    fun <A> Stream<A>.toList(): List<A> {
        tailrec fun go(acc: List<A>, stream: Stream<A>): List<A> =
            when (stream) {
                Empty -> acc
                is Cons<A> -> go(ConsL(stream.head(), acc), stream.tail())
            }
        return go(Nil, this).reverse()
    }
    //end::init[]

    "Stream.toList" should {
        "force the stream into an evaluated list" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.toList() shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})

package chapter3.exercises.ex11

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldLeft
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> reverse(xs: List<A>): List<A> {
    // tailrec fun loop(partial: List<A>, xs: List<A>): List<A> = when (xs) {
    //     Nil -> partial
    //     is Cons -> loop(Cons(xs.head, partial), xs.tail)
    // }
    // return loop(Nil, xs)
    return foldLeft(xs, Nil as List<A>) { acc, a -> Cons(a, acc) }
}
// end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise11 : WordSpec({
    "list reverse" should {
        "reverse list elements" {
            reverse(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(5, 4, 3, 2, 1)
        }
    }
})

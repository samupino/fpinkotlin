package chapter5.exercises.ex14

import chapter4.Some
import chapter5.Stream
import chapter5.exercises.ex13.map
import chapter5.exercises.ex13.takeWhile
import chapter5.exercises.ex13.zipAll
import chapter5.exercises.ex4.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

//TODO: Enable tests by removing `!` prefix
class Exercise14 : WordSpec({

    //tag::startswith[]
    fun <A> Stream<A>.startsWith(that: Stream<A>): Boolean =
        this.zipAll(that)
            .takeWhile { (_, ob) -> !ob.isEmpty() }
            .forAll { (oa, ob) -> oa == ob }
    //end::startswith[]

    "Stream.startsWith" should {
        "detect if one stream is a prefix of another" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(1, 2)
            ) shouldBe true
        }
        "detect if one stream is not a prefix of another" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(2, 3)
            ) shouldBe false
        }
        "detect if the prefix is longer than the original stream" {
            Stream.of(1, 2, 3).startsWith(
                Stream.of(1, 2, 3, 4)
            ) shouldBe false
        }
    }
})

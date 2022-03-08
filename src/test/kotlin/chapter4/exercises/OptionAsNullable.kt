package chapter4.exercises

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter4.foldRight
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

fun <A, B> A?.map(f: (A) -> B): B? =
    this?.let(f)

fun <A, B> A?.flatMap(f: (A) -> B?): B? =
    this?.let(f)

fun <A> A?.getOrElse(default: () -> A): A =
    this ?: default()

fun <A> A?.orElse(ob: () -> A?): A? =
    this ?: ob()

fun <A> A?.filter(f: (A) -> Boolean): A? =
    this?.takeIf(f)

fun <A, B> lift(f: (A) -> B): (A?) -> B? =
    { a: A? -> a.map(f) }

// with nested lets
fun <A, B, C> map2(a: A?, b: B?, f: (A, B) -> C): C? =
    a?.let { sa -> b?.let { sb -> f(sa, sb) } }

// with for-comprehension (?)
fun <A, B, C, D> map3(a: A?, b: B?, c: C?, f: (A, B, C) -> D): D? {
    a ?: return null
    b ?: return null
    c ?: return null
    return f(a, b, c)
}

fun <A, B> traverse(xa: List<A>, f: (A) -> B?): List<B>? =
    xa.foldRight(Nil) { a: A, acc: List<B>? ->
        map2(f(a), acc) { b, sAcc -> Cons(b, sAcc) }
    }

fun <A> sequence(xs: List<A?>): List<A>? =
    traverse(xs) { it }

class Exercise1 : WordSpec({

    val none: Int? = null

    val some: Int? = 10

    "option map" should {
        "transform an option of some value" {
            some.map { it * 2 } shouldBe 20
        }
        "pass over an option of none" {
            none.map { it * 10 } shouldBe null
        }
    }

    "option flatMap" should {
        """apply a function yielding an option to an
            option of some value""" {
            some.flatMap { a ->
                a.toString()
            } shouldBe "10"
        }
        "pass over an option of none" {
            none.flatMap { a ->
                a.toString()
            } shouldBe null
        }
    }

    "option getOrElse" should {
        "extract the value of some option" {
            some.getOrElse { 0 } shouldBe 10
        }
        "return a default value if the option is none" {
            none.getOrElse { 10 } shouldBe 10
        }
    }

    "option orElse" should {
        "return the option if the option is some" {
            some.orElse { 20 } shouldBe some
        }
        "return a default option if the option is none" {
            none.orElse { 20 } shouldBe 20
        }
    }

    "option filter" should {
        "return some option if the predicate is met" {
            some.filter { it > 0 } shouldBe some
        }
        "return a none option if the predicate is not met" {
            some.filter { it < 0 } shouldBe null
        }
    }
})

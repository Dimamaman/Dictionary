package uz.gita.dimadictionary.util

fun <T> T.myApply(block: T.() -> Unit) {
    block(this)
}
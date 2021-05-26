package kometa.util

fun <T> List<T>.peek(): T? = lastOrNull()

fun <T> MutableList<T>.push(t: T) {
    add(t)
}

fun <T> MutableList<T>.pop(): T? = if (isEmpty()) null else removeLast()

fun <T> Any?.cast(): T = this as T
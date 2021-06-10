package kometa.util

fun <T> List<T>.peek(): T? = lastOrNull()

fun <T> MutableList<T>.push(t: T) {
    add(t)
}

fun <T> MutableList<T>.pop(): T? = if (isEmpty()) null else removeLast()

fun <T> Any?.cast(): T = this as T

fun <T, R: T> List<T>.reduce2(op: (T, T, T) -> R): R {
    singleOrNull()?.let { return it.cast() }

    var res = op(get(0), get(1), get(2))
    var tail = drop(3)
    while (tail.size > 1) {
        res = op(res, tail[0], tail[2])
        tail = tail.drop(2)
    }
    return res
}
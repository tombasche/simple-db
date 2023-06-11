package utils

sealed class Either<out T>
data class Success<out T>(val value: T) : Either<T>()
data class Failure(val error: String) : Either<Nothing>()


fun <T> Either<T>.get(): T = (this as Success).value
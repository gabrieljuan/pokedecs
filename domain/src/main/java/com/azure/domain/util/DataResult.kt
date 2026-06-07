package com.azure.domain.util

sealed interface DataResult<out T> {
    data class Success<T>(val value: T) : DataResult<T>
    data class Exception(val throwable: Throwable) : DataResult<Nothing>
}

inline fun <T : Any> tryGetDataCall(dataCall: () -> T): DataResult<T> = try {
    val value = dataCall()
    DataResult.Success(value)
} catch (ex: Throwable) {
    DataResult.Exception(ex)
}

inline fun <T : Any, V : Any> DataResult<V>.mapToDomain(mapper: (V) -> T): DataResult<T> {
    return when (this) {
        is DataResult.Success -> DataResult.Success(mapper(value))
        is DataResult.Exception -> DataResult.Exception(this.throwable)
    }
}
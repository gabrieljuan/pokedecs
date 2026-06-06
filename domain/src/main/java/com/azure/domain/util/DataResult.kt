package com.azure.domain.util

sealed interface DataResult<out T> {
    data class Success<T>(val value: T) : DataResult<T>
    data class Exception(val throwable: Throwable) : DataResult<Nothing>
}
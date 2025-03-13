package com.nativeprojects.utilities

sealed class ResourceState <T> { // Managing loading state
    class Loading<T> : ResourceState<T>()
    data class Success<T> (val data: T): ResourceState<T>()
    data class Error<T> (val error: String): ResourceState<T>()
}

package com.sagayathri.data.async

sealed class AsyncEvents {
    data object Success: AsyncEvents()
    data object Failed: AsyncEvents()
    data object Loading: AsyncEvents()
}
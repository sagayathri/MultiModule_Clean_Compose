package com.sagayathri.data.utils

interface Mapper<From, To> {
    fun map(from: From): To
}
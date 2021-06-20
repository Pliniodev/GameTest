package com.pliniodev.gametest.utils

interface Mapper<S, T> {
    fun map(source: List<S>): List<T>
}
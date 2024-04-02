package com.ovidiucristurean.kmpsportcomposables

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
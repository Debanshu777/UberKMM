package com.debanshu777.uberkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.aozora.hlsmui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
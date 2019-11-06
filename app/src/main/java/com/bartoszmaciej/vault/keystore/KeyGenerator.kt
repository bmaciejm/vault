package com.bartoszmaciej.vault.keystore

interface KeyGenerator<out T> {
  fun generate(alias: String): T
}
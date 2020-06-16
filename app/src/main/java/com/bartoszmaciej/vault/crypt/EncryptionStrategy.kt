package com.bartoszmaciej.vault.crypt

interface EncryptionStrategy<T> {
  fun encrypt(): T
}
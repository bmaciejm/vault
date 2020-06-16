package com.bartoszmaciej.vault.crypt

interface DecryptionStrategy<T> {
  fun decrypt(): T
}
package com.bartoszmaciej.cert

import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.Base64

object PeerExtractor {

  fun extract(certificate: File): String =
    FileInputStream(certificate).use {
      val x509Certificate: X509Certificate = CertificateFactory.getInstance("X509").generateCertificate(it)
        as X509Certificate

      val publicKeyEncoded = x509Certificate.publicKey.encoded
      val md = MessageDigest.getInstance("SHA-256")

      val publicKeySha256 = md.digest(publicKeyEncoded)
      val publicKeyShaBase256 = Base64.getEncoder().encode(publicKeySha256)

      return "sha256/$publicKeyShaBase256"
    }
}
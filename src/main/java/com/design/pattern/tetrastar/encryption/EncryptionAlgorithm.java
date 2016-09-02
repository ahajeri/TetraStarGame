package com.design.pattern.tetrastar.encryption;

public interface EncryptionAlgorithm {

    String encrypt(String dataToEncrypt);

    String decrypt(String dataToDecrypt);

}

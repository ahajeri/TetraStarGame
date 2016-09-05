/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.encryption;

import java.util.HashMap;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;

/**
 *
 * @author Akshata, Rachna and Shweta. Factory Design pattern
 */
public class EncryptionAlgorithmFactory {

    static HashMap<String, EncryptionAlgorithm> strategyMap = new HashMap<String, EncryptionAlgorithm>();

    public static void addStrategy(String key, EncryptionAlgorithm strategy) {
        strategyMap.put(key, strategy);
    }

    public static void loadStrategies() {
        addStrategy("simpleEncryption", new SimpleEncryptionAlgorithm());
        addStrategy("null", new NullEncryptionAlgorithm());
    }

    public static EncryptionAlgorithm getEncryptionAlgorithmForStrategy(EncryptionStrategy encryptionStrategy) {
        loadStrategies();
        return strategyMap.get(encryptionStrategy.getStrategyName());
    }

}

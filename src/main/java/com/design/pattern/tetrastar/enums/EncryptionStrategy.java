package com.design.pattern.tetrastar.enums;

public enum EncryptionStrategy {

    SIMPLE("simpleEncryption"),
    NULL("null");

    private String strategyName;

    private EncryptionStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public static EncryptionStrategy getEncryptionStrategyFromStrategyName(String sName) {
        if(sName != null) {
            for(EncryptionStrategy e : values()) {
                if(e.getStrategyName().equalsIgnoreCase(sName)) {
                    return e;
                }
            }
        }
        return null;
    }

}

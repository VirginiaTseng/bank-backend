package com.virginiabank.bankdemo.tools;

public enum TransactionType {
    DEPOSIT(1),  // 存款
    WITHDRAW(2); // 取款

    private final int code; // 定义对应的整数值

    // 构造函数
    TransactionType(int code) {
        this.code = code;
    }

    // 获取 code 的方法
    public int getCode() {
        return code;
    }

    // 根据 code 获取枚举类型的方法
    public static TransactionType fromCode(int code) {
        for (TransactionType type : TransactionType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}

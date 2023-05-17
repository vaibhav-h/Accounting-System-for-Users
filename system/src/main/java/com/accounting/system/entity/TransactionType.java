package com.accounting.system.entity;

public enum TransactionType{
    debit("debit"), credit ("credit");

    String type;

    TransactionType(String type) {
        this.type=type;
    }

}

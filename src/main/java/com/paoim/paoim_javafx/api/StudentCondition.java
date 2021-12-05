package com.paoim.paoim_javafx.api;

public enum StudentCondition {
    ZDROWY {
        @Override
        public String toString() {
            return "zdrowy";
        }
    },
    CHORY {
        @Override
        public String toString() {
            return "chory";
        }
    }
}

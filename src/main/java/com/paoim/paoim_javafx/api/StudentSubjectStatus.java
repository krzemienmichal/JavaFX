package com.paoim.paoim_javafx.api;

public enum StudentSubjectStatus {
    SINGED_IN{
        @Override
        public String toString() {
            return "zapisany";
        }
    },
    SIGNED_OUT {
        @Override
        public String toString() {
            return "wypisany";
        }
    },
    RELOCATED {
        @Override
        public String toString() {
            return "przepisany";
        }
    },
    WAITING {
        @Override
        public String toString() {
            return "oczekujacy na zapisanie";
        }
    },
    NOT_SIGNED_IN{
        @Override
        public String toString(){
            return "nie zapisany";
        }
    }
}

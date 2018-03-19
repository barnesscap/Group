package com.sch.igor;

public class ExceededAmountException extends Exception {
    @Override
    public String getMessage() {
        return " Possible amount is exceed";
    }
}

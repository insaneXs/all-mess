package com.insanexs.mess.javap;

public class JavapTest {

    protected static final String VAR_CONSTANT = "CONSTANT";

    private volatile int intField;

    private int[] intArraysField;

    private String strField;

    public JavapTest(){

    }

    public void publicMethod(){

    }

    protected String protectedReturnStrMethod(){
        return strField;
    }

    private synchronized void privateSynchronizedMethod(int intArgs){
        intField = intArgs;
    }
}

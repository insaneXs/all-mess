package com.insanexs.mess.nullcompares;

/**
 * @Author: insaneXs
 * @Description:
 * @Date: Create at 2019-12-04
 */
public class NullCompares {

    public static void main(String[] args){

        testNormalNull();

        testNullStr();

        testNullObject();

        testNullWrapper();

    }

    protected static void testNormalNull(){
        //compile failed, null can not compare int
//        System.out.println(null == 1);

        //can execute, print false
        System.out.println("null type compare with object: " + (null == new Object()));
        //can execute, print false
        System.out.println("object compare with null type: " + (null == new Object()));
    }

    protected static void testNullStr(){
        String nullStr = null;
        //print false
        System.out.println("null str compare with empty str " + (nullStr == ""));
        System.out.println("empty str compare with null str " + ("" == nullStr));
    }

    protected static void testNullObject(){
        Object nullObject = null;

        System.out.println("null object compare with object " + (nullObject == new Object()));
        System.out.println("object compare with null object " + (new Object() == nullObject));
    }

    protected static void testNullWrapper(){
        Long nullLong = null;

        //throw exception
        try {
            System.out.println("null long wrapper compare with primitive " + (nullLong == 1));
        } catch (Exception e) {
            System.err.println("null long wrapper compare with primitive throws exception");
        }

        //throw exception
        try {
            System.out.println("primitive compare with primitive long wrapper " + (1 == nullLong));
        } catch (Exception e) {
            System.err.println("primitive compare with null long wrapper throws exception");
        }

        //print false
        try {
            System.out.println("null long wrapper compare with new Object " + (nullLong == new Object()));
        } catch (Exception e) {
            System.err.println("null long wrapper compare with new Object throws exception");
        }

        //print false
        try {
            System.out.println("new Object compare with null long wrapper" + (new Object() == nullLong));
        } catch (Exception e) {
            System.err.println("new Object compare with null long wrapper");
        }

        //print false
        try {
            System.out.println("null long wrapper compare with long wrapper " + (nullLong == new Long(1)));
        } catch (Exception e) {
            System.err.println("null long wrapper compare with long wrapper throws exception");
        }

        //print false
        try {
            System.out.println("long wrapper compare with null long wrapper " + (new Long(1) == nullLong));
        } catch (Exception e) {
            System.err.println("long wrapper compare with null long wrapper throws exception");
        }

        //print true
        try {
            System.out.println("null long wrapper compare with itself " + (nullLong == nullLong));
        } catch (Exception e) {
            System.err.println("null long wrapper compare with itself throws exception");
        }

        Long anotherNullLong = null;
        try {
            System.out.println("null long wrapper compare with another null long wrapper " + (nullLong == anotherNullLong));
        } catch (Exception e) {
            System.err.println("null long wrapper compare with another null long wrapper throws exception");
        }
    }
}

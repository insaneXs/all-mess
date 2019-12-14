package com.insanexs.mess.collection;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-14
 */
public class MapPutNullValue {

    public static void main(String[] args){
        hashtablePutNullKey();

        hashtablePutNullValue();

        hashMapPutNullKey();

        hashMapPutNullValue();
    }

    protected static void hashtablePutNullKey(){
        System.out.println("Test Hashtable Put Null Key");

        Hashtable<String, String> table = new Hashtable<>();
        try {
            table.put(null, "Value");

            if(table.get(null).equals("Value")){
                System.out.println("Test Hashtable Put Null Key Success");
            }else{
                System.out.println("Test Hashtable Put Null Key failed");
            }
        } catch (Exception e) {
            System.out.println("Test Hashtable CAN NOT PUT NULL VALUE");
        }

    }

    protected static void hashtablePutNullValue(){
        System.out.println("Test Hashtable Put Null Value");

        Hashtable<String, String> hashtable = new Hashtable<>();
        try {
            hashtable.put("Key", null);

            if(null == hashtable.get("Key")){
                System.out.println("Test Hashtable Put Null Key Success");
            }else{
                System.out.println("Test Hashtable Get Null Value failed");
            }
        } catch (Exception e) {
            System.out.println("Test Hashtable CAN NOT PUT NULL KEY");
        }

    }

    protected static void hashMapPutNullKey(){
        System.out.println("TEST Hash map put null key");
        HashMap<String, String> hashMap = new HashMap<String, String>();

        try {
            hashMap.put(null, "Value");

            if("Value".equals(hashMap.get(null))){
                System.out.println("HashMap test put Null Key Success");
            }else{
                System.out.println("HashMap test put Null Key Fail");
            }
        } catch (Exception e) {
            System.out.println("HashMap test CAN NOT PUT NULL KEY");
        }
    }

    protected static void hashMapPutNullValue(){
        System.out.println("TEST Hash map put null value");
        HashMap<String, String> hashMap = new HashMap<String, String>();

        try {
            hashMap.put("Key", null);

            if(null == (hashMap.get("Key"))){
                System.out.println("HashMap test put Null value Success");
            }else{
                System.out.println("HashMap test put Null value Fail");
            }
        } catch (Exception e) {
            System.out.println("HashMap test CAN NOT PUT NULL VALUE");
        }
    }
}

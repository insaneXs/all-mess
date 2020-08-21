package com.insanexs.mess.sugar;

/**
 * @ClassName StringSwitch
 * @Description TODO
 * @Author xieshang
 * @Date 2020/7/19 11:59 上午
 */
public class StringSwitch {

    public void testStringSwitch(String str){

        switch (str){
            case "Aa":
                System.out.println("Aa");
            case "BB":
                System.out.println("BB");
                break;
            default:
                System.out.println("Unknown");
        }
    }

    public static void main(String[] args){
        String str = "abcd";
        new StringSwitch().testStringSwitch(str);
    }
}

package com.insanexs.mess.innerclass;

/**
 * @ClassName OuterClass
 * @Description TODO
 * @Author xieshang
 * @Date 2020/1/18 12:59 PM
 */
public class OuterClass {



    public InnerClass newInnerClass(){
        return new InnerClass();
    }

    public class InnerClass{

        protected OuterClass getOutClass(){
            return OuterClass.this;
        }

    }

    public static void main(String[] args){

        //Case 1

        OuterClass out = new OuterClass();

        InnerClass inner = out.newInnerClass();

        System.out.println("Inner.getOutClass().equals(out) => " + inner.getOutClass().equals(out));

        //Case 2 Create Inner Class without outer class, compile failed!!!
        //InnerClass innerClassWithoutOutClass = new InnerClass();

    }
}

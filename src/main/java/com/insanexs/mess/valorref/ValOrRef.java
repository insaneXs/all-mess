package com.insanexs.mess.valorref;

/**
 * @Author: insaneXs
 * @Description:
 * @Date: Create at 2019-12-06
 */
public class ValOrRef {

    public static void main(String[] args){
        System.out.println("SCENE 1:--------------------");
        scene1();
        System.out.println("SCENE 2:--------------------");
        scene2();
        System.out.println("SCENE 3:--------------------");
        scene3();
    }

    protected static void scene1(){
        int argument = 1;
        incrVal(argument);
        System.out.println("After method invoke, argument: " + argument);
    }

    protected static void scene2(){
        Foo argument = new Foo("original");
        changeStr(argument);
        System.out.println("After method invoke, argument: " + argument);
    }


    protected static void scene3(){
        Foo argument = new Foo("original");
        createNewInstance(argument);
        System.out.println("After method invoke, argument: " + argument);
    }

    private static void incrVal(int parameter){
        parameter = parameter + 1;
        System.out.println("parameter: " + parameter);
    }

    private static void changeStr(Foo parameter){
        parameter.setStr("changed");
        System.out.println("parameter: " + parameter);
    }

    private static void createNewInstance(Foo parameter){
        parameter = new Foo("Brand New");
        System.out.println("parameter: " + parameter);
    }


    static class Foo{
        private String str;

        public Foo(String str){
            this.str = str;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }
}

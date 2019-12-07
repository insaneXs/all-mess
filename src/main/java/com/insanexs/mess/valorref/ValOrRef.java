package com.insanexs.mess.valorref;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-06
 */
public class ValOrRef {

    public static void main(String[] args){
        int val = 1;
        incrVal(val);
        System.out.println("after incrVal: " + val);

        Foo foo = new Foo("original");
        changeStr(foo);
        System.out.println(foo);

        createNewInstance(foo);
        System.out.println(foo);
    }

    protected static void incrVal(int val){
        val = val + 1;
        System.out.println("incrVal method: " + val);
    }

    protected static void changeStr(Foo foo){
        foo.setStr("changed");
        System.out.println(foo);
    }

    protected static void createNewInstance(Foo foo){
        foo = new Foo("Brand New");
        System.out.println(foo);
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

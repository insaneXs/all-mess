package com.insanexs.mess.parameterized;

/**
 * @ClassName UpAndDownBoundsTest
 * @Description TODO
 * @Author xieshang
 * @Date 2020/5/7 10:28 上午
 */
public class UpAndDownBoundsTest {

    public static void main(String[] args){
        test();


        /******SCENE 1 UP BOUNDS **********/
        Plate<? extends Fruit> p1 = new Plate<Apple>();
        //Compile Error
//        p1.add(new Apple());
        Fruit f = p1.get();

        //Plate<Apple> applePlate = p1;

        /*****SCENE 2 DOWN BOUNDS**********/
        Plate<? super  Fruit> p2 = new Plate();
        p2.add(new Apple());
        //Food food = p2.get();

        /*****SCENE 3*********************/

        FruitPlate fruitPlate = new FruitPlate();

        fruitPlate.add(new Banana());

        Fruit fruit = fruitPlate.get();

        /*****SCENE 4********************/
        Plate<Fruit> p3 = new Plate<>();
        Fruit fruit1 = p3.get();
        p3.add(fruit1);
    }

    protected static void test(){
        Plate<Apple> applePlate = new Plate<>();
        Plate<Fruit> fruitPlate = new Plate<>();
        Plate<Food> foodPlate = new Plate<>();

        Plate<? extends Fruit> upBoundsFruitPlate;
        upBoundsFruitPlate = applePlate;
        upBoundsFruitPlate = fruitPlate;
        //compile error
        //upBoundsFruitPlate = foodPlate;


        Plate<? super Fruit> downBoundsFruitPlate;
        downBoundsFruitPlate = foodPlate;
        downBoundsFruitPlate = fruitPlate;
        //compile error
        //downBoundsFruitPlate = applePlate;
    }
}

interface Plant{

}

class Food{

}


class Fruit extends Food implements Plant {

}

class Apple extends Fruit{

}

class Banana extends Fruit{

}

class Plate<T>{
    T t;
    T get(){
        return t;
    }

    void add(T t){
        this.t = t;
    }
}

class FruitPlate<T extends Fruit> extends  Plate{

    T fruit;

    public void add(T fruit){
       this.fruit = fruit;
    }

    @Override
    public Fruit get(){
        return fruit;
    }
}


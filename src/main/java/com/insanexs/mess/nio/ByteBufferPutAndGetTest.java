package com.insanexs.mess.nio;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @ClassName ByteBufferPutAndGetTest
 * @Description 测试ByteBuffer put/get时 position和limit的变化
 * @Author insaneXs
 * @Date 2020/8/28
 */
public class ByteBufferPutAndGetTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        printByteBufferInfo(byteBuffer);

        byteBuffer.put("你好".getBytes());
        printByteBufferInfo(byteBuffer);

        byteBuffer.flip();
        printByteBufferInfo(byteBuffer);

        while(byteBuffer.remaining() > 0){
            byteBuffer.get();
            printByteBufferInfo(byteBuffer);
        }
    }

    static class foo{
        boo b;
        public foo(boo b){
            this.b = b;
        }
    }

    static class boo{

    }


    protected static void printByteBufferInfo(ByteBuffer buffer) throws NoSuchFieldException, IllegalAccessException {

        Field markField = Buffer.class.getDeclaredField("mark");
        markField.setAccessible(true);
        int mark = markField.getInt(buffer);

        Field positionField = Buffer.class.getDeclaredField("position");
        positionField.setAccessible(true);
        int position = positionField.getInt(buffer);

        Field limitField = Buffer.class.getDeclaredField("limit");
        limitField.setAccessible(true);
        int limit = limitField.getInt(buffer);

        Field capacityField = Buffer.class.getDeclaredField("capacity");
        capacityField.setAccessible(true);
        int capacity = capacityField.getInt(buffer);

        System.out.println("mark[" + mark +"] position[" + position + "] limit[" + limit + "] capacity[" + capacity + "]");

    }
}

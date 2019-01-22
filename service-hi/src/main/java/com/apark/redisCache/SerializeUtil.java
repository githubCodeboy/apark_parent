package com.apark.redisCache;

import java.io.*;

public class SerializeUtil {


    /**
     7      *
     8      * 序列化
     9      */
     public static byte[] serialize(Object obj){

                 ObjectOutputStream oos = null;
                ByteArrayOutputStream baos = null;

                 try {
                        //序列化
                        baos = new ByteArrayOutputStream();
                        oos = new ObjectOutputStream(baos);

                        oos.writeObject(obj);
                        byte[] byteArray = baos.toByteArray();
                       return byteArray;

                   } catch (IOException e) {
                       e.printStackTrace();
                    }
                return null;
           }

            /**
 31      *
 32      * 反序列化
 33      * @param bytes
 34      * @return
 35      */
            public static Object unSerialize(byte[] bytes){

              ByteArrayInputStream bais = null;

                try {
                        //反序列化为对象
                        bais = new ByteArrayInputStream(bytes);
                        ObjectInputStream ois = new ObjectInputStream(bais);
                        return ois.readObject();

                    } catch (Exception e) {
                        e.printStackTrace();
                   }
                return null;
           }

}

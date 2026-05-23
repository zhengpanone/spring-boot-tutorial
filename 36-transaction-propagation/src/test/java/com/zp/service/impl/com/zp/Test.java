package com.zp.service.impl.com.zp;

import com.zp.pojo.User1;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        long round = Math.round(11.5);
        System.out.println(round);


        User1 user1 = new User1();
        user1.setId(1);
        user1.setName("张三");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./txt"));
        objectOutputStream.writeObject(user1);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./txt"));
        Object o = objectInputStream.readObject();
        if(o instanceof User1){
            System.out.println(o);
        }else{
            System.out.println(o);
        }

    }
}

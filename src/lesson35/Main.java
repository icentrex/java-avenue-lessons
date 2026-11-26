package lesson35

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cat> cats = new ArrayList<>();

        Cat cat1 = new Cat("Барсик", 2);
        cats.add(cat1);
        Cat cat2 = new Cat("Барсик", 2);

        if (cat1 == cats.get(0)){
            System.out.println("Они одинаковы");
        }else {
            System.out.println("Они разные");
        }

    }

}


class Cat{
    String name;
    int age;

    Cat(String name, int age){
        this.name = name;
        this.age = age;
    }
}

/*public class Main {
    public static void main(String[] args) {
        *//*MyThread thread1 = new MyThread("Задача 1");
        MyThread thread2 = new MyThread("Задача 2");
        MyThread thread3 = new MyThread("Задача 3");
        thread1.start();
        thread2.start();
        thread3.start();*//*
        *//*Thread thread1 = new Thread(new MyRunnable("Задача 1"));
        Thread thread2 = new Thread(new MyRunnable("Задача 2"));
        Thread thread3 = new Thread(new MyRunnable("Задача 3"));
        thread1.start();
        thread2.start();
        thread3.start();*//*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("Задача 1 - " +i+"%");
                }
            }
        });

        Thread t1 = new Thread(()->{
            for (int i = 1; i <= 100; i++) {
                System.out.println("Задача 2 - " +i+"%");
            }
        });
        t.start();
        t1.start();
    }
}*/

/*
class MyRunnable implements Runnable {
    String taskName;
    public MyRunnable(String taskName) {
        this.taskName = taskName;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(taskName+" - "+i+"%");
        }
    }
}

class MyThread extends Thread {
    String taskName;
    public MyThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(taskName+" - "+i+"%");
        }
    }
}*/

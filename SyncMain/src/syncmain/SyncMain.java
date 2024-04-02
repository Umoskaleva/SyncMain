package syncmain;

class Data {
    int count = 0;
    static int countSt = 0;
}

class MyThread implements Runnable {
    private Data obj;

    MyThread(Data d) {
        obj = d;
        new Thread(this).start();
    }

    void Add() {
        try {
            Thread.sleep(5);
            synchronized (obj) { // synchronized - только один поток одновременно может выполнить код внутри
                int n = obj.count;
                n++;
                Thread.sleep(5);
                obj.count = n;
            }
        } catch (InterruptedException ex) {
        }
    }

    static void AddStatic() {
        try {
            Thread.sleep(5);
            synchronized (Data.class) { // synchronized - только один поток одновременно может выполнить код внутри
                int n = Data.countSt;
                n++;
                Thread.sleep(5);
                Data.countSt = n;
            }
        } catch (InterruptedException ex) {
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) Add();
        for (int i = 0; i < 10; i++) AddStatic();
    }
}

public class SyncMain {

    public static void main(String[] args) throws Exception {
        Data d = new Data();
        MyThread t1 = new MyThread(d); // первый поток, который стартует из конструктора MyThread и увеличивает обьект d
        MyThread t2 = new MyThread(d);// второй поток, который стартует из конструктора MyThread и увеличивает обьект d
//        MyThread t3 = new MyThread(d);// третий поток, который стартует из конструктора MyThread и увеличивает обьект d
//        MyThread t4 = new MyThread(d); // 4й поток, который стартует из конструктора MyThread и увеличивает обьект d
        Thread.sleep(3000);
        System.out.println(d.count);
        System.out.println(Data.countSt);

    }
}

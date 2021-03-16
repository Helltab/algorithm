package com.helltab.dynamic;

import com.helltab.util.MyUtil;

public class Fib {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            MyUtil.test(a -> {
//                new Thread(new Fibonacci()).start();
                System.out.print(finalI + "\t " + fib(finalI) + "\t");
            });
        }

    }

    public static int fib(int n) {
        if (n <= 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}

class Fibonacci implements Runnable {
    public static int num = 2;   //用于记录角标，静态变量实现多线程共享
    public int numRe;     //用于记录角标，常量实现线程内角标持久化
    public static int a = 0;     //f[n-2]
    public static int b = 1;     //f[n-1]
    public int c;          //f[n]

    public Fibonacci() {
        c = a + b;
        a = b;
        b = c;
        numRe = num++;
    }

    @Override
    public void run() {
        System.out.print("F[" + numRe + "]=" + c + "\t");
        Thread.yield();
    }

}
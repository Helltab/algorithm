package com.helltab.util;


import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Slf4j
public class MyUtil {
    public static void test(Consumer<Long> consumer) {
        long start = System.nanoTime();
        consumer.accept(start);
        long end = System.nanoTime() - start;
        System.out.println(end + "ns : " + end / 1000000 + "ms : " + end / 1000000000 + "s");
    }

    public static Consumer<Object> visit = data -> {
        System.out.print(ColorUtil.colorFontB(data + "\t", ColorCons.F_BLUE_H));
    };

    public static void test(Predicate<Long> predicate, String testTitle) {
        System.out.println(ColorUtil.colorFont("\n开始测试: " + testTitle, ColorCons.F_G));
        System.out.println(ColorUtil.colorFont("------------------------------------", ColorCons.F_B_H));
        long start = System.nanoTime();
        boolean result = predicate.test(start);
        long res = System.nanoTime() - start;
        System.out.println(ColorUtil.colorFont("\n------------------------------------", ColorCons.F_B_H));
        System.out.print(ColorUtil.colorFont("结束测试, 结果: ", ColorCons.F_G));
        System.out.print(ColorUtil.colorFont(result + "", ColorCons.F_Y));
        System.out.print(ColorUtil.colorFont(" 耗时: ", ColorCons.F_G));
        System.out.println(ColorUtil.colorFont(res + "ns", ColorCons.F_R));
    }
    public static void visitRecursion(Object info, boolean in) {
        if(in) {
            printIndent(count++);
            System.out.print("in " + info);
        } else {
            printIndent(count--);
            System.out.print("return " + info);
        }
    }
    // 全局变量，记录递归函数的递归层数
    static int count = 0;

    // 输入 n，打印 n 个 tab 缩进
    static void printIndent(int n) {
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("   ");
        }
//        System.out.println(ColorUtil.colorFont((System.nanoTime() - start) + "ns", ColorCons.F_R));
    }
}

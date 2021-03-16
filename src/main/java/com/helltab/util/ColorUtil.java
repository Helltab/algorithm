package com.helltab.util;

public class ColorUtil {
    public static void main(String[] args) {
        System.out.println(colorFont("hello", ColorCons.F_W, ColorCons.B_BLUE));
        System.out.println(colorFontB("hello", ColorCons.F_W, ColorCons.B_BLUE));
    }

    public static String colorFont(String org, String... cons) {
        return detail(org, false, cons);
    }

    public static String colorFontB(String org, String... cons) {
        return detail(org, true, cons);
    }

    private static String detail(String org, boolean ifBlod, String... cons) {
        StringBuffer result = new StringBuffer().append(ColorCons.O);
        if (ifBlod) {
            result.append(ColorCons.B).append(ColorCons.SP);
        }
        for (String con : cons) {
            result.append(con).append(ColorCons.SP);
        }
        result.deleteCharAt(result.lastIndexOf(ColorCons.SP));
        result.append(ColorCons.M).append(org).append(ColorCons.E);
        return result.toString();
    }

    void testColor() {
        System.out.println(" \033[(前缀),m(后缀),格式:\033[XX;XX;XXm");
        System.out.println("------ 字体颜色30~37 ------");
        System.out.println("\033[30m" + "就是酱紫的");
        System.out.println("\033[31m" + "就是酱紫的");
        System.out.println("\033[32m" + "就是酱紫的");
        System.out.println("\033[37m" + "就是酱紫的");
        System.out.println("------ 背景颜色40~47 -------");
        System.out.println("\033[43m" + "就是酱紫的");
        System.out.println("\033[44m" + "就是酱紫的" + "\033[m");
        System.out.println("\033[45m" + "就是酱紫的");
        System.out.println("\033[46m" + "就是酱紫的" + "\033[m");
        System.out.println("--- 1:加粗,;:隔开,90~97字体颜色变亮 ---");
        System.out.println("\033[1;90m" + "就是酱紫的");
        System.out.println("\033[1;94m" + "就是酱紫的");
        System.out.println("\033[1;95m" + "就是酱紫的");
        System.out.println("\033[1;97m" + "就是酱紫的");
        System.out.println("\033[1;93;45m" + "就是酱紫的" + "\033[m");
    }
}

interface ColorCons {
    // open
    String O = "\033[";
    // bold
    String B = "1";
    String SP = ";";
    // m
    String M = "m";
    String E = "\033[m";
    String F_W = "30";
    String F_R = "31";
    String F_Y = "32";
    String F_G = "37";
    String F_G_H = "90";
    String F_Y_H = "93";
    String F_BLUE_H = "94";
    String F_P_H = "95";
    String F_B_H = "97";
    String B_Y = "43";
    String B_BLUE = "44";
    String B_P = "45";
    String B_SKY = "46";
}

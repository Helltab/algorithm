package com.helltab.DSA.stack;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 进制转换
 */
public class Converse {
    public static void main(String[] args) {
//        converse(12, 7);
//        brace("[([])]");
//        System.out.println((int) "[]".charAt(0));
//        System.out.println((int) "[]".charAt(1));
        System.out.println(compute("1*2+3+2232"));
    }

    private static void converse(int i, int i1) {
        Stack<Integer> stack = new Stack<>();
        System.out.println(i + ": 结果");
        while (i > 0) {
            stack.push(i % i1);
            i = i / i1;
        }
        System.out.println();
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    /**
     * 括号匹配
     */
    public static void brace(String braceStr) {
        Stack<String> stack = new Stack<>();
        for (String s : braceStr.split("")) {
            if (s.matches("[()\\[\\]{}<>]")) {
                if (!stack.isEmpty() && match(s, stack.peek())) {
                    stack.pop();
                } else {
                    stack.push(s);
                }
            }
        }
        System.out.println(stack.isEmpty());
    }

    private static boolean match(String a, String b) {
        return ("(".equals(a) && ")".equals(b)) ||
                       (")".equals(a) && "(".equals(b)) ||
                       ("[".equals(a) && "]".equals(b)) ||
                       ("]".equals(a) && "[".equals(b)) ||
                       ("{".equals(a) && "}".equals(b)) ||
                       ("}".equals(a) && "{".equals(b));

    }

    public static int compute(String expression) {
        Pattern pattern = Pattern.compile("(?<op>[+\\-*/^!])");
        Matcher matcher = pattern.matcher(expression.replaceAll("\\s", ""));
        while (matcher.find()) {
            expression = expression.replaceFirst(matcher.group("op"), "`" + matcher.group("op") + "`");
        }
        Stack<Integer> dataS = new Stack<>();
        Stack<String> opS = new Stack<>();
        String[] orgs = expression.split("`");
        for (int i = 0; i < orgs.length; i++) {
            String s = orgs[i];
            if (s.matches("[+\\- */^!]")) {
                if (true) {
                    dataS.push(result(dataS.pop(), Integer.parseInt(orgs[i + 1]), s));
                    i++;
                } else {
                    opS.push(s);
                }
            } else {
                dataS.push(Integer.valueOf(s));
            }
        }

        return dataS.peek();
    }

    static int result(int a, int b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }

        return 0;
    }
}

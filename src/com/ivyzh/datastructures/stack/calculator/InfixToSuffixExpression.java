package com.ivyzh.datastructures.stack.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀转后缀表达式
 * <p>
 * 1+((2+3)×4)-5 ==> 1 2 3 + 4 × + 5 –
 */
public class InfixToSuffixExpression {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";

        List<String> inFixList = getInfixList(expression);//得到中序表达式List
        System.out.println(expression + " 对应的中序表达式List=> " + inFixList);
        List<String> suffixExpreesionList = parseSuffixExpreesionList(inFixList);//中缀表达式的List->后缀表达式List
        System.out.println(expression + " 对应的后序表达式List=> " + suffixExpreesionList);


        System.out.println("-------------------------");
        int result = calc(suffixExpreesionList);
        System.out.println(expression + " = " + result);


    }

    private static List<String> parseSuffixExpreesionList(List<String> list) {
        Stack<String> s1 = new Stack<>();//s1:运算符栈
        ArrayList<String> s2 = new ArrayList<>();//s2:存储中间值的的栈，这里用ArrayList方便直接读取就是了。

        for (String item : list) {
            if (item.matches("\\d+")) {// 如果遇到操作数，则将其压入s2
                s2.add(item);
            } else {//遇到运算符时
                if (s1.empty()) {//如果s1为空，则直接压入s1
                    s1.push(item);
                } else {
                    if (s1.peek().equals("(")) {//如果s1不为空，且s1栈顶为左括号"("， 则直接压入s1
                        s1.push(item);
                    } else if (getPriority(item) > getPriority(s1.peek())) {//若优先级比栈顶运算符高，则直接压入s1
                        s1.push(item);
                    } else if (item.equals("(")) {//左括号直接入栈
                        s1.push(item);
                    } else if (item.equals(")")) {// 右括号，弹出运算符栈s1压入到s2，直到遇到左括号
                        while (!s1.peek().equals("(")) {
                            s2.add(s1.pop());
                        }
                        s1.pop();//!!!重要，将 （ 弹出s1，消除小括号
                    } else {//若优先级比栈顶运算符小于或等于,将s1栈顶运算符弹出压入到s2中，再次转入到与s1中新的栈顶运算符比较
                        while (!s1.empty() && getPriority(item) <= getPriority(s1.peek())) {
                            s2.add(s1.pop());
                        }
                        s1.push(item);
                    }
                }
            }

        }

        // 将s1中剩余运算符依次压入s2
        while (!s1.empty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    private static int getPriority(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 0;
        } else if (c.equals("*") || c.equals("/")) {
            return 1;
        } else {
            return -1;
        }
    }

    private static List<String> getInfixList(String expression) {
        ArrayList<String> ls = new ArrayList<>();
        String keepNum = "";
        char c;
        int i = 0;
        do {
            c = expression.charAt(i);

            if (c < 48 || c > 57) {//非数字，直接接入到ls中
                ls.add(c + "");
                i++;
            } else {
                keepNum = "";
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    keepNum += c;
                    i++;
                }
                ls.add(keepNum);
            }
        } while (i < expression.length());

        return ls;
    }


    /**
     * 后缀表达式计算值
     *
     * @param list，后缀表达式
     * @return
     */
    private static int calc(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

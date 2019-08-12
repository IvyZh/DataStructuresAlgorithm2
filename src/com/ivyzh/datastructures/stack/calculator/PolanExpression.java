package com.ivyzh.datastructures.stack.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 演示波兰表达式又叫前缀表达式
 * 举例说明： (3+4)×5-6=29 对应的前缀表达式就是 - × + 3 4 5 6
 * <p>
 * 计算过程：
 * 从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
 * 用运算符对它们做相应的计算（栈顶元素 和 次顶元素），并将结果入栈；重复上述过程直到表达式最左端，
 * 最后运算得出的值即为表达式的结果。
 */
public class PolanExpression {
    public static void main(String[] args) {
        String expression = "- * + 3 4 5 6";
        List<String> list = getListString(expression);//根据格式要求用空格分隔，然后依次加入list当中
        System.out.println(list);//[-, *, +, 3, 4, 5, 6]

        int result = calc(list);//前缀表达式求值
        System.out.println("前缀表达式[ " + expression + " ]对应的结果为：" + result);
    }

    private static int calc(List<String> list) {
        Collections.reverse(list);
        System.out.println(list);//逆序
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {//使用正则表达式来判断是否为数字
                stack.push(item);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
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
    private static List<String> getListString(String expression) {
        ArrayList<String> list = new ArrayList<>();
        String[] split = expression.split(" ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }
}

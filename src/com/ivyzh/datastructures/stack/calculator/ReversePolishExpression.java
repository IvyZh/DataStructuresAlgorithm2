package com.ivyzh.datastructures.stack.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算过程：
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 和 栈顶元素），
 * 并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果
 * <p>
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * <p>
 * 从左至右扫描，将3和4压入堆栈；
 * 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
 * 将5入栈；
 * 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * 将6入栈；
 * 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
 * <p>
 * 输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果
 * 支持小括号和多位数整数，因为这里我们主要讲的是数据结构，因此计算器进行简化，只支持对整数的计算。
 */
public class ReversePolishExpression {
    public static void main(String[] args) {
        String expression = "3 4 + 5 * 6 -";
        List<String> list = getListString(expression);
        System.out.println(list);//[3, 4, +, 5, ×, 6, -]

        int result = calc(list);//前缀表达式求值
        System.out.println("后缀(逆波兰)表达式[ " + expression + " ]对应的结果为：" + result);
    }

    public static int calc(List<String> list) {
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

    private static List<String> getListString(String expression) {
        ArrayList<String> list = new ArrayList<>();
        String[] split = expression.split(" ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }
}

package com.ivyzh.datastructures.stack.calculator;

/**
 *
 * 栈实现综合计算器:中缀表达式
 * 运算规则（重要）
 * 依次扫描表达式每一位
 * 1.如果是数字，进入数栈
 * 2.如果是操作符
 * 2.1 如果操作符栈是空，则直接放进去
 * 2.2 如果操作符大于从操作符栈peek出来的，则直接放入
 * 2.3 如果小于或等于，则从数栈pop出两个值，从操作符栈pop出一个值，进行运算(注意操作顺序)，结果push到数栈，当前操作符放入到操作符栈
 * 3. 扫描完毕，则遍历操作符栈和数栈依次运算，最终数栈中保留一位即为结果值。
 * <p>
 * 问题：
 * 1. 多位数实现
 * 2. 加入括号
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-2+3+8-4-10+900";//24

        ArrayStack numberStack = new ArrayStack(20);//数字栈
        ArrayStack operatorStack = new ArrayStack(20);//操作符栈

        char c = ' ';
        String keepNum = "";//用于多位数计算
        for (int i = 0; i < expression.length(); i++) {
            c = expression.substring(i, i + 1).charAt(0);
            if (isOperator(c)) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(c);//如果操作符栈是空，则直接放入
                } else {
                    int peekOper = operatorStack.peek();//从栈中取出一个操作符
                    if (operatorPriority(c) > operatorPriority(peekOper)) {//如果当前操作符优先级大于栈中的操作符，则直接入符号栈中
                        operatorStack.push(c);
                    } else {
                        // 如果当前操作符优先级小于或等于peek出来的操作符，则
                        // pop出数字栈两个值，pop出符号栈的符号,进行运算，结果值放入数字栈，当前操作符放入符号栈。
                        int num1 = numberStack.pop();
                        int num2 = numberStack.pop();
                        int operator = operatorStack.pop();
                        int res = calc(num1, num2, operator);
                        numberStack.push(res);
                        operatorStack.push(c);
                    }
                }
            } else {//如果是数字，则直接放入数栈

                keepNum += c;
                // 多位数情况判断
                if (i == expression.length() - 1) {
                    //如果是最后一个数字，则直接入栈
                    numberStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一位是不是数字，如果不是，则入栈。如果是，则进行拼接，同时i++，进行下一轮判断
                    // 不是数字，入栈
                    if (isOperator(expression.substring(i + 1, i + 2).charAt(0))) {
                        numberStack.push(Integer.parseInt(keepNum));
                        keepNum = "";// 重要！！！记得要清空
                    }
                }
            }
        }

        numberStack.list();
        operatorStack.list();
        // 此时就要遍历操作符栈和数字栈来进行计算了

        while (!operatorStack.isEmpty()) {
            int num1 = numberStack.pop();
            int num2 = numberStack.pop();
            int operator = operatorStack.pop();
            int res = calc(num1, num2, operator);
            numberStack.push(res);
        }

        // 运算完成，则留在数栈中即为表达式的结果
        operatorStack.list();
        numberStack.list();
    }

    private static int calc(int num1, int num2, int operator) {

        int result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }

    /**
     * 判断字符c是数字还是操作符
     *
     * @param c
     * @return
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * + -:0
     * * /:1
     *
     * @param c
     * @return
     */
    private static int operatorPriority(int c) {
        if (c == '+' || c == '-') {
            return 0;
        } else if (c == '*' || c == '/') {
            return 1;
        } else {
            return -1;
        }
    }


    static class ArrayStack {
        int[] arr;
        int maxSize;
        int top = -1;//默认是-1，每push一个值top++,pop一个值top--

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
        }

        public void list() {
            if (isEmpty()) {
                System.out.println("stack是空的");
                return;
            }

            for (int i = 0; i <= top; i++) {
                System.out.print(arr[i] + " -> ");
            }
            System.out.println();
        }

        public void push(int ele) {
            if (isFull()) {
                System.out.println("stack已经满了");
                return;
            }
            top++;
            arr[top] = ele;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("stack是空的");
                throw new RuntimeException("stack是空的");
            }
            return arr[top];
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("stack是空的");
                throw new RuntimeException("stack是空的");
            }
            int value = arr[top];
            top--;
            return value;
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

}

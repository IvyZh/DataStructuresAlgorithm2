package com.ivyzh.datastructures.tree;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 800W个数据
 * 排序前的时间：01:31:00
 * 排序后的时间：01:31:03
 */
public class HeadSortDemo {
    public static void main(String[] args) {
//        System.out.println("~~HeadSortDemo~~");
//        int[] arr = {4, 6, 8, 5, 9};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        sort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));


        // 演示性能测试
        Date start = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String date1Str = format.format(start);
        System.out.println("排序前的时间：" + date1Str);

        int num = 8000000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * num);
        }
//        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        Date end = new Date();
        String date2Str = format.format(end);
        System.out.println("排序后的时间：" + date2Str);

        // 检验排序数组正确性
        boolean isOk = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                isOk = false;
                break;
            }
        }
        System.out.println("排序验证结果：" + isOk);
    }

    public static void sort(int[] arr) {
        // 首先生成大顶堆
        // 按照完全二叉树的特点，从最后一个非叶子节点开始，对于整棵树进行大根堆的调整
        // 也就是说，是按照自下而上，每一层都是自右向左来进行调整的
        // 注意，这里元素的索引是从0开始的
        // 另一件需要注意的事情，这里的建堆，是用堆调整的方式来做的
        // 堆调整的逻辑在建堆和后续排序过程中复用的
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }


        // 依次调整
        // 下面，开始排序逻辑
        for (int j = arr.length - 1; j > 0; j--) {
            // 说是交换，其实质就是把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(arr, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(arr, 0, j);
        }
    }

    // 生成大顶堆的方法

    /**
     * @description 这里，是整个堆排序最关键的地方，正是因为把这个方法抽取出来，才更好理解了堆排序的精髓，会尽可能仔细讲解
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];// temp 第一个非叶子节点，即6

        // i 为第一个非叶子节点，即6，k为i的左孩子即为5
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                swap(arr, i, k);
                // 下面就是非常关键的一步了
                // 如果子节点更换了，那么，以子节点为根的子树会不会受到影响呢？
                // 所以，循环对子节点所在的树继续进行判断
                i = k;
                // 如果不用交换，那么，就直接终止循环了
            } else {
                break;
            }
        }

    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }
}
package org.example.learn.algo.sorting;

/**
 * 冒泡排序算法（升序）
 * 核心思想：重复遍历数组，[依次比较相邻元素]，若顺序错误则交换。
 * 每一轮都会将未排序部分的最大元素“冒泡”到末尾
 * 这个算是大数下沉,最终是小数上浮. 还有一种就是小数上浮,最终大数下沉
 */
public class BubbleSort {

    // 大数下沉,最终是小数上浮.
    public static void bubbleSort1(int[] arr) {
        // 边界检查：如果数组为空或只有一个元素，无需排序
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            // 内层循环进行相邻元素比较和交换
            // 每完成一轮，末尾 i 个元素已经就位，因此比较范围逐步缩小
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换相邻元素
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }

            // 优化标志位：如果某一轮没有发生任何交换，说明数组已经有序，提前结束
            if (!swapped) {
                break;
            }
        }
    }

    // 小数上浮,最终大数下沉
    public static void bubbleSort2(int[] arr) {
        // 边界检查：如果数组为空或只有一个元素，无需排序
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = n-1; i > 0; i--) {
            boolean swapped = false;
            for (int j = n-1; j < n-1-i; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void log(int[] arr){
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 演示冒泡排序的完整流程
     */
    public static void main(String[] args) {
        System.out.println("========== 冒泡排序算法演示: 下沉 ==========");

        // 测试用例1：普通随机数组
        int[] array1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.print("原始数组: ");
        log(array1);
        bubbleSort1(array1);
        System.out.print("排序后数组: ");
        log(array1);
        System.out.println();


        System.out.println("========== 冒泡排序算法演示: 上浮 ==========");
        // 测试用例1：普通随机数组
        int[] array2 = {64, 34, 25, 12, 22, 11, 90};
        System.out.print("原始数组: ");
        log(array2);
        bubbleSort1(array2);
        System.out.print("排序后数组: ");
        log(array2);
        System.out.println();
    }
}

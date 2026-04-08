package org.example.learn.algo.sorting;

public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // 获取分区点(包含了移位)
        int pivotIndex = partition(arr, left, right);

        // 递归排序左右两边
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    // 分区函数
    private static int partition(int[] arr, int left, int right) {
        // 选最右边元素作为基准 pivot([left,right]中任意值)
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        // 将 pivot 放到正确位置
        swap(arr, i, right);
        return i;
    }

    // 交换数组元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6, 3};
        System.out.println("排序前：");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        quickSort(arr);

        System.out.println("\n排序后：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

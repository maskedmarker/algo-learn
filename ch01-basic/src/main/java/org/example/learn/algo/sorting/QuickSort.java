package org.example.learn.algo.sorting;

/**
 * 使用递归的思想
 * 随便找一个数组中的值(称为pivot), 除去pivot,通过移动操作,将数组分为2块, 左侧都小于pivot, 右侧不小于pivot (左侧/右侧不必排序)
 * 然后递归
 * 最终会收敛到从小到大排序
 */
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

    /**
     * 循环不变式: 保持[left, i)都是小于pivot的
     * 意味着: 将小于pivot的移动到[left, i),留下[i,right)为大于等于pivot的,
     * 最后将arr[right]与arr[i]交换位置,依然不影响左侧是小于的,右侧是不小于的
     */
    private static int partition(int[] arr, int left, int right) {
        // 选最右边元素作为基准 pivot([left,right]中任意一个位置都行)
        int pivot = arr[right];

        // 经过调整,最终pivot未来在arr[i]这个位置,且pivot左侧都比自己小,右侧都不比自己小
        // 那么i必定满足 right>=i>=left,所以i的初始值是left
        int i = left;
        for (int j = left; j < right; j++) {                          // 遍历pivot左侧的所有元素
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;                                                  // 遍历pivot左侧的所有元素的过程中,当遇到元素比pivot小,那么最终的i值肯定不在当前位置,至少在其后,所以要交换当前arr[i]与当前元素arr[j],并i++
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
        log(arr);
    }

    private static void log(int[] arr){
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    // 测试
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 4, 2, 3};
        System.out.println("排序前：");
        log(arr);

        quickSort(arr);

        System.out.println("\n排序后：");
        log(arr);
    }
}

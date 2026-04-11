package org.example.learn.algo.solution;

import org.example.learn.algo.util.ArrayUtils;

/**
 * 合并两个有序数组
 *
 * [1,2,3,0,0,0] 3   [2,5,6]  3     ==> [1,2,2,3,5,6]
 */
public class S1 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] sorted = new int[m+n];
        int p0 = 0;

        int p1 = 0, p2 = 0;
        int cur;
        while(p1 < m || p2 < n) {
            if(p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if(nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }

            sorted[p0++] = cur;
        }

        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;

        merge(nums1, m, nums2, n);
        ArrayUtils.log(nums1);
    }
}

package cn.tzinfo.shenk.algorithm.sort;

import java.util.Arrays;

/**
 * @Author:shenk
 * @Date: 2020/5/13 9:27
 * @Description: 快速排序
 */
public class QuickSort extends AbstractSort{

    @Override
    public int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
        return arr;
    }

    private void quickSort(int[] arr, int l, int r) {
        if(l >= r) {
            return;
        }
        int s = l; int e=r;
        int v = arr[l];
        while(l < r) {
            while(l < r && arr[r] > v) {
                r--;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] < v) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = v;
        quickSort(arr, s, l-1);
        quickSort(arr, l+1, e);
    }
}

package cn.tzinfo.shenk.algorithm.sort;

import java.util.Arrays;

/**
 * @Author:shenk
 * @Date: 2020/5/15 9:44
 * @Description: 选择排序
 */
public class SelectSort extends AbstractSort{

    public int[] sort(int[] arr) {
        if(arr == null) {
            throw new NullPointerException();
        }
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length-i;j++) {
                if(arr[j] > arr[arr.length-i-1]) {
                    swap(arr, j, arr.length-i-1);
                }
            }
        }
        return arr;
    }
}

package cn.tzinfo.shenk.algorithm.sort;

import java.util.Arrays;

/**
 * @Author:shenk
 * @Date: 2020/5/13 9:28
 * @Description: 插入排序
 */
public class InsertSort extends AbstractSort{

    @Override
    public int[] sort(int[] arr) {
        if(arr == null) {
            throw new NullPointerException();
        }
        for(int i=0;i<arr.length-1;i++) {
            for(int j=i;j != -1;j--) {
                if(arr[j+1] < arr[j]) {
                    swap(arr, j, j+1);
                }
            }
        }
        return arr;
    }

}

package cn.tzinfo.shenk.algorithm.sort;

/**
 * @Author:shenk
 * @Date: 2020/5/13 9:31
 * @Description: 冒泡排序
 */
public class BubbleSort extends AbstractSort {

    public int[] sort(int[] arr) {
        if(arr == null) {
            throw new NullPointerException();
        }
        int len = arr.length;
        for(int i=0;i<len;i++) {
            for(int j=0;j<len-1-i;j++) {
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
        return arr;
    }



}

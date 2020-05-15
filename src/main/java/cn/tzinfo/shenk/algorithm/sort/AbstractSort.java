package cn.tzinfo.shenk.algorithm.sort;

/**
 * @Author:shenk
 * @Date: 2020/5/15 9:45
 * @Description:
 */
public abstract class AbstractSort {

    public abstract int[] sort(int[] arr);

    protected void swap(int[] arr, int from, int to) {
        int t = arr[from];
        arr[from] = arr[to];
        arr[to] = t;
    }
}

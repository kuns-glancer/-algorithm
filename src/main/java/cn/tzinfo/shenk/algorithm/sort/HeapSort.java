package cn.tzinfo.shenk.algorithm.sort;

/**
 * @Author:shenk
 * @Date: 2020/5/13 9:29
 * @Description: 堆排序
 */
public class HeapSort {

    boolean isMaxHeap;

    public HeapSort() {
        this(false);
    }

    public HeapSort(boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
    }
}

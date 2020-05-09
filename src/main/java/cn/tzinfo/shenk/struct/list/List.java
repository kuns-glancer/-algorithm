package cn.tzinfo.shenk.struct.list;

import java.util.Iterator;

/**
 * @Author:shenk
 * @Date: 2020/5/9 10:58
 * @Description:
 */
public interface List<T> {

    int size();

    boolean isEmpty();

    boolean contains(T t);

    boolean add(T t);

    boolean remove(T t);

    void clear();

    Iterator iterator();
}

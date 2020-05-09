package cn.tzinfo.shenk.struct.list;

import java.util.Iterator;

/**
 * @Author:shenk
 * @Date: 2020/5/9 11:01
 * @Description:
 */
public class MyArrayList<T> implements List<T> {

    static final int DEFAULT_CAPACITY = 10;

    int size = 0;

    Object[] elements;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T t) {
        return false;
    }

    public T set(int idx, T t) {
        if(idx < 0 || idx > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        T oldVal = (T) elements[idx];
        elements[idx] = t;
        return oldVal;
    }

    public T get(int idx) {
        if(idx < 0 || idx > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[idx];
    }

    @Override
    public boolean add(T t) {
        return add(size, t);
    }

    public boolean add(int idx, T t) {
        if(elements == null) {
            elements = new Object[DEFAULT_CAPACITY];
        }
        if(elements.length == size) {
            ensureCapacity(size << 1 + 1);
        }
        if(idx > size + 1) {
            idx = size;
        }
        for(int i=size;i>idx;i--) {
            elements[i] = elements[i-1];
        }
        elements[idx] = t;
        size++;
        return true;
    }

    @Override
    public boolean remove(T t) {
        Iterator<T> it = iterator();
        if (t==null) {
            while (it.hasNext()) {
                if (it.next()==null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (t.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public T remove(int idx) {
        if(idx >= size()) {
            throw new IndexOutOfBoundsException();
        }
        T t = (T) elements[idx];
        for(int i = idx; i<size-1;i++) {
            elements[i] = elements[i+1];
        }
        elements[size-1] = null;
        size--;
        return t;
    }

    @Override
    public void clear() {
        for(int i=0; i<size; i++) {
            elements[i] = null;
        }
        size = 0;
        elements = null;
    }

    void ensureCapacity(int newCapacity) {
        Object[] arr = new Object[newCapacity];
        System.arraycopy(elements, 0, arr, 0, elements.length);
        elements = arr;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            return (T) elements[cursor++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(cursor);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(0);
        myArrayList.remove(0);
    }
}

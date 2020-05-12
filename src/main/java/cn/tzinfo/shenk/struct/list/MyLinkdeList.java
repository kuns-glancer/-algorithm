package cn.tzinfo.shenk.struct.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @Author:shenk
 * @Date: 2020/5/11 17:15
 * @Description:
 */
public class MyLinkdeList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;

    private int size = 0;
    private transient int modCount;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) > -1;
    }

    public int indexOf(Object t) {
        int idx = 0;
        if(t == null) {
            for(Node n=head; n != null; n = n.next) {
                if(n.val == null) {
                    return idx;
                }
                idx++;
            }
        }else {
            for(Node n=head; n != null; n = n.next) {
                if(t.equals(n.val)) {
                    return idx;
                }
                idx++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object t) {
        int idx = size - 1;
        if(t == null) {
            for(Node n=tail; n != null; n = n.pre) {
                if(n.val == null) {
                    return idx;
                }
                idx--;
            }
        }else {
            for(Node n=tail; n != null; n = n.pre) {
                if(t.equals(n.val)) {
                    return idx;
                }
                idx--;
            }
        }
        return -1;
    }

    @Override
    public boolean add(T t) {
        linkLast(t);
        return true;
    }

    public void addFirst(T t) {
        linkFirst(t);
    }

    public void addLast(T t) {
        linkLast(t);
    }

    public T get(int index) {
        if(index < 0 || index > size -1) {
            throw new IndexOutOfBoundsException();
        }
        return node(index).val;
    }

    public T set(int index, T val) {
        if(index < 0 || index > size -1) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = node(index);
        T oldVal = node.val;
        node.val = val;
        return oldVal;
    }

    private Node<T> node(int index) {
        if(index < (size >> 2)) {
            Node<T> node = head;
            for(int i=0;i<index;i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<T> node = tail;
            for(int i=size-1;i>index;i--) {
                node = node.pre;
            }
            return node;
        }
    }

    @Override
    public boolean remove(T t) {
        if(t == null) {
            for(Node node=head; node != null; node = node.next) {
                if(node.val == null) {
                    unLink(node);
                    return true;
                }
            }
        }else {
            for(Node node=head; node != null; node = node.next) {
                if(t.equals(node.val)) {
                    unLink(node);
                    return true;
                }
            }
        }
        return false;
    }

    public T remove(int index) {
        if(index < 0 || index > size -1) {
            throw new IndexOutOfBoundsException();
        }
        return unLink(node(index));
    }

    private T unLink(Node<T> node) {
        T ele = node.val;
        Node<T> pre = node.pre;
        Node<T> next = node.next;
        if(pre != null) {
            pre.next = next;
            node.pre = null; //GC
        }else{
            head = next;
            node.next = null; //GC
        }
        if(next != null) {
            next.pre = pre;
        }else{
            tail = pre;
        }
        node.val = null;
        size--;
        modCount++;
        return ele;
    }

    @Override
    public void clear() {
        for (Node<T> x = head; x != null; ) {
            Node<T> next = x.next;
            x.val = null;
            x.next = null;
            x.pre = null;
            x = next;
        }
        head = tail = null;
        size = 0;
        modCount++;
    }

    private void linkFirst(T t) {
        Node<T> node = head;
        Node<T> newNode = new Node(null, t, head);
        head = newNode;
        if(node == null) {
            tail = newNode;
        }else{
            node.pre = newNode;
        }
        size++;
        modCount++;
    }

    private void linkLast(T t) {
        Node<T> node = tail;
        Node<T> newNode = new Node(tail, t, null);
        tail = newNode;
        if(node == null) {
            head = newNode;
        }else{
            node.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    @Override
    public String toString() {
        Iterator<T> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            T e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    class LIterator implements Iterator<T>{

        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            checkForComodification();
            int i = cursor;
            cursor++;
            Node<T> node = MyLinkdeList.this.node(lastRet = i);
            return node.val;
        }

        @Override
        public void remove() {
            if(lastRet < 0) {
                throw new IndexOutOfBoundsException();
            }
            checkForComodification();
            try {
                MyLinkdeList.this.remove(lastRet);
                cursor = lastRet;
                expectedModCount = modCount;
                lastRet = -1;
            }catch (Exception e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    class Node<T>{

        private T val;

        private Node next;
        private Node pre;

        Node(Node pre, T val, Node next) {
            this.pre = pre;
            this.next = next;
            this.val = val;
        }
    }

}

package com.endava.internship.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class StudentList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private int capacity;

    private Object[] elements;

    public StudentList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public StudentList(int capacity) {
        if (capacity <= DEFAULT_CAPACITY && capacity >= 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
            this.capacity = DEFAULT_CAPACITY;
        } else if (capacity > DEFAULT_CAPACITY) {
            this.elements = new Object[capacity];
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    capacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(t, elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        int currentElement;

        Iter() {
        }

        @Override
        public boolean hasNext() {
            return currentElement < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return StudentList.this.get(currentElement++);
        }

        @Override
        public void remove() {
            if (currentElement > 0) {
                StudentList.this.remove(--currentElement);
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> E[] toArray(E[] ts) {
        if (Objects.isNull(ts)) {
            throw new NullPointerException();
        }
        if (ts.length >= this.size()) {
            int i = 0;
            for (; i < this.size; i++) {
                ts[i] = (E) this.get(i);
            }
            for (; i < ts.length; i++) {
                ts[i] = null;
            }
            return ts;
        }

        return (E[]) Arrays.copyOf(elements, size, ts.getClass());
    }

    @Override
    public boolean add(T t) {
        if (size == this.capacity) {
            resizeArray();
        }
        elements[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object t) {
        int index = this.indexOf(t);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int i) {
        if (i >= 0 && i < size) {
            return (T) elements[i];
        } else {
            throw new IndexOutOfBoundsException("Index should be greater then or equal to 0 and less then " + size());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T set(int i, T t) {
        if (i >= 0 && i < size) {
            return (T) (elements[i] = t);
        } else {
            throw new IndexOutOfBoundsException("Insertion index was out of range. Must be non-negative and less than size");
        }
    }

    @Override
    public void add(int i, T t) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Insertion index was out of range. Must be non-negative and less than or equal to size");
        }
        if (size == capacity) {
            resizeArray();
        }
        if (size - i > 0) {
            System.arraycopy(elements, i, elements, i + 1, size - i);
        }
        elements[i] = t;
        size++;
    }

    @Override
    public T remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Insertion index was out of range. Must be non-negative and less than size");
        }
        T elementToRemove = this.get(i);
        System.arraycopy(elements, i + 1, elements, i, size() - i);
        elements[size] = null;
        size--;
        return elementToRemove;
    }

    @Override
    public int indexOf(Object t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(t, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object t) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(t, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIter(0);
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return new ListIter(i);
    }

    private class ListIter extends Iter implements ListIterator<T> {
        ListIter(int index) {
            super();
            currentElement = index;
        }

        @Override
        public boolean hasPrevious() {
            return currentElement > 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T previous() {
            if (hasPrevious()) {
                return StudentList.this.get(--currentElement);
            }
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return currentElement;
        }

        @Override
        public int previousIndex() {
            if (hasPrevious()) {
                return currentElement - 1;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void set(T t) {
            if (hasPrevious()) {
                StudentList.this.set(--currentElement, t);
            }
                throw new NoSuchElementException();
        }

        @Override
        public void add(T t) {
            int i = currentElement;
            StudentList.this.add(i, t);
            currentElement = i + 1;
        }
    }

    @Override
    public List<T> subList(int i, int i1) {
        List<T> subList = new StudentList<>();
        for (int j = i; j < i1; j++) {
            subList.add(this.get(j));
        }
        return subList;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        resizeArray(calculateCapacity(collection.size()));
        collection.forEach(this::add);
        return true;
    }


    //Ignore this for homework
    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    //Ignore this for homework
    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    //Ignore this for homework
    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    //Ignore this for homework
    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    private void resizeArray() {
        this.capacity *= 2;
        elements = Arrays.copyOf(elements, capacity);
    }

    private void resizeArray(int capacity) {
        elements = Arrays.copyOf(elements, capacity);
        this.capacity = capacity;
    }

    private int calculateCapacity(int size) {
        final int sizeCombined = size + this.size();
        int currentCapacity = this.capacity;
        while (currentCapacity < sizeCombined) {
            currentCapacity *= 2;
        }
        return currentCapacity;
    }
}


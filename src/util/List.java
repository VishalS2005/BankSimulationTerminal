package util;

import java.util.Iterator;


public class List<E> implements Iterable<E> {
    private E[] objects;

    private int size;

    private static final int GROW_SIZE = 4;

    /**
     * The starting size of the AccountDatabase
     */
    private static final int STARTING_SIZE = 0;

    /**
     * The integer -1 is returned if an Account is not found
     */
    private static final int NOT_FOUND = -1;

    public List() {
        this.objects = (E[]) new Object[GROW_SIZE];
        this.size = STARTING_SIZE;
    }

    private int find(E e) {
        for(int i = 0; i < this.size; i++) {
            if(this.objects[i].equals(e)) {
                return i;
            }
        }
        return NOT_FOUND;
    }


    private void grow() {
        int newLength = this.objects.length + GROW_SIZE; //the new length can hold 4 more accounts than the old length
        E[] newObjects = (E[]) new Object[newLength];
        for(int i = 0; i < this.size; i++) {
            newObjects[i] = this.objects[i];
        }
        this.objects = newObjects;
    }


    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }

    public void add(E e) {
        if(this.size == this.objects.length - 1) {
            grow();
        }

        this.objects[this.size] = e;
        this.size++;
    }



    public void remove(E e) {
        int index = find(e); //represents index of account
        if(index == -1) {
            return;
        }
        this.objects[index] = this.objects[this.size - 1];
        this.objects[this.size - 1] = null;
        this.size--;
    }

    public boolean isEmpty() {
        return this.size == STARTING_SIZE;
    }


    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    } //for traversing the list


    public E get(int index) {
        return this.objects[index];
    } //return the object at the index


    public void set(int index, E e) {
        this.objects[index] = e;
    } //put object e at the index


    public int indexOf(E e) {
        return find(e);
    } //return the index of the object or return -1


    private class ListIterator implements Iterator<E> {
        int current = 0; //current index when traversing the list (array)

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return objects[current++];
        }
    }
}

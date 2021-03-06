/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.hashtable;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class HashTable_LP<Key, Value> implements ST<Key, Value> {

    private final int length;
    private final int bits;
    private final Value[] values;
    private final Key[] keys;
    private final int[] hashes;
    int size;

    @SuppressWarnings("unchecked")
    public HashTable_LP(int capacity) {
        this.bits = (int) Math.ceil(Math.log(capacity) / Math.log(2));
        this.length = (int) Math.pow(2, bits);
        this.values = (Value[]) new Object[length];
        this.keys = (Key[]) new Object[length];
        this.hashes = new int[length];
        this.size = 0;
    }

    public void put(Key key, Value value) {
        if (size >= length)
            throw new HashTableException("table is full");
        int index = getIndex(key, bits);
        while (hashes[index] != 0 || keys[index] != null) {
            index++;
            if (index == length - 1) index = 0;
        }
        if (index < length) {
            hashes[index] = key.hashCode();
            keys[index] = key;
            values[index] = value;
            size++;
        } else
            throw new HashTableException("logic error");
    }

    // TODO should be private
    static int getIndex(Object key, int bits) {
        int mask = 0xFFFFFFFF;
        return key.hashCode() & (mask << bits ^ mask);
    }

    public Value get(Key key) {
        int index = findMatchingIndex(key, getIndex(key, bits));
        assert (index >= 0);
        assert (index < length);
        assert (hashes[index] == key.hashCode());
        assert (keys[index] == key);
        return values[index];
    }

    @Override
    public Set<Key> keys() {
        return new TreeSet<>(Arrays.asList(keys));
    }

    public Value getValueMaybe(Key key) {
        int index = findMatchingIndex(key, getIndex(key, bits));
        if (index >= 0) {
            assert (index < length);
            assert (hashes[index] == key.hashCode());
            assert (keys[index] == key);
            return values[index];
        }
        return null;
    }

    private boolean checkKey(Key key, int index) {
        return key == keys[index];
    }

    private int findMatchingIndex(Key key, int index) {
        int result = index;
        int hash = key.hashCode();
        while (hashes[result] != 0) {
            if (key.hashCode() == hash)
                if (checkKey(key, result))
                    return result;
                else {
                    result++;
                    if (result == length) result = 0;
                }
            else
                return -1;
        }
        return -1;
    }

    // This is only for testing and should be made private
    public boolean check(int bits, int length) {
        return (this.bits == bits && this.length == length);
    }

    public void show() {
        for (int i = 0; i < length; i++)
            if (hashes[i] != 0)
                System.out.println("i: " + i + ": hash: " + hashes[i] + ", key: " + keys[i] + ", value: " + values[i]);
    }

    private static class HashTableException extends RuntimeException {
        public HashTableException(String s) {
            super(s);
        }
    }
}

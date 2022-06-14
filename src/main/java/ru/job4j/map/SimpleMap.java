package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;


public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        int i = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] table = new MapEntry[capacity];
        for (MapEntry<K, V> el : this.table) {
            int i = indexFor(hash((el.key == null) ? 0 : el.key.hashCode()));
            if (table[i] == null) {
                table[i] = el;
            }
        }
        this.table = table;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int i = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[i] != null && Objects.equals(table[i].key, key)) {
            rsl = table[i].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hashCode = (key == null) ? 0 : key.hashCode();
        int i = indexFor(hash(hashCode));
        if (table[i] != null && Objects.equals(table[i].key, key)) {
            table[i] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                if (modCount != count) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity && table[point] != null;
            }

            @Override
            public K next() {
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
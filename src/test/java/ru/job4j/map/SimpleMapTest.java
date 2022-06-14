package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class SimpleMapTest {
    private SimpleMap<Integer, String> map = new SimpleMap<>();

    @Test
    public void whenPutAndGet() {
        map.put(9, "value9");
        assertThat(map.get(9), is("value9"));
    }

    @Test
    public void whenGet() {
        map.put(1, "value1");
        map.put(2, "value2");
        map.put(3, "value3");
        map.put(4, "value4");
        map.put(5, "value5");
        assertThat(map.get(1), is("value1"));
        assertThat(map.get(2), is("value2"));
        assertThat(map.get(3), is("value3"));
        assertThat(map.get(4), is("value4"));
        assertThat(map.get(5), is("value5"));
    }

    @Test
    public void whenRemove() {
        map.put(1, "value1");
        map.remove(1);
        assertThat(map.get(1), nullValue());
    }

    @Test
    public void whenPutThanRemove() {
        map.put(1, "value1");
        map.put(2, "value2");
        map.remove(1);
        assertThat(map.get(1), nullValue());
        assertThat(map.get(2), is("value2"));
    }
}
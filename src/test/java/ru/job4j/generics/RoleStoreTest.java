package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsFirst() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "First"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("First"));
    }

    @Test
    public void whenAddAndFindThenRolenameIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "First"));
        Role result = store.findById("2");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindThenRolenameIsFirst() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "First"));
        store.add(new Role("1", "Second"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("First"));
    }

    @Test
    public void whenReplaceAndFindThenRolenameIsSecond() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "First"));
        store.replace("1", new Role("1", "Second"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Second"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "First"));
        store.replace("2", new Role("1", "Second"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("First"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "First"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsFirst() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "First"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("First"));
    }
}
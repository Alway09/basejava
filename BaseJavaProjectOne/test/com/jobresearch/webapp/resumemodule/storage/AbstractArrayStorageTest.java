package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.exception.NotExistStorageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    protected StorageInterface arrayStorage;
    private final String UUID_1 = "UUID_1";
    private final String UUID_2 = "UUID_2";
    private final String UUID_3 = "UUID_3";

    @BeforeEach
    void setUp() {
        arrayStorage.clear();
        //arrayStorage.
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    // executable code
                });

        assertEquals("expected message", thrown.getMessage());
    }

    @Test
    void update() {
    }

    @Test
    void clear() {
    }

    @Test
    void getAll() {
    }

    @Test
    void size() {
    }
}
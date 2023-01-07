package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.exception.*;
import com.jobresearch.webapp.resumemodule.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final StorageInterface storageInterface;
    protected static final String UUID_1 = "UUID_1";
    protected static final String NAME_1 = "NAME_1";
    protected static final Resume RESUME_1 = new Resume(NAME_1, UUID_1);
    protected static final String UUID_2 = "UUID_2";
    protected static final String NAME_2 = "NAME_2";
    protected static final Resume RESUME_2 = new Resume(NAME_2, UUID_2);
    protected static final String UUID_3 = "UUID_3";
    protected static final String NAME_3 = "NAME_3";
    protected static final Resume RESUME_3 = new Resume(NAME_3, UUID_3);
    protected static final String UUID_4 = "UUID_4";
    protected static final String NAME_4 = "NAME_4";
    protected static final Resume RESUME_4 = new Resume(NAME_4, UUID_4);
    protected static final String UUID_A = "UUID_A";
    protected static final String NAME_A = "NAME_A";
    protected static final Resume RESUME_A = new Resume(NAME_A, UUID_A);

    public AbstractStorageTest(StorageInterface storage){
        storageInterface = storage;
    }

    @BeforeEach
    void setUp() {
        storageInterface.save(RESUME_4);
        storageInterface.save(RESUME_1);
        storageInterface.save(RESUME_3);
        storageInterface.save(RESUME_2);
    }
    @AfterEach
    void tearDown(){
        storageInterface.clear();
    }

    @Test
    void save() {
        storageInterface.save(RESUME_A);
        //assertEquals(UUID_A, arrayStorage.get(UUID_A).getUuid());
        assertTrue(RESUME_A.equals(storageInterface.get(UUID_A)));
        assertEquals(5, storageInterface.size());
    }

    @Test
    void saveExist() {
        ExistStorageException thrown = assertThrows(ExistStorageException.class,
                () ->{
                    storageInterface.save(RESUME_2);
                });
    }

    @Test
    void saveNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    storageInterface.save(null);
                });
    }

    @Test
    void delete() {
        storageInterface.delete(UUID_1);

        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.get(UUID_1);
                });

        assertEquals(3, storageInterface.size());
    }

    @Test
    void deleteNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.delete(UUID_A);
                });
    }

    @Test
    void deleteNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    storageInterface.delete(null);
                });
    }

    @Test
    void get() {
        Object o = storageInterface.get(UUID_3);

        assertTrue(o.equals(RESUME_3));
    }

    @Test
    void getNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.get(UUID_A);
                });

        //assertEquals("Resume with UUID " + UUID_A + " not exist", thrown.getMessage());
    }

    @Test
    void getNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    storageInterface.get(null);
                });
    }

    @Test
    void update() {
        Resume r = storageInterface.get(UUID_4);
        // modifications
        storageInterface.update(r);

        assertTrue(r.equals(RESUME_4));
    }

    @Test
    void updateNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.update(RESUME_A);
                });
    }

    @Test
    void updateNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    storageInterface.update(null);
                });
    }

    @Test
    void clear() {
        storageInterface.clear();

        assertEquals(0, storageInterface.size());
    }

    @Test
    void getAllSorted() {
        List<Resume> r = storageInterface.getAllSorted();

        assertEquals(storageInterface.size(), r.size());
        assertEquals(r, Arrays.asList(RESUME_1, RESUME_2, RESUME_3, RESUME_4));
        /*assertEquals(RESUME_1, r.get(0));
        assertEquals(RESUME_2, r.get(1));
        assertEquals(RESUME_3, r.get(2));
        assertEquals(RESUME_4, r.get(3));*/
    }

    @Test
    void getAllSortedEmpty() {
        storageInterface.clear();
        List<Resume> r = storageInterface.getAllSorted();

        assertEquals(0, r.size());
    }

    @Test
    void size() {
        assertEquals(4, storageInterface.size());
    }
}
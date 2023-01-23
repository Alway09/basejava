package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.Config;
import com.jobresearch.webapp.resumemodule.exception.*;
import com.jobresearch.webapp.resumemodule.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.jobresearch.webapp.resumemodule.TestData.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final StorageInterface storageInterface;

    public AbstractStorageTest(StorageInterface storage){
        storageInterface = storage;
    }

    @BeforeEach
    void setUp() {
        //storageInterface.save(R4);
        storageInterface.clear();
        storageInterface.save(R1);
        storageInterface.save(R3);
        storageInterface.save(R2);
    }
    @AfterEach
    void tearDown(){
        //storageInterface.clear();
    }

    @Test
    void save() {
        storageInterface.save(R4);
        //assertEquals(UUID_A, arrayStorage.get(UUID_A).getUuid());
        assertTrue(R4.equals(storageInterface.get(UUID_4)));
        assertEquals(4, storageInterface.size());
    }

    @Test
    void saveExist() {
        ExistStorageException thrown = assertThrows(ExistStorageException.class,
                () ->{
                    storageInterface.save(R2);
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

        assertEquals(2, storageInterface.size());
    }

    @Test
    void deleteNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.delete(UUID_4);
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

        assertTrue(o.equals(R3));
    }

    @Test
    void getNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.get(UUID_4);
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
        Resume r = storageInterface.get(UUID_2);
        // modifications
        storageInterface.update(r);

        assertTrue(r.equals(R2));
    }

    @Test
    void updateNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.update(R4);
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
        assertEquals(r, Arrays.asList(R1, R2, R3));
        /*assertEquals(R1, r.get(0));
        assertEquals(R2, r.get(1));
        assertEquals(R3, r.get(2));
        assertEquals(R4, r.get(3));*/
    }

    @Test
    void getAllSortedEmpty() {
        storageInterface.clear();
        List<Resume> r = storageInterface.getAllSorted();

        assertEquals(0, r.size());
    }

    @Test
    void size() {
        assertEquals(3, storageInterface.size());
    }
}
package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.exception.*;
import com.jobresearch.webapp.resumemodule.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private final StorageInterface arrayStorage;
    private static final String UUID_1 = "UUID_1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "UUID_2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "UUID_3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "UUID_4";
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private static final String UUID_A = "UUID_A";
    private static final Resume RESUME_A = new Resume(UUID_A);

    public AbstractArrayStorageTest(StorageInterface storage){
        arrayStorage = storage;
    }

    @BeforeEach
    void setUp() {
        arrayStorage.save(RESUME_1);
        arrayStorage.save(RESUME_2);
        arrayStorage.save(RESUME_3);
        arrayStorage.save(RESUME_4);
    }
    @AfterEach
    void tearDown(){
        arrayStorage.clear();
    }

    @Test
    void save() {
        arrayStorage.save(RESUME_A);
        //assertEquals(UUID_A, arrayStorage.get(UUID_A).getUuid());
        assertTrue(RESUME_A.getUuid().equals(arrayStorage.get(UUID_A).getUuid()));
        assertEquals(5, arrayStorage.size());
    }

    @Test
    void saveExist() {
        ExistStorageException thrown = assertThrows(ExistStorageException.class,
                () ->{
                    arrayStorage.save(RESUME_2);
                });
    }

    @Test
    void saveInFull() {
        try{
            for(int i = 5; i <= AbstractArrayStorage.STORAGE_MAX_SIZE; ++i){
                arrayStorage.save(new Resume());
            }
        }catch (FullStorageException e){
            e.printStackTrace();
            fail();
        }

        FullStorageException thrown = assertThrows(FullStorageException.class,
                () ->{
                    arrayStorage.save(RESUME_A);
                });
    }

    @Test
    void saveNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    arrayStorage.save(null);
                });
    }

    @Test
    void delete() {
        arrayStorage.delete(UUID_1);

        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    arrayStorage.get(UUID_1);
                });

        assertEquals(3, arrayStorage.size());
    }

    @Test
    void deleteNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    arrayStorage.delete(UUID_A);
                });
    }

    @Test
    void deleteNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    arrayStorage.delete(null);
                });
    }

    @Test
    void get() {
        Object o = arrayStorage.get(UUID_3);

        assertTrue(o.equals(RESUME_3));
    }

    @Test
    void getNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    arrayStorage.get(UUID_A);
                });

        //assertEquals("Resume with UUID " + UUID_A + " not exist", thrown.getMessage());
    }

    @Test
    void getNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    arrayStorage.get(null);
                });
    }

    @Test
    void update() {
        Resume r = arrayStorage.get(UUID_4);
        // modifications
        arrayStorage.update(r);

        assertTrue(r.equals(RESUME_4));
    }

    @Test
    void updateNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    arrayStorage.update(RESUME_A);
                });
    }

    @Test
    void updateNull() {
        NullStorageException thrown = assertThrows(NullStorageException.class,
                () ->{
                    arrayStorage.update(null);
                });
    }

    @Test
    void clear() {
        arrayStorage.clear();

        assertEquals(0, arrayStorage.size());
    }

    @Test
    void getAll() {
        Resume[] r = arrayStorage.getAll();

        assertEquals(arrayStorage.size(), r.length);
        assertEquals(RESUME_1, r[0]);
        assertEquals(RESUME_2, r[1]);
        assertEquals(RESUME_3, r[2]);
        assertEquals(RESUME_4, r[3]);
    }

    @Test
    void getAllEmpty() {
        arrayStorage.clear();
        Resume[] r = arrayStorage.getAll();

        assertEquals(0, r.length);
    }

    @Test
    void size() {
        assertEquals(4, arrayStorage.size());
    }
}
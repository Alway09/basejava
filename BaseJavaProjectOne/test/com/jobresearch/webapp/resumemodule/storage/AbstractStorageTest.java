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

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final StorageInterface storageInterface;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_A = "uuidA";

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;
    protected static final Resume RA;

    static {
        //STORAGE_DIR_PATH = "/Users/alvvay/Documents/JavaLearn/basejava/BaseJavaProjectOne/storage";

        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");
        RA = new Resume(UUID_A, "NameA");

        /*R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));*/
    }
    public AbstractStorageTest(StorageInterface storage){
        storageInterface = storage;
    }

    @BeforeEach
    void setUp() {
        storageInterface.save(R4);
        storageInterface.save(R1);
        storageInterface.save(R3);
        storageInterface.save(R2);
    }
    @AfterEach
    void tearDown(){
        storageInterface.clear();
    }

    @Test
    void save() {
        storageInterface.save(RA);
        //assertEquals(UUID_A, arrayStorage.get(UUID_A).getUuid());
        assertTrue(RA.equals(storageInterface.get(UUID_A)));
        assertEquals(5, storageInterface.size());
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

        assertTrue(o.equals(R3));
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

        assertTrue(r.equals(R4));
    }

    @Test
    void updateNotExist() {
        NotExistStorageException thrown = assertThrows(NotExistStorageException.class,
                () ->{
                    storageInterface.update(RA);
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
        assertEquals(r, Arrays.asList(R1, R2, R3, R4));
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
        assertEquals(4, storageInterface.size());
    }
}
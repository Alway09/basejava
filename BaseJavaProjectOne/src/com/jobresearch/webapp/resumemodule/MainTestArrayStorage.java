package com.jobresearch.webapp.resumemodule;

import com.jobresearch.webapp.resumemodule.model.Resume;
import com.jobresearch.webapp.resumemodule.storage.ArrayStorage;
import com.jobresearch.webapp.resumemodule.storage.*;

public class MainTestArrayStorage {
    static final StorageInterface ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        //r1.setUuid("uuid1");
        Resume r2 = new Resume("uuid2");
        //r2.setUuid("uuid2");
        Resume r3 = new Resume("uuid3");
        //r3.setUuid("uuid3");
        Resume r4 = new Resume("uuid4");
        //r4.setUuid("uuid1");
        Resume r5 = new Resume("uuid5");
        //r5.setUuid("uuid5");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        //for (Resume r : ARRAY_STORAGE.getAll()) {
        //    System.out.println(r);
        //}
    }
}

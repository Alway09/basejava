package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    private final int STORAGE_MAX_SIZE = 10000;
    private int size = 0;
    private Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    public void save(Resume resume){
        if(size >= STORAGE_MAX_SIZE){
            System.out.println("com.jobresearch.webapp.resumemodule.storage.ArrayStorage::save - Storage is full");
            return;
        }

        if(findIndex(resume.getUuid()) != -1) {
            System.out.println("com.jobresearch.webapp.resumemodule.storage.ArrayStorage::save - com.jobresearch.webapp.resumemodule.model.Resume is duplicated, not saved");
            return;
        }

        storage[size] = resume;
        ++size;
    }

    public Resume get(String uuid){
        int index = findIndex(uuid);

        if(index == -1){
            System.out.println("com.jobresearch.webapp.resumemodule.storage.ArrayStorage::get - com.jobresearch.webapp.resumemodule.model.Resume with UUID " + uuid + " not found");
            return null;
        }

        return storage[index];
    }

    public void update(Resume resume){
        int index = findIndex(resume.getUuid());

        if(index == -1){
            System.out.println("com.jobresearch.webapp.resumemodule.storage.ArrayStorage::update - com.jobresearch.webapp.resumemodule.model.Resume with UUID " + resume.getUuid() + " not found");
            return;
        }

        storage[index] = resume;
    }

    public void delete(String uuid){
        int index = findIndex(uuid);

        if(index == -1){
            System.out.println("com.jobresearch.webapp.resumemodule.storage.ArrayStorage::delete - com.jobresearch.webapp.resumemodule.model.Resume with UUID " + uuid + " not found");
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        --size;
    }

    public void clear(){
        Arrays.fill(storage, null);
        size = 0;
    }

    public Resume[] getAll(){
        return Arrays.copyOf(storage, size);
    }

    public int size(){
        return size;
    }

    private int findIndex(String uuid){
        for(int i = 0; i < size; ++i){
            if(storage[i].getUuid() == uuid){
                return i;
            }
        }

        return -1;
    }
}

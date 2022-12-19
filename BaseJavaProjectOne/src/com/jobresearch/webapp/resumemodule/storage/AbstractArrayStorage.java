package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements StorageInterface {
    protected static final int STORAGE_MAX_SIZE = 10000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    public final void save(Resume resume){
        if(resume == null){
            return;
        }

        if(size >= STORAGE_MAX_SIZE){
            System.out.println("ArrayStorage::save - Storage is full");
            return;
        }

        int index = findIndex(resume.getUuid());

        if(index >= 0) {
            System.out.println("ArrayStorage::save - Resume is duplicated, not saved");
            return;
        }

        insertResume(resume, index);
        ++size;
    }

    @Override
    public final void delete(String uuid){
        if(uuid == null){
            return;
        }

        int index = findIndex(uuid);

        if(index < 0){
            System.out.println("ArrayStorage::delete - Resume with UUID " + uuid + " not found");
            return;
        }

        fillDeletedResume(index);
        storage[size - 1] = null;
        --size;
    }
    @Override
    public final Resume get(String uuid){
        int index = findIndex(uuid);

        if(index < 0){
            System.out.println("AbstractArrayStorage::get - Resume with UUID " + uuid + " not found");
            return null;
        }

        return storage[index];
    }

    @Override
    public final void update(Resume resume){
        int index = findIndex(resume.getUuid());

        if(index < 0){
            System.out.println("AbstractArrayStorage::update - Resume with UUID " + resume.getUuid() + " not found");
            return;
        }

        storage[index] = resume;
    }
    @Override
    public final void clear(){
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
    @Override
    public final Resume[] getAll(){
        return Arrays.copyOf(storage, size);
    }
    @Override
    public final int size(){
        return size;
    }

    protected abstract void fillDeletedResume(int index);
    protected abstract void insertResume(Resume resume, int index);
    protected abstract int findIndex(String uuid);
}

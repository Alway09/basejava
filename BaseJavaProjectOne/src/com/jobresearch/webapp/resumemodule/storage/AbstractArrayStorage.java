package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements StorageInterface {
    protected static final int STORAGE_MAX_SIZE = 10000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    @Override
    public final Resume get(String uuid){
        int index = findIndex(uuid);

        if(index == -1){
            System.out.println("AbstractArrayStorage::get - Resume with UUID " + uuid + " not found");
            return null;
        }

        return storage[index];
    }

    @Override
    public final void update(Resume resume){
        int index = findIndex(resume.getUuid());

        if(index == -1){
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

    protected abstract int findIndex(String uuid);
    public abstract void save(Resume resume);
    public abstract void delete(String uuid);
}

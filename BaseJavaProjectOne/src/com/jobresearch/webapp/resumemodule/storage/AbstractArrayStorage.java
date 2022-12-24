package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.exception.*;
import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements StorageInterface {
    protected static final int STORAGE_MAX_SIZE = 10000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    public final void save(Resume resume){
        if(resume == null){
            throw new NullStorageException();
        }

        if(size >= STORAGE_MAX_SIZE){
            throw new FullStorageException();
        }

        int index = findIndex(resume.getUuid());

        if(index >= 0) {
            //throw new StorageException(resume.getUuid(), "Resume is duplicated");
            throw new ExistStorageException(resume.getUuid());
        }

        insertResume(resume, index);
        ++size;
    }

    @Override
    public final void delete(String uuid){
        if(uuid == null){
            throw new NullStorageException();
        }

        int index = findIndex(uuid);

        if(index < 0){
            throw new NotExistStorageException(uuid);
        }

        fillDeletedResume(index);
        storage[size - 1] = null;
        --size;
    }
    @Override
    public final Resume get(String uuid){
        if(uuid == null){
            throw new NullStorageException();
        }

        int index = findIndex(uuid);

        if(index < 0){
            throw new NotExistStorageException(uuid);
        }

        return storage[index];
    }

    @Override
    public final void update(Resume resume){
        if(resume == null){
            throw new NullStorageException();
        }

        int index = findIndex(resume.getUuid());

        if(index < 0){
            throw new NotExistStorageException(resume.getUuid());
        }

        storage[index] = resume;
    }
    @Override
    public final void clear(){
        if(size == 0){
            return;
        }

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

package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.exception.*;
import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_MAX_SIZE = 10000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    @Override
    public final void clear(){
        if(size == 0){
            return;
        }

        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /*@Override
    public final List<Resume> getAllSorted(){
        List<Resume> l = List.of(Arrays.copyOf(storage, size));
        l.sort(RESUME_COMPARATOR_UUID);
        return l;
    }*/
    @Override
    protected final List<Resume> doGetAllCopied(){
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public final int size(){
        return size;
    }
    @Override
    protected final void doSave(Resume resume, Integer notExistedKey){
        if(size == STORAGE_MAX_SIZE){
            throw new FullStorageException();
        }

        insertResume(resume, notExistedKey);
        ++size;
    }

    @Override
    protected final void doDelete(Integer existedKey){
        fillDeletedResume(existedKey);
        storage[size - 1] = null;
        --size;
    }

    @Override
    protected final Resume doGet(Integer existedKey){
        return storage[existedKey.intValue()];
    }

    @Override
    protected final void doUpdate(Resume resume, Integer existedKey){
        storage[existedKey.intValue()] = resume;
    }

    @Override
    protected final boolean isExist(Integer key){
        return key.intValue() >= 0;
    }

    protected abstract void fillDeletedResume(int index);
    protected abstract void insertResume(Resume resume, int index);
}

package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected final void fillDeletedResume(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected final void insertResume(Resume resume, int index){
        storage[size] = resume;
    }

    protected final int findIndex(String uuid){
        for(int i = 0; i < size; ++i){
            if(uuid.equals(storage[i].getUuid())){
                return i;
            }
        }

        return -1;
    }
}

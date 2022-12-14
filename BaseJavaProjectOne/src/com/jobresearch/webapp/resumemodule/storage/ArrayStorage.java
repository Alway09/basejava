package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public final void save(Resume resume){
        if(resume == null){
            return;
        }

        if(size >= STORAGE_MAX_SIZE){
            System.out.println("ArrayStorage::save - Storage is full");
            return;
        }

        if(findIndex(resume.getUuid()) != -1) {
            System.out.println("ArrayStorage::save - Resume is duplicated, not saved");
            return;
        }

        storage[size] = resume;
        ++size;
    }


    @Override
    public final void delete(String uuid){
        if(uuid == null){
            return;
        }

        int index = findIndex(uuid);

        if(index == -1){
            System.out.println("ArrayStorage::delete - Resume with UUID " + uuid + " not found");
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        --size;
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

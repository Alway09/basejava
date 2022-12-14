package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public final void save(Resume resume){
        if(resume == null){
            return;
        }

        if(size >= STORAGE_MAX_SIZE) {
            System.out.println("ArrayStorage::save - Storage is full");
            return;
        }

        int index = Arrays.binarySearch(storage, 0, size, resume);

        if(index >= 0) {
            System.out.println("ArrayStorage::save - Resume with UUID " + resume.getUuid() + " is duplicated, not saved");
            return;
        }
        //System.out.println(index);

        index = (index * -1) - 1;
        for(int i = size - 1; i > index; --i){
            storage[i] = storage[i - 1];
        }
        storage[index] = resume;
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

        for(int i = index; i < size - 1; ++i){
            storage[i] = storage[i + 1];
        }
        --size;
    }

    protected final int findIndex(String uuid){
        Resume key = new Resume();
        key.setUuid(uuid);
        int index = Arrays.binarySearch(storage, 0, size, key);

        if(index < 0){
            return -1;
        }
        return index;
    }
}

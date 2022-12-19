package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected final void fillDeletedResume(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected final void insertResume(Resume resume, int index){
        index = (index * -1) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    protected final int findIndex(String uuid){
        Resume key = new Resume();
        key.setUuid(uuid);
        int index = Arrays.binarySearch(storage, 0, size, key);

        return index;
    }
}

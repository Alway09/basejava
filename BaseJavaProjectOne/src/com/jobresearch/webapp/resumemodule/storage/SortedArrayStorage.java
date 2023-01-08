package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    //private static final Comparator<Resume> RESUME_COMPARATOR = (r1, r2) -> r1.getUuid().compareTo(r2.getUuid());
    //private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);
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

    protected final Integer getSearchKey(String uuid){
        Resume key = new Resume(uuid, "");
        int index = Arrays.binarySearch(storage, 0, size, key, RESUME_COMPARATOR_UUID);

        return index;
    }
}

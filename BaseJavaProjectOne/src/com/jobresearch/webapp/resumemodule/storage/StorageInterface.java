package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;

import java.util.List;

public interface StorageInterface {

    void save(Resume resume);

    Resume get(String uuid);

    void update(Resume resume);

    void delete(String uuid);

    void clear();

    //Resume[] getAll();
    List<Resume> getAllSorted();

    int size();
}

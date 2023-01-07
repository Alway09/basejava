package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class MapStorage extends AbstractStorage{
    @Override
    public void clear() {

    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<Resume>();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void doSave(Resume r, Object notExistedKey) {

    }

    @Override
    protected void doDelete(Object existedKey) {

    }

    @Override
    protected Resume doGet(Object existedKey) {
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Object existedKey) {

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(Object key) {
        return false;
    }

    @Override
    protected List<Resume> doGetAllCopied() {
        return null;
    }
}

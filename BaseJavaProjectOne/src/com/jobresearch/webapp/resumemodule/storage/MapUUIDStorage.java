package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;

import java.util.*;

public class MapUUIDStorage extends AbstractStorage<String>{
    private final Map<String, Resume> map = new HashMap<>();
    @Override
    protected void doSave(Resume r, String notExistedKey) {
        map.put(notExistedKey, r);
    }

    @Override
    protected void doDelete(String existedKey) {
        map.remove(existedKey);
    }

    @Override
    protected Resume doGet(String existedKey) {
        return map.get(existedKey);
    }

    @Override
    protected void doUpdate(Resume resume, String existedKey) {
        map.put(existedKey, resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String key) {
        return map.containsKey(key);
    }

    @Override
    protected List<Resume> doGetAllCopied() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}

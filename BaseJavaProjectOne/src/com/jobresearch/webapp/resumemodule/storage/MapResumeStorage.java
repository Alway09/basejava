package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume>{
    private final Map<String, Resume> map = new HashMap<>();
    @Override
    protected void doSave(Resume r, Resume notExistedKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume existedKey) {
        map.remove(existedKey.getUuid());
    }

    @Override
    protected Resume doGet(Resume existedKey) {
        return existedKey;
    }

    @Override
    protected void doUpdate(Resume resume, Resume existedKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
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

package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer>{
    private final List<Resume> list = new ArrayList<>();
    @Override
    public final void clear() {
        list.clear();
    }

    @Override
    public final List<Resume> doGetAllCopied() {
        return new ArrayList<>(list);
    }

    @Override
    public final int size() {
        return list.size();
    }
    @Override
    protected final void doSave(Resume r, Integer notExistedKey) {
        list.add(r);
    }

    @Override
    protected final void doDelete(Integer existedKey) {
        list.remove(existedKey.intValue());
    }

    @Override
    protected final Resume doGet(Integer existedKey) {
        return list.get(existedKey.intValue());
    }

    @Override
    protected final void doUpdate(Resume resume, Integer existedKey) {
        list.set(existedKey, resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for(int i = 0; i < list.size(); ++i){
            if(list.get(i).getUuid().equals(uuid)){
                return i;
            }
        }

        return null;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }
}

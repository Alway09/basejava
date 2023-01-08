package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.exception.*;
import com.jobresearch.webapp.resumemodule.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements StorageInterface{
    protected final static Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected final Comparator<Resume> RESUME_COMPARATOR_UUID = Comparator.comparing(Resume::getUuid);
    //protected final Comparator<Resume> RESUME_COMPARATOR_NAME = Comparator.comparing(Resume::getFullName);

    @Override
    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        checkNull(resume);

        SK key = getNotExistedKey(resume.getUuid());
        doSave(resume, key);
    }

    @Override
    public final void delete(String uuid){
        LOG.info("Delete " + uuid);
        checkNull(uuid);

        SK key = getExistedKey(uuid);
        doDelete(key);
    }

    @Override
    public final Resume get(String uuid){
        LOG.info("Get " + uuid);
        checkNull(uuid);

        SK key = getExistedKey(uuid);
        return doGet(key);
    }

    @Override
    public final void update(Resume resume){
        LOG.info("Update " + resume);
        checkNull(resume);

        SK key = getExistedKey(resume.getUuid());
        doUpdate(resume, key);
    }

    @Override
    public List<Resume> getAllSorted(){
        LOG.info("getAllSorted");
        List<Resume> list = doGetAllCopied();
        Collections.sort(list);
        return list;
    }

    private SK getNotExistedKey(String uuid){
        SK key = getSearchKey(uuid);
        if(isExist(key)){
            LOG.warning("Resume with id " + uuid + " is already exist");
            throw new ExistStorageException(uuid);
        }

        return key;
    }

    private SK getExistedKey(String uuid){
        SK key = getSearchKey(uuid);
        if(!isExist(key)){
            LOG.warning("Resume with id " + uuid + " is not exist");
            throw new NotExistStorageException(uuid);
        }

        return key;
    }

    private void checkNull(Object o){
        if(o == null){
            LOG.warning("Tried to operate with null object");
            throw new NullStorageException();
        }
    }

    protected abstract void doSave(Resume r, SK notExistedKey);
    protected abstract void doDelete(SK existedKey);
    protected abstract Resume doGet(SK existedKey);
    protected abstract void doUpdate(Resume resume, SK existedKey);
    protected abstract SK getSearchKey(String uuid);
    protected abstract boolean isExist(SK key);
    protected abstract List<Resume> doGetAllCopied();
}

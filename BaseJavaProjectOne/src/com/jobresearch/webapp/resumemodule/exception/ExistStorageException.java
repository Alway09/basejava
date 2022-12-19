package com.jobresearch.webapp.resumemodule.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super(uuid, "Resume with UUID " + uuid + " already exist");
    }
}

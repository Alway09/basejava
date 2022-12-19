package com.jobresearch.webapp.resumemodule.exception;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid){
        super(uuid, "Resume with UUID " + uuid + " not exist");
    }
}

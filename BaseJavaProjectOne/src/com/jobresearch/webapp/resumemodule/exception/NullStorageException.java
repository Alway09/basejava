package com.jobresearch.webapp.resumemodule.exception;

public class NullStorageException extends StorageException{
    public NullStorageException() {
        super("Null reference has been passed in storage method");
    }
}

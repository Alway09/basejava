package com.jobresearch.webapp.resumemodule.exception;

public class FullStorageException extends StorageException{
    public FullStorageException() {
        super("Storage is full");
    }
}

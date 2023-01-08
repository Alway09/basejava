package com.jobresearch.webapp.resumemodule.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String uuid, String message){
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message){
        super(message);
        uuid = null;
    }

    public StorageException(String message, String uuid, Exception e){
        super(message, e);
        this.uuid = uuid;
    }

    public final String getUuid(){ return uuid; }
}

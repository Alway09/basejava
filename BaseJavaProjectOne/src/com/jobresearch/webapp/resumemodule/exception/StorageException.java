package com.jobresearch.webapp.resumemodule.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid, Exception e){
        super(message, e);
        this.uuid = uuid;
    }

    public StorageException(String message, Exception e){
        this(message, null, e);
    }

    public StorageException(String uuid, String message){
        this(message, uuid, null);
    }

    public StorageException(String message){
        this(null, message);
    }
    public StorageException(Exception e) {super(e.getMessage(), e); uuid = null; }

    public final String getUuid(){ return uuid; }
}

package com.jobresearch.webapp.resumemodule.model;

public class Resume {
    private String uuid;

    public String getUuid(){
        return uuid;
    }

    public void setUuid(String id){
        uuid = id;
    }
    @Override
    public String toString(){
        return uuid;
    }
}

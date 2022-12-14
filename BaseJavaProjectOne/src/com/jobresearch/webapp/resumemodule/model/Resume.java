package com.jobresearch.webapp.resumemodule.model;

public class Resume implements Comparable<Resume> {
    private String uuid;

    public String getUuid(){
        return uuid;
    }

    public void setUuid(String id){
        uuid = id;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) { return true; }
        if((o == null) || (getClass() != o.getClass())) { return false; }

        Resume r = (Resume) o;
        return uuid.equals(r.getUuid());
    }

    @Override
    public int hashCode(){
        return uuid.hashCode();
    }

    @Override
    public String toString(){
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.getUuid());
    }
}

package com.jobresearch.webapp.resumemodule.model;

import java.util.*;

public class Resume implements Comparable<Resume>{
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName, String uuid){
        Objects.requireNonNull(uuid, "UUID can't be null");
        Objects.requireNonNull(fullName, "fullName can't be null");
        this.fullName = fullName;
        this.uuid = uuid;
    }

    public Resume(String fullName){
        this(fullName, UUID.randomUUID().toString());
    }

    public String getUuid(){
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) { return true; }
        if((o == null) || (getClass() != o.getClass())) { return false; }

        Resume r = (Resume) o;
        return uuid.equals(r.getUuid()) & fullName.equals(r.getFullName());
    }

    @Override
    public int hashCode(){
        return uuid.hashCode();
    }

    @Override
    public String toString(){
        return fullName + "@" + uuid;
    }

    @Override
    public int compareTo(Resume o) {
        int res = fullName.compareTo(o.getFullName());
        return res != 0 ? res : uuid.compareTo(o.getUuid());
    }

    public String getContact(ContactType type){
        return contacts.get(type);
    }

    public Section getSection(SectionType type){
        return sections.get(type);
    }
}

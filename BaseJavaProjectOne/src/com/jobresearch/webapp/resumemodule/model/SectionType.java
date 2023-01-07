package com.jobresearch.webapp.resumemodule.model;

public enum SectionType {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образованике");

    private final String title;

    SectionType(String title){
        this.title = title;
    }

    public final String getTitle(){
        return title;
    }
}

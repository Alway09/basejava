package com.jobresearch.webapp.resumemodule.storage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest(){
        arrayStorage = new ArrayStorage();
    }
}
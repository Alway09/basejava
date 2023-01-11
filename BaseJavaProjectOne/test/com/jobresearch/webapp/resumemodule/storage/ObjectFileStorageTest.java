package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.storage.serializer.ObjectStreamSerializer;

public class ObjectFileStorageTest extends AbstractStorageTest{
    public ObjectFileStorageTest(){
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}

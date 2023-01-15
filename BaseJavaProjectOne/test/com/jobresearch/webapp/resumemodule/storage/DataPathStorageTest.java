package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest{
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}

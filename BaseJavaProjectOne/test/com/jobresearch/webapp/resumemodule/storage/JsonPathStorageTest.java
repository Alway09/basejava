package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}

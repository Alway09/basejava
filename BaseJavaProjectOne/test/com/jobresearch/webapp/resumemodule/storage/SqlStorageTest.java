package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}

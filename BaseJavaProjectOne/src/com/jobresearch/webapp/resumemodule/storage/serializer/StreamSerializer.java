package com.jobresearch.webapp.resumemodule.storage.serializer;

import com.jobresearch.webapp.resumemodule.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    void doWrite(Resume r, OutputStream os) throws IOException;

    Resume doRead(InputStream is) throws IOException;
}

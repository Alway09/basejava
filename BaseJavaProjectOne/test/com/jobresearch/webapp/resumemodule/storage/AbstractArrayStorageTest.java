package com.jobresearch.webapp.resumemodule.storage;

import com.jobresearch.webapp.resumemodule.exception.FullStorageException;
import com.jobresearch.webapp.resumemodule.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    protected AbstractArrayStorageTest(AbstractArrayStorage arrayStorage){
        super(arrayStorage);
    }

    @Test
    void saveInFull() {
        try{
            for(int i = 4; i <= AbstractArrayStorage.STORAGE_MAX_SIZE; ++i){
                storageInterface.save(new Resume("SomeName"));
            }
        }catch (FullStorageException e){
            e.printStackTrace();
            fail();
        }

        FullStorageException thrown = assertThrows(FullStorageException.class,
                () ->{
                    storageInterface.save(new Resume("SomeName_exception"));
                });
    }
}

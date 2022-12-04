import java.util.Arrays;

public class ArrayStorage {
    private final int STORAGE_MAX_SIZE = 10000;
    private int size = 0;
    private Resume[] storage = new Resume[STORAGE_MAX_SIZE];

    public void save(Resume resume){
        if(size >= STORAGE_MAX_SIZE){
            System.out.println("ArrayStorage::save - Storage is full");
            return;
        }

        if(findIndex(resume.getUuid()) != -1) {
            System.out.println("ArrayStorage::save - Resume is duplicated, not saved");
            return;
        }

        storage[size] = resume;
        ++size;
    }

    public Resume get(String uuid){
        int index = findIndex(uuid);

        if(index == -1){
            System.out.println("ArrayStorage::get - Invalid index");
            return null;
        }

        return storage[index];
    }

    public void delete(String uuid){
        int index = findIndex(uuid);

        if(index == -1){
            //System.out.println("ArrayStorage::delete - Invalid index");
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        --size;
    }

    public void clear(){
        Arrays.fill(storage, null);
        size = 0;
    }

    public Resume[] getAll(){
        return Arrays.copyOf(storage, size);
    }

    public int size(){
        return size;
    }

    private int findIndex(String uuid){
        for(int i = 0; i < size; ++i){
            if(storage[i].getUuid() == uuid){
                return i;
            }
        }

        return -1;
    }
}

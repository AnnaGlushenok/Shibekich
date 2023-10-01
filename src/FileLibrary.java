import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileLibrary implements CRUD {
    private final String PATH = "././libraries.json";
    private List<Library> libraries;

    public FileLibrary() {
        libraries = read();
        if (libraries == null)
            libraries = new ArrayList<>();
    }

    private synchronized List<Library> read() {
        try (FileReader fr = new FileReader(PATH)) {
            libraries = new Gson().fromJson(fr, new TypeToken<List<Library>>() {
            }.getType());
        } catch (IOException e) {
            e.getStackTrace();
        }
        return libraries;
    }

    private synchronized void write() {
        try (FileWriter writer = new FileWriter(PATH)) {
            new Gson().toJson(libraries, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized List<Library> get() {
        return libraries;
    }

    @Override
    public synchronized void create(Library library) {
        libraries.add(library);
        write();
    }

    @Override
    public synchronized void update(Library library) {
        libraries.removeIf(el -> el.getId() == library.getId());
        libraries.add(library);
        write();
    }

    @Override
    public synchronized void delete(int id) {
        libraries.removeIf(el -> el.getId() == id);
        write();
    }
}

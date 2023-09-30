import java.util.List;

public interface CRUD {
    List<Library> get();

    void create(Library library);

    void update(Library library);

    void delete(int id);
}

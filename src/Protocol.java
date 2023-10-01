import java.util.List;

public class Protocol {
    private CRUD protocol;

    public static void main(String[] args) {

    }

    /**
     * change protocol type
     *
     * @param protocol protocol type
     */
    public void setProtocol(ProtocolType protocol) {
        if (protocol.equals(ProtocolType.FILE))
            this.protocol = new FileLibrary();
        else if (protocol.equals(ProtocolType.DATABASE))
            this.protocol = new DatabaseLibrary();
    }

    public List<Library> get() {
        return protocol.get();
    }

    public void create(Library library) {
        protocol.create(library);
    }

    public void update(Library library) {
        protocol.update(library);
    }

    public void delete(int id) {
        protocol.delete(id);
    }
}

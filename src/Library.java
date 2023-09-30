import com.google.gson.Gson;

public class Library implements Comparable<Library> {
    private int id;
    private int price;
    private int count;
    private String author;
    private String name;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Library(int id, String author, String name, int price, int count) {
        this.id = id;
        this.price = price;
        this.count = count;
        this.author = author;
        this.name = name;
    }

    public Library(String json) {//{"id":2,"price":4,"count":4,"author":"q56y","name":""}
        Library obj = new Gson().fromJson(json, Library.class);
        this.id = obj.getId();
        this.price = obj.getPrice();
        this.count = obj.getCount();
        this.author = obj.getAuthor();
        this.name = obj.getName();
    }

    /**
     * @param param enter searched substring
     * @return string matching regular expression
     **/
    public String find(String param) {
        return ".*" + param + ".*";
    }

    public String toString() {
        return String.format("%d %s %s %d %d", this.id, this.author, this.name, this.price, this.count);
    }

    public int compareTo(Library o) {
        return this.price - o.price;
    }
}

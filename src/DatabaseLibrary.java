import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseLibrary implements CRUD {
    private Connection con;

    /**
     * constructor try to connect with database else stack trace error
     */
    public DatabaseLibrary() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized List<Library> get() {
        List<Library> libraries = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM library")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int price = resultSet.getInt("price");
                int count = resultSet.getInt("count");
                String author = resultSet.getString("author");
                String name = resultSet.getString("name");

                Library library = new Library(id, author, name, price, count);
                libraries.add(library);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libraries;
    }

    @Override
    public synchronized void create(Library library) {
        String sql = "INSERT INTO library (id, price, count, author, name) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, library.getId());
            preparedStatement.setInt(2, library.getPrice());
            preparedStatement.setInt(3, library.getCount());
            preparedStatement.setString(4, library.getAuthor());
            preparedStatement.setString(5, library.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void update(Library library) {
        String sql = "UPDATE library SET price = ?, count = ?, author = ?, name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, library.getPrice());
            preparedStatement.setInt(2, library.getCount());
            preparedStatement.setString(3, library.getAuthor());
            preparedStatement.setString(4, library.getName());
            preparedStatement.setInt(5, library.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void delete(int id) {
        String sql = "DELETE FROM library WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package ru.geekbrains.homeworks.Server;

import ru.geekbrains.homeworks.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class AuthenticationService {
    private Set<Client> clients;
    public AuthenticationService () {
        clients = Set.of(
                new Client(1, "l1", "p1", "user1"),
                new Client(2,"l2", "p2", "user2"),
                new Client(3,"l3", "p3", "user3")
        );
    }

    public Client findByLoginAndPassword(String login, String password){
        for (Client c: clients){
            if (c.getLogin().equals(login) && c.getPassword().equals(password)){
                return c;
            }
        }
        return null;
    }

    static public class Client {
        private long id;
        private String login;
        private String password;
        private String name;

        public Client(long id, String login, String password, String name) {
            this.id = id;
            this.login = login;
            this.password = password;
            this.name = name;
        }

        public long getId(){return id;}
        public String getLogin (){
            return login;
        }
        public String getPassword (){
            return password;
        }
        public String getName (){
            return name;
        }
    }

    public class ClientService{
        public Client findByLoginAndPassword (String login, String password) throws SQLException {
            Connection connection = Main.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM clients WHERE login = ? AND password = ?");
                statement.setString(1,login);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    return new Client(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("name")
                    );
                }
                return null;
            } catch (SQLException e){
                throw new RuntimeException("SWW", e);
            } finally {
                connection.close();
            }
        }
    }
}

package ru.geekbrains.homeworks.Server;

import ru.geekbrains.homeworks.Main;

import java.sql.*;
import java.util.Set;

public class AuthenticationService {
    private Set<Client> clients;

    public Client findByLoginAndPassword(String login, String password){
        for (Client c: clients){
            if (c.getLogin().equals(login) && c.getPassword().equals(password)){
                return c;
            }
        }
        return null;
    }

    static public class Client {
        private String name;
        private String login;
        private String password;

        public Client(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.password = password;
        }

        public String getName () {return name;}
        public String getLogin (){
            return login;
        }
        public String getPassword (){
            return password;
        }

        public void addToSql (String name, String login, String password) throws Exception{
            Connection connection = Main.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO clients (id, name, login, password) VALUES (1, name, login, password);");
        }

    }

    public static class ClientService{
        private String login;
        private String password;
        public ClientService(String login, String password){
            this.login = login;
            this.password = password;
        }
        public boolean findByLoginAndPassword (String login, String password) throws SQLException {
            Connection connection = Main.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM clients WHERE login = ? AND password = ?");
                statement.setString(1,login);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    return true;
                    /*return new Client(
                            rs.getString("name"),
                            rs.getString("login"),
                            rs.getString("password")
                    );*/
                }
                return false;
            } catch (SQLException e){
                throw new RuntimeException("SWW", e);
            } finally {
                connection.close();
            }
        }
    }
}



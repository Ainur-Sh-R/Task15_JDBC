package StudentsService.dao;

import StudentsService.connectionManager.ConnectionManager;
import StudentsService.connectionManager.ConnectionManagerJdbcImpl;
import StudentsService.pojo.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl implements CityDao {
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    /**
     * выполняет поиск города в базе данных по его имени
     * @return city
     */
    @Override
    public City searchCityByName(String name) {
        Connection connection = connectionManager.getConnection();
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from cities WHERE name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("citizens"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return city;
    }

    /**
     * выполняет поиск города в базе данных по его 'id'
     * @return city
     */
    @Override
    public City searchCityById(int id) {
        Connection connection = connectionManager.getConnection();
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from cities WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("citizens"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return city;
    }

    /**
     * добавляет город в базу данных
     * @return true при успешном добавлении, иначе - false
     */
    @Override
    public boolean addCity(City city) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO cities(id, name, citizens) VALUES (default , ?, ?)")
        ) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getCitizens());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

package StudentsService.dao.mappers;

import StudentsService.dao.CityDao;
import StudentsService.dao.CityDaoImpl;
import StudentsService.pojo.City;
import StudentsService.pojo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper {

    private StudentMapper() {
    }

    /**
     * Добавляет студента и его город в базу данных
     * если сохраняем город, а его нет в базе данных, то добавляем его
     * если город есть в базе данных, то получаем его 'id' и назначем студенту
     */
    public static void addStudentToBD(Student student, PreparedStatement statement) throws SQLException {
        statement.setString(1, student.getName());
        statement.setString(2, student.getFamily_name());
        statement.setInt(3, student.getAge());
        statement.setString(4, student.getContact());
        student = checkStudentCity(student);
        statement.setInt(5, student.getIdCity());
        statement.execute();
    }

    /**
     * возвращает студента из базы данных по его 'id'
     *
     * @return student
     */
    public static Student getStudentByIdFromBD(int id, PreparedStatement statement) throws SQLException {
        CityDao cityDao = new CityDaoImpl();
        Student student = null;
        statement.setInt(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int cityId = resultSet.getInt("city");
                City city = cityDao.searchCityById(cityId);

                student = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("family_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("contact"),
                        city);
            }
        }
        return student;
    }

    /**
     * обновляет данные о студенте в базе данных по его 'id'
     */
    public static void updateStudentToBD(Student student, PreparedStatement statement) throws SQLException {
        student = checkStudentCity(student);
        statement.setString(1, student.getName());
        statement.setString(2, student.getFamily_name());
        statement.setInt(3, student.getAge());
        statement.setString(4, student.getContact());
        statement.setInt(5, student.getIdCity());
        statement.setInt(6, student.getId());
        statement.execute();
    }

    /**
     * удаляет студента из базы данных по 'id'
     */
    public static void deleteStudentByIdFromBD(int id, PreparedStatement statement) throws SQLException {
        statement.setInt(1, id);
        statement.execute();
    }

    /**
     * удаляет студента по значению (объекту)
     */
    public static void deleteStudentByNameFromBD(Student student, PreparedStatement statement) throws SQLException {
        statement.setString(1, student.getName());
        statement.setString(2, student.getFamily_name());
        statement.execute();
    }


    /**
     * Проверяет наличие города указанного у студента в базе данных
     * если сохраняем город, а его нет в базе данных, то добавляем его
     * если город есть в базе данных, то получаем его 'id' и назначем студенту
     *
     * @return student
     */
    private static Student checkStudentCity(Student student) {
        CityDao cityDao = new CityDaoImpl();
        if (student.getCity() != null) {
            String cityName = student.getCity().getName();
            City city = cityDao.searchCityByName(student.getCity().getName());
            if (city == null) {
                cityDao.addCity(student.getCity());
                student.setIdCity(cityDao.searchCityByName(cityName).getId());
            } else {
                student.setIdCity(city.getId());
            }
        }
        return student;
    }
}



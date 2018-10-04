package StudentsService;

import StudentsService.connectionManager.ConnectionManager;
import StudentsService.connectionManager.ConnectionManagerJdbcImpl;
import StudentsService.dao.StudentDaoImpl;
import StudentsService.pojo.City;
import StudentsService.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

    //очищаем БД
        ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("delete from cities")) {
            statement.execute();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement("delete from students")) {
            statement.execute();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }


        //заполняем БД данными (студентами, городами)
        new InsertToDB();

        StudentDaoImpl studentDao = new StudentDaoImpl();

        //возвращаем студента по его 'id'
        Student student01 = studentDao.getStudentById(2);
        System.out.println("возвращаем студента по его 'id'");
        System.out.println(student01+ "\n");

        //обновим данные о студенте в базе данных по его 'id'
        Student student03 =
                new Student(3, "Marat", "Kirilov", 55, "+798888888",
                        new City(0, "V. Novgorod", 500000));
        boolean b3 = studentDao.update(student03);
        System.out.println("обновим данные о студенте в базе данных: " +b3 + "\n");

        //удаляем студента из базы по его 'id'
        boolean b = studentDao.deleteStudentById(3);
        System.out.println("удаляем студента из базы по его 'id': " + b + "\n");

        //удаляем студента по имени и фамилии
        Student student02 = new Student
                (0, "Konstantin", "Hmelov", 0, "",
                        new City(0, "Rostov", 20));
        boolean b2 = studentDao.deleteStudentByName(student02);
        System.out.println("удаляем студента по имени и фамилии: " + b2);
    }
}

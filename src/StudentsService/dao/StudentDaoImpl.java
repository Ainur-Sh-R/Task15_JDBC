package StudentsService.dao;

import StudentsService.connectionManager.ConnectionManager;
import StudentsService.connectionManager.ConnectionManagerJdbcImpl;
import StudentsService.dao.mappers.StudentMapper;
import StudentsService.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    /**
     * Добавляет студента и его город в базу данных
     */
    @Override
    public boolean addStudent(Student student) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO students VALUES (DEFAULT , ?, ?, ?, ?, ?)")) {
            StudentMapper.addStudentToBD(student, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * возвращает студента из базы данных по его 'id'
     *
     * @return student
     */
    @Override
    public Student getStudentById(int id) {
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from students WHERE id = ?")) {
            student = StudentMapper.getStudentByIdFromBD(id, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    /**
     * обновляет данные о студенте в базе данных по его 'id'
     * @return true если студент есть в базе данных, иначе false
     */
    @Override
    public boolean update(Student student) {
        if (student.getId() != 0) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET name=?, family_name=?, " +
                            "age=?, contact=?, city=? WHERE id=?")) {
                StudentMapper.updateStudentToBD(student, statement);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * удаляет студента из базы данных по 'id'
     */
    @Override
    public boolean deleteStudentById(int id) {
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM students WHERE id=?")) {
            StudentMapper.deleteStudentByIdFromBD(id, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * удаляет студента по имени и фамилии
     */
    @Override
    public boolean deleteStudentByName(Student student) {
        if ((student.getName() != null) && (student.getFamily_name() != null)) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM students WHERE name =? and family_name=?")) {
                StudentMapper.deleteStudentByNameFromBD(student, statement);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

}

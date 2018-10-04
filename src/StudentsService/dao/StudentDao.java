package StudentsService.dao;

import StudentsService.pojo.Student;

public interface StudentDao {
    public boolean addStudent(Student student);
    public Student getStudentById(int id);
    public boolean update(Student student);
    public boolean deleteStudentById(int id);
    public boolean deleteStudentByName(Student student);
}

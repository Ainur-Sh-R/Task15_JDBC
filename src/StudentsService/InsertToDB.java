package StudentsService;

import StudentsService.dao.StudentDao;
import StudentsService.dao.StudentDaoImpl;
import StudentsService.pojo.City;
import StudentsService.pojo.Student;

public class InsertToDB {
    public InsertToDB() {
        //создаем города
        City city_1 = new City(0, "Sankt-Peterburg", 8000000);
        City city_2 = new City(0, "Kazan", 2000000);
        City city_3 = new City(0, "Samara", 1000000);
        City city_4 = new City(0, "Moscow", 15000000);
        City city_5 = new City(0, "Ufa", 1000000);


        //создаем студентов с и добавляем их вместе с городами в БД
        StudentDao studentDao = new StudentDaoImpl();
        Student student_1 = new Student
                (0, "Konstantin", "Hmelov", 20, "79224578965", city_1);
        Student student_2 = new Student
                (0, "Michail", "Igorev", 22, "+7933333333", city_4);
        Student student_3 = new Student
                (0, "Marat", "Shamut", 21, "+792265414785", city_4);
        Student student_4 = new Student
                (0, "Anna", "Shigapova", 29, "+799965414756", city_2);
        Student student_5 = new Student
                (0, "Kiril", "Kabz", 32, "+799911122244", city_3);
        Student student_6 = new Student
                (0, "Liliya", "Lapteva", 25, "+79995555244", city_5);
        studentDao.addStudent(student_1);
        studentDao.addStudent(student_2);
        studentDao.addStudent(student_3);
        studentDao.addStudent(student_4);
        studentDao.addStudent(student_5);
        studentDao.addStudent(student_6);
    }
}

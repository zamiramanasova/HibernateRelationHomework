package org.example.five;

import org.example.four.User;
import org.example.two.Book;
import org.example.two.Library;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AppFive {
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();

        Student student1 = new Student("Zamira", 32);
        Student student2 = new Student("Bermet", 27);
        Student student3 = new Student("Zarina", 34);
        Student student4 = new Student("Akysia", 16);
        Course course1 = new Course("Java", 8000);
        Course course2 = new Course("Cook", 12000);
        Course course3 = new Course("Make-up", 40000);
        Course course4 = new Course("CSS", 9000);
        System.out.println("===================================================================");
        System.out.println("СОЗДАНИЕ ТАБЛИЦ");
        create(student1,course1);
        create(student2,course2);
        create(student3,course3);
        create(student4,course4);
        System.out.println("===================================================================");
        System.out.println("Чтение таблиц");
        read();
        for(Student student: read()) {
            System.out.println(student);
        }

        readCourse();
        for(Course course: readCourse()) {
            System.out.println(course);
        }

    }

    public static void create(Student student, Course course){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(course);
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Good added!!!!!!");
        }catch(Exception e) {
            e.getMessage();
        }
    }

    public static List<Student> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Student> studentList = null;
        try {
            transaction = session.beginTransaction();
            studentList = session.createQuery("FROM Student ").getResultList();
            transaction.commit();
            System.out.println(studentList.size() + "students");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return studentList;

    }

    public static List<Course> readCourse() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Course> courseList = null;
        try {
            transaction = session.beginTransaction();
            courseList = session.createQuery("FROM Course ").getResultList();
            transaction.commit();
            System.out.println(courseList.size() + "courses");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return courseList;

    }
}

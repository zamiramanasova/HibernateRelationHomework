package org.example.four;

import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AppManyToOneBi {
    // отношение ManyToOne bi-directional
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();

        User user1 = new User("Akysia", 12);
        User user2 = new User("Wawa", 23);
        User user3 = new User("Qaqa", 15);
        User user4 = new User("Haha", 16);
        Passport passport1 = new Passport(12345678L);
        Passport passport2 = new Passport(13333333L);
        Passport passport3 = new Passport(22222228L);
        Passport passport4 = new Passport(33333333L);
        create(user1,passport1);
        create(user2,passport2);
        create(user3,passport3);
        create(user4,passport4);

        System.out.println("Вывод всех пользователей");
        read();
        for(User user: read()) {
            System.out.println(user);
        }

        System.out.println("Вывод пользователя по ID");
        System.out.println(getId(1));

        System.out.println("УДАЛЕНИЕ ПОЛЬЗОВАТЕЛЯ");
        deleteUser(2);
    }

    public static void create(User user, Passport passport) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            user.setPassport(passport);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("УСПЕШНОЕ ДОБАВЛЕНИЕ В ТАБЛИЦУ");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static List<User> read() {
        List<User> userList = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            userList = session.createQuery("FROM User ").getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("ВЫВОД ВСЕХ ПОЛЬЗОВАТЕЛЕЙ");
        } catch (Exception e) {
            e.getMessage();
        }
        return userList;
    }

    public static User getId(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public static void deleteUser(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("Пользователь :" + user + " удален!");
        }catch(Exception e) {
            e.getMessage();
        }
    }
}

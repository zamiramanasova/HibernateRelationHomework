package org.example.one;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class ManyToOneApp {
    public static void main(String[] args) {
        //здесь реализация ManyToOne Uni directional. С использованием Ломбок. Проблема в том что
        // авто создании конструктора идет с айди.

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Mother mother1 = new Mother(1, "Jessy","Patrik", 34);
        Mother mother2 = new Mother(2, "Lessy","Vatrik", 45);
        Mother mother3 = new Mother(3, "Ressy","Gatrik", 39);
        Mother mother4 = new Mother(4, "Messy","Matrik", 51);
        Children children1 = new Children(1,"Marik","Patrik",12,2,mother1);
        Children children2 = new Children(2,"Varik","Vatrik",12,2,mother2);
        Children children3 = new Children(3,"Garik","Gatrik",12,2,mother3);
        Children children4 = new Children(4,"Tarik","Matrik",12,2,mother4);

        System.out.println("==================================================================================");
        System.out.println("СОЗДАНИЕ ТАБЛИЦ");
        createChildren(mother1,children1);
        createChildren(mother2,children2);
        createChildren(mother3,children3);
        createChildren(mother4,children4);

        System.out.println("========================================================================");
        System.out.println("ЧТЕНИЕ ВСЕХ СУЩНОСТЕЙ");
        read();
        List<Children> childrenList = read();
        for (Children children: childrenList) {
            System.out.println(children);
        }
        System.out.println("========================================================================");
        System.out.println("УДАЛЕНИЕ ПО ID");
        delete(3);
        System.out.println("========================================================================");
        System.out.println("УДАЛЕНИЕ ТАБЛИЦ");
        dropTable();
    }

    public static void createChildren(Mother mother, Children children) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(mother);
            session.save(children);
            transaction.commit();
            System.out.println("Successfully");
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static List<Children> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Children> childrenList = null;
        try {
            transaction = session.beginTransaction();
            childrenList = session.createQuery("FROM Children ").getResultList();
            transaction.commit();
            System.out.println("List: " + childrenList.size());
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return childrenList;
    }

    public static void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Children children = session.get(Children.class, id);
            session.delete(children);
            transaction.commit();
            System.out.println("====================================================================");
            System.out.println(children + " DELETED DELETED DELETED");
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static void dropTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Children ");
            query.executeUpdate();
            transaction.commit();
            System.out.println("==================================================================");
            System.out.println("SUCCESSFULLY DELETED TABLE");
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}

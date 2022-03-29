package org.example.two;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Hello world!
 *
 */
public class ManyToManyLazy {
    public static void main(String[] args) {
       //по умолчанию использована Lazy загрузка, MANYTOMANY,
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
       Book book1 = new Book("Джейн Эйр", "Шарлотта Бронте");
       Book book2 = new Book("Американская Трагедия", "Теодор Драйзер");
       Book book3 = new Book("Руслан и Людмила", "Александр Сергеевич Пушкин");
       Library library1 = new Library("Библиотека имени Ленина",1924,"Москва");
       Library library2 = new Library("Библиотека имени Тургенева",1885,"Москва");
       Library library3 = new Library("Государственная историческая библиотека",1863,"Москва");

       System.out.println("==================================================================================");
       System.out.println("СОЗДАНИЕ ТАБЛИЦ");
       create(book1,library1);
       create(book2,library2);
       create(book3,library3);

       System.out.println("==================================================================================");
       System.out.println("ВЫВОД ВСЕХ СУЩНОСТЕЙ");
       read();
        List<Book> bookList = read();
        for (Book book: bookList) {
            System.out.println(book);
        }

        readLibrary();
        List<Library> libraryList = readLibrary();
        for (Library library: libraryList) {
            System.out.println(library);
        }

    }

    public static void create(Book book,Library library){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(book);
            session.save(library);
            transaction.commit();
            System.out.println("SAVE good");
        }catch(Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    public static List<Book> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Book> bookList = null;
        try {
            transaction = session.beginTransaction();
            bookList = session.createQuery("FROM Book ").getResultList();
            transaction.commit();
            System.out.println(bookList.size() + "books");
        } catch (Exception e) {
        transaction.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
        return bookList;

    }

    public static List<Library> readLibrary() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Library> libraryList = null;
        try {
            transaction = session.beginTransaction();
            libraryList = session.createQuery("FROM Library ").getResultList();
            transaction.commit();
            System.out.println(libraryList.size() + "libraries");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return libraryList;

    }

}


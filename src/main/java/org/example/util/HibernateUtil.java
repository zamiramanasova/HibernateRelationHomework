package org.example.util;

import org.example.five.Course;
import org.example.five.Student;
import org.example.four.Passport;
import org.example.four.User;
import org.example.one.Children;
import org.example.one.Mother;
import org.example.three.Detail;
import org.example.three.Employee;
import org.example.two.Book;
import org.example.two.Library;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.Property;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "org.postgresql.Driver");
                properties.put(Environment.USER, "postgres");
                properties.put(Environment.PASS, "stulchik");
                properties.put(Environment.HBM2DDL_AUTO, "create");
                properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
                properties.put(Environment.SHOW_SQL, true);
              properties.put(Environment.FORMAT_SQL, true);
                configuration.setProperties(properties);
                // configuration.addAnnotatedClass(Mother.class);
             // configuration.addAnnotatedClass(Children.class);
             // configuration.addAnnotatedClass(Book.class);
              // configuration.addAnnotatedClass(Library.class);
              // configuration.addAnnotatedClass(Employee.class);
                // configuration.addAnnotatedClass(Detail.class);
               //   configuration.addAnnotatedClass(User.class);
                //  configuration.addAnnotatedClass(Passport.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory((org.hibernate
                        .service.ServiceRegistry) serviceRegistry);

                System.out.println("Good connecting");
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return sessionFactory;


    }
}

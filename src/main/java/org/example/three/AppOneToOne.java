package org.example.three;


import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

//использование bi directional ONETOONE
public class AppOneToOne {
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();
        Employee employee1 = new Employee("Varvara", "Varicheva", "IT", 5000);
        Employee employee2 = new Employee("Warvara", "Waricheva", "IT", 9000);
        Detail detail1 = new Detail("Moscow", "89671793585", "var@gmail.com" );
        Detail detail2 = new Detail("Bishkek", "89654676825", "warf@gmail.com" );
        System.out.println("==========================================================================");
        System.out.println("СОЗДАНИЕ ТАБЛИЦ");
        create(employee1,detail1);
        create(employee2,detail2);
        System.out.println("==========================================================================");
        System.out.println("ЧТЕНИЕ ВСЕХ СУЩНОСТЕЙ");
        read();
        List<Employee> employeeList = read();
        for (Employee employee: employeeList) {
            System.out.println(employee);
        }
        readDetail();
        List<Detail> detailList = readDetail();
        for (Detail detail: detailList) {
            System.out.println(detail);
        }
        System.out.println("==========================================================================");
        System.out.println("ВЫВОД ПО ID");
        System.out.println(getEmployeeId(2));
    }


    public static void create(Employee employee, Detail detail){
          try {
              Session session = HibernateUtil.getSessionFactory().openSession();
              session.beginTransaction();
              employee.setDetail(detail);
              session.save(employee);
              System.out.println("Good added : " + employee);
              System.out.println("Good added : " + detail);
              session.close();
          }catch(Exception e) {
              e.getMessage();
          }
    }

    public static List<Employee> read() {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List employeeList = null;
        try {
            transaction = session.beginTransaction();
            employeeList = session.createQuery("FROM Employee").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeList;
    }

    public static List<Detail> readDetail() {
        Session s;
        s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List employeeList = null;
        try {
            transaction = s.beginTransaction();
            employeeList = s.createQuery("FROM Detail").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            s.close();
        }
        return employeeList;
    }

    //по айди выводит только employee без detail
    public static Employee getEmployeeId(long id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.getTransaction().commit();
            System.out.println("ВЫВОД ПО ID");
            System.out.println(employee.getDetail() + " good operation");
            return employee;
        }finally {
            HibernateUtil.getSessionFactory().close();
        }

    }
}

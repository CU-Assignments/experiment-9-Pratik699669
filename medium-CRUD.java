import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class CRUDOperations {
    static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public static void createStudent(String name, int age) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student s = new Student();
        s.setName(name);
        s.setAge(age);
        session.save(s);
        tx.commit();
        session.close();
    }

    public static Student getStudent(int id) {
        Session session = factory.openSession();
        Student s = session.get(Student.class, id);
        session.close();
        return s;
    }

    public static void updateStudent(int id, String name) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, id);
        s.setName(name);
        session.update(s);
        tx.commit();
        session.close();
    }

    public static void deleteStudent(int id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, id);
        session.delete(s);
        tx.commit();
        session.close();
    }
}

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class TransactionService {
    static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void transfer(int fromId, int toId, double amount) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Account from = session.get(Account.class, fromId);
            Account to = session.get(Account.class, toId);

            if (from.getBalance() < amount) throw new RuntimeException("Insufficient funds");

            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);

            session.update(from);
            session.update(to);

            tx.commit();
            System.out.println("Transaction Successful!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Transaction Failed. Rolled back.");
        } finally {
            session.close();
        }
    }
}

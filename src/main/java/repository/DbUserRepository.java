package repository;

import model.Board;
import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.IOException;
import java.util.Collection;

@Repository
public class DbUserRepository implements UserRepository{
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private final EntityTransaction tx;


    public DbUserRepository(){
        this.emf = Persistence.createEntityManagerFactory("test");
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    public void save(User user) {
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public User findById(String userId) {
        return em.find(User.class, userId);
    }

    public Collection<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

}

package repository;

import model.Board;

import javax.persistence.*;
import java.util.Collection;

public class DbBoardRepository implements BoardRepository{

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private final EntityTransaction tx;

    public DbBoardRepository(){
        this.emf = Persistence.createEntityManagerFactory("test");
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    @Override
    public void save(Board board){
        try {
            tx.begin();
            em.persist(board);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }
    @Override
    public Collection<Board> findAll(){
        return em.createQuery("select b from Board b",Board.class).getResultList();
    }
}


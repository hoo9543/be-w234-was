package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface UserRepository{
    public void save(User user);

    public User findById(String userId);

    public Collection<User> findAll();
}

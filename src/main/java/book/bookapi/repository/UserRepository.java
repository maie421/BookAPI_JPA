package book.bookapi.repository;

import book.bookapi.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id){
       return em.find(User.class,id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u",User.class)
                .getResultList();
    }


}

package book.bookapi.repository;

import book.bookapi.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RatingRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Rating rating){
        em.persist(rating);
    }

    public List<Rating> findAll(){
        return em.createQuery("select r from Rating r",Rating.class)
                .getResultList();
    }

    public Rating findOne(Long id){
        return em.find(Rating.class,id);
    }

    public void Delete(Long id){
        Rating rating=em.find(Rating.class,id);
        em.remove(rating);
    }
}

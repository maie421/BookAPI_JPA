package book.bookapi.repository;

import book.bookapi.domain.Like;
import book.bookapi.domain.Post;
import book.bookapi.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeReopository {

    @PersistenceContext
    private EntityManager em;

    public void save(Like like){
        em.persist(like);
    }

    public List<Like> findAll(){
        return em.createQuery("select l from Like l",Like.class)
                .getResultList();
    }

    public Like findOne(Long id){
        return em.find(Like.class,id);
    }

    public void Delete(Long id){
        Like like = em.find(Like.class, id); // 삭제할 대상 엔티티 조회
        em.remove(like);
    }

}

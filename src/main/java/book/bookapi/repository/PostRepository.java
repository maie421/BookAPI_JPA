package book.bookapi.repository;

import book.bookapi.domain.Post;
import book.bookapi.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p",Post.class)
                .getResultList();
    }

    public Post findOne(Long id){
        return em.find(Post.class,id);
    }

    public void delete(Long id){
        Post post = em.find(Post.class, id); // 삭제할 대상 엔티티 조회

        em.remove(post);
    }
}

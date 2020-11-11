package book.bookapi.repository;

import book.bookapi.domain.Post;
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

}

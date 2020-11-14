package book.bookapi;

import book.bookapi.domain.Like;
import book.bookapi.domain.Post;
import book.bookapi.domain.Rating;
import book.bookapi.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {
            User user = createUser("이름");
            em.persist(user);
            Post post = createPost("테스트", "5", "홍길동","123","한빛","Spring","123","테스트중입니다",user);
            em.persist(post);
            Post post1 = createPost("테스트1", "5", "홍길동1","123","한빛1","Spring","123","테스트중입니다1",user);
            em.persist(post1);
            Like like=createLike(user,post);
            em.persist(like);
            Rating rating=createRating(user,post,"댓글 테스트");
            em.persist(rating);

        }
        private User createUser(String name) {
            User user = new User();
            user.setName(name);
            return user;
        }
        private Post createPost(String content,String score,String Authors,String isbn,String publisher,String title,String thumbnall,String body,User user) {
            Post post= new Post();
            post.setContent(content);
            post.setScore(score);
            post.setAuthors(Authors);
            post.setIsbn(isbn);
            post.setPublisher(publisher);
            post.setTitle(title);
            post.setThumbnail(thumbnall);
            post.setBody(body);
            post.setCreateDate(LocalDateTime.now());
            post.setUser(user);
            return post;
        }
        private Like createLike(User user,Post post) {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            return like;
        }
        private Rating createRating(User user, Post post, String commet){
            Rating rating=new Rating();
            rating.setUser(user);
            rating.setRating(commet);
            rating.setPost(post);
            rating.setCreateDate(LocalDateTime.now());
            return rating;
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> develop

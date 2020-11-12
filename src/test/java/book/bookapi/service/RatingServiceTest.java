package book.bookapi.service;

import book.bookapi.domain.Post;
import book.bookapi.domain.Rating;
import book.bookapi.domain.User;
import book.bookapi.repository.LikeReopository;
import book.bookapi.repository.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RatingServiceTest {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    RatingService ratingService;
    @Autowired
    PostServise postServise;
    @Autowired
    UserService userService;

    @Test
    void save() {
    }


    @Test
    void updatePost() {
    }

    @Test
    void delete() {
    }

    @Test
    @Rollback(value = false)
    public void 댓글저장() throws Exception {
        // Given
        User user = new User();
        user.setName("테스트");
        userService.join(user);

        Post post= new Post();
        post.setContent("테스트 중입니다");
        post.setScore("5");
        post.setAuthors("작가");
        post.setIsbn("123");
        post.setPublisher("한빛");
        post.setTitle("PHP");
        post.setThumbnail("123");
        post.setAuthors("홍길동");
        post.setBody("재미있습니다");
        Post post1=postServise.postSave(user.getId(), post);

        // When
        Rating rating=ratingService.save(user.getId(),post1.getId(),"테스트");

        // Then
        assertEquals(rating,ratingRepository.findOne(rating.getId()));
    }

    @Test
    public void 댓글업데이트() throws Exception {
        // Given
        User user = new User();
        user.setName("테스트");
        userService.join(user);

        Post post= new Post();
        post.setContent("테스트 중입니다");
        post.setScore("5");
        post.setAuthors("작가");
        post.setIsbn("123");
        post.setPublisher("한빛");
        post.setTitle("PHP");
        post.setThumbnail("123");
        post.setAuthors("홍길동");
        post.setBody("재미있습니다");
        Post post1=postServise.postSave(user.getId(), post);

        Rating rating=ratingService.save(user.getId(),post1.getId(),"테스트");
        // When
        ratingService.updateRating(rating.getId(), "업데이트");
        // Then
        assertEquals(rating,ratingRepository.findOne(rating.getId()));
    }

}
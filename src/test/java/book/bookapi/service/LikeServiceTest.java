package book.bookapi.service;

import book.bookapi.domain.Like;
import book.bookapi.domain.Post;
import book.bookapi.domain.User;
import book.bookapi.repository.LikeReopository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class LikeServiceTest {

    @Autowired
    LikeReopository likeReopository;
    @Autowired
    LikeService likeService;
    @Autowired
    PostServise postServise;
    @Autowired
    UserService userService;


    @Test
    @Rollback(value = false)
    public void 좋아요저장() throws Exception {
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
        Like like=likeService.save(user.getId(), post1.getId());
        // Then
        assertEquals(like,likeReopository.findOne(like.getId()));
    }

    @Test
    public void 전체데이터() throws Exception {
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

        likeService.save(user.getId(), post1.getId());

        // When
        List<Like> likes =likeReopository.findAll();
        int likeCount= likes.size();
        // Then
        assertTrue(likeCount==1,"좋아요 전체 출력");
    }

    @Test
    public void 좋아요취소() throws Exception {
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

        Like like=likeService.save(user.getId(), post1.getId());

        // When
        likeService.delete(like.getId());
        List<Like> result = likeReopository.findAll();
        int resultCount=result.size();
        // Then
        assertTrue(resultCount==0,"삭제 완료");
    }

}
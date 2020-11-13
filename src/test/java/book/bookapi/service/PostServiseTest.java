package book.bookapi.service;

import book.bookapi.domain.Post;
import book.bookapi.domain.User;
import book.bookapi.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PostServiseTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostServise postServise;
    @Autowired
    UserService userService;

    @Test
    public void 포스트저장() throws Exception {
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
        post.setBody("바디 입니다");
        post.setCreateDate(LocalDateTime.now());
        // post.setUser(user);
        // When
        Post post1= postServise.postSave(user.getId(), post);
        // Then
        assertEquals(post1,postRepository.findOne(post1.getId()));
    }

    @Test
    public void 포스트업데이트() throws Exception {
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
        post.setBody("바디 입니다");
        Post post1=postServise.postSave(user.getId(), post);
        // When
        postServise.update(post1.getId(),"4","재미 있습니다");

        // Then
        assertEquals(post1, postRepository.findOne(post1.getId()));

    }
    @Test
    public void 전체포스트() throws Exception {

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
        post.setBody("바디 입니다");
        postServise.postSave(user.getId(), post);

        Post post1= new Post();
        post1.setContent("테스트 중입니다");
        post1.setScore("5");
        post1.setAuthors("작가");
        post1.setIsbn("123");
        post1.setPublisher("한빛");
        post1.setTitle("PHP");
        post1.setThumbnail("123");
        post1.setAuthors("홍길동");
        post1.setBody("바디 입니다");
        postServise.postSave(user.getId(), post);
        // When

        List<Post> posts=postRepository.findAll();
        int postCount=posts.size();

        // Then
        assertTrue(postCount==2,"회원수가 같습니다");
    }
    @Test
    public void 포스트삭제() throws Exception {
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
        postServise.delete(post1.getId());
        List<Post> result = postRepository.findAll();
        int resultCount=result.size();
        // Then
        assertTrue(resultCount==0,"삭제 완료");
    }


}
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

    @Test
    public void 포스트저장() throws Exception {
        // Given
        Post post= new Post();
        post.setContent("테스트 중입니다");
        post.setScore("5");
        post.setAuthors("작가");
        post.setIsbn("123");
        post.setPublisher("한빛");
        post.setTitle("PHP");
        post.setThumbnail("123");

        // When
        Long postId=postServise.postSave(post);
        // Then
        assertEquals(post,postServise.findOne(postId));
    }

    @Test
    public void 포스트업데이트() throws Exception {
        // Given
        Post post= new Post();
        post.setContent("테스트 중입니다");
        post.setScore("5");
        post.setAuthors("작가");
        post.setIsbn("123");
        post.setPublisher("한빛");
        post.setTitle("PHP");
        post.setThumbnail("123");
        postServise.postSave(post);
        // When
        Long postid=postServise.update(1L,"4","재미 있습니다");

        // Then
        assertEquals(post, postRepository.findOne(postid));

    }
    @Test
    public void 전체포스트() throws Exception {
        // Given
        Post post= new Post();
        post.setContent("테스트 중입니다");
        post.setScore("5");
        post.setAuthors("작가");
        post.setIsbn("123");
        post.setPublisher("한빛");
        post.setTitle("PHP");
        post.setThumbnail("123");
        postServise.postSave(post);

        Post post1= new Post();
        post1.setContent("테스트 중입니다");
        post1.setScore("5");
        post1.setAuthors("작가");
        post1.setIsbn("123");
        post1.setPublisher("한빛");
        post1.setTitle("PHP");
        post1.setThumbnail("123");
        postServise.postSave(post1);
        // When

        List<Post> posts=postRepository.findAll();
        int postCount=posts.size();

        // Then
        assertTrue(postCount==2,"회원수가 같습니다");
    }


}
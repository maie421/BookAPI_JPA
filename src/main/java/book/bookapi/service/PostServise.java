package book.bookapi.service;

import book.bookapi.domain.Post;
import book.bookapi.domain.User;
import book.bookapi.repository.PostRepository;
import book.bookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServise {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post postSave(Long user_id,Post post){
        User user=userRepository.findOne(user_id);
        Post post1=Post.CreatePost(user,post);
        postRepository.save(post1);
        return post1;
    }

    @Transactional
    public Long update(Long id,String score,String content){
         Post post=postRepository.findOne(id);
         post.setScore(score);
         post.setContent(content);
         return post.getId();
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findOne(Long id){
        return postRepository.findOne(id);
    }

    @Transactional
    public void delete(Long id){
        postRepository.delete(id);
    }
}

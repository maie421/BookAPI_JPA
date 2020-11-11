package book.bookapi.service;

import book.bookapi.domain.Post;
import book.bookapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServise {

    private final PostRepository postRepository;

    @Transactional
    public Long postSave(Post post){
        postRepository.save(post);
        return post.getId();
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
}

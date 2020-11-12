package book.bookapi.service;

import book.bookapi.domain.Like;
import book.bookapi.domain.Post;
import book.bookapi.domain.User;
import book.bookapi.repository.LikeReopository;
import book.bookapi.repository.PostRepository;
import book.bookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeReopository likeReopository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

/*    @Transactional
    public Long save(Like like){
        likeReopository.save(like);
        return like.getId();
    }*/

    @Transactional
    public Like save(Long userId,Long postId){
        Post post = postRepository.findOne(postId);
        User user = userRepository.findOne(userId);

        Like like=Like.createLike(user,post);
        likeReopository.save(like);
        return like;
    }


    public List<Like> findAll(){
        return likeReopository.findAll();
    }

    @Transactional
    public void delete(Long id){
        likeReopository.Delete(id);
    }
}

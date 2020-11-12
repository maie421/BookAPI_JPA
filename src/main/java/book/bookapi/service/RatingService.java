package book.bookapi.service;

import book.bookapi.domain.Post;
import book.bookapi.domain.Rating;
import book.bookapi.domain.User;
import book.bookapi.repository.PostRepository;
import book.bookapi.repository.RatingRepository;
import book.bookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingService {

    private final RatingRepository ratingRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Rating save(Long user_id,Long post_id,String rating){
        User user=userRepository.findOne(user_id);
        Post post=postRepository.findOne(post_id);

        Rating rating1=Rating.createRating(user,post,rating);
        ratingRepository.save(rating1);
        return rating1;
    }

    public List<Rating> findAll(){
        return ratingRepository.findAll();
    }

    @Transactional
    public Long updateRating(Long id,String text){
       Rating rating= ratingRepository.findOne(id);
       rating.setRating(text);
       return rating.getId();
    }

    @Transactional
    public void delete(Long id){
        ratingRepository.Delete(id);
    }
}

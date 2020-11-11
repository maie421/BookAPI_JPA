package book.bookapi.api;

import book.bookapi.domain.Like;
import book.bookapi.domain.Post;
import book.bookapi.domain.Rating;
import book.bookapi.domain.User;
import book.bookapi.service.PostServise;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostServise postServise;

    @GetMapping("/api/v1/posts")
    public List<Post> postV1(){
        return postServise.findAll();

    }

    @GetMapping("/api/v2/posts")
    public List<PostDto> postV2(){
        List<Post> posts = postServise.findAll();
        List<PostDto> result= posts.stream()
                .map(p->new PostDto(p))
                .collect(toList());
        return result;
    }

    @Data
    class PostDto{
        private String body;
        private String score;
        private String thumbnail;
        private String isbn;
        private String title;
        private String contents;
        private String publisher;
        private LocalDateTime createDate;

        private List<LikesDto> likes;
        private String name;
        private List<RatinsDto> rating;

        public PostDto(Post post){
            body=post.getBody();
            score=post.getScore();
            thumbnail=post.getThumbnail();
            isbn=post.getThumbnail();
            title=post.getTitle();
            contents=post.getContent();
            publisher=post.getPublisher();
            createDate=post.getCreateDate();

            name=post.getUser().getName();
            likes=post.getLikes().stream()
                    .map(like->new LikesDto(like))
                    .collect(toList());

            rating=post.getRatings().stream()
                    .map(r->new RatinsDto(r))
                    .collect(toList());
        }
    }


    @Data
    class  RatinsDto{
        private String rating;
        private String name;
        public RatinsDto(Rating rating){
            this.rating=rating.getRating();
            name=rating.getUser().getName();

        }
    }

    @Data
    class  LikesDto{
        private Long like_id;
        public LikesDto(Like like){
            like_id=like.getId();
        }
    }

}

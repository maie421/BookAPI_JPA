package book.bookapi.api;

import book.bookapi.domain.Like;
import book.bookapi.domain.Post;
import book.bookapi.domain.Rating;
import book.bookapi.domain.User;
import book.bookapi.repository.PostRepository;
import book.bookapi.service.PostServise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostServise postServise;
    private final PostRepository postRepository;

    /**
     * 포스트 출력
    */

    @GetMapping("/api/v2/posts")
    public Result postV2(){
        List<Post> posts = postServise.findAll();
        List<PostDto> result= posts.stream()
                .map(p->new PostDto(p))
                .collect(toList());
        return new Result(result);
    }

/*    @GetMapping("/api/v3/posts")
    public List<PostDto> postV3(){
        List<Post> posts = postRepository.findfetchAll();
        List<PostDto> result= posts.stream()
                .map(p->new PostDto(p))
                .collect(toList());
        return result;
    }*/

    /**
     * 포스트 입력
     */
    @PostMapping("/api/v2/posts")
    public CreatePostResponse saveV2(@RequestBody @Valid CreatePostRequest request){
        Post post= new Post();
        post.setContent(request.contents);
        post.setScore(request.score);
        post.setAuthors(request.authors);
        post.setIsbn(request.isbn);
        post.setPublisher(request.publisher);
        post.setTitle(request.title);
        post.setThumbnail(request.thumbnail);
        post.setBody(request.body);

        Post post1=postServise.postSave(request.user_id,post);
        return new CreatePostResponse(post1.getId());
    }

    /**
     * 포스트 수정
     */
    @PatchMapping("/api/v2/posts/{id}")
    public UpdatePostResponse updatepostV2(@PathVariable("id") Long id,
                                           @RequestBody @Valid UpdatePostRequest request){
        postServise.update(id,request.score,request.content);
        Post post=postServise.findOne(id);
        return new UpdatePostResponse(post.getContent());

    }
    /**
     * 포스트 삭제
     */
    @DeleteMapping("/api/v2/posts/{id}")
    public void DeletePostV2(@PathVariable("id") Long id){
        postServise.delete(id);
    }

    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }
    @Data
    static class UpdatePostRequest{
        private String content;
        private String score;
    }
    @Data
    @AllArgsConstructor
    class UpdatePostResponse{
        private String content;
    }
    @Data
    @AllArgsConstructor
    class CreatePostResponse{
        private Long id;
    }
    @Data
    static class CreatePostRequest{
        private String body;
        private String score;
        private String thumbnail;
        private String isbn;
        private String title;
        private String contents;
        private String publisher;
        private String authors;
        private Long user_id;

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
        private String authors;
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
            authors=post.getAuthors();

            name=post.getUser().getName();
            likes=post.getLikes().stream()
                    .map(l->new LikesDto(l))
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

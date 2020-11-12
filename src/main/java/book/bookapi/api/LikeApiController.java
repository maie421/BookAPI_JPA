package book.bookapi.api;

import book.bookapi.domain.Like;
import book.bookapi.domain.Post;
import book.bookapi.domain.User;
import book.bookapi.service.LikeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeService likeService;

    @PostMapping("/api/v2/like")
    public Long save(@RequestBody @Valid CreatelikeRequest request){
            Like like=likeService.save(request.userid,request.postid);
            return like.getId();
    }

    @DeleteMapping("/api/v2/like/{id}")
    public void likedelete(@PathVariable("id") Long id){

        likeService.delete(id);
    }

    @Data
    static class CreatelikeRequest{
        private Long userid;
        private Long postid;
    }

}

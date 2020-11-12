package book.bookapi.api;

import book.bookapi.domain.Rating;
import book.bookapi.service.RatingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RatingApiController {
    private final RatingService ratingService;

    @PostMapping("/api/v2/rating")
    public Long save(@RequestBody @Valid CreateratingRequest request){
        Rating rating=ratingService.save(request.user_id, request.post_id,request.rating);
        return rating.getId();
    }

    @PatchMapping("/api/v2/rating/{id}")
    public Long update(@PathVariable("id") Long id,@RequestBody @Valid UpdateRtingRequest request){
        Long rating_id=ratingService.updateRating(id, request.getString());
        return rating_id;
    }
    @Data
    static class CreateratingRequest{
        private  Long user_id;
        private  Long post_id;
        private String rating;
    }
    @Data
    static class UpdateRtingRequest{
        private String string;
    }
}

package book.bookapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Rating {

    @Id
    @GeneratedValue
    @Column(name="rating_id")
    private Long id;

    private String rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    private LocalDateTime createDate;

    //== 생성메소드 ==//
    public static Rating createRating(User user,Post post,String text){
        Rating rating=new Rating();
        rating.setUser(user);
        rating.setPost(post);
        rating.setRating(text);
        rating.setCreateDate(LocalDateTime.now());
        return rating;
    }
}

package book.bookapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String thumbnail;

    private String isbn;

    private String title;

    private String authors;

    private String contents;

    private String publisher;

    @OneToMany
    @JoinColumn(name="rating_id")
    private List<Rating> ratings = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="like_id")
    private Like like;

    private LocalDateTime createDate;

}

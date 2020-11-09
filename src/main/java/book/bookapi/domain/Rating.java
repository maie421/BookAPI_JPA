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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

/*    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;*/

    private LocalDateTime createDate;

}

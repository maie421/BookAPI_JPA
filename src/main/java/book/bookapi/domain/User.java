package book.bookapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

/*    @OneToOne(mappedBy = "user" ,fetch = FetchType.LAZY)
    private Like like;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Rating rating;*/

}

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

    private String body;

    private String score;

    private String thumbnail;

    private String isbn;

    private String title;

    private String authors;

    private String content;

    private String publisher;

    @OneToMany(mappedBy="post", cascade = CascadeType.ALL)
    @OrderBy("createDate DESC")
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy="post" , cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    private LocalDateTime createDate;

    //==생성 메서드==//
    public static Post CreatePost(User user,Post post){
        Post postsave=new Post();
        postsave.setContent(post.getContent());
        postsave.setScore(post.getScore());
        postsave.setAuthors(post.getAuthors());
        postsave.setIsbn(post.getIsbn());
        postsave.setPublisher(post.getPublisher());
        postsave.setTitle(post.getTitle());
        postsave.setThumbnail(post.getThumbnail());
        postsave.setBody(post.getBody());
        postsave.setCreateDate(LocalDateTime.now());
        postsave.setUser(user);

        return postsave;
    }

}

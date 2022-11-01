package hasEntities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "author", targetEntity = Article.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Article> articles;

    public Author() {
        this.articles = new ArrayList<>();
    }

    public Author(String name) {
        this();
        this.name = name;
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }
}

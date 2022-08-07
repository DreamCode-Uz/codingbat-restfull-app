package uz.pdp.codingbatrestfullapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String solution;

    private String hint;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private boolean hasStar;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Language language;

    public Task(String name, String description, String solution, String hint, String method, boolean hasStar, Category category, Language language) {
        this.name = name;
        this.description = description;
        this.solution = solution;
        this.hint = hint;
        this.method = method;
        this.hasStar = hasStar;
        this.category = category;
        this.language = language;
    }
}

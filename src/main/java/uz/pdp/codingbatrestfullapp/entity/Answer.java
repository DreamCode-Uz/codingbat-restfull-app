package uz.pdp.codingbatrestfullapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String result;

    @ManyToOne(optional = false)
    private Task task;

    @ManyToOne
    private User users;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect = false;

    public Answer(String result, Task task, User users, boolean isCorrect) {
        this.result = result;
        this.task = task;
        this.users = users;
        this.isCorrect = isCorrect;
    }

    public Answer(String result, Task task, User users) {
        this.result = result;
        this.task = task;
        this.users = users;
    }
}

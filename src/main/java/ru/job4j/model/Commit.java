package ru.job4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.deserializer.CommitDeserializer;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "commits")
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = CommitDeserializer.class)
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String author;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "repository_id")
    private Repository repository;
}

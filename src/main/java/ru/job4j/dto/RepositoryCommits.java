package ru.job4j.dto;

import ru.job4j.model.Commit;

import java.time.LocalDateTime;

public class RepositoryCommits {

    private Long repoId;
    private String repoName;
    private String repoUrl;
    private Long id;
    private String message;
    private String author;
    private LocalDateTime date;

    public RepositoryCommits(long repoId,
                             String repoName,
                             String repoUrl,
                             Commit commit) {
        this.repoId = repoId;
        this.repoName = repoName;
        this.repoUrl = repoUrl;
        this.id = commit.getId();
        this.message = commit.getMessage();
        this.author = commit.getAuthor();
        this.date = commit.getDate();
    }
}
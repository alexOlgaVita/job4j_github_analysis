package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.RepositoryCommits;
import ru.job4j.model.Repository;
import ru.job4j.service.GitHubRemote;
import ru.job4j.service.RepositoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GitHubController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private GitHubRemote gitHubRemote;

    @GetMapping("/repositories")
    public List<Repository> getAllRepositories() {
        return repositoryService.findAll().stream().toList();
    }

    @GetMapping("/commits/{name}")
    public List<RepositoryCommits> getCommits(@PathVariable(value = "name") String name) {
        return gitHubRemote.findAllWithRepo();
    }

    @PostMapping("/repository")
    public ResponseEntity<Void> create(@RequestBody Repository repository) {
        repositoryService.create(repository);
        return ResponseEntity.noContent().build();
    }
}
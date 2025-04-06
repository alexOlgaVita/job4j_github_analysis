package ru.job4j.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {
    private final RepositoryService repositoryService;
    private final GitHubRemote gitHubRemote;
    private final GitHubService gitHubService;

    @Value("${repositories.owner}")
    private final String owner;

    public ScheduledTasks(RepositoryService repositoryService, GitHubRemote gitHubRemote, GitHubService gitHubService,
                          @Value("${repositories.owner}") String owner) {
        this.repositoryService = repositoryService;
        this.gitHubRemote = gitHubRemote;
        this.gitHubService = gitHubService;
        this.owner = owner;
    }

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void fetchCommits() {
        System.out.println("Привет!");
        var repos = repositoryService.findAll();
        repos.stream().forEach(repo -> {
            var last = gitHubRemote.findLastCommit(repo.getName());
            var commits = gitHubService.fetchCommits(owner, repo.getName());
            commits.stream()
                    .peek(System.out::println)
                    .filter(commit -> last.isPresent() ? commit.getDate().isAfter(last.get().getDate()) : true)
                    .forEach(commit -> {
                        commit.setRepository(repo);
                        gitHubRemote.create(commit);
                    });
        });
    }

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void fetchRepositories() {
        var repos = gitHubService.fetchRepositories(owner);
        repos.stream().forEach(repo -> {
            if (repositoryService.findRepoByName(repo.getName()).isEmpty()) {
                repositoryService.create(repo);
            }
        });
    }
}
package ru.job4j.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.job4j.dto.RepositoryCommits;
import ru.job4j.mapper.RepositoryCommitsMapper;
import ru.job4j.model.Commit;
import ru.job4j.repository.CommitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GitHubRemote {

    private final CommitRepository commitRepository;
    private final RepositoryCommitsMapper repositoryCommitsMapper;

    public GitHubRemote(CommitRepository commitRepository, RepositoryCommitsMapper repositoryCommitsMapper) {
        this.commitRepository = commitRepository;
        this.repositoryCommitsMapper = repositoryCommitsMapper;
    }

    @Async
    public void create(Commit commit) {
        commitRepository.save(commit);
    }

    public List<RepositoryCommits> findAllWithRepo() {
        return (!commitRepository.findAll().isEmpty())
                ? (commitRepository.findAll()
                .stream().map(e -> repositoryCommitsMapper.getModelFromEntity(e)).toList()) : List.of();
    }

    public List<Commit> findAll() {
        return commitRepository.findAll();
    }

    public Optional<Commit> findLastCommit(String repoName) {
        return commitRepository.findLastCommit(repoName);
    }
}
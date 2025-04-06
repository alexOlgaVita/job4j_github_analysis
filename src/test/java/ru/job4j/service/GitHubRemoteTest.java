package ru.job4j.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.mapper.RepositoryCommitsMapper;
import ru.job4j.model.Commit;
import ru.job4j.model.Repository;
import ru.job4j.repository.CommitRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GitHubRemoteTest {

    @Autowired
    private RepositoryCommitsMapper repositoryCommitsMapper;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final String date = "2025-03-14T11:57:01Z";

    @Test
    void whenCreateSuccessfullyWithMock() {
        var repo = new Repository();
        var commit = new Commit(null, "Первый коммит", "Ваня Васильчиков", LocalDateTime.parse(date, dateTimeFormatter), repo);
        CommitRepository commitRepositoryMock = mock(CommitRepository.class);
        when(commitRepositoryMock.save(commit)).thenReturn(null);
        GitHubRemote gitHubRemote = new GitHubRemote(commitRepositoryMock, repositoryCommitsMapper);
        gitHubRemote.create(commit);
        when(commitRepositoryMock.findAll()).thenReturn(List.of(commit));
        assertThat(gitHubRemote.findAll()).isEqualTo(List.of(commit));
    }

    @Test
    void whenFindLastCommitSuccessfullyWithMock() {
        var repo = new Repository();
        var commit = new Commit(null, "Первый коммит", "Ваня Васильчиков", LocalDateTime.parse(date, dateTimeFormatter), repo);
        CommitRepository commitRepositoryMock = mock(CommitRepository.class);
        when(commitRepositoryMock
                .findLastCommit(repo.getName())).thenReturn(Optional.of(commit));

        GitHubRemote gitHubRemote = new GitHubRemote(commitRepositoryMock, repositoryCommitsMapper);
        Optional<Commit> foundRepoByName =
                gitHubRemote.findLastCommit(repo.getName());

        assertThat(foundRepoByName.get()).isEqualTo(commit);
    }
}
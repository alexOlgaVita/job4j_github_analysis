package ru.job4j.service;

import org.junit.jupiter.api.Test;
import ru.job4j.model.Repository;
import ru.job4j.repository.RepoRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RepositoryServiceTest {

    @Test
    void whenCreateSuccessfullyWithMock() {
        var repo = new Repository();
        RepoRepository repositoryMock = mock(RepoRepository.class);
        when(repositoryMock.save(repo)).thenReturn(null);
        RepositoryService repositoryService = new RepositoryService(repositoryMock);
        repositoryService.create(repo);
        when(repositoryMock.findAll()).thenReturn(List.of(repo));
        assertThat(repositoryService.findAll()).isEqualTo(List.of(repo));
    }

    @Test
    void whenFindRepoByNameSuccessfullyWithMock() {
        var repo = new Repository();
        RepoRepository repositoryMock = mock(RepoRepository.class);
        when(repositoryMock
                .findRepoByName(repo.getName())).thenReturn(Optional.of(repo));

        RepositoryService repositoryService = new RepositoryService(repositoryMock);
        Optional<Repository> foundRepoByName =
                repositoryService.findRepoByName(repo.getName());

        assertThat(foundRepoByName.get()).isEqualTo(repo);
    }

    @Test
    void whenFindAllSuccessfullyWithMock() {
        var repo = new Repository();
        var repo2 = new Repository();
        RepoRepository repositoryMock = mock(RepoRepository.class);
        when(repositoryMock
                .findAll()).thenReturn(List.of(repo, repo2));

        RepositoryService repositoryService = new RepositoryService(repositoryMock);
        List<Repository> foundAllRepo =
                repositoryService.findAll().stream().toList();

        assertThat(foundAllRepo).isEqualTo(List.of(repo, repo2));
    }
}
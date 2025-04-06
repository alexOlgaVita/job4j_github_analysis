package ru.job4j.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.dto.RepositoryCommits;
import ru.job4j.model.Commit;

@Mapper(componentModel = "spring")
public interface RepositoryCommitsMapper {

    @Mapping(source = "commit.repository.id", target = "repoId")
    @Mapping(source = "commit.repository.name", target = "repoName")
    @Mapping(source = "commit.repository.url", target = "repoUrl")
    RepositoryCommits getModelFromEntity(Commit commit);
}
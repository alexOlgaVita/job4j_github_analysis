package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.model.Commit;
import ru.job4j.model.Repository;

import java.util.List;

@Service
public class GitHubService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Repository> fetchRepositories(String owner) {
        String url = String.format("https://api.github.com/users/%s/repos", owner);
        ResponseEntity<List<Repository>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Repository>>() {
                });
        return response.getBody();
    }

    public List<Commit> fetchCommits(String owner, String repoName) {
        String url = String.format("https://api.github.com/repos/%s/%s/commits", owner, repoName);
        ResponseEntity<List<Commit>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commit>>() {
                });
        return response.getBody();
    }

    public List<Commit> fetchCommits(String owner, String repoName, String sha) {
        String url = String.format("https://api.github.com/repos/%s/%s/commits?sha=%s", owner, repoName, sha);
        ResponseEntity<List<Commit>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commit>>() {
                });
        return response.getBody();
    }
}
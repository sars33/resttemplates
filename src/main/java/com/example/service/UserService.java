package com.example.service;

import com.example.dom.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private static final String URL_ADDRESS = "http://91.241.64.178:7081/api/users";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    String cookie = "";

    public void getAllUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity(URL_ADDRESS, String.class);
        HttpHeaders headers = response.getHeaders();
        System.out.println(response);
        cookie = headers.getFirst(headers.SET_COOKIE);
        System.out.println("cookie " + cookie);
    }

    public void addUser(User user) {
        headers.add("Cookie", cookie);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(URL_ADDRESS, requestEntity, String.class);
        System.out.println("added " + result.getBody());

    }

    public void editUser(User user) {
        headers.add("Cookie", cookie);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL_ADDRESS, HttpMethod.PUT, requestEntity, String.class);
        System.out.println("changed " + responseEntity.getBody());
    }

    public void deleteUser(long id) {
        HttpEntity<User> httpentity = new HttpEntity<>(headers);
        String result = restTemplate.exchange(URL_ADDRESS + "/" + id, HttpMethod.DELETE, httpentity, String.class).getBody();
        System.out.println("deleted " + result);
    }

}

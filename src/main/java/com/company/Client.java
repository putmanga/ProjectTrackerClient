package com.company;

import com.company.model.*;
import com.company.validation.Validator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;
import java.io.BufferedReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Class with client's logic
 */
@RequiredArgsConstructor
public class Client {
    private static final String SESSION_ID = "sessionId";
    @NonNull private BufferedReader reader;
    private RestTemplate restTemplate = new RestTemplate();


    public void registration() {

        RegistrationRequest request = makeUserRegistrationRequest();

        ResponseEntity<AuthenticationResponse> responseEntity = restTemplate.postForEntity(
                "http://localhost:8080/users/register",
                request,
                AuthenticationResponse.class);

        AuthenticationResponse response = responseEntity.getBody();

        System.setProperty(SESSION_ID, response.getSessionId());

        printWelcomeMessage(response.getUser());
    }

    public void login() throws ValidationException {
        LoginRequest request = makeLoginRequest();

        Validator.validate(request);

        ResponseEntity<AuthenticationResponse> responseEntity = restTemplate.postForEntity(
                "http://localhost:8080/users/login",
                request,
                AuthenticationResponse.class);

        AuthenticationResponse response = responseEntity.getBody();

        System.setProperty(SESSION_ID, response.getSessionId());

        printWelcomeMessage(response.getUser());

    }

    public void logout() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", System.getProperty(SESSION_ID));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/users/logout",
                HttpMethod.POST,
                entity,
                String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.clearProperty(SESSION_ID);
            System.out.println("You have been logged out!");
        } else {
            System.out.println("Error in logging out");
        }

    }

    public void getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", System.getProperty(SESSION_ID));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<User[]> exchange = restTemplate.exchange(
                "http://localhost:8080/users/",
                HttpMethod.GET,
                entity,
                User[].class);

        for (User user : exchange.getBody()) {
            System.out.println(user);
        }
    }

    public void createItem() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", System.getProperty(SESSION_ID));
        ItemRequest request = makeItemRequest();
        HttpEntity<ItemRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Item> response = restTemplate.exchange(
                "http://localhost:8080/items/",
                HttpMethod.POST,
                entity,
                Item.class);

        System.out.println("Item created. Item ID: " + response.getBody().getId());
    }

    public void getItems(boolean myItems) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", System.getProperty(SESSION_ID));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Item[]> exchange = restTemplate.exchange(
                "http://localhost:8080/items/?my-items=" + (myItems ? "true" : "false"),
                HttpMethod.GET,
                entity,
                Item[].class);

        for (Item item : exchange.getBody()) {
            System.out.println(item);
        }
    }

    @SneakyThrows
    public void getUserById() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", System.getProperty(SESSION_ID));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        System.out.println("Enter User's ID:");
        String id = reader.readLine();
        ResponseEntity<User> response = restTemplate.exchange(
                "http://localhost:8080/users/" + id,
                HttpMethod.GET,
                entity,
                User.class);

        System.out.println(response.getBody());

    }

    @SneakyThrows
    public void updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", System.getProperty(SESSION_ID));
        System.out.println("Enter User's ID:");
        String id = reader.readLine();
        UpdateUserRequest request = makeUpdateUserRequest();
        HttpEntity<UpdateUserRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<User> response = restTemplate.exchange(
                "http://localhost:8080/users/" + id,
                HttpMethod.PUT,
                entity,
                User.class);

        System.out.println(response.getBody());

    }

    @SneakyThrows
    private UpdateUserRequest makeUpdateUserRequest() {
        UpdateUserRequest request = new UpdateUserRequest();
        System.out.println("Enter first name:");
        request.setFirstName(reader.readLine());
        System.out.println("Enter last name:");
        request.setLastName(reader.readLine());
        System.out.println("Enter email:");
        request.setEmail(reader.readLine());
        return request;
    }

    private void printWelcomeMessage(User user) {

        String line = IntStream.range(0,
                user.getFirstName().length() + user.getLastName().length() + 15)
                    .mapToObj(i -> "*").collect(Collectors.joining(""));
        System.out.println(line);
        System.out.println(String.format("* Welcome, %s %s! *",
                user.getFirstName(),
                user.getLastName()));
        System.out.println(line);
    }

    @SneakyThrows
    private ItemRequest makeItemRequest() {
        ItemRequest request = new ItemRequest();
        System.out.println("Enter title:");
        request.setTitle(reader.readLine());
        System.out.println("Enter description:");
        request.setDescription(reader.readLine());
        System.out.println("Enter item type:");
        request.setItemType(Integer.parseInt(reader.readLine()));

        return request;
    }

    @SneakyThrows
    private LoginRequest makeLoginRequest() {
        LoginRequest request = new LoginRequest();
        System.out.println("Enter email:");
        request.setEmail(reader.readLine());
        System.out.println("Enter password:");
        request.setPassword(reader.readLine());

        return request;
    }

    @SneakyThrows
    private RegistrationRequest makeUserRegistrationRequest() {
        RegistrationRequest request =
                new RegistrationRequest();
        System.out.println("Enter first name:");
        request.setFirstName(reader.readLine());
        System.out.println("Enter last name:");
        request.setLastName(reader.readLine());
        System.out.println("Enter email:");
        request.setEmail(reader.readLine());
        System.out.println("Enter password:");
        request.setPassword(reader.readLine());

        return request;
    }
}

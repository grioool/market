package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.config.WebAppInitializer;
import by.sam_solutions.grigorieva.olga.backend.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = WebAppInitializer.class)
@AutoConfigureMockMvc(addFilters = false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String username = "testName";
    private static final String email = "test@gmail.com";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final List<UserDto> userList = new ArrayList<>();

    @BeforeClass
    public static void init() {

        UserDto user = new UserDto();
        user.setUsername(username);
        user.setEmail(email);
        user.setNameCompany("");
        user.setWildBerriesKeys("");

        user.setPassword("");

        user.setIsBlocked(false);
        user.setIsSubscribed(false);

        UserDto user1 = new UserDto();
        user1.setUsername(username + "1");
        user1.setEmail(email + "1");
        user1.setNameCompany("");
        user1.setWildBerriesKeys("");

        user1.setPassword("");

        user1.setIsBlocked(false);
        user1.setIsSubscribed(false);

        UserDto user2 = new UserDto();
        user2.setUsername(username + "2");
        user2.setEmail(email + "2");
        user2.setNameCompany("");
        user2.setWildBerriesKeys("");

        user2.setPassword("");

        user2.setIsBlocked(false);
        user2.setIsSubscribed(false);

        userList.add(user);
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void test_1_shouldCreateNewUser() throws Exception {
        for (UserDto user : userList) createNewUser(user);
    }

    private void createNewUser(UserDto user) throws Exception {
        String createdUserJson = this.mockMvc.perform(post("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.nameCompany", is(user.getNameCompany())))
                .andExpect(jsonPath("$.wildBerriesKeys", is(user.getWildBerriesKeys())))
                .andExpect(jsonPath("$.isBlocked", is(user.getIsBlocked())))
                .andExpect(jsonPath("$.isSubscribed", is(user.getIsSubscribed())))
                .andReturn()
                .getResponse()
                .getContentAsString();
        user.setId(objectMapper.readValue(createdUserJson, UserDto.class).getId());
    }


    @Test
    @SuppressWarnings("unchecked")
    public void test_2_shouldFetchAllUsers() throws Exception {
        List<LinkedHashMap<String, Object>> users = objectMapper.readValue(this.mockMvc.perform(get("/admin/users"))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                List.class
        );
        Assertions.assertThat(userList.stream().allMatch(userDto ->
                users.stream().anyMatch(user -> user.get("id").equals(userDto.getId()))
        )).isEqualTo(true);
    }

    @Test
    public void test_3_shouldFetchOneUserById() throws Exception {
        UserDto requestedUser = userList.stream().findAny().orElseThrow();
        this.mockMvc.perform(get("/admin/users/{id}", requestedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(requestedUser.getEmail())))
                .andExpect(jsonPath("$.username", is(requestedUser.getUsername())))
                .andExpect(jsonPath("$.nameCompany", is(requestedUser.getNameCompany())))
                .andExpect(jsonPath("$.wildBerriesKeys", is(requestedUser.getWildBerriesKeys())))
                .andExpect(jsonPath("$.isBlocked", is(requestedUser.getIsBlocked())))
                .andExpect(jsonPath("$.isSubscribed", is(requestedUser.getIsSubscribed())));
    }


    @Test
    public void test_4_shouldUpdateUser() throws Exception {
        UserDto updatedUser = userList.stream().findAny().orElseThrow();
        updatedUser.setIsBlocked(true);
        this.mockMvc.perform(
                put("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(updatedUser))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(updatedUser.getEmail())))
                .andExpect(jsonPath("$.username", is(updatedUser.getUsername())))
                .andExpect(jsonPath("$.nameCompany", is(updatedUser.getNameCompany())))
                .andExpect(jsonPath("$.wildBerriesKeys", is(updatedUser.getWildBerriesKeys())))
                .andExpect(jsonPath("$.isBlocked", is(updatedUser.getIsBlocked())))
                .andExpect(jsonPath("$.isSubscribed", is(updatedUser.getIsSubscribed())));

    }

    @Test
    public void test_5_shouldDeleteUser() throws Exception {
        for (UserDto user : userList) deleteUser(user);
    }

    private void deleteUser(UserDto user) throws Exception {
        this.mockMvc.perform(delete("/admin/users/{id}", user.getId())).andExpect(status().isOk());
    }
}

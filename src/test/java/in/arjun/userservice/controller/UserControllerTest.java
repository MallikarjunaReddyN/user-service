package in.arjun.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.arjun.userservice.domain.entity.User;
import in.arjun.userservice.domain.request.AddUserRequest;
import in.arjun.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void testAddUser() throws Exception {
        AddUserRequest addUserRequest = new AddUserRequest("Raju", "raju@gmail.com", "9988776655");
        String userData = objectMapper.writeValueAsString(addUserRequest);
        mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON).
                content(userData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.name").value("Raju"))
                .andDo(print());
    }

    @Test
    void testGetAllUsers() throws Exception {
        userRepository.save(User.builder().name("Raju").email("raju@gmail.com").phone("9988665544").build());
        mockMvc.perform(get("/user/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andDo(print());
    }

    @Test
    void testGetUserByEmail() throws Exception {
        userRepository.save(User.builder().name("Raju").email("raju@gmail.com").phone("9988665544").build());
        mockMvc.perform(get("/user/raju@gmail.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.email").value("raju@gmail.com"))
                .andExpect(jsonPath("$.data.name").value("Raju"))
                .andDo(print());
    }

    @Test
    void testDeleteUserByEmail() throws Exception {
        userRepository.save(User.builder().name("Raju").email("raju@gmail.com").phone("9988665544").build());
        mockMvc.perform(delete("/user/raju@gmail.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").value("User deleted successfully"))
                .andDo(print());
    }



}
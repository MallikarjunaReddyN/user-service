package in.arjun.userservice.service;

import in.arjun.userservice.domain.entity.User;
import in.arjun.userservice.domain.request.AddUserRequest;
import in.arjun.userservice.exception.UserNotFoundException;
import in.arjun.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void addUser() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(userRepository.save(any(User.class)))
                .thenReturn(new User("123", "Raju", "raju@gmail.com", "65543323"));
        User user = userService.addUser(new AddUserRequest("Raju", "raju@gmail.com", "65543323"));
        assertEquals("Raju", user.getName());
        assertEquals("raju@gmail.com", user.getEmail());
        assertEquals("65543323", user.getPhone());
    }

    @Test
    void addUserWithExists() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(new User("123", "Raju", "raju@gmail.com", "65543323")));
        try {
            User user = userService.addUser(new AddUserRequest("Raju", "raju@gmail.com", "65543323"));
            fail("Should fail test");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User("123", "Raju", "raju@gmail.com", "65543323"), new User("124", "Arjun", "arjun@gmail.com", "9988655443")));
        List<User> allUsers = userService.getAllUsers();
        assertEquals(2, allUsers.size());
    }

    @Test
    void getUserByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User("123", "Raju", "raju@gmail.com", "65543323")));
        User userByEmail = userService.getUserByEmail("raju@gmail.com");
        assertEquals("raju@gmail.com", userByEmail.getEmail());
        assertEquals("Raju", userByEmail.getName());
    }

    @Test
    void getUserByEmailNotExists() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        try {
            User userByEmail = userService.getUserByEmail("raju@gmail.com");
            fail("User not exists, must fail this");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteUserByEmail() {
        doNothing().when(userRepository).deleteByEmail(anyString());
        String response = userService.deleteUserByEmail("raju@gmail.com");
        assertEquals("User deleted successfully", response);
    }
}
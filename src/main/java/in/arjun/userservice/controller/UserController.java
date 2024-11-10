package in.arjun.userservice.controller;

import in.arjun.userservice.domain.entity.User;
import in.arjun.userservice.domain.request.AddUserRequest;
import in.arjun.userservice.domain.response.ApiResponse;
import in.arjun.userservice.domain.response.UserStatusResponse;
import in.arjun.userservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ApiResponse<User> addUser(@RequestBody AddUserRequest addUserRequest) {
        User user = userService.addUser(addUserRequest);
        return ApiResponse.created("User created successfully", user);
    }

    @GetMapping("/list")
    public ApiResponse<List<User>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ApiResponse.ok(allUsers);
    }

    @GetMapping("/{email}")
    public ApiResponse<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ApiResponse.ok(user);
    }

    @DeleteMapping("/{email}")
    public ApiResponse<String> deleteUser(@PathVariable String email) {
        String response = userService.deleteUserByEmail(email);
        return ApiResponse.ok(response);
    }
}

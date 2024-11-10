package in.arjun.userservice.service;

import in.arjun.userservice.domain.entity.User;
import in.arjun.userservice.domain.request.AddUserRequest;
import in.arjun.userservice.exception.UserNotFoundException;
import in.arjun.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
   	private UserRepository userRepository;

	public User addUser(AddUserRequest addUserRequest) {
		Optional<User> optionalUserByEmail = userRepository.findByEmail(addUserRequest.email());
		if (optionalUserByEmail.isPresent()) {
			throw new RuntimeException("User already exists with email: " + addUserRequest.email());
		}
		User user = new User();
		BeanUtils.copyProperties(addUserRequest, user);
        return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserByEmail(String email) {
		Optional<User> optionalUserByEmail = userRepository.findByEmail(email);
		if (optionalUserByEmail.isEmpty()) {
			throw new UserNotFoundException("User not found with email: " + email);
		}
		return optionalUserByEmail.get();
	}

	@Transactional
	public String deleteUserByEmail(String email) {
		userRepository.deleteByEmail(email);
		return "User deleted successfully";
	}
}

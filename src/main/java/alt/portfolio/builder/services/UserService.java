package alt.portfolio.builder.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import alt.portfolio.builder.dtos.UserRequestDto;
import alt.portfolio.builder.entities.User;
import alt.portfolio.builder.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(UserRequestDto userRequest) {
		User user = userRequest.toUser(new User());	
		return userRepository.save(user);
	}
	
	public User findById(UUID userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));;
		return user;
	}
	
	public void deleteById(UUID userId) { 	
		userRepository.deleteById(userId);
	}
	
	public User editUser(UserRequestDto userRequest) {
		User user = findById(userRequest.getId());
		user.setEmail(userRequest.getEmail());
		user.setFirstname(userRequest.getFirstname());
		user.setLastname(userRequest.getLastname());
		user.setUsername(userRequest.getUsername());
		return userRepository.save(user);
	}


}

package com.comedyhub.prot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.comedyhub.prot.dto.LoginRequest;
import com.comedyhub.prot.dto.UserDtoCreate;
import com.comedyhub.prot.dto.UserDtoResponse;
import com.comedyhub.prot.dto.mapper.UserMapper;
import com.comedyhub.prot.model.User;
import com.comedyhub.prot.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }
    
    public LoginRequest setPasswordLogin(LoginRequest loginRequest) {
    	loginRequest.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
    	return loginRequest;
    }

    public UserDtoResponse createUser(UserDtoCreate userDTO) {
    	User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public List<UserDtoResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTO(users);
    }

    public UserDtoResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

	public UserDtoResponse getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User not found");
		}
		return userMapper.toDTO(user);
	}
	
}

package com.adminpanel.miniproject.service;

import com.adminpanel.miniproject.dto.UserDto;
import com.adminpanel.miniproject.entity.User;
import com.adminpanel.miniproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setGender(userDto.getGender());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email).orElse(null);
//    }
    @Override
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
    public UserDto findById(int id) {

        User existing= userRepository.findById(id);
        UserDto userDto=new UserDto();
        userDto.setId(existing.getId());
        String[] name = existing.getName().split(" ");
        System.out.println(name);
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(existing.getEmail());
        userDto.setPhoneNumber(existing.getPhoneNumber());
        userDto.setGender(existing.getGender());
        userDto.setRole(existing.getRole());


        return userDto;
    }

    @Override
    public List<UserDto> findAllByRole(String role) {
        List<User> users = userRepository.findAllByRole(role);
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


//    @Override
//    public User updateUser(UserDto user) {
//        int id=user.getId();
//        User existing=userRepository.findById(id);
//        existing.setName(user.getFirstName() + " " + user.getLastName());
//        existing.setEmail(user.getEmail());
//        existing.setPhoneNumber(user.getPhoneNumber());
//        existing.setGender(user.getGender());
//
//
//        return userRepository.save(existing);
//    }

    @Override
    public User updateUser(UserDto userDto) {
        int id = userDto.getId();
        User existing = userRepository.findById(id);

        // Preserve the existing first name
        String existingFirstName = existing.getName().split(" ")[0];
        // Use the existing first name with the new last name
        String updatedName = existingFirstName + " " + userDto.getLastName();

        existing.setName(updatedName);
        existing.setEmail(userDto.getEmail());
        existing.setPhoneNumber(userDto.getPhoneNumber());
        existing.setGender(userDto.getGender());

        return userRepository.save(existing);
    }


    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }


    @Override
    public List<UserDto> searchUser(String role, String name) {
        List<User> users = userRepository.findAllByRoleAndNameStartingWithIgnoreCase(role, name);
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());

        String[] nameParts = user.getName() != null ? user.getName().split(" ") : new String[0];
        userDto.setFirstName(nameParts.length > 0 ? nameParts[0] : "");
        userDto.setLastName(nameParts.length > 1 ? nameParts[1] : "");

        userDto.setEmail(user.getEmail() != null ? user.getEmail() : "");
        userDto.setPhoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber() : "");
        userDto.setGender(user.getGender() != null ? user.getGender() : "");
        userDto.setPassword(user.getPassword() != null ? user.getPassword() : "");
        userDto.setRole(user.getRole() != null ? user.getRole() : "");

        return userDto;
    }
}

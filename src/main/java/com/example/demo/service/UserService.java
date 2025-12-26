// package com.example.demo.service;

// import com.example.demo.entity.User;
// import java.util.List;

// public interface UserService {

//     // Register a new user
//     User register(User user);
//     User registerUser(User user);

//     // Find user by email
//     User findByEmail(String email);

//     // Get user by ID
//     User getUserById(Long id);

//     // Get all users
//     List<User> getAllUsers();
// }

package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();
    // Alias for controller
    User registerUser(User user);
}
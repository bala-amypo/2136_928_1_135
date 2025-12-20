// package com.example.demo.service;

// import java.util.List;
// import java.util.Optional;

// import com.example.demo.entity.User;

// public interface UserService {
//     User save(User user);
//     List<User> getAll();
//     Optional<User> getById(Integer id);
//     void delete(Integer id);
// }
package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User save(User user);

    void delete(Integer id);
}

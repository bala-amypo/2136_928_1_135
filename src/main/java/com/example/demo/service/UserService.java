public interface UserService {

    User register(User user);      // ← ADD THIS
    User registerUser(User user);

    User findByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();
}

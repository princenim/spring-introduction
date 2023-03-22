package hello.hellospring.service;

import hello.hellospring.repository.UserRepository;

/**
 * @author hazel
 */
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}

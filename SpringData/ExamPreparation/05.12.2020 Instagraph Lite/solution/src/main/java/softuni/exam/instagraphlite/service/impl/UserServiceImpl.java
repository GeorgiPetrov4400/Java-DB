package softuni.exam.instagraphlite.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return null;
    }

    @Override
    public String importUsers() throws IOException {
        return null;
    }

    @Override
    public String exportUsersWithTheirPosts() {
        return null;
    }
}

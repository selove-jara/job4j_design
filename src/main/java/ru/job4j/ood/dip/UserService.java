package ru.job4j.ood.dip;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void getUser() {
        userRepository.findUser();
    }
}

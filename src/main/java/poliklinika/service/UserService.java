package poliklinika.service;

import poliklinika.model.User;

public interface UserService {
    User findByUsername(String username);
}

package co.istad.ite2mbanking.feature.user;

import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;

public interface UserService {
    void createUser(UserCreateRequest userCreateRequest);
}

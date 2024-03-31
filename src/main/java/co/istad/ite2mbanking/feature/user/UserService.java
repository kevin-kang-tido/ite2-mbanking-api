package co.istad.ite2mbanking.feature.user;

import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserDetailsResponse;
import co.istad.ite2mbanking.feature.user.dto.UserEditPassword;
import co.istad.ite2mbanking.feature.user.dto.UserEditRequest;

import java.util.List;

public interface UserService {
    void createUser(UserCreateRequest userCreateRequest);
//     List<UserDetailsResponse> createUser(UserCreateRequest userCreateRequest);
    UserDetailsResponse editUerPassword(UserCreateRequest userCreateRequest);
    void editUserProfileByUuid(String uuid, UserEditRequest userEditRequest);
    void editUserPassword(String oldPassword, UserEditPassword userEditPassword);
}

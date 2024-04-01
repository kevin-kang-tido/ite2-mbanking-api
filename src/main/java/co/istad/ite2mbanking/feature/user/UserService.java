package co.istad.ite2mbanking.feature.user;

import co.istad.ite2mbanking.feature.base.BaseMessage;
import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserDetailsResponse;
import co.istad.ite2mbanking.feature.user.dto.UserEditPassword;
import co.istad.ite2mbanking.feature.user.dto.UserEditRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    void createUser(UserCreateRequest userCreateRequest);
//     List<UserDetailsResponse> createUser(UserCreateRequest userCreateRequest);
    UserDetailsResponse editUerPassword(UserCreateRequest userCreateRequest);
    void editUserProfileByUuid(String uuid, UserEditRequest userEditRequest);
    void editUserPassword(String oldPassword, UserEditPassword userEditPassword);
    BaseMessage blockByUuid(String uuid);

   Page<UserDetailsResponse> findList (int page, int limit);

    void deleteUserByUuid(String uuid);
    BaseMessage enableByUuid(String uuid);
    BaseMessage disableByUuid(String uuid);

}

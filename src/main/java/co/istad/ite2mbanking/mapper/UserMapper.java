package co.istad.ite2mbanking.mapper;

import co.istad.ite2mbanking.domain.User;
import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // sourceType = userCreateRequest
    // TargetType = User (ReturnType)
    User fromUserCrateRequest(UserCreateRequest userCreateRequest);


    UserDetailsResponse toUserDetailsResponse(User user);

}

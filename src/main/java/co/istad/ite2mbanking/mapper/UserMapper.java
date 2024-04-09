package co.istad.ite2mbanking.mapper;

import co.istad.ite2mbanking.domain.User;
import co.istad.ite2mbanking.domain.UserAccount;
import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserDetailsResponse;
import co.istad.ite2mbanking.feature.user.dto.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // sourceType = userCreateRequest
    // TargetType = User (ReturnType)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUserCrateRequest(UserCreateRequest userCreateRequest);

    UserDetailsResponse toUserDetailsResponse(User user);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }
    UserResponse toUserResponse (User user);




}

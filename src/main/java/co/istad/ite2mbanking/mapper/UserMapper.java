package co.istad.ite2mbanking.mapper;

import co.istad.ite2mbanking.domain.User;
import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserDetailsResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // sourceType = userCreateRequest
    // TargetType = User (ReturnType)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUserCrateRequest(UserCreateRequest userCreateRequest);

    UserDetailsResponse toUserDetailsResponse(User user);






}

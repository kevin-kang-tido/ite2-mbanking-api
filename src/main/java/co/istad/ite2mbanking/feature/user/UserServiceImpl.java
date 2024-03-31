package co.istad.ite2mbanking.feature.user;

import co.istad.ite2mbanking.domain.Role;
import co.istad.ite2mbanking.domain.User;
import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserDetailsResponse;
import co.istad.ite2mbanking.feature.user.dto.UserEditPassword;
import co.istad.ite2mbanking.feature.user.dto.UserEditRequest;
import co.istad.ite2mbanking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service // create bean for inject
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final   UserRepository userRepository;
    private  final  RoleRepository roleRepository;
    private  final UserMapper userMapper;

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {


        if (userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }

        if (userRepository.existsByNationalCardId(userCreateRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID has already been existed!"
            );
        }

        if (userRepository.existsByStudentIdCard(userCreateRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student card ID has already been existed!"
            );
        }

        if (!userCreateRequest.password()
                .equals(userCreateRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCrateRequest(userCreateRequest);
        user.setUuid(UUID.randomUUID().toString());

        user.setProfileImage("avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setIsDeleted(false);


        // Assign default user role
        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Role USER has not been found!"));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public UserDetailsResponse editUerPassword(UserCreateRequest userCreateRequest) {

        return null;
    }

    @Override
    public void editUserProfileByUuid(String uuid, UserEditRequest userEditRequest) {
        // load old data
        User user = userRepository.findAll().stream().filter(user1 -> user1.getUuid().equals(uuid))
                .findFirst().orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User is been not found!"
                ));
        user.setName(userEditRequest.name());
        user.setPin(userEditRequest.pin());
        user.setPhoneNumber(userEditRequest.phoneNumber());
        user.setCityOrProvince(userEditRequest.cityOrProvince());
        user.setKhanOrDistrict(userEditRequest.khanOrDistrict());
        user.setSangkatOrCommune(userEditRequest.sangkatOrCommune());
        user.setEmployeeType(userEditRequest.employeeType());
        user.setPosition(userEditRequest.position());
        user.setCompanyName(userEditRequest.companyName());
        user.setMainSourceOfIncome(userEditRequest.mainSourceOfIncome());
        user.setMonthlyIncomeRange(userEditRequest.monthlyIncomeRange());

        userRepository.save(user);

    }

    @Override
    public void editUserPassword(String oldPassword, UserEditPassword userEditPassword) {
        System.out.println("User edit Password : "+userEditPassword);
        if (!oldPassword.equals(userEditPassword.oldPassword())){
            throw  new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Wrong password,Please Try again"
            );
        }
        if (!userEditPassword.newPassword().equals(userEditPassword.confirmedPassword())){
            throw  new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Password is not match! Please Try again"
            );
        }
        User user = userRepository.findAll().stream().findFirst().orElseThrow();
        user.setPassword(userEditPassword.newPassword());
        userRepository.save(user);

    }

}

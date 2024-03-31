package co.istad.ite2mbanking.feature.user;


import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserEditPassword;
import co.istad.ite2mbanking.feature.user.dto.UserEditRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
   void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest){

     userService.createUser(userCreateRequest);
    }
    @PatchMapping("/{uuid}")
    void editProfileByUuid(@Valid @PathVariable String uuid, @RequestBody UserEditRequest userEditRequest){
        userService.editUserProfileByUuid(uuid,userEditRequest);
    }
    @PatchMapping("old-password/{oldPassword}")
    void editPassword(@Valid @PathVariable String oldPassword, @RequestBody UserEditPassword userEditPassword){
        userService.editUserPassword(oldPassword,userEditPassword);

    }


}

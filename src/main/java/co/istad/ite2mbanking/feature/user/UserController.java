package co.istad.ite2mbanking.feature.user;


import co.istad.ite2mbanking.feature.base.BaseMessage;
import co.istad.ite2mbanking.feature.user.dto.UserCreateRequest;
import co.istad.ite2mbanking.feature.user.dto.UserEditPassword;
import co.istad.ite2mbanking.feature.user.dto.UserEditRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteUserByUuid(@PathVariable String uuid){
        userService.deleteUserByUuid(uuid);
    }

    @GetMapping
    ResponseEntity<?> findAllUser(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "3") int limit
    ){
        return ResponseEntity.accepted().body(
                Map.of(
                        "users",userService.findList(page,limit)
                )
        );

    }
    @PutMapping("/{uuid}/block")
    BaseMessage blockUuid(@PathVariable String uuid){
        return userService.blockByUuid(uuid);
    }
    @PutMapping("/{uuid}/enable")
    BaseMessage enableByUuid(@PathVariable String uuid){
        return userService.enableByUuid(uuid);
    }
    @PutMapping("/{uuid}/disable")
    BaseMessage disableByUuid(@PathVariable String uuid){
        return userService.disableByUuid(uuid);
    }



}

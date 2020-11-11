package book.bookapi.api;

import book.bookapi.domain.User;
import book.bookapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public CreateUserResponse saveUserV1(@RequestBody @Valid User user){
        Long id= userService.join(user);
        return new CreateUserResponse(id);
    }

    @PostMapping("/api/v2/users")
    public CreateUserResponse saveUserV2(@RequestBody @Valid CreateUserRequest request){
        User user = new User();
        user.setName(request.getName());

        Long id= userService.join(user);
        return new CreateUserResponse(id);
    }

    @PatchMapping("/api/v2/users/{id}")
    public UpdateUserResponse udpateUserV2(@PathVariable("id") Long id,
                                           @RequestBody @Valid UpdateUserRequest request){
        userService.update(id, request.getName());
        User findUser = userService.findOne(id);
        return new UpdateUserResponse(findUser.getId(),findUser.getName());
    }

    @GetMapping("/api/v1/users")
    public List<User> usersV1(){
        return userService.findMembers();
    }

    @GetMapping("/api/v2/users")
    public Result usersV2(){
        List<User> findusers = userService.findMembers();

        List<UserDto> collect = findusers.stream()
                .map(u->new UserDto(u.getName()))
                .collect(Collectors.toList());
        return new Result(collect);
    }
    @Data
    @AllArgsConstructor
    class Result<T>{
        private T data;
    }
    @Data
    @AllArgsConstructor
    class UserDto{
        private String name;
    }
    @Data
    static class UpdateUserRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    class UpdateUserResponse{
        private long id;
        private String name;

    }

    @Data
    static class CreateUserRequest{

        @NotEmpty
        private String name;
    }
    @Data
    class  CreateUserResponse{
        private Long id;
        public CreateUserResponse(Long id){
            this.id=id;
        }
    }
}

package book.bookapi.service;


import book.bookapi.domain.User;
import book.bookapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @Test
//    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //Given
        User user = new User();
        user.setName("테스트");
        //When
        Long saveId = userService.join(user);
        //Then
        assertEquals(user, userRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {

        String name="테스트";
        //Given
        User user1 = new User();
        user1.setName(name);

        User user2 = new User();
        user2.setName(name);
        //When
        userService.join(user1);

        //Then
/*        assertThrows(IllegalStateException.class, () -> {
            userService.Join(user2);
           fail("이미 존재하는 회원입니다.");
        });*/

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->userService.join(user2));
        assertEquals("이미 존재하는 회원입니다", thrown.getMessage());
    }

    @Test
    public void 회원수정() throws Exception {
        // Given
        User user = new User();
        user.setName("테스트");
        userRepository.save(user);

        // When
        Long userid=userService.update(1L,"테스트 업데이트");
        // Then
        assertEquals(user, userRepository.findOne(userid));
    }
    @Test
    public void 전체회원() {
        //given
        User user1 = new User();
        user1.setName("spring1");
        userRepository.save(user1);
        User user2 = new User();
        user2.setName("spring2");
        userRepository.save(user2);
        //when
        List<User> result = userRepository.findAll();
        int resultCount=result.size();
        //then
        assertTrue(resultCount==2,"회원수가 같습니다");
    }

}
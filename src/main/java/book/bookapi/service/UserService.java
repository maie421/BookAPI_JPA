package book.bookapi.service;

import book.bookapi.domain.User;
import book.bookapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(User user){
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user) {
        List<User> findusers=userRepository.findByName(user.getName());
        if(!findusers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /**
     * 회원 조회
     */
    public List<User> findMembers(){
        return userRepository.findAll();
    }
    public User findOne(Long id){
        return userRepository.findOne(id);
    }

    /**
     * 회원 수정
     */
    @Transactional
    public Long update(Long id,String name){
        User user=userRepository.findOne(id);
        user.setName(name);
        return user.getId();
    }
}

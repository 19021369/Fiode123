package com.example.fiode123.config;

import com.example.fiode123.model.User;
import com.example.fiode123.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // logic cx tương tự như phần đăng nhập, check xem email tồn tại hay chưa, nếu chưa thì mã hóa pass rồi lưu user vào db.
    public String register(UserRegisterRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return userRepository.findByEmail(request.getEmail())
                .flatMap(user -> {
                    if (user.isPresent()) {
                        return Single.error(new ApiException("email already used!", HttpStatus.BAD_REQUEST.value()));
                    } else {
                        String password = passwordEncoder.encode(request.getPassword());
                        User new_user = userMapper.toPOJO(request);
                        new_user.setPassword(password);
                        return userRepository.insert(new_user)
                                .map(user1 -> "Register Success!");
                    }
                });
    }

}
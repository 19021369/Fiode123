package com.example.fiode123.service;

import com.example.fiode123.model.AuthRequest;
import com.example.fiode123.model.TokenResponse;
import com.example.fiode123.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // Hàm dưới tự viết lại = jpa nhé.
    // bỏ usermapper.

    //check
    public TokenResponse authenticate(AuthRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return userRepository.findByEmail(request.getEmail()) // tìm user ở trong db dựa vào email gửi lên.
                .flatMap(user -> {
                    if (user.isEmpty()) // nếu k tìm thấy nhả ra lỗi
                        return Single.error(new ApiException("not found user!", HttpStatus.BAD_REQUEST.value()));

                    // Nếu tìm thấy thì kiểm tra xem mật khẩu gửi lên có giống mk trong db không.
                    return passwordEncoder.matches(request.getPassword(), user.get().getPassword()) ?
                            Single.just(user) :
                            Single.error(new ApiException("invalid password!", HttpStatus.BAD_REQUEST.value()));
                })
                .flatMap(user -> userRoleRepository.getListRoleName(user.get().getId())
                        .map(roles -> {
                            String token = jwtTokenUtil.generateAccessToken(user.get(), roles); // tạo token.
                            return new TokenResponse(token); // trả về token.
                        }));
    }


}
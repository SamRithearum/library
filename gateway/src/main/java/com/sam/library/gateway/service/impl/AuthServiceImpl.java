package com.sam.library.gateway.service.impl;

import com.netflix.discovery.EurekaClient;
import com.sam.library.gateway.dto.LoginRequestDTO;
import com.sam.library.gateway.dto.LoginResponseDTO;
import com.sam.library.gateway.dto.RegisterRequestDTO;
import com.sam.library.gateway.entity.Student;
import com.sam.library.gateway.repository.StudentRepository;
import com.sam.library.gateway.service.AuthService;
import com.sam.library.gateway.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final StudentRepository studentRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO req) {
        Student student = studentRepository.getStudentByStudentId(req.getStudentId());

        if (student == null) return null;
        if (StringUtils.isBlank(student.getPassword())) return null;

        boolean passwordMatched = passwordEncoder.matches(req.getPassword(), student.getPassword());
        if (!passwordMatched) return null;

        String token = jwtUtil.generateToken(student.getId());
        LoginResponseDTO res = new LoginResponseDTO();
        res.setToken(token);

        return res;
    }

    @Override
    public String register(RegisterRequestDTO reqBody) {

        return "Register successfully";
    }
}

package com.sam.library.gateway.service;

import com.sam.library.gateway.dto.RegisterRequestDTO;
import com.sam.library.gateway.dto.LoginRequestDTO;
import com.sam.library.gateway.dto.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO req);
    String register(RegisterRequestDTO req);
}

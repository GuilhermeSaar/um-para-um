package com.gsTech.um_para_um.controller;

import com.gsTech.um_para_um.DTO.ApiResponseDTO;
import com.gsTech.um_para_um.DTO.RegisterDTO;
import com.gsTech.um_para_um.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private RegisterService registerService;


    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponseDTO> register(@RequestBody RegisterDTO dto) {

        if(registerService.registerUser(dto)) {

            return ResponseEntity.ok().body(new ApiResponseDTO("User registed successfully"));

        } else return ResponseEntity.badRequest().body(new ApiResponseDTO("User already exists"));
    }
}

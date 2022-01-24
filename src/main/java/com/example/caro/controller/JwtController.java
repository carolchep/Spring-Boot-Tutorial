package com.example.caro.controller;

import com.example.caro.model.JwtRequest;
import com.example.caro.model.JwtResponse;
import com.example.caro.service.CustomUserDetailService;
import com.example.caro.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/generateToken")
  public ResponseEntity<JwtResponse>generateToken(@RequestBody JwtRequest jwtRequest){
        //authentication
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getPassWord());
        authenticationManager.authenticate(upat);
        UserDetails userDetails=customUserDetailService.loadUserByUsername(jwtRequest.getUserName());
        String jwtToken=jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse= new JwtResponse(jwtToken);
        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
  }
}

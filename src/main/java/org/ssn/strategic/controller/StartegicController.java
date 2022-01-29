package org.ssn.strategic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ssn.strategic.security.StrategicAuthRequest;
import org.ssn.strategic.security.service.StrategicUserDetailService;
import org.ssn.strategic.security.util.JwtUtil;

@RestController
public class StartegicController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	StrategicUserDetailService userDetailService;
	
	@Autowired
	JwtUtil jwtUtil;

	@RequestMapping("/health")
	public ResponseEntity<String> checkHealth(){
		return ResponseEntity.ok("Up And Running");
	}
	
	@RequestMapping(value = "/authenticate", method= RequestMethod.POST)
	public ResponseEntity<String> createAuthenticationToken(@RequestBody StrategicAuthRequest authRequest) throws Exception{
		try{
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}catch(BadCredentialsException e){
			throw new Exception("Incorrect username and password!");
		}
		final UserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getUsername());
		final String lToken = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(lToken);
	}
}

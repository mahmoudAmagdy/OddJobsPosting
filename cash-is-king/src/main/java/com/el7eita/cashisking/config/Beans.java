package com.el7eita.cashisking.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.el7eita.cashisking.security.MyUserDetailsService;
import com.el7eita.cashisking.security.jwt.JwtUtil;
import com.el7eita.cashisking.service.*;
import com.el7eita.cashisking.service.implementation.*;


@Configuration
@EnableTransactionManagement
public class Beans {
	
	
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
	@Bean
	public UserDetailsService userDetailService() {
		return new MyUserDetailsService();
		}
    @Bean
    public AccessLog accessLog() {
        return new AccessLog();
    }

    
    @Bean
    public JobService jobService() {
        return new JobServiceImpl();
    }
    
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public ModelMapper pojoMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
    


}
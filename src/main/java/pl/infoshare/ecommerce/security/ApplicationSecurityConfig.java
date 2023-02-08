package pl.infoshare.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/cart").authenticated()
                .and().httpBasic();
    }


    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> {
            Object credentials = authentication.getCredentials();
            Object principal = authentication.getPrincipal();

            if (credentials.equals(principal)) {
                return authentication;
            }

            throw new BadCredentialsException("Invalid username or password");
        };
    }
}

package ch.uzh.ifi.hase.soprafs23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ch.uzh.ifi.hase.soprafs23.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

    private JwtRequestFilter jwtFilter;

    @Autowired
    public JwtSecurityConfig(@Qualifier("userRepository") UserRepository userRepository) {
        this.jwtFilter = new JwtRequestFilter(userRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
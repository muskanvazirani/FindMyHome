package com.project.group17.config.AppConfig;
import com.project.group17.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class provides the configuration for the application, including beans for UserDetailsService,
 * AuthenticationProvider, AuthenticationManager, and PasswordEncoder.
 */
@Configuration
public class ApplicationConfig {
    private final UserRepository repository;

    /**
     * Constructs an ApplicationConfig with the specified user repository.
     *
     * @param repository The user repository.
     */
    public ApplicationConfig(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates and returns a UserDetailsService bean that retrieves user details
     * using the provided email address.
     *
     * @return The UserDetailsService bean.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Creates and returns an AuthenticationProvider bean using the configured
     * UserDetailsService and PasswordEncoder beans.
     *
     * @return The AuthenticationProvider bean.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Creates and returns an AuthenticationManager bean using the provided
     * AuthenticationConfiguration.
     *
     * @param config The AuthenticationConfiguration.
     * @return The AuthenticationManager bean.
     * @throws Exception If the AuthenticationManager cannot be created.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    /**
     * Creates and returns a PasswordEncoder bean that uses BCryptPasswordEncoder.
     *
     * @return The PasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

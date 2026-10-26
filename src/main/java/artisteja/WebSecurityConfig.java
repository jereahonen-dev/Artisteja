package artisteja;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration 
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean 
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
     http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests (authorize -> authorize
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/api/**").permitAll()
            .anyRequest().authenticated()
        ).formLogin(formlogin -> 
            formlogin.loginPage("/login")
            .defaultSuccessUrl("/artistList", true)
            .permitAll()
        ).logout(logout -> logout
            .permitAll());
        return http.build();
}

}



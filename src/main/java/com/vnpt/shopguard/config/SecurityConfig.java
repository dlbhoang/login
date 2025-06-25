package com.vnpt.shopguard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/assets/**").permitAll() // Cho phép assets (CSS, JS, hình ảnh) truy cập công khai
                .requestMatchers("/", "/about", "/products/**").permitAll() // Trang chủ, about, products mở cho mọi người
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Trang admin yêu cầu quyền ADMIN
                .anyRequest().authenticated() // Các URL khác cần đăng nhập
            )
            .formLogin(login -> login
                .loginPage("/login")     // Trang login bạn đã tự tạo
                .defaultSuccessUrl("/", true) // ✅ Sau khi login thành công → chuyển về Trang chủ
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")  // ✅ Sau khi logout → về Trang chủ
                .permitAll()
            );

        return http.build();
    }

    // ✅ Mã hóa mật khẩu bằng BCrypt (an toàn)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Tạo user cứng trong bộ nhớ (demo) - Có thể chuyển sang database về sau
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("123")) // Password = 123 (BCrypt mã hóa)
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(admin);
    }
}

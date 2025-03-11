package com.Quang.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import com.Quang.demo.service.CustomUserDetailsService;
import com.Quang.demo.service.UserService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  @Bean // sử dụng Bcrypt để hash password
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // thông báo cho spring security biết sử dụng CustomUserDetailsService ta tự
  // code thay cho UserDetailsService mặc định

  @Bean
  public UserDetailsService userDetailsService(UserService userService) {
    return new CustomUserDetailsService(userService);
  }

  @Bean
  public DaoAuthenticationProvider authProvider(
      PasswordEncoder passwordEncoder,
      UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    authProvider.setHideUserNotFoundExceptions(false);
    return authProvider;
  }

  // config để trang login ra trang login của mình chứ ko phải tragn mặc định của
  // Spring security

  // dispatcherType và requestMatchers để giúp có thể vào những mục kia mà không
  // cần authenticated

  @Bean
  public AuthenticationSuccessHandler customSuccessHandler() {
    return new CustomSuccessHandler();
  }

  @Bean // remember me
  public SpringSessionRememberMeServices rememberMeServices() {
    SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();

    // optionally customize
    rememberMeServices.setAlwaysRemember(true); // thay đổi giá trị hết hạn của session mặc định là 30 ngày
    // đối với các ứng dụng như ngân hàng thì sẽ ko có rememberme vì chỉ cần đóng
    // app là phải log lại
    return rememberMeServices;
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll()
            .requestMatchers("/", "/product/**", "/login", "/logout", "/client/**", "/css/**", "/js/**", "/images/**")
            .permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated())
        .sessionManagement((sessionManagement) -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // neu ng dung ko co session thi tao moi
            .invalidSessionUrl("/logout?expired") // het han session thi logout
            .maximumSessions(1) // tai 1 thoi diem thi tai khoan chi dc login o 1 thiet bi
            .maxSessionsPreventsLogin(false)) // false: log vào sau thì đá ng trước ra, true: đợi ng trc

        .logout(logout -> logout.deleteCookies("JSESSIONID").invalidateHttpSession(true)) // logout thi xoa cookie
        .rememberMe((rememberMe) -> rememberMe.rememberMeServices(rememberMeServices()))
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .failureUrl("/login?error")
            .successHandler(customSuccessHandler())
            .permitAll())
        .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"));
    // http.logout(logout -> logout.logoutUrl("/logout").logoutRequestMatcher(new
    // AntPathRequestMatcher("/logout", "POST"))
    // .logoutSuccessUrl("/login?logout").permitAll());

    return http.build();
  }

}

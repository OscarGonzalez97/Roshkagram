package com.roshka.bootcamp.ProyectoJunio.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/index", "/registro", "/css/**","/js/**","/img/**","/foto-comentario").permitAll()
                    //.anyRequest()
                    //.authenticated()
                    .antMatchers("/", "/registro","/password_reset","/password_reset/verified", "/verificacion", "/login",
                            "/css/**","/js/**","/img/**")
                    .permitAll()
                    .anyRequest()
                    //.authenticated()
                    //.hasRole()
                    .hasAuthority("conectarse")
                    //.antMatchers("/", "/home", "/index", "/registro", "/css/**","/js/**","/img/**","/foto-comentario").permitAll()
                    //.anyRequest()
                    //.authenticated()
                    //.hasRole("CONECTARSE")
                    .and()
                .formLogin()
                    .loginPage("/login")
                    //.loginProcessingUrl("")
                    .defaultSuccessUrl("/albumes", true)
                    //.failureUrl("/login.html?error=true")
                    //.failureHandler(authenticationFailureHandler())
                    .permitAll()
                    .and()
                .logout()
                    .invalidateHttpSession(false)
                    .clearAuthentication(false)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .permitAll();

    }
}

    package br.com.projetointegrador.newdente.config;


    import br.com.projetointegrador.newdente.security.SecurityFilter;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import java.util.Arrays;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Autowired
        private SecurityFilter securityFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.cors() // Configuração do CORS
                    .and()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll()
                    .requestMatchers("/admin/**").hasRole("admin")
                    .requestMatchers("/paciente/**", "/consulta/**").hasAnyRole("admin", "usuario")
                    .anyRequest().authenticated()
                    .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*")); // Permitir todas as origens (cuidado em ambiente de produção)
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Permitir todos os métodos
            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Permitir cabeçalhos específicos
            configuration.setAllowCredentials(true); // Permitir uso de credenciais (cookies)
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }
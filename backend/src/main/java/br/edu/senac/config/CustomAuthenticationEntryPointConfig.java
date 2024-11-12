package br.edu.senac.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import br.edu.senac.exceptions.ErrorResponseException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPointConfig  implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorResponseException errorResponse = new ErrorResponseException(
                HttpStatus.UNAUTHORIZED,
                "Acesso não autorizado. Faça login para acessar este recurso."
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
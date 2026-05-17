package com.sistema.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.dashboard.dto.LoginRequest;
import com.sistema.dashboard.model.Usuario;
import com.sistema.dashboard.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*") 
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioRepository.findByUsername(loginRequest.username())
                    .orElse(null);

            if (usuario != null) {
                
                System.out.println("👉 [LOGIN TEST] Enviado desde cliente REST: [" + loginRequest.password() + "]");
                System.out.println("👉 [LOGIN TEST] Almacenado en tabla MySQL:    [" + usuario.getPassword() + "]");

                if (usuario.getPassword().trim().equals(loginRequest.password().trim())) {
                    
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "success");
                    response.put("username", usuario.getUsername());
                    response.put("rol", usuario.getRol());
                    
                    return ResponseEntity.ok(response); 
                }
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas de inicio de sesión");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno en el servidor: " + e.getMessage());
        }
    }
}
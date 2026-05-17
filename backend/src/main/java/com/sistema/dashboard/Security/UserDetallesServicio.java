package com.sistema.dashboard.Security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; // 🔑 IMPORT CRÍTICO
import org.springframework.stereotype.Service;

import com.sistema.dashboard.model.Usuario;
import com.sistema.dashboard.repository.UsuarioRepository;

@Service
public class UserDetallesServicio implements UserDetailsService { // 🔑 DEBE IMPLEMENTAR ESTO OBLIGATORIAMENTE

    private final UsuarioRepository usuarioRepository;

    public UserDetallesServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        System.out.println("👉 [DEBUG] Cargando usuario de MySQL: " + usuario.getUsername());

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol()))
        );
    }
}
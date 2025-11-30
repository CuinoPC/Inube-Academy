package com.inube.inube_academy.service;

import com.inube.inube_academy.model.Usuario;
import com.inube.inube_academy.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findActivos() {
        return usuarioRepository.findByEstatus('A');
    }

    public List<Usuario> findInactivos() {
        return usuarioRepository.findByEstatus('I');
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario data) {
        return usuarioRepository.findById(id).map(u -> {

            u.setNombre(data.getNombre());
            u.setCorreo(data.getCorreo());
            u.setEstatus(data.getEstatus());

            return usuarioRepository.save(u);

        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
    }

    public void desactivar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no válido:" + id));

        usuario.setEstatus('I');
        usuarioRepository.save(usuario);
    }

    public void activar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no válido:" + id));

        usuario.setEstatus('A');
        usuarioRepository.save(usuario);
    }

}

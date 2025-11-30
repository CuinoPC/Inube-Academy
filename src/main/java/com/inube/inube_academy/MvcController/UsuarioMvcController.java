package com.inube.inube_academy.MvcController;

import com.inube.inube_academy.model.Usuario;
import com.inube.inube_academy.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/web/usuarios")
public class UsuarioMvcController {

    private final UsuarioService usuarioService;

    public UsuarioMvcController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("filtro", "todos");
        return "usuarios/list";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("usuarios", usuarioService.findActivos());
        model.addAttribute("filtro", "activos");
        return "usuarios/list";
    }
    
    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {
        model.addAttribute("usuarios", usuarioService.findInactivos());
        model.addAttribute("filtro", "inactivos");
        return "usuarios/list";
    }

    @GetMapping({"/new", "/edit/{id}"})
    public String showFormulario(@PathVariable(required = false) Long id, Model model) {
        Usuario usuario;
        if (id != null) {
            usuario = usuarioService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
            model.addAttribute("action", "edit");            
        } else {
            usuario = new Usuario();
            model.addAttribute("action", "new");
        }
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    @PostMapping
    public String save(@ModelAttribute Usuario usuario) {
        usuario.setNombre(usuario.getNombre().toUpperCase());
        usuario.setCorreo(usuario.getCorreo().toUpperCase());

        usuarioService.save(usuario);
        return "redirect:/web/usuarios";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Long id) {
        usuarioService.desactivar(id);
        return "redirect:/web/usuarios";
    }
    
    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Long id) {
        usuarioService.activar(id);
        return "redirect:/web/usuarios";
    }

    @GetMapping("/{id}")
    public String showDetalle(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " +  id));
        model.addAttribute("usuario", usuario);
        return "usuarios/show";
    }
}

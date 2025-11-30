package com.inube.inube_academy.MvcController;

import com.inube.inube_academy.model.Actividad;
import com.inube.inube_academy.service.ActividadService;
import com.inube.inube_academy.service.SalaService;
import com.inube.inube_academy.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/web/actividades")
public class ActividadMvcController {

    private final ActividadService actividadService;
    private final UsuarioService usuarioService;
    private final SalaService salaService;

    public ActividadMvcController(ActividadService actividadService, UsuarioService usuarioService,
        SalaService salaService) {

        this.actividadService = actividadService;
        this.usuarioService = usuarioService;
        this.salaService = salaService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Actividad> actividades = actividadService.findAll();
        model.addAttribute("actividades", actividades);
        model.addAttribute("filtro", "todos");
        return "actividades/list";
    }

    @GetMapping("/activas")
    public String listarActivas(Model model) {
        model.addAttribute("actividades", actividadService.findActivas());
        model.addAttribute("filtro", "activas");
        return "actividades/list";
    }
    
    @GetMapping("/inactivas")
    public String listarInactivas(Model model) {
        model.addAttribute("actividades", actividadService.findInactivas());
        model.addAttribute("filtro", "inactivas");
        return "actividades/list";
    }

    @GetMapping({"/new", "/edit/{id}"})
    public String showFormulario(@PathVariable(required = false) Long id, Model model) {
        Actividad actividad;
        if (id != null) {
            actividad = actividadService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
            model.addAttribute("action", "edit");
        } else {
            actividad = new Actividad();
            model.addAttribute("action", "new");
        }
        model.addAttribute("actividad", actividad);
        model.addAttribute("usuarios", usuarioService.findActivos());
        model.addAttribute("salas", salaService.findActivos());
        return "actividades/form";
    }

    @PostMapping
    public String save(@ModelAttribute Actividad actividad) {        
        actividad.setNombreActividad(actividad.getNombreActividad().toUpperCase());
        actividadService.save(actividad);
        return "redirect:/web/actividades";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Long id) {
        actividadService.desactivar(id);
        return "redirect:/web/actividades";
    }
    
    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Long id) {
        actividadService.activar(id);
        return "redirect:/web/actividades";
    }

    @GetMapping("/{id}")
    public String showDetalle(@PathVariable Long id, Model model) {
        Actividad actividad = actividadService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada: " + id));
        model.addAttribute("actividad", actividad);
        return "actividades/show";
    }

}

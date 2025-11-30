package com.inube.inube_academy.MvcController;

import com.inube.inube_academy.model.Sala;
import com.inube.inube_academy.service.SalaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public String listar(Model model) {
        List<Sala> salas = salaService.findAll();
        model.addAttribute("salas", salas);
        model.addAttribute("fitlro", "todos");
        return "salas/list";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("salas", salaService.findActivos());
        model.addAttribute("filtro", "activos");
        return "salas/list";
    }

    @GetMapping("/inactivos")
    public String listarInactivos(Model model) {
        model.addAttribute("salas", salaService.findInactivas());
        model.addAttribute("filtro", "inactivos");
        return "salas/list";
    }

    @GetMapping({ "/new", "/edit/{id}" })
    public String showFormulario(@PathVariable(required = false) Long id, Model model) {
        Sala sala;
        if (id != null) {
            sala = salaService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID Válido: " + id));
            model.addAttribute("action", "edit");
        } else {
            sala = new Sala();
            model.addAttribute("action", "new");
        }
        model.addAttribute("sala", sala);
        return "salas/form";
    }

    @PostMapping
    public String save(@ModelAttribute Sala sala) {
        sala.setNombreSala(sala.getNombreSala().toUpperCase());
        if (sala.getDescripcion() != null)
            sala.setDescripcion(sala.getDescripcion().toUpperCase());

        if (sala.getIdSala() == null) {

            salaService.save(sala);
        } else {

            salaService.update(sala.getIdSala(), sala);
        }

        return "redirect:/web/salas";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Long id) {
        salaService.desactivar(id);
        return "redirect:/web/salas";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Long id) {
        salaService.activar(id);
        return "redirect:/web/salas";
    }

    @GetMapping("/{id}")
    public String showDetalle(@PathVariable Long id, Model model) {
        Sala sala = salaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sala no encontrada: " + id));
        model.addAttribute("sala", sala);
        return "salas/show";
    }

}

package com.inube.inube_academy.MvcController;

import com.inube.inube_academy.model.RegistroDiario;
import com.inube.inube_academy.service.RegistroDiarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web/registroDiario")
public class RegistroDiarioMvcController {

    private final RegistroDiarioService registroDiarioService;

    public RegistroDiarioMvcController(RegistroDiarioService registroDiarioService) {
        this.registroDiarioService = registroDiarioService;
    }
    
    @GetMapping
    public String listar(Model model) {
        List<RegistroDiario> registros = registroDiarioService.findAll();
        model.addAttribute("registros", registros);
        return "registroDiario/list";
    }

}

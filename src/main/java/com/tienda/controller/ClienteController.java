package com.tienda.controller;

import com.tienda.dao.ClienteDao;
import com.tienda.domain.Cliente;
import com.tienda.service.ClienteService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    //Provoca que el objeto se cree si no existe o se use el que existe... no hace más de 1 objeto
    //Esto se conoce como inyeccion de dependencias...
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/nuevo")
    
    public String clienteNuevo(Cliente cliente) {
        return "modificarCliente";
    }
    
     @PostMapping("/cliente/guardar")
    
    public String clienteGuardar(Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/";
    }
    
     @GetMapping("/cliente/actualiza/{idCliente}")
    public String clienteActualiza(Cliente cliente, Model model) {
        cliente = clienteService.getCliente(cliente);
        model.addAttribute("cliente",cliente);
        return "modificarCliente";
    }
    
     @GetMapping("/cliente/elimina/{idCliente}")
    public String clienteElimina(Cliente cliente) {
        clienteService.delete(cliente);
        return "redirect:/";
    }

}

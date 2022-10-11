package com.tienda.controller;

import com.tienda.dao.ClienteDao;
import com.tienda.domain.Cliente;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    //Provoca que el objeto se cree si no existe o se use el que existe... no hace más de 1 objeto
    //Esto se conoce como inyeccion de dependencias...
    @Autowired
    private ClienteDao clienteDao;
    
    @GetMapping("/")
    public String inicio(Model model){
        var texto="Estamos en semana 4";
        model.addAttribute("saludo", texto);
        
        /*Cliente cliente1 = new Cliente("Pedro","Jimenez Retana","pjimenez@gmail.com","89899898");
        Cliente cliente2 = new Cliente("Juan","Jimenez Retana","jretana@gmail.com","69699696");
        Cliente cliente3 = new Cliente("Juancho","Jimenez Retana","juretana@gmail.com","69699696");
        
        var clientes=Arrays.asList(cliente1,cliente2,cliente3);*/
        
        var clientes = clienteDao.findAll();
        
        model.addAttribute("clientes",clientes);
        
        return "index";
    }
    
    
    
}

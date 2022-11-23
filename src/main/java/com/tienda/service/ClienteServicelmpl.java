package com.tienda.service;

import com.tienda.dao.ClienteDao;
import com.tienda.dao.CreditoDao;
import com.tienda.domain.Cliente;
import com.tienda.domain.Credito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicelmpl implements ClienteService {

    //AutoWired provoca que si el objeto ya esta
    @Autowired
    private ClienteDao clienteDao;
    
    @Autowired
    private CreditoDao creditoDao;

    //Los metodos para poder hacer un CRUD
    //Create Read Update Delete
    
     @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientesPorApellidos(String apellidos) {
        return (List<Cliente>) clienteDao.findByApellidos(apellidos);
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Cliente cliente) {
        
        Credito credito = cliente.getCredito();
        credito = creditoDao.save(credito);
        cliente.setCredito(credito);
        
        clienteDao.save(cliente);
    }
    
    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }
}

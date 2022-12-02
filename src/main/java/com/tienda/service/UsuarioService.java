package com.tienda.service;

import com.tienda.dao.UsuarioDao;
import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //se busca el ussuario en la tabla de usuarios
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) { // No se encontro el usuario
            throw new UsernameNotFoundException(username);
        }

        //se cargan los roles que tiene el usuario
        var roles = new ArrayList<GrantedAuthority>();

        for (Rol rol : usuario.getRoles()) { // se recorre la lista de roles
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}

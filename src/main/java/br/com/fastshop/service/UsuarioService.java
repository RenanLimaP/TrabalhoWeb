package br.com.fastshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fastshop.model.Role;
import br.com.fastshop.model.Usuario;
import br.com.fastshop.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void adicionarUsuario(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hasSenha = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(hasSenha);
    	Role role = new Role();
    	role.setId(1L);
    	List<Role> roles = new ArrayList<Role>();
    	roles.add(role);
    	usuario.setRoles(roles);
		usuarioRepository.save(usuario);
	}

	public List<Usuario> retornarTodasOsUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.getOne(id);
	}

	public void removerUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
}

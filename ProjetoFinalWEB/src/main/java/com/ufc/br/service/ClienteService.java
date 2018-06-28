package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ufc.br.model.Cliente;
import com.ufc.br.model.Role;
import com.ufc.br.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public void adicionarCliente(Cliente cliente) {
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		Role role = new Role();
		role.setId(1L);
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		cliente.setRoles(roles);

		clienteRepository.save(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return clienteRepository.getOne(id);
	}
}

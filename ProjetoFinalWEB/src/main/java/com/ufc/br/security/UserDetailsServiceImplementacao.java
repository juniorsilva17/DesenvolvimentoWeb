package com.ufc.br.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Cliente;
import com.ufc.br.repository.ClienteRepository;

@Repository
@Transactional
public class UserDetailsServiceImplementacao implements UserDetailsService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByLogin(login);

		if (cliente == null) {
			throw new UsernameNotFoundException("Cliente não encontrado");
		}

		return new User(cliente.getUsername(), cliente.getPassword(), true, true, true, true, cliente.getAuthorities());
	}

}
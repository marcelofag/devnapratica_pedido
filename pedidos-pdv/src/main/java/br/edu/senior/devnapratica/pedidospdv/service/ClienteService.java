package br.edu.senior.devnapratica.pedidospdv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDao;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

@Service
public class ClienteService {
	@Autowired
	private ClienteDao clienteDao;

	public List<Cliente> buscarTodos() {
		return clienteDao.buscarTodos();

	}

	public Optional<Cliente> buscar(Long clienteId) {
		return clienteDao.buscar(clienteId);
	}

	public Cliente salvar(Cliente cliente) {
		boolean temClienteComMesmoEmail = clienteDao.buscarTodos().stream()
				.anyMatch(outroCliente -> outroCliente.getEmail().equals(cliente.getEmail()));

		if (temClienteComMesmoEmail) {
			// e agora josé?
		}
		return clienteDao.salvar(cliente);
	}

	public Cliente alterar(Cliente cliente) {
		return clienteDao.alterar(cliente);
	}

	public void excluir(Long clienteId) {
		clienteDao.excluir(clienteId);
		;
	}

}

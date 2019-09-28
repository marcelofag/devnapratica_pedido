package br.edu.senior.devnapratica.pedidospdv.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET, path = "/v1/clientes")
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> clientes = clienteService.buscarTodos();

		return ResponseEntity.ok(clientes);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/v1/clientes")
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {

		Cliente clienteSalvo = clienteService.salvar(cliente);
		return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/v1/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable("clienteId") Long idDoClienteId) {
		Optional<Cliente> clienteOpt = clienteService.buscar(idDoClienteId);
       if(clienteOpt.isPresent()) {
    	   return ResponseEntity.ok(clienteOpt.get());
       }
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/v1/clientes/{clienteId}")

	public ResponseEntity<Cliente> alterar(@RequestBody Cliente cliente) {

		Cliente clienteAlterado = clienteService.alterar(cliente);
		return ResponseEntity.ok(clienteAlterado);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/v1/clientes/{clienteId}")

	public void remover(@PathVariable("clienteId") Long clienteId) {

		clienteService.excluir(clienteId);
	}

}

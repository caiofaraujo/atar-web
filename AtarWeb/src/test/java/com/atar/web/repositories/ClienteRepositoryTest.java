package com.atar.web.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.atar.web.entities.Clientes;

@RunWith(SpringRunner .class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

		// Classe de execução de testes com JUNIT
	
		@Autowired
		private ClienteRepository clienteRepository;
		
		// Massa de dados
		private static final String NOME = "Caio Fernandes";
		
		// 1. Antes do teste cadastra um cliente
		@Before
		public void setUp() throws Exception {
			Clientes cliente = new Clientes();
			cliente.setNome(NOME);
			cliente.setEndereco("Rua Cabral do Nascimento");
			this.clienteRepository.save(cliente);
		}
		
		// 2. Após o teste deleta o cliente criado
		@After
		public final void tearDown() {
			this.clienteRepository.deleteAll();
		}
		 
		@Test
		public void testBuscarPorNome() {
			Clientes cliente = this.clienteRepository.findByNome(NOME);
			assertEquals(NOME, cliente.getNome());
		}
		
		
		
}

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

import com.atar.web.entities.Equipamentos;

@RunWith(SpringRunner .class)
@SpringBootTest
@ActiveProfiles("test")
public class EquipamentoRepositoryTest {
	
			// Classe de execução de testes com JUNIT
	
			@Autowired
			private EquipamentoRepository equipamentoRepository;
			
			// Massa de dados
			private static final String MARCA = "ATAR";
			private static final String MODELO = "420ATR";
			
			// 1. Antes do teste cadastra um equipamento
			@Before
			public void setUp() throws Exception {
				Equipamentos equipamento = new Equipamentos();
				equipamento.setMarca(MARCA);	
				equipamento.setModelo(MODELO);
				this.equipamentoRepository.save(equipamento);
			}
			
			// 2. Após o teste deleta o equipamento criado
			@After
			public final void tearDown() {
				
				this.equipamentoRepository.deleteAll();
			}
			
			@Test
			public void testBuscarPorModelo() {
				Equipamentos equipamento = this.equipamentoRepository.findByModelo(MODELO);
				equipamento = this.equipamentoRepository.findByMarca(MARCA);
				assertEquals(equipamento.getModelo(), equipamento.getMarca());
			}
			
			@Test
			public void testBuscarPorMarca() {
				Equipamentos equipamento = this.equipamentoRepository.findByMarca(MARCA);
				assertEquals(equipamento.getModelo(), equipamento.getMarca());
			}
}

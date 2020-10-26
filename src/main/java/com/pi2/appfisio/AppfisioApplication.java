package com.pi2.appfisio;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pi2.appfisio.domain.Cidade;
import com.pi2.appfisio.domain.Especialidade;
import com.pi2.appfisio.domain.Estado;
import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.repositories.CidadeRepository;
import com.pi2.appfisio.repositories.EspecialidadeRepository;
import com.pi2.appfisio.repositories.EstadoRepository;
import com.pi2.appfisio.repositories.PatologiaRepository;

@SpringBootApplication
public class AppfisioApplication implements CommandLineRunner {
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private PatologiaRepository patologiaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppfisioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Especialidade esp1 = new Especialidade(null, "Traumatologia");
		Especialidade esp2 = new Especialidade(null, "Neurologia");
		
		Patologia p1 = new Patologia(null, "AVC", esp2);
		Patologia p2 = new Patologia(null, "Parkinson", esp2);
		Patologia p3 = new Patologia(null, "Tendinite", esp1);
		Patologia p4 = new Patologia(null, "Esporão calcâneo", esp1);
		
		esp1.getPatologias().addAll(Arrays.asList(p3, p4));
		esp2.getPatologias().addAll(Arrays.asList(p1, p2));
		
		especialidadeRepository.saveAll(Arrays.asList(esp1, esp2));
		patologiaRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Paraiba");
		
		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "Olinda", est1);
		Cidade c3 = new Cidade(null, "João Pessoa", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	}

}

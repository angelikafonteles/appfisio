package com.pi2.appfisio;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pi2.appfisio.domain.Cidade;
import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.domain.Especialidade;
import com.pi2.appfisio.domain.Estado;
import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.domain.enums.EspecialidadeMedica;
import com.pi2.appfisio.domain.enums.Genero;
import com.pi2.appfisio.repositories.AnamneseRepository;
import com.pi2.appfisio.repositories.CidadeRepository;
import com.pi2.appfisio.repositories.EnderecoRepository;
import com.pi2.appfisio.repositories.EspecialidadeRepository;
import com.pi2.appfisio.repositories.EstadoRepository;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.repositories.PatologiaRepository;
import com.pi2.appfisio.repositories.UsuarioRepository;

@SpringBootApplication
public class AppfisioApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private AnamneseRepository anamneseRepository;

	@Autowired
	private PatologiaRepository patologiaRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppfisioApplication.class, args);
	}
	
	@Configuration
	@EnableWebMvc
	public class WebConfig extends WebMvcConfigurerAdapter {
	
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE");
		}
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Especialidade esp1 = new Especialidade(null, EspecialidadeMedica.TRAUMATOLOGIA);
		Especialidade esp2 = new Especialidade(null, EspecialidadeMedica.NEUROLOGIA);

		Patologia p1 = new Patologia(null, "AVC", esp2);
		Patologia p2 = new Patologia(null, "Parkinson", esp2);
		Patologia p3 = new Patologia(null, "Tendinite", esp1);
		Patologia p4 = new Patologia(null, "Esporão calcâneo", esp1);

		esp1.getPatologias().addAll(Arrays.asList(p3, p4));
		esp2.getPatologias().addAll(Arrays.asList(p1, p2));

		especialidadeRepository.saveAll(Arrays.asList(esp1, esp2));
		patologiaRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Galaxy");

		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "Olinda", est1);
		Cidade c3 = new Cidade(null, "GA", est2);

		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c2));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Usuario user1 = new Usuario(null, "Fabio", "345343123-98", sdf.parse("01/01/1983"), "123ED23",
				"fabio@gmail.com", "123456");

		Paciente pcte1 = new Paciente(null, "123.456.789-00", "Darth Vader", sdf.parse("01/04/1954"), Genero.MASCULINO,
				"darth@nasa.com", "Comandante", user1);

		pcte1.getTelefones().addAll(Arrays.asList("99999999"));

		Endereco end1 = new Endereco(null, "Estrela da Morte", "S/N", "Estação Espacial Bélica", "Star Wars", "5000",
				c3, pcte1);

		pcte1.getEnderecos().addAll(Arrays.asList(end1));

		user1.getPacientes().addAll(Arrays.asList(pcte1));

		usuarioRepository.saveAll(Arrays.asList(user1));
		pacienteRepository.saveAll(Arrays.asList(pcte1));
		enderecoRepository.saveAll(Arrays.asList(end1));

	}

}

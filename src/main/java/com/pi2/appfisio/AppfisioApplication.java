package com.pi2.appfisio;

import java.text.SimpleDateFormat;
import java.time.Instant;
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

import com.pi2.appfisio.domain.Anamnese;
import com.pi2.appfisio.domain.Cidade;
import com.pi2.appfisio.domain.Conduta;
import com.pi2.appfisio.domain.CondutaTecnica;
import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.domain.Especialidade;
import com.pi2.appfisio.domain.Estado;
import com.pi2.appfisio.domain.Login;
import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.domain.Sessao;
import com.pi2.appfisio.domain.Tecnica;
import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.domain.enums.Genero;
import com.pi2.appfisio.domain.enums.TecnicaTerapeutica;
import com.pi2.appfisio.repositories.AnamneseRepository;
import com.pi2.appfisio.repositories.CidadeRepository;
import com.pi2.appfisio.repositories.CondutaRepository;
import com.pi2.appfisio.repositories.CondutaTecnicaRepository;
import com.pi2.appfisio.repositories.EnderecoRepository;
import com.pi2.appfisio.repositories.EspecialidadeRepository;
import com.pi2.appfisio.repositories.EstadoRepository;
import com.pi2.appfisio.repositories.LoginRepository;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.repositories.PatologiaRepository;
import com.pi2.appfisio.repositories.SessaoRepository;
import com.pi2.appfisio.repositories.TecnicaRepository;
import com.pi2.appfisio.repositories.UsuarioRepository;

@SpringBootApplication
public class AppfisioApplication implements CommandLineRunner {

	@Autowired
	private LoginRepository loginRepository;
	
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
	
	@Autowired
	private TecnicaRepository tecnicaRepository;
	
	@Autowired
	private CondutaRepository condutaRepository;
	
	@Autowired
	private CondutaTecnicaRepository condutaTecnicaRepository;
	
	@Autowired
	private SessaoRepository sessaoRepository;

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
		
		Especialidade esp1 = new Especialidade(null, "Traumatologia");
		Especialidade esp2 = new Especialidade(null, "Neurologia");
		
		Patologia p1 = new Patologia(null, "AVC", esp2);
		Patologia p2 = new Patologia(null, "Parkinson", esp2);
		Patologia p3 = new Patologia(null, "Tendinite", esp1);
		Patologia p4 = new Patologia(null, "Esporão calcâneo", esp1);
		
		especialidadeRepository.saveAll(Arrays.asList(esp1, esp2));
		patologiaRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Galaxy");

		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "Olinda", est1);
		Cidade c3 = new Cidade(null, "GA", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1,c2));
		est2.getCidades().addAll(Arrays.asList(c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Login login1 = new Login(null, "fabio@gmail.com", "123456");
		Login login2 = new Login(null, "adriana@gmail.com", "654321");
		
		Usuario user1 = new Usuario(null, "Fabio", "345343123-98", sdf.parse("01/01/1983"), "123ED23", login1);
		Usuario user2 = new Usuario(null, "Adriana", "123123123-12", sdf.parse("01/01/1980"), "13DF234", login2);
		
		login1.setUsuario(user1);
		login2.setUsuario(user2);
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2));
		loginRepository.saveAll(Arrays.asList(login1,login2));
	
		Paciente pcte1 = new Paciente(null, "123.456.789-00", "Darth Vader", sdf.parse("01/04/1954"), Genero.MASCULINO, "darth@nasa.com", "Comandante", user1);
		
		pcte1.getTelefones().addAll(Arrays.asList("99999999"));
		
		Endereco end1 = new Endereco(null, "Estrela da Morte", "S/N", "Estação Espacial Bélica", "Star Wars", "5000", c3, pcte1);
		
		pcte1.setEndereco(end1);
		
		user1.getPacientes().addAll(Arrays.asList(pcte1));
		
		enderecoRepository.saveAll(Arrays.asList(end1));
		pacienteRepository.saveAll(Arrays.asList(pcte1));
		
		Anamnese a1 = new Anamnese(null, sdf.parse("12/10/2020"), "120x90 mmHg", "100 Bpm", "Avô tinha diabetes", "Hemofilia",
				"Paciente em quadro álgico agudo, apresenta hemartrose em toda área do joelho esquerdo afetando sua ADM (amplitude de movimento)",
				"Dor na “junta” do joelho e tem dificuldade para dobrar ele, devido a ter caído de uma moto",
				"A goniometria do paciente mostra uma perda de 20 graus na flexão do joelho, o raio-x não acusa nenhuma fratura ou fissura. Paciente faz uso de anticoagulante",
				pcte1, p3);
		
		pcte1.getAnamneses().addAll(Arrays.asList(a1));
		
		anamneseRepository.saveAll(Arrays.asList(a1));
		
		Tecnica t1 = new Tecnica(null, TecnicaTerapeutica.ALOGAMENTOCADEIAANTERIOR);
		Tecnica t2 = new Tecnica(null, TecnicaTerapeutica.ALOGAMENTOCADEIALATERIAL);
		Tecnica t3 = new Tecnica(null, TecnicaTerapeutica.ALOGAMENTOCADEIAPOSTERIOR);
		Tecnica t4 = new Tecnica(null, TecnicaTerapeutica.ALOGAMENTOMEMBROINFERIOR);
		Tecnica t5 = new Tecnica(null, TecnicaTerapeutica.ALOGAMENTOMEMBROSUPERIOR);
		Tecnica t6 = new Tecnica(null, TecnicaTerapeutica.ALONGAMENTODAMUSCULATURARESPIRATORIA);
		Tecnica t7 = new Tecnica(null, TecnicaTerapeutica.CONSCIENTIZACAORESPIRATORIA);
		Tecnica t8 = new Tecnica(null, TecnicaTerapeutica.EAPARTESANAL);
		Tecnica t9 = new Tecnica(null, TecnicaTerapeutica.EXERCICIOSCALISTENICOS);
		Tecnica t10 = new Tecnica(null, TecnicaTerapeutica.ONDASCURTAS);
		Tecnica t11 = new Tecnica(null, TecnicaTerapeutica.TENSCONVENCIONAL);
		Tecnica t12 = new Tecnica(null, TecnicaTerapeutica.US);
		
		tecnicaRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
		
		Conduta conduta1 = new Conduta(null, "Traumato I", p3);
		
		p3.getCondutas().addAll(Arrays.asList(conduta1));
		
		condutaRepository.saveAll(Arrays.asList(conduta1));
		
		CondutaTecnica ct1 = new CondutaTecnica(conduta1, t11, "(100Hz-50 de largura) durante 40 minutos");
		CondutaTecnica ct2 = new CondutaTecnica(conduta1, t12, "(1MHz-pulsátil-atermico) -10 minutos");
		
		conduta1.getTecnicas().addAll(Arrays.asList(ct1, ct2));
		
		t11.getTecnicas().addAll(Arrays.asList(ct1));
		t12.getTecnicas().addAll(Arrays.asList(ct2));
		
		condutaTecnicaRepository.saveAll(Arrays.asList(ct1,ct2));
		
		Sessao s1 = new Sessao(null, Instant.parse("2020-10-20T10:53:07Z"), pcte1, conduta1);
		
		conduta1.getSessoes().addAll(Arrays.asList(s1));
		
		sessaoRepository.saveAll(Arrays.asList(s1));
		
	}

}

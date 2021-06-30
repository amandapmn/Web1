package br.ufscar.dc.dsw;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IConsultaDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SistemaConsultas {

	private static final Logger log = LoggerFactory.getLogger(SistemaConsultas.class);

	public static void main(String[] args) {
		SpringApplication.run(SistemaConsultas.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IClienteDAO clienteDAO, IProfissionalDAO profissionalDAO, IConsultaDAO consultaDAO) {
		return (args) -> {

			Date data1  = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss").parse("22/08/202109:00:00");

			Usuario usuario1 = new Usuario("admin@admin.com", encoder.encode("12345678"), "111.111.111-11", "Admin", "Admin", "ADMIN");
			Cliente cliente1 = new Cliente("nathan.oliveirasc17@gmail.com", encoder.encode("12345678"), "222.222.222-22", "Matheus", "Freitas", "CLIENTE", "11 99999-9999", "Masculino", "11/11/2005");
			Cliente cliente2 = new Cliente("nathanael.silva@gmail.com", encoder.encode("12345678"), "444.444.444-44", "Nathanael", "Silva", "CLIENTE", "11 98888-9999", "Masculino", "09/12/2011");
			Profissional profissional1 = new Profissional("pro1@pro.com", encoder.encode("12345678"), "333.333.333-33", "João", "Silva", "PROFISSIONAL", "Médico", "Medicina USP");
			Profissional profissional2 = new Profissional("amandamanso7@gmail.com", encoder.encode("12345678"), "555.555.555-55", "Alice", "Peixoto", "PROFISSIONAL", "Cantora", "Música UFSCar");
			Consulta consulta1 = new Consulta(data1, "dsfsfdssfd", cliente1, profissional1);
			Consulta consulta2 = new Consulta(data1, "dsfsfASSSAS", cliente2, profissional1);
			Consulta consulta3 = new Consulta(data1, "dsfssaasaSAS", cliente2, profissional2);
			log.info("Salvando Usuario - Admin");
			usuarioDAO.save(usuario1);

			log.info("Salvando Cliente - Matheus Freitas");
			clienteDAO.save(cliente1);
			
			log.info("Salvando Cliente - Nathanael Silva");
			clienteDAO.save(cliente2);

			log.info("Salvando Profissional - João Silva");
			profissionalDAO.save(profissional1);
			
			log.info("Salvando Profissional - Alice Peixoto");
			profissionalDAO.save(profissional2);

			log.info("Salvando Consulta");
			consultaDAO.save(consulta1);
			
			log.info("Salvando Consulta");
			consultaDAO.save(consulta2);
			
			log.info("Salvando Consulta");
			consultaDAO.save(consulta3);

		};
	}
}

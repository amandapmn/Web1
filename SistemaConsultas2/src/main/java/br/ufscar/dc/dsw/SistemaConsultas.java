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

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date data1 = new Date();

			Usuario usuario1 = new Usuario("admin@admin.com", encoder.encode("12345678"), "448.992.758-45", "Admin", "Admin", "ADMIN");
			Cliente cliente1 = new Cliente("nathan.oliveirasc17@gmail.com", encoder.encode("12345678"), "448.992.758-46", "Matheus", "Freitas", "CLIENTE", "11 99999-9999", "Masculino", "11/11/1111");
			Profissional profissional1 = new Profissional("pro1@pro.com", encoder.encode("12345678"), "448.992.758-47", "João", "Silva", "PROFISSIONAL", "Médico", "Medicina USP");
			Consulta consulta1 = new Consulta(data1, "dsfsfdssfd", cliente1, profissional1);
			log.info("Salvando Usuario - Admin");
			usuarioDAO.save(usuario1);

			log.info("Salvando Cliente - Matheus Freitas");
			clienteDAO.save(cliente1);

			log.info("Salvando Profissional - João Silva");
			profissionalDAO.save(profissional1);

			log.info("Salvando Consulta");
			consultaDAO.save(consulta1);

		};
	}
}

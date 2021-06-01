package br.ufscar.dc.dsw;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {

		EmailService service = new EmailService();

		InternetAddress from = new InternetAddress("dsw1sistemaconsultas@gmail.com", "Fulano");
		InternetAddress to1 = new InternetAddress("amandamanso@estudante.ufscar.br", "Beltrano");
		//InternetAddress to2 = new InternetAddress("{profissional.getEmail()}", "Beltrano");

		String subject1 = "Consulta.me: Consulta marcada com sucesso!";
		String subject2 = "Consulta.me: Nova consulta marcada";

		String body1 = "Consulta agendada com sucesso. Segue informações:"
				+ "Profissional: {getprimeiroNome()}"
				+ "Especialidade: {getEspecialidade()}"
				+ "Data: {getData()}"
				+ "Link da consulta: {link}";

		String body2 = "Nova consulta agendada. Segue informações:"
				+ "Cliente: {getNome()}"
				+ "Data: {getData()} "
				+ "Link da consulta: {link}";

		// Envio cliente
		service.send(from, to1, subject1, body1);

		// Envio profissional
		//service.send(from, to2, subject2, body2);
	}
}

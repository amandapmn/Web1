package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="#{NotBlank.usuario.email}")
	@Column(nullable = false, unique = true, length = 254)
	private String email;

	@NotBlank(message="#{NotBlank.usuario.senha}")
	@Column(nullable = false, unique = false, length = 1024)
	private String senha;

	@NotBlank(message="#{NotBlank.usuario.cpf}")
	@Column(nullable = false, unique = true, length = 14)
	private String cpf;

	@NotBlank(message="#{NotBlank.usuario.primeiroNome}")
	@Column(nullable = false, unique = false, length = 255)
	private String primeiroNome;

	@NotBlank(message="#{NotBlank.usuario.sobrenome}")
	@Column(nullable = false, unique = false, length = 512)
	private String sobrenome;

	@Column(nullable = false, unique = false, length = 128)
	private String papel;

	public Usuario() {
	}

	public Usuario(String email, String senha, String cpf, String primeiroNome, String sobrenome, String papel) {
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.primeiroNome = primeiroNome;
		this.sobrenome = sobrenome;
		this.papel = papel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + ", cpf=" + cpf +
		 ", primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", papel=" +
		  papel + "]";
	}
}

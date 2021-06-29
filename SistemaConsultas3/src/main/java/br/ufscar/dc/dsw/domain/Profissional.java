package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Usuario;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "consultas", "senha"})
@Entity
@Table(name = "profissional")
public class Profissional extends Usuario {

	@NotBlank(message="{NotBlank.profissional.especialidade}")
	@Column(nullable = false, unique = false, length = 255)
	private String especialidade;

	@NotBlank(message="{NotBlank.profissional.qualificacoes}")
  @Column(nullable = false, unique = false, length = 512)
	private String qualificacoes;


  @OneToMany(mappedBy = "profissional", cascade=CascadeType.REMOVE)
	private List<Consulta> consultas;

	public Profissional() {

	}

	public Profissional(String email, String senha, String cpf, String primeiroNome,
   String sobrenome, String papel, String especialidade, String qualificacoes) {
		super(email, senha, cpf, primeiroNome, sobrenome, papel);
		this.especialidade = especialidade;
		this.qualificacoes = qualificacoes;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

  public String getQualificacoes() {
		return qualificacoes;
	}

	public void setQualificacoes(String qualificacoes) {
		this.qualificacoes = qualificacoes;
	}

  public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

  public void addConsulta(Consulta consulta) {
		this.consultas.add(consulta);
	}

	@Override
	public String toString() {
    return "Profissional [email=" + this.getEmail() + ", senha=" + this.getSenha() + ", cpf=" +
		this.getCpf() + ", primeiroNome=" + this.getPrimeiroNome() + ", sobrenome=" +
		 this.getSobrenome() + ", papel=" + this.getPapel() + ", especialidade=" +
      this.getEspecialidade() + ", qualificacoes=" + this.getQualificacoes() + "]";
	}
}

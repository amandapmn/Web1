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
@Table(name = "cliente")
public class Cliente extends Usuario {

	@NotBlank(message="{NotBlank.cliente.telefone}")
	@Column(nullable = false, unique = false, length = 20)
	private String telefone;

	@NotBlank(message="{NotBlank.cliente.sexo}")
  @Column(nullable = false, unique = false, length = 50)
	private String sexo;

	@NotBlank(message="{NotBlank.cliente.dataNasc}")
	@Column(nullable = false, unique = false, length = 14)
	private String dataNasc;

  @OneToMany(mappedBy = "cliente", cascade=CascadeType.REMOVE)
	private List<Consulta> consultas;

	public Cliente() {

	}

	public Cliente(String email, String senha, String cpf, String primeiroNome,
   String sobrenome, String papel, String telefone, String sexo, String dataNasc) {
		super(email, senha, cpf, primeiroNome, sobrenome, papel);
    this.telefone = telefone;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

  public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
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
    return "Cliente [email=" + this.getEmail() + ", senha=" + this.getSenha() + ", cpf=" +
		this.getCpf() + ", primeiroNome=" + this.getPrimeiroNome() + ", sobrenome=" +
		 this.getSobrenome() + ", papel=" + this.getPapel() + ", telefone=" + this.getTelefone() +
		  ", sexo=" + this.getSexo() + ", dataNasc=" + this.getDataNasc() +  "]";
	}
}

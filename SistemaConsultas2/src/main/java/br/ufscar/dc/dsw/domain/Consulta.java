package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false, unique = false)
	private Date dataHorario;

	@NotNull
	@Column(nullable = false, unique = false, length = 255)
	private String videoconferencia;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_profissional")
	private Profissional profissional;

	@Transient
	private String data;

	@Transient
	private String horario;

	@Transient
	private String cpfCliente;

	@Transient
	private String cpfProfissional;

	public Consulta() {

	}

	public Consulta(Date dataHorario, String videoconferencia, Cliente cliente,
	Profissional profissional) {
    this.dataHorario = dataHorario;
		this.videoconferencia = videoconferencia;
		this.cliente = cliente;
		this.profissional = profissional;
	}

	public Date getDataHorario() {
		return dataHorario;
	}

	public void setDataHorario(Date dataHorario) {
		this.dataHorario = dataHorario;
	}

  public String getVideoconferencia() {
		return videoconferencia;
	}

	public void setVideoconferencia(String videoconferencia) {
		this.videoconferencia = videoconferencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

  public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCpfProfissional() {
		return cpfProfissional;
	}

	public void setCpfProfissional(String cpfProfissional) {
		this.cpfProfissional = cpfProfissional;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}


	@Override
	public String toString() {
    return "Consulta [dataHorario=" + dataHorario + ", videoconferencia=" +
		 videoconferencia + ", " + cliente.toString() + ", " + profissional.toString() +
		 "]";
	}
}

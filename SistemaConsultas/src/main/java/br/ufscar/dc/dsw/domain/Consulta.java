package br.ufscar.dc.dsw.domain;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

public class Consulta {

    private Long id;
    private String dataHorario;
    private String videoconferencia;
    private Cliente cliente;
    private Profissional profissional;

    public Consulta(Long id) {
        this.id = id;
    }

    public Consulta(Long id, String dataHorario, String videoconferencia) {
        this.id = id;
        this.dataHorario = dataHorario;
        this.videoconferencia = videoconferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(String dataHorario) {
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
}

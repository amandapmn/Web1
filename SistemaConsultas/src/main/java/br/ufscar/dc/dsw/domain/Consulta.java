package br.ufscar.dc.dsw.domain;

public class Consulta {

    private Long id;
    private String dataHorario;
    private String videoconferencia;

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
}

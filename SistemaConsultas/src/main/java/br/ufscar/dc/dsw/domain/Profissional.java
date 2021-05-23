package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.domain.Usuario;

public class Profissional {

    private Long id;
    private Usuario usuario;
    private String especialidade;
    private String qualificacoes;

    public Profissional(Long id) {
        this.id = id;
    }

    public Profissional(Long id, Usuario usuario, String especialidade, String qualificacoes) {
        this.id = id;
        this.usuario = usuario;
        this.especialidade = especialidade;
        this.qualificacoes = qualificacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

}

package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.domain.Usuario;

public class Cliente {

    private Long id;
    private Usuario usuario;
    private String telefone;
    private String sexo;
    private String dataNasc;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(Long id, Usuario usuario, String telefone, String sexo, String dataNasc) {
        this.id = id;
        this.usuario = usuario;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
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

}

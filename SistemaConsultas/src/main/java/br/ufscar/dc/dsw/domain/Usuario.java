package br.ufscar.dc.dsw.domain;

public class Usuario {

    private Long id;
    private String email;
    private String senha;
    private String cpf;
    private String primeiroNome;
    private String sobrenome;
    private String papel;

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(Long id, String email, String senha, String cpf, String primeiroNome, String sobrenome, String papel) {
        this.id = id;
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

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
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
}

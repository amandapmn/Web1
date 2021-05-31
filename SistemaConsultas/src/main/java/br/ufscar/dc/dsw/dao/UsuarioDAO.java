package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public Long insert(Usuario usuario) {

        Long last_id = null;

        String sql = "INSERT INTO usuario (email, senha, cpf, primeiro_nome, sobrenome, papel) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getCPF());
            statement.setString(4, usuario.getPrimeiroNome());
            statement.setString(5, usuario.getSobrenome());
            statement.setString(6, usuario.getPapel());
            statement.execute();


            sql = "SELECT LAST_INSERT_ID()";
            statement = conn.prepareStatement(sql);
            statement.execute();

            ResultSet  resultSet = statement.getResultSet();
            if (resultSet.next()) {
                last_id = resultSet.getLong("LAST_INSERT_ID()");

            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return last_id;
    }

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM usuario where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE usuario SET email = ?, senha = ?, cpf = ?, primeiro_nome = ?, sobrenome = ?";
        sql += ", papel = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getCPF());
            statement.setString(4, usuario.getPrimeiroNome());
            statement.setString(5, usuario.getSobrenome());
            statement.setString(6, usuario.getPapel());
            statement.setLong(7, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * from usuario";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String primeiroNome = resultSet.getString("primeiro_nome");
                String sobrenome = resultSet.getString("sobrenome");
                String papel = resultSet.getString("papel");
                Usuario usuario = new Usuario(id, email, senha, cpf, primeiroNome, sobrenome, papel);
                listaUsuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public Usuario get(Long id) {
        Usuario usuario = null;

        String sql = "SELECT * from usuario where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("cpf");
                String primeiroNome = resultSet.getString("primeiro_nome");
                String sobrenome = resultSet.getString("sobrenome");
                String papel = resultSet.getString("papel");
                usuario = new Usuario(id, email, senha, cpf, primeiroNome, sobrenome, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario getbyEmail(String email) {
        Usuario usuario = null;

        String sql = "SELECT * from usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	  Long id = resultSet.getLong("id");
                String primeiroNome = resultSet.getString("primeiro_nome");
                String sobrenome = resultSet.getString("sobrenome");
                String cpf = resultSet.getString("cpf");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                usuario = new Usuario (id, email, senha, cpf, primeiroNome, sobrenome, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public boolean verificaEmail(Usuario usuario) {

        String sql = "SELECT id from usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	  Long id = resultSet.getLong("id");
                if(id != usuario.getId()){
                  resultSet.close();
                  statement.close();
                  conn.close();
                  return false;
                }
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean verificaCPF(Usuario usuario) {

        String sql = "SELECT id from usuario WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getCPF());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	  Long id = resultSet.getLong("id");
                if(id != usuario.getId()){
                  resultSet.close();
                  statement.close();
                  conn.close();
                  return false;
                }
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

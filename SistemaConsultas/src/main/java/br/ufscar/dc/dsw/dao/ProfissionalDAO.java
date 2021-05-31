package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.UsuarioDAO;

//Usuario já deve estar dentro do objeto Profissional
public class ProfissionalDAO extends GenericDAO {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void insert(Profissional profissional) {

        String sql = "INSERT INTO profissional (id_usuario, especialidade, qualificacoes) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            statement.setLong(1, usuarioDAO.insert(profissional.getUsuario()));
            statement.setString(2, profissional.getEspecialidade());
            statement.setString(3, profissional.getQualificacoes());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM profissional where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            //usuarioDAO.delete(profissional.getUsuario());
            statement.setLong(1, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE profissional SET especialidade = ?, qualificacoes = ?";
        sql += " WHERE id = ?;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            UsuarioDAO usuarioDao = new UsuarioDAO();
            usuarioDAO.update(profissional.getUsuario());
            statement.setString(1, profissional.getEspecialidade());
            statement.setString(2, profissional.getQualificacoes());
            statement.setLong(3, profissional.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from profissional";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
              Long id = resultSet.getLong("id");
              Usuario usuario = usuarioDAO.get(resultSet.getLong("id_usuario"));
              String especialidade = resultSet.getString("especialidade");
              String qualificacoes = resultSet.getString("qualificacoes");
              Profissional profissional = new Profissional(id, usuario, especialidade, qualificacoes);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public Profissional get(Long id) {
        Profissional profissional = null;

        String sql = "SELECT * from profissional where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Usuario usuario = usuarioDAO.get(resultSet.getLong("id_usuario"));
                String especialidade = resultSet.getString("especialidade");
                String qualificacoes = resultSet.getString("qualificacoes");
                profissional = new Profissional(id, usuario, especialidade, qualificacoes);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public Profissional getByUsuario(Usuario usuario) {
        Profissional profissional = null;

        String sql = "SELECT * from profissional where id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String especialidade = resultSet.getString("especialidade");
                String qualificacoes = resultSet.getString("qualificacoes");
                profissional = new Profissional(id, usuario, especialidade, qualificacoes);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
}

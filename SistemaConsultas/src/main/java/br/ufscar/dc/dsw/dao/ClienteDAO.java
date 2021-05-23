package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.UsuarioDAO;

//Usuario já deve estar dentro do objeto Profissional

public class ClienteDAO extends GenericDAO {
    UsuarioDAO usuarioDAO;
    public void insert(Cliente cliente) {

        String sql = "INSERT INTO cliente (id_usuario, telefone, sexo, data_nasc) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setLong(1, cliente.getUsuario().getId());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getSexo());
            statement.setString(4, cliente.getDataNasc());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE cliente SET telefone = ?, sexo = ?, data_nasc = ?";
        sql += " WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getUsuario().getId());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getSexo());
            statement.setString(4, cliente.getDataNasc());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * from cliente";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
              Long id = resultSet.getLong("id");
              Usuario usuario = usuarioDAO.get(resultSet.getLong("id_usuario"));
              String telefone =  resultSet.getString("telefone");
              String sexo = resultSet.getString("sexo");
              String dataNasc =  resultSet.getString("data_nasc");
              Cliente cliente = new Cliente(id, usuario, telefone, sexo, dataNasc);
              listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public Cliente get(Long id) {
        Cliente cliente = null;
        String sql = "SELECT * from cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
              Usuario usuario = usuarioDAO.get(resultSet.getLong("id_usuario"));
              String telefone =  resultSet.getString("telefone");
              String sexo = resultSet.getString("sexo");
              String dataNasc =  resultSet.getString("dataNasc");
              cliente = new Cliente(id, usuario, telefone, sexo, dataNasc);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}

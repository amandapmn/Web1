package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Consulta;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class ConsultaDAO extends GenericDAO {
    ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    ClienteDAO clienteDAO  = new ClienteDAO() ;

    public void insert(Consulta consulta) {

        String sql = "INSERT INTO consulta (data_horario, videoconferencia, id_profissional, id_cliente) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dt = LocalDateTime.parse(consulta.getDataHorario(), formatter);
            Timestamp ts = Timestamp.valueOf(dt);

            statement.setTimestamp(1, ts);

            statement.setString(2, consulta.getVideoconferencia());
            statement.setLong(3, consulta.getProfissional().getId());
            statement.setLong(4, consulta.getCliente().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Consulta consulta) {
        String sql = "DELETE FROM consulta where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            //usuarioDAO.delete(cliente.getUsuario());
            statement.setLong(1, consulta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getConsultasCliente(Cliente cliente) {

        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * from consulta where id_cliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
              Long id = resultSet.getLong("id");
              String dataHorario = resultSet.getString("data_horario");
              String videoconferencia =  resultSet.getString("videoconferencia");
              Long idProfissional =  resultSet.getLong("id_profissional");

              Consulta consulta = new Consulta(id, dataHorario, videoconferencia);
              consulta.setProfissional(profissionalDAO.get(idProfissional));
              listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public List<Consulta> getConsultasProfissional(Profissional profissional) {

        List<Consulta> listaConsultas = new ArrayList<>();

        String sql = "SELECT * from consulta where id_profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, profissional.getId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
              Long id = resultSet.getLong("id");
              String dataHorario = resultSet.getString("data_horario");
              String videoconferencia =  resultSet.getString("videoconferencia");
              Long idCliente =  resultSet.getLong("id_cliente");

              Consulta consulta = new Consulta(id, dataHorario, videoconferencia);
              consulta.setCliente(clienteDAO.get(idCliente));
              listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }

    public boolean verificaDataHorario(Consulta consulta) {

        String sql = "SELECT COUNT(*) from consulta where id_profissional = ? and data_horario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dt = LocalDateTime.parse(consulta.getDataHorario(), formatter);
            Timestamp ts = Timestamp.valueOf(dt);

            statement.setLong(1, consulta.getProfissional().getId());
            statement.setTimestamp(2, ts);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count > 0){
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

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

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class ConsultaDAO extends GenericDAO {

    public void insert(Consulta consulta, Profissional profissional, Cliente cliente) {

        String sql = "INSERT INTO consulta (data_horario, videoconferencia, id_profissional, id_cliente) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getDataHorario());
            statement.setString(2, consulta.getVideoconferencia());
            statement.setLong(3, profissional.getId());
            statement.setLong(4, cliente.getId());
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

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
              Long id = resultSet.getLong("id");
              String dataHorario = resultSet.getString("data_horario");
              String videoconferencia =  resultSet.getString("videoconferencia");

              Consulta consulta = new Consulta(id, dataHorario, videoconferencia);
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

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
              Long id = resultSet.getLong("id");
              String dataHorario = resultSet.getString("data_horario");
              String videoconferencia =  resultSet.getString("videoconferencia");

              Consulta consulta = new Consulta(id, dataHorario, videoconferencia);
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

        String sql = "SELECT COUNT(*) from consulta WHERE data_horario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dt = LocalDateTime.parse(consulta.getDataHorario(), formatter);
            Timestamp ts = Timestamp.valueOf(dt);

            statement.setTimestamp(1, ts);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count != 0){
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

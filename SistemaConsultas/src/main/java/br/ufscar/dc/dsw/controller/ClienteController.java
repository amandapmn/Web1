package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Consulta;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.util.Erro;

import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/cliente/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try{
          Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        	Erro erros = new Erro();

        	if (usuario == null) {
        		response.sendRedirect("/SistemaConsultas/publico/login");
        	} else if (usuario.getPapel().equals("CLIENTE")) {
            switch (action) {
                case "/minhasConsultas":
                    minhasConsultas(request, response);
                    break;
                case "/marcarConsulta":
                    marcarConsulta(request, response);
                    break;
                case "/profissionais":
                    profissionais(request, response);
                    break;
                default:
                    index(request, response);
                    break;
            }
            return;
        	} else {
        		erros.add("Acesso não autorizado!");
        		erros.add("Apenas Papel [CLIENTE] tem acesso a essa página");
        		request.setAttribute("mensagens", erros);
        		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
        		rd.forward(request, response);
        	}
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }

    }

    private void minhasConsultas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
      ConsultaDAO consultaDAO = new ConsultaDAO();
      ClienteDAO clienteDAO = new ClienteDAO();
      List<Consulta> listaConsultas = consultaDAO.getConsultasCliente(clienteDAO.getByUsuario(usuario));
      request.setAttribute("listaConsultas", listaConsultas);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/listaConsultas.jsp");
      dispatcher.forward(request, response);

    }

    private void marcarConsulta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("id") != null){
          Long id = Long.parseLong(request.getParameter("id"));
          ProfissionalDAO profissionalDAO = new ProfissionalDAO();
          Profissional profissional = profissionalDAO.get(id);
          request.setAttribute("profissional", profissional);

          if(request.getParameter("data") != null && request.getParameter("horario") != null &&
            request.getParameter("videoconferencia") != null){
            ConsultaDAO consultaDAO = new ConsultaDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

            String data = request.getParameter("data");
            String horario = request.getParameter("horario");
            String videoconferencia = request.getParameter("videoconferencia");
            Consulta consulta = new Consulta(null, data + ' ' + horario, videoconferencia);
            consulta.setCliente(clienteDAO.getByUsuario(usuario));
            consulta.setProfissional(profissional);
            if(consultaDAO.verificaDataHorario(consulta) == true){
              consultaDAO.insert(consulta);
              response.sendRedirect("/SistemaConsultas/cliente/minhasConsultas");
            }
            else{
              List<String> messages = new ArrayList<>();
              messages.add("datetime_unavailable");
              request.setAttribute("mensagens", messages);
              request.setAttribute("profissional", profissional);
              RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/marcarConsulta.jsp");
              dispatcher.forward(request, response);
            }
            return;
          }
          RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/marcarConsulta.jsp");
          dispatcher.forward(request, response);
        }
        response.sendRedirect("/SistemaConsultas/cliente/profissionais");
        return;
    }

    private void profissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ProfissionalDAO dao = new ProfissionalDAO();
      List<Profissional> listaProfissionais = dao.getAll();
      request.setAttribute("listaProfissionais", listaProfissionais);
      request.setAttribute("usuarioLogado", request.getSession().getAttribute("usuarioLogado"));
      RequestDispatcher dispatcher = request.getRequestDispatcher("/listaProfissionais.jsp");
      dispatcher.forward(request, response);

    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/index.jsp");
      dispatcher.forward(request, response);
    }

}

package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

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

@WebServlet(urlPatterns = "/profissional/*")
public class ProfissionalController extends HttpServlet {

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
        	} else if (usuario.getPapel().equals("PROFISSIONAL")) {
            switch (action) {
                case "/clientes":
                    break;
                default:
                    index(request, response);
                    break;
            }
            return;
        	} else {
        		erros.add("Acesso não autorizado!");
        		erros.add("Apenas Papel [PROFISSIONAL] tem acesso a essa página");
        		request.setAttribute("mensagens", erros);
        		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
        		rd.forward(request, response);
        	}
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }

    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/index.jsp");
      dispatcher.forward(request, response);
    }

}

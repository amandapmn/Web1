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

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

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
        	} else if (usuario.getPapel().equals("ADMIN")) {
            switch (action) {
                case "/usuarios":
                    usuarios(request, response);
                    break;
                case "/clientes":
                    clientes(request, response);
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
        		erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
        		request.setAttribute("mensagens", erros);
        		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
        		rd.forward(request, response);
        	}
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }

    }

    private void usuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      UsuarioDAO dao = new UsuarioDAO();
      List<Usuario> listaUsuarios = dao.getAll();
      request.setAttribute("listaUsuarios", listaUsuarios);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/usuarios.jsp");
      dispatcher.forward(request, response);
    }

    private void clientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ClienteDAO dao = new ClienteDAO();
      List<Cliente> listaClientes = dao.getAll();
      request.setAttribute("listaClientes", listaClientes);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/clientes.jsp");
      dispatcher.forward(request, response);
    }

    private void profissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ProfissionalDAO dao = new ProfissionalDAO();
      List<Profissional> listaProfissionais = dao.getAll();
      request.setAttribute("listaUsuarios", listaProfissionais);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissionais.jsp");
      dispatcher.forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/index.jsp");
      dispatcher.forward(request, response);
    }

}

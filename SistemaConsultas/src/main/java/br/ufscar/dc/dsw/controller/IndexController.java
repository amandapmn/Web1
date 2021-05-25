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

@WebServlet(name="Index", urlPatterns = "/publico/*")
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProfissionalDAO profissionalDao;

    @Override
    public void init() {
      profissionalDao = new ProfissionalDAO();
    }

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

        try {

            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    listaProfissionais(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaProfissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Profissional> listaProfissionais = profissionalDao.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaProfissionais.jsp");
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      request.getSession().setAttribute("usuarioLogado", null);
      request.getSession().invalidate();
      RequestDispatcher rd = request.getRequestDispatcher("/logout.jsp");
      rd.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Erro erros = new Erro();

        if (request.getParameter("bOK") != null) {
    			String email = request.getParameter("email");
    			String senha = request.getParameter("senha");
    			if (email == null || email.isEmpty()) {
    				erros.add("Login não informado!");
    			}
    			if (senha == null || senha.isEmpty()) {
    				erros.add("Senha não informada!");
    			}
    			if (!erros.isExisteErros()) {
    				UsuarioDAO dao = new UsuarioDAO();
    				Usuario usuario = dao.getbyEmail(email);
    				if (usuario != null) {
    					if (usuario.getSenha().equals(senha)) {
    						request.getSession().setAttribute("usuarioLogado", usuario);
    						if (usuario.getPapel().equals("ADMIN")) {
    							response.sendRedirect("/SistemaConsultas/admin/");
    						} else if (usuario.getPapel().equals("CLIENTE")) {
    							response.sendRedirect("/SistemaConsultas/cliente/");
    						}
                else if (usuario.getPapel().equals("PROFISSIONAL")){
                  response.sendRedirect("/SistemaConsultas/cliente/");
                }
    						return;
    					} else {
    						erros.add("Senha inválida!");
    					}
    				} else {
    					erros.add("Usuário não encontrado!");
    				}
    			}
    		}
    		request.getSession().invalidate();

    		request.setAttribute("mensagens", erros);

        String URL = "/login.jsp";
    		RequestDispatcher rd = request.getRequestDispatcher(URL);
    		rd.forward(request, response);
    }

}

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
                case "/clientes":
                    clientes(request, response);
                    break;
                case "/clientes_criacao":
                    clientes_criacao(request, response);
                    break;
                case "/clientes_edicao":
                    clientes_edicao(request, response);
                    break;
                case "/clientes_remocao":
                    clientes_remocao(request, response);
                    break;
                case "/profissionais":
                    profissionais(request, response);
                    break;
                case "/profissionais_criacao":
                    profissionais_criacao(request, response);
                    break;
                case "/profissionais_edicao":
                    profissionais_edicao(request, response);
                    break;
                case "/profissionais_remocao":
                    profissionais_remocao(request, response);
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

    private void clientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ClienteDAO dao = new ClienteDAO();
      List<Cliente> listaClientes = dao.getAll();
      request.setAttribute("listaClientes", listaClientes);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/cliente/clientes.jsp");
      dispatcher.forward(request, response);
    }

    private void clientes_criacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ClienteDAO clienteDAO = new ClienteDAO();
      UsuarioDAO usuarioDAO = new UsuarioDAO();
      if(request.getParameter("email") != null &&
       request.getParameter("senha") != null && request.getParameter("cpf") != null &&
       request.getParameter("primeiroNome") != null && request.getParameter("sobrenome") != null &&
       request.getParameter("telefone") != null && request.getParameter("sexo") != null &&
       request.getParameter("dataNasc") != null){

        //Informações do usuário
        String email = String.valueOf(request.getParameter("email"));
        String senha = String.valueOf(request.getParameter("senha"));
        String cpf = String.valueOf(request.getParameter("cpf"));
        String primeiroNome = String.valueOf(request.getParameter("primeiroNome"));
        String sobrenome = String.valueOf(request.getParameter("sobrenome"));

        ////Informações do cliente
        String telefone = String.valueOf(request.getParameter("telefone"));
        String sexo = String.valueOf(request.getParameter("sexo"));
        String dataNasc = String.valueOf(request.getParameter("dataNasc"));


        Usuario usuario = new Usuario(null, email, senha, cpf, primeiroNome, sobrenome, "CLIENTE");
        Cliente cliente = new Cliente(null, usuario, telefone, sexo, dataNasc);

        List<String> messages = new ArrayList<>();
        if(usuarioDAO.verificaEmail(usuario) == false){
          messages.add("email_exists");
        }
        if(usuarioDAO.verificaCPF(usuario) == false){
          messages.add("cpf_exists");
        }

        if(messages.size() > 0){
          request.setAttribute("mensagens", messages);
          RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/cliente/formulario_cliente.jsp");
          dispatcher.forward(request, response);
        }
        else{
          clienteDAO.insert(cliente);
          response.sendRedirect("/SistemaConsultas/admin/clientes");
        }

      }else{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/cliente/formulario_cliente.jsp");
        dispatcher.forward(request, response);
      }
      return;
    }

    private void clientes_edicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ClienteDAO dao = new ClienteDAO();
      UsuarioDAO usuarioDAO = new UsuarioDAO();

      if(request.getParameter("email") != null &&
       request.getParameter("senha") != null && request.getParameter("cpf") != null &&
       request.getParameter("primeiroNome") != null && request.getParameter("sobrenome") != null &&
       request.getParameter("telefone") != null && request.getParameter("sexo") != null &&
       request.getParameter("dataNasc") != null){

        //Informações do usuário
        String email = String.valueOf(request.getParameter("email"));
        String senha = String.valueOf(request.getParameter("senha"));
        String cpf = String.valueOf(request.getParameter("cpf"));
        String primeiroNome = String.valueOf(request.getParameter("primeiroNome"));
        String sobrenome = String.valueOf(request.getParameter("sobrenome"));

        ////Informações do cliente
        Long id = Long.parseLong(request.getParameter("id"));
        String telefone = String.valueOf(request.getParameter("telefone"));
        String sexo = String.valueOf(request.getParameter("sexo"));
        String dataNasc = String.valueOf(request.getParameter("dataNasc"));

        Cliente cliente = dao.get(id);

        cliente.getUsuario().setEmail(email);
        cliente.getUsuario().setSenha(senha);
        cliente.getUsuario().setCPF(cpf);
        cliente.getUsuario().setPrimeiroNome(primeiroNome);
        cliente.getUsuario().setSobrenome(sobrenome);

        cliente.setTelefone(telefone);
        cliente.setSexo(sexo);
        cliente.setDataNasc(dataNasc);

        List<String> messages = new ArrayList<>();
        if(usuarioDAO.verificaEmail(cliente.getUsuario()) == false){
          messages.add("email_exists");
        }
        if(usuarioDAO.verificaCPF(cliente.getUsuario()) == false){
          messages.add("cpf_exists");
        }

        if(messages.size() > 0){
          request.setAttribute("mensagens", messages);
          request.setAttribute("cliente", cliente);
          RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/cliente/formulario_cliente.jsp");
          dispatcher.forward(request, response);
        }
        else{
          if(cliente != null){
            dao.update(cliente);
          }
          response.sendRedirect("/SistemaConsultas/admin/clientes");
        }

      }
      else if(request.getParameter("id") != null){
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.get(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/cliente/formulario_cliente.jsp");
        dispatcher.forward(request, response);
      }
      return;
    }

    private void clientes_remocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ClienteDAO dao = new ClienteDAO();
      if(request.getParameter("id") != null){
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.get(id);
        if(cliente != null){
          dao.delete(cliente);
        }
      }
      profissionais(request, response);
      return;
    }

    private void profissionais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ProfissionalDAO dao = new ProfissionalDAO();
      List<Profissional> listaProfissionais = dao.getAll();
      request.setAttribute("listaProfissionais", listaProfissionais);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissional/profissionais.jsp");
      dispatcher.forward(request, response);

    }

    private void profissionais_criacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if(request.getParameter("email") != null &&
         request.getParameter("senha") != null && request.getParameter("cpf") != null &&
         request.getParameter("primeiroNome") != null && request.getParameter("sobrenome") != null &&
         request.getParameter("especialidade") != null && request.getParameter("qualificacoes") != null){

          //Informações do usuário
          String email = String.valueOf(request.getParameter("email"));
          String senha = String.valueOf(request.getParameter("senha"));
          String cpf = String.valueOf(request.getParameter("cpf"));
          String primeiroNome = String.valueOf(request.getParameter("primeiroNome"));
          String sobrenome = String.valueOf(request.getParameter("sobrenome"));

          ////Informações do profissional
          String especialidade = String.valueOf(request.getParameter("especialidade"));
          String qualificacoes = String.valueOf(request.getParameter("qualificacoes"));

          Usuario usuario = new Usuario(null, email, senha, cpf, primeiroNome, sobrenome, "PROFISSIONAL");
          Profissional profissional = new Profissional(null, usuario, especialidade, qualificacoes);

          List<String> messages = new ArrayList<>();
          if(usuarioDAO.verificaEmail(usuario) == false){
            messages.add("email_exists");
          }
          if(usuarioDAO.verificaCPF(usuario) == false){
            messages.add("cpf_exists");
          }

          if(messages.size() > 0){
            request.setAttribute("mensagens", messages);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissional/formulario_profissional.jsp");
            dispatcher.forward(request, response);
          }
          else{
            profissionalDAO.insert(profissional);
            response.sendRedirect("/SistemaConsultas/admin/profissionais");
          }

        }else{
          RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissional/formulario_profissional.jsp");
          dispatcher.forward(request, response);
        }
        return;
    }

    private void profissionais_edicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ProfissionalDAO dao = new ProfissionalDAO();
      UsuarioDAO usuarioDAO = new UsuarioDAO();
      if(request.getParameter("id") != null && request.getParameter("email") != null &&
       request.getParameter("senha") != null && request.getParameter("cpf") != null &&
       request.getParameter("primeiroNome") != null && request.getParameter("sobrenome") != null &&
       request.getParameter("especialidade") != null && request.getParameter("qualificacoes") != null){

        //Informações do usuário
        String email = String.valueOf(request.getParameter("email"));
        String senha = String.valueOf(request.getParameter("senha"));
        String cpf = String.valueOf(request.getParameter("cpf"));
        String primeiroNome = String.valueOf(request.getParameter("primeiroNome"));
        String sobrenome = String.valueOf(request.getParameter("sobrenome"));

        ////Informações do profissional
        Long id = Long.parseLong(request.getParameter("id"));
        String especialidade = String.valueOf(request.getParameter("especialidade"));
        String qualificacoes = String.valueOf(request.getParameter("qualificacoes"));

        Profissional profissional = dao.get(id);

        profissional.getUsuario().setEmail(email);
        profissional.getUsuario().setSenha(senha);
        profissional.getUsuario().setCPF(cpf);
        profissional.getUsuario().setPrimeiroNome(primeiroNome);
        profissional.getUsuario().setSobrenome(sobrenome);

        profissional.setEspecialidade(especialidade);
        profissional.setQualificacoes(qualificacoes);

        List<String> messages = new ArrayList<>();
        if(usuarioDAO.verificaEmail(profissional.getUsuario()) == false){
          messages.add("email_exists");
        }
        if(usuarioDAO.verificaCPF(profissional.getUsuario()) == false){
          messages.add("cpf_exists");
        }

        if(messages.size() > 0){
          request.setAttribute("mensagens", messages);
          request.setAttribute("profissional", profissional);
          RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissional/formulario_profissional.jsp");
          dispatcher.forward(request, response);
        }
        else{
          if(profissional != null){
            dao.update(profissional);
          }
          response.sendRedirect("/SistemaConsultas/admin/profissionais");
        }

      }
      else if(request.getParameter("id") != null){
        Long id = Long.parseLong(request.getParameter("id"));
        Profissional profissional = dao.get(id);
        request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/profissional/formulario_profissional.jsp");
        dispatcher.forward(request, response);
      }
      return;
    }

    private void profissionais_remocao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProfissionalDAO dao = new ProfissionalDAO();
        if(request.getParameter("id") != null){
          Long id = Long.parseLong(request.getParameter("id"));
          Profissional profissional = dao.get(id);
          if(profissional != null){
            dao.delete(profissional);
          }
        }
        profissionais(request, response);
        return;
    }


    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/index.jsp");
      dispatcher.forward(request, response);
    }

}

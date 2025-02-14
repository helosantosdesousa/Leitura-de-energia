package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cliente;
import dao.ClienteDAO;
import dao.LeituraDAO;

/**
 * Servlet implementation class ControleClienteServlet
 */
@WebServlet("/controleCliente")
public class ControleClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteDAO cdao = new ClienteDAO();
		String nome = request.getParameter("cliente");
		
		Cliente cliente = new Cliente();
		
		cliente.setNome(nome);
		cliente.setId(cdao.procuraIDCliente(nome));
		
		//System.out.println("id do cliente: " + cliente.getId());
		
		
		//cdao.procuraIDCliente(nome);
		//request.setAttribute("consumo", cliente.get)
		
		cdao.pegaConsumoCliente(cliente.getId());
		
		LeituraDAO ldao = new LeituraDAO();
		int consumo = cdao.pegaConsumoCliente(cliente.getId());
		
		request.setAttribute("nome", cliente.getNome());
		request.setAttribute("consumo", consumo);
		
		RequestDispatcher rd = request.getRequestDispatcher("pagConsumoCliente.jsp");
		rd.forward(request, response);
	}

}

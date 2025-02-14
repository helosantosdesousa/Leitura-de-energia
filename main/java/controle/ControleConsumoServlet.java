package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LeituraDAO;

/**
 * Servlet implementation class ControleConsumoServlet
 */
@WebServlet("/controleConsumo")
public class ControleConsumoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleConsumoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LeituraDAO ldao = new LeituraDAO();
		
		int consumoTotal = ldao.calculaConsumoTotal();
		
		
		request.setAttribute("consumoTotal", consumoTotal);
		
		RequestDispatcher rd = request.getRequestDispatcher("pagConsumoTotal.jsp");
		rd.forward(request, response);
	}

}

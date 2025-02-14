package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dao.LeituraDAO;


@WebServlet("/controleMaiorConsumidor")
public class ControleMaiorConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ControleMaiorConsumidor() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteDAO cdao = new ClienteDAO();
		LeituraDAO ldao = new LeituraDAO();
		
		//ldao.pegaMaiorConsumo();
		ldao.pegaMaiorConsumidor();
		List<Integer> idMaioresConsumidores = new ArrayList<>();
		idMaioresConsumidores = ldao.pegaMaiorConsumidor();
		
		List<String> nomeMaioresConsumidores = new ArrayList<>();
		
		
		for(int c: idMaioresConsumidores) {
			nomeMaioresConsumidores.add(cdao.procuraNomeCliente(c));
		}
		
		for(String c: nomeMaioresConsumidores) {
			System.out.println(c);
		}
		
		request.setAttribute("consumidores", nomeMaioresConsumidores);
		
		RequestDispatcher rd = request.getRequestDispatcher("pagMaiorConsumidor.jsp");
		rd.forward(request, response);
		
	}

}

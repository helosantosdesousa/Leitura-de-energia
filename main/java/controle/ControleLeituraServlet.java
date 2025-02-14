package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Leitura;
import dao.ClienteDAO;
import dao.LeituraDAO;


@WebServlet("/controleLeitura")
public class ControleLeituraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ControleLeituraServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeCliente = request.getParameter("cliente");
		int consumo = Integer.valueOf(request.getParameter("consumo"));
		
		Leitura leitura = new Leitura();
		LeituraDAO ldao = new LeituraDAO();
		
		
		ClienteDAO cdao = new ClienteDAO();
		
		leitura.setIdCliente(cdao.procuraIDCliente(nomeCliente));
		leitura.setIdLeitura(ldao.geraID());
		leitura.setConsumo(consumo);
		
		//System.out.println(leitura.getIdLeitura() + " -- " + leitura.getIdCliente() + " -- " + leitura.getConsumo());
		ldao.inserir(leitura);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.forward(request, response);
		
	}

}

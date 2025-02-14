package dao;

import java.sql.SQLException;

public class ClienteDAO extends DAO {
	
	public int procuraIDCliente(String nome) {
		int id = 0;
		boolean achei = false;
		sql = "SELECT id_cliente from gs_cliente where nome = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, nome);
			rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id_cliente");
			}
			
			if(id!=0) {
				achei = true;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if(ps!= null) ps.close();
				if(rs!=null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		return id;
	}

	public int pegaConsumoCliente(int id) {
		int consumo =0;
		
		try {
			sql = "SELECT consumo_kwh from gs_leitura where id_cliente = ?";
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				consumo = rs.getInt("consumo_kwh");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!= null) ps.close();
				if(rs!=null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}

		return consumo;
	}
	
	public String procuraNomeCliente(int id) {
		String nome = null;
		
		boolean achei = false;
		sql = "SELECT nome from gs_cliente where id_cliente = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				nome = rs.getString("nome");
			}
			
			if(id!=0) {
				achei = true;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if(ps!= null) ps.close();
				if(rs!=null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		return nome;
	}
}

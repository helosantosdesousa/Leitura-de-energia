package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Leitura;

public class LeituraDAO extends DAO {
	
	public void inserir(Leitura leitura) {
		sql = "INSERT INTO gs_leitura values (?,?,?)";
				
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, leitura.getIdLeitura());
			ps.setInt(2, leitura.getIdCliente());
			ps.setInt(3, leitura.getConsumo());
			
			ps.execute();
			connection.commit();
			
		}catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(ps!= null) ps.close();
				if(rs!=null) rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	public int geraID() {
		int id=0;
		try {
			sql = "SELECT MAX(id_leitura) AS maior_id from gs_leitura";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("maior_id") < 500) {
					id = 500;
				} else {
					id = rs.getInt("maior_id")+1;
				}
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
		return id;
	}

	public int calculaConsumoTotal() {
		int consumoTotal =0;
		
		try {
			sql = "select sum(consumo_kwh) AS soma from gs_leitura";
			ps = connection.prepareStatement(sql);			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				consumoTotal = rs.getInt("soma");
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
System.out.println("c total: " + consumoTotal);
		return consumoTotal;
	}
	
	public int pegaMaiorConsumo() {
	int maiorConsumo =0;
		
		try {
			sql = "select MAX(consumo_kwh) AS maior FROM gs_leitura";
			ps = connection.prepareStatement(sql);
						
			rs = ps.executeQuery();
			
			while(rs.next()) {
				maiorConsumo = rs.getInt("maior");
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

		System.out.println(maiorConsumo);
		return maiorConsumo;
	}
	
	public List pegaMaiorConsumidor() {
		List<Integer> maioresConsumidores = new ArrayList<>();
		int maior = pegaMaiorConsumo();
		
		int id =0;
		
		try {
			sql = "SELECT * FROM gs_leitura WHERE consumo_kwh = ?";
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, maior);
						
			rs = ps.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("id_cliente");
				maioresConsumidores.add(id);
			}
			
			/*for(Integer c: maioresConsumidores) {
				System.out.println(c);
			}*/
			
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

		System.out.println(id);
		return maioresConsumidores;
	}
}

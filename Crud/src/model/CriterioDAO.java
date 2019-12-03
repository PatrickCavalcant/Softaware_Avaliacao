package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriterioDAO {
	
	private Connection connection;
	
	public CriterioDAO() {
		connection = new ConexãoBD().getConnection();
	}
	
	
	public void cadastrar(Criterio criterio) {
		String sql = "insert into criterio (nome, descricao, peso) VALUES (?,?,?)";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setString(1, criterio.getNome());
			statement.setString(2, criterio.getDescricao());
			statement.setInt(3, criterio.getPeso());
			
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void atualizar(Criterio criterio) {
		String sql = "update criterio set nome=?, descricao=?, peso=? where id=?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, criterio.getNome());
			statement.setString(2, criterio.getDescricao());
			statement.setInt(3, criterio.getPeso());
			statement.setInt(4, criterio.getId());
			
			
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void deletar(Integer idCriterio) {
		
		String sql = "delete from criterio where id = ?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idCriterio);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
		
		
	
	public List<Criterio> consultar(String nomeCriterio) {
		
		List<Criterio> criterios = new ArrayList();
		
		 String sql = "select * from criterio where nome like '%"+ nomeCriterio + "%'";
		 
		 try {
				PreparedStatement  statement = connection.prepareStatement(sql);
				
				
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					
					Criterio criterio = new Criterio();
					
					//Buscar os Itens do Banco de dados
					criterio.setId(resultSet.getInt("id"));
					criterio.setNome(resultSet.getString("nome"));
					criterio.setDescricao(resultSet.getString("descricao"));
					criterio.setPeso(resultSet.getInt("peso"));
					
					
					
					criterios.add(criterio);
					
				}
				
				resultSet.close();
				statement.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		 
		return criterios;
		
	}
	
	
	public void cadastrarNota(Criterio criterio) {
		String sql = "insert into criterio  (nota) VALUES (?)";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			

			statement.setDouble(1, criterio.getNota());
		
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
	}
	
//	public void atualizarNota(Criterio criterio) {
//		String sql = "update nota set valor=? where id=?";
//		
//		
//		try {
//			PreparedStatement  statement = connection.prepareStatement(sql);
//			
//			
//			statement.setDouble(1, criterio.getValor());
//			statement.setInt(2, criterio.getId());
//			
//			
//			
//			statement.execute();
//			statement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//		
//	}
	
//	public List<Criterio> consultarNota(String nomeNota) {
//		
//		List<Criterio> criterios = new ArrayList();
//		
//		 String sql = "select * from nota where valor like '%"+ nomeNota + "%'";
//		 
//		 try {
//				PreparedStatement  statement = connection.prepareStatement(sql);
//				
//				
//				ResultSet resultSet = statement.executeQuery();
//				
//				while(resultSet.next()) {
//					
//					Criterio criterio = new Criterio();
//					
//					//Buscar os Itens do Banco de dados
//					criterio.setId(resultSet.getInt("id"));
//					criterio.setValor(resultSet.getDouble("valor"));
//					
//					
//					criterios.add(criterio);
//					
//				}
//				
//				resultSet.close();
//				statement.close();
//				
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new RuntimeException(e);
//			}
//		 
//		return criterios;
		
	
	
	
	
	
	
}

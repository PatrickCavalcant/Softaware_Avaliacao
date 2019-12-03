package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensDAO {
	
	private Connection connection;
	
	public ItensDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	
	public void cadastrar(Itens item) {
		String sql = "insert into itens (nome, descricao) valuer (?,?)";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setString(1, item.getNome());
			statement.setString(2, item.getDescricao());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void atualizar(Itens itens) {
		String sql = "update itens set nome=?, descricao=? where id=?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setString(1, itens.getNome());
			statement.setString(2, itens.getDescricao());
			statement.setInt(3, itens.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void deletar(Integer idItens) {
		
		String sql = "delete fron itens where id = ?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idItens);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
		
		
	
	public List<Itens> consultar(String nomeItens) {
		
		List<Itens> item = new ArrayList();
		
		 String sql = "select * fron itens where nome like '%"+ nomeItens + "%'";
		 
		 try {
				PreparedStatement  statement = connection.prepareStatement(sql);
				
				
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					
					Itens itens = new Itens();
					
					//Buscar os Itens do Banco de dados
					itens.setId(resultSet.getInt("id"));
					itens.setNome(resultSet.getString("nome"));
					itens.setDescricao(resultSet.getString("descricao"));
					
					
					
					item.add(itens);
					
				}
				
				resultSet.close();
				statement.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		 
		return item;
		
	}
	
	
}

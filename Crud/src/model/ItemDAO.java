package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	
	private Connection connection;
	
	public ItemDAO() {
		connection = new ConexãoBD().getConnection();
	}
	
	
	public void cadastrar(Item item) {
		String sql = "insert into item (nome, descricao) VALUES (?,?)";
		
		
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
	
	
	public void atualizar(Item item) {
		String sql = "update item set nome=?, descricao=? where id=?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setString(1, item.getNome());
			statement.setString(2, item.getDescricao());
			statement.setInt(3, item.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void deletar(Integer idItem) {
		
		String sql = "delete from item where id = ?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idItem);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
		
		
	
	public List<Item> consultar(String nomeItem) {
		
		List<Item> itens = new ArrayList();
		
		 String sql = "select * from item where nome like '%"+ nomeItem + "%'";
		 
		 try {
				PreparedStatement  statement = connection.prepareStatement(sql);
				
				
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					
					Item item = new Item();
					
					//Buscar os Itens do Banco de dados
					item.setId(resultSet.getInt("id"));
					item.setNome(resultSet.getString("nome"));
					item.setDescricao(resultSet.getString("descricao"));
					
					
					
					itens.add(item);
					
				}
				
				resultSet.close();
				statement.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		 
		return itens;
		
	}
	
	
	
	public List<Item> consultarComboBox(String nomeItem) {
		
		List<Item> itensCB = new ArrayList();
		
		 String sql = "select * from item where nome like '%"+ nomeItem + "%'";
		 
		 try {
				PreparedStatement  statement = connection.prepareStatement(sql);
				
				
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					
					Item itemCB = new Item();
					
					//Buscar os Itens do Banco de dados
					
					itemCB.setNome(resultSet.getString("nome"));
					
					itensCB.add(itemCB);
					
				}
				
				resultSet.close();
				statement.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		 
		return itensCB;
		
	}
	
	
}

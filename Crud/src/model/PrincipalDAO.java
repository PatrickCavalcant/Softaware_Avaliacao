package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrincipalDAO {
	
	private Connection connection;
	
	public PrincipalDAO() {
		connection = new ConexãoBD().getConnection();
	}
	
	
	public void consultarNota(double nota, int peso) {
	
		
//		String sql = "SELECT item.id, criterio.id FROM item, criterio";
//		
//		
//		
//		try {
//			PreparedStatement  statement = connection.prepareStatement(sql);
//			
//			statement.setDouble(1, nota);
//			statement.setInt(2, peso);
//			
//			
//			
//			statement.execute();
//			statement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
	}
	
	public void cadastrar(Criterio nota) {
		String sql = "insert into mediaPonderada (nota) VALUES (?)";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setDouble(1, nota.getNota());
		
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void atualizar(Principal nota) {
		String sql = "update mediaPonderada set nota=? where id=?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			
			statement.setDouble(1, nota.getNota());
			statement.setInt(2, nota.getId());
			
			
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	public void deletar(Integer idNota) {
		
		String sql = "delete from mediaPonderada where id = ?";
		
		
		try {
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idNota);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
		
		
	
	

	
		
	}
	

		

	

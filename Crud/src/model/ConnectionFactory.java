package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Classe ConexaoBD - Faz a conexão com o banco de dados
public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/crud", "postgres" , "123");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

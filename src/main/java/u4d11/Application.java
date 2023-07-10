package u4d11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {
	static Connection conn = null;

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/d11?useSSL=false";
		String username = "postgres";
		String password = "1234";
		try {
			System.out.println("Connecting to PG...");

			conn = DriverManager.getConnection(url, username, password);

			System.out.println("Connected successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ********************** INSERT ************************

		// insertPizza("Marinara", 4);


		// ******************* UPDATE *************************

		String sqlUpdate = "UPDATE pizze SET price=? WHERE id=?";
		try {
			PreparedStatement s = conn.prepareStatement(sqlUpdate);
			s.setInt(1, 140);
			s.setInt(2, 2);
			s.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ******************* DELETE ************************
		String sqlDelete = "DELETE FROM pizze WHERE id=?";
		try {
			PreparedStatement s = conn.prepareStatement(sqlDelete);
			s.setInt(1, 2);
			s.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ********************** FIND *************************
		String sqlSelect = "SELECT * FROM pizze";
		try {
			Statement s = conn.createStatement();
			ResultSet pizze = s.executeQuery(sqlSelect);
			while (pizze.next()) {
				System.out.println("Nome pizza: " + pizze.getString("name") + ", prezzo:" + pizze.getInt("price"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertPizza(String nomePizza, int prezzo) {
		String sqlInsert = "INSERT INTO pizze(name, price) VALUES (?, ?)";
		try {
			PreparedStatement s = conn.prepareStatement(sqlInsert);
			s.setString(1, nomePizza);
			s.setInt(2, prezzo);
			s.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

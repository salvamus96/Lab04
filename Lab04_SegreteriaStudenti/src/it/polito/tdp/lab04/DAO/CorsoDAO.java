package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;

public class CorsoDAO {

	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {

				String codins = res.getString("codins");
				int numeroCrediti = res.getInt("crediti");
				String nome = res.getString("nome");
				int periodoDidattico = res.getInt("pd");

				Corso c = new Corso(codins, nome, numeroCrediti, periodoDidattico);
				corsi.add(c);
			}
			
			conn.close();
// ORDINAMENTO ALFABETICO DEL MENU' A TENDINA E IN CIMA UNA RIGA VUOTA
			Collections.sort(corsi);
			corsi.add(0, new Corso ());

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		return corsi;
	}

	
	public List<Corso> getCorsiByStudente(int matricola) {
		
		final String sql = "SELECT c.codins, crediti, nome, pd " +
						   "FROM corso as c, iscrizione as i " +
						   "WHERE c.codins = i.codins and matricola = ?" ;

		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet res = st.executeQuery();
		
			while (res.next()) {
				Corso c = new Corso (res.getString("codins"), res.getString("nome"),
									res.getInt("crediti"), res.getInt("pd"));
				
				corsi.add(c);
			}
			
			conn.close();
			return corsi;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}

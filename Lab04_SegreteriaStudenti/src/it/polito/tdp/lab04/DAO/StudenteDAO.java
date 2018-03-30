package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudenteByMatricola(int matricola) {
		
		final String sql = "SELECT matricola, cognome, nome, CDS " + 
						   "FROM studente " + 
						   "WHERE matricola = ?" ;

		Studente studente = null;

		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet res = st.executeQuery();

			if (res.next()) 
				studente = new Studente (matricola, res.getString("cognome"),
										res.getString("nome"), res.getString("cds"));
			
			conn.close();
			return studente;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Studente> getStudentiByCodins(String codins) {
		
		final String sql = "SELECT s.matricola, cognome, nome, CDS " +
						   "FROM studente AS s, iscrizione as i " +
						   "WHERE s.matricola = i.matricola and codins = ?" ;

		List <Studente> studenti = new ArrayList <> ();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			
			ResultSet res = st.executeQuery();
		
			while (res.next()) { 
				Studente studente = new Studente (res.getInt("matricola"), res.getString("cognome"),
										res.getString("nome"), res.getString("cds"));
			
			studenti.add(studente);
			}
			
			conn.close();
			return studenti;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public boolean isStudenteIscrittoCorsoByCodins(int matricola, String codins) {

		final String sql = "SELECT c.codins, crediti, nome, pd " +
						   "FROM corso as c, iscrizione as i " +
						   "WHERE c.codins = i.codins and matricola = ? and c.codins = ?";

		Corso c = null;
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);
			ResultSet res = st.executeQuery();
		
			if (res.next()) 
				c = new Corso (res.getString("codins"), res.getString("nome"),
						res.getInt("crediti"), res.getInt("pd"));
			
			
			conn.close();
			if (c != null)
				return true;
			
			return false;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public boolean iscriviStudenteCorso(int matricola, String codins) {

		final String sql = "INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES(?,?)";
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);
			
			int res = st.executeUpdate();
			
			
			conn.close();
			
			if (res == 1) 
				return true;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
			
		}
		return false;
	}
}

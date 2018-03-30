package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model () {
		this.corsoDAO = new CorsoDAO ();
		this.studenteDAO = new StudenteDAO ();
	}
	
	public List<Corso> listAll() {
		return this.corsoDAO.getTuttiICorsi();
	}

	public Studente getStudenteByMatricola(int matricola) {
		return this.studenteDAO.getStudenteByMatricola(matricola);
	}

	public List<Studente> getStudentiByCodins(String codins) {
		return this.studenteDAO.getStudentiByCodins(codins);
	}

	public List<Corso> getCorsiByStudente(int matricola) {
		return this.corsoDAO.getCorsiByStudente(matricola);
	}

	public boolean isStudenteIscrittoCorsoByCodins(int matricola, String codins) {
		return this.studenteDAO.isStudenteIscrittoCorsoByCodins(matricola, codins);
	}

	public boolean iscriviStudenteCorso(int matricola, String codins) {
		return this.studenteDAO.iscriviStudenteCorso(matricola, codins);
	}
	
}

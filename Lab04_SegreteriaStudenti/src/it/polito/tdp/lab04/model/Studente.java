package it.polito.tdp.lab04.model;

public class Studente implements Comparable <Studente> {
	
	private int matricola;
	private String cognome;
	private String nome;
	private String cds;
	
	public Studente(int matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}
	
	public int getMatricola() {
		return matricola;
	}
	
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCds() {
		return cds;
	}
	
	public void setCds(String cds) {
		this.cds = cds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Studente==> matricola: " + matricola + ", nome: " + nome + ", cognome: " + cognome ;
	}

	public String toStringTabella() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-10d %-20s %-20s %-10s\n", matricola, nome, cognome, cds));
		return sb.toString();
	}

	@Override
	public int compareTo(Studente s) {
		return matricola - s.getMatricola();
	}
	
}

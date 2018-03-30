package it.polito.tdp.lab04.model;

public class Corso implements Comparable <Corso>{

	private String codins;
	private String nome;
	private int crediti;
	private int pd;
	
	public Corso(String codins, String nome, int crediti, int pd) {
		super();
		this.codins = codins;
		this.nome = nome;
		this.crediti = crediti;
		this.pd = pd;
	}
	
	public Corso () {
		
	}

	public String getCodins() {
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
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
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	public String toStringTabella() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-8s %-2d %-50s %-4d\n", codins, crediti, nome, pd));
		return sb.toString();
	}

	
// COMPARE TO SI USA SOLO PER GLI ORDINAMENTI, NON PER IL CONFRONTO
	public int compareTo(Corso c) {
		return this.nome.compareTo(c.nome);
	}
	
	
	
	
}

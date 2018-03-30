package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model = new Model ();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboBox"
    private ComboBox<Corso> comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNomeCognome"
    private Button btnCercaNomeCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {

    	this.txtResult.clear();
    	
    	int matricola;
    		
    	try {
    		matricola = Integer.parseInt(this.txtMatricola.getText());	
    		Studente s = model.getStudenteByMatricola(matricola);
        	
        	if (s == null)
        		this.txtResult.setText("ERRORE: Matricola inesistente!");
        	
        	List <Corso> corsi = model.getCorsiByStudente(matricola);
        	Collections.sort(corsi);
        	
        	for (Corso c : corsi)
        		this.txtResult.appendText(c.toStringTabella());
    	}
    	catch (NumberFormatException e) {
    		this.txtResult.setText("Inserisci una matricola valida!");	
    	}		
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {

    	this.txtResult.clear();
    	Corso c = this.comboBox.getValue();
    	
    	if (c == null || c.getNome() == null) {
    		this.txtResult.setText("Seleziona un corso!");
    		return;
    	}	
    	
    	List <Studente> studenti = model.getStudentiByCodins(c.getCodins());
    	Collections.sort(studenti);
    	
    	for (Studente s : studenti)
    		this.txtResult.appendText(s.toStringTabella());    	
    }

    @FXML
    void doCercaNomeCognome(ActionEvent event) {
    	
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	this.txtResult.clear();
    	
    	try {
	    
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
	    	Studente s = model.getStudenteByMatricola (matricola);
	    	
	    	if (s == null) {
	    		this.txtResult.setText("ERRORE: Matricola inesistente!");
	    		return;
	    	}
	
		    Corso c = this.comboBox.getValue();
		    if (c == null || c.getNome() == null || !model.isStudenteIscrittoCorsoByCodins(matricola, c.getCodins())) 
		    	this.txtResult.setText("Studente non iscritto a questo corso");
		    else
		    	this.txtResult.setText("Studente iscritto a questo corso");
	    	
		    this.txtNome.setText(s.getNome());
	    	this.txtCognome.setText(s.getCognome());
    
    	}
    	catch (NumberFormatException e) {
    		this.txtResult.setText("Inserisci una matricola valida!");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    	this.txtNome.clear();
    	this.txtCognome.clear();
    	this.txtResult.clear();
    	
    	try {
	    
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
	    	Studente s = model.getStudenteByMatricola (matricola);
	
	    	if (s == null) {
	    		this.txtResult.setText("ERRORE: Matricola inesistente!");
	    		return;
	    	}
	
	    	this.txtNome.setText(s.getNome());
	    	this.txtCognome.setText(s.getCognome());
	    	
	    	Corso c = this.comboBox.getValue();
		    if (c == null || c.getNome() == null) {
		    	this.txtResult.setText("Seleziona un corso per iscrivere lo studente!");
		    	return;
		    }
		    
		    if (model.isStudenteIscrittoCorsoByCodins(matricola, c.getCodins())) { 
		    	this.txtResult.setText("Studente già iscritto a questo corso!");
		    	return;
		    }
		    
// ISCRIZIONE DELLO STUDENTE E CONTROLLO CHE L'INSERIMENTO VADA A BUON FINE
		    if (!model.iscriviStudenteCorso(matricola, c.getCodins())) {
		    	this.txtResult.setText("ERRORE: Non è possibile iscrivere lo studente al corso!");
		    	return;
		    }
		    else 	
		    	this.txtResult.setText("Studente iscritto al corso!");
		    	return ;
        		
		}
    	catch (NumberFormatException e) {
    		this.txtResult.setText("Inserisci una matricola valida!");	
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	this.txtResult.clear();
    	this.comboBox.getSelectionModel().clearSelection();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNomeCognome != null : "fx:id=\"btnCercaNomeCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

//      Utilizzare questo font per incolonnare correttamente i dati
     	txtResult.setStyle("-fx-font-family: monospace");
    }

	public void setModel(Model model) {
		this.model = model;
		
		List <Corso> corsi = model.listAll();
		this.comboBox.getItems().addAll(corsi);
	}
    
    
}


package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.security.Principal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import model.ConexãoBD;
import model.Criterio;
import model.CriterioDAO;
import model.Item;
import model.ItemDAO;
import model.PrincipalDAO;





public class CrudPrincipalController implements Initializable {
		
	
	    
	
	    @FXML
	    private TextField nomeConsultaCriterio;
	    
	
	    @FXML
	    private TableView<Criterio> criteriosPrincipal;

	    @FXML
	    private TableColumn<Criterio, String> nomeCriterios;
	    
	    @FXML
	    private TableColumn<Criterio, Integer> PesoCriterios;
	    
	    @FXML
	    private TableColumn<Principal, Double> notaCriterios;
	
		@FXML
	    private Button button;
	
	    @FXML
	    private Label labelCriterio;
	
	    @FXML
	    private TabPane abas;
	
	    @FXML
	    private Tab principal;
	    

	    @FXML
	    private TextField txtDado;
	    

	    @FXML
	    private TextField nomeNota;
	    

	    @FXML
	    private ComboBox<Item> cbItens;
	    
	    @FXML
	    private TextField notaAtualizacao;
	    @FXML
	    private Tab atualizarNota;
	    
	    private CriterioDAO dao2;
	    
	    private ItemDAO dao;
	    
	    private Item itemSelecionado;
	    
	    private Criterio notaSelecionado;
	    
	    
	    
    
	    //Controlador
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	dao = new ItemDAO();
	    	dao2 = new CriterioDAO();
	
	    	
	    	
	    	nomeCriterios.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    	PesoCriterios.setCellValueFactory(new PropertyValueFactory<>("peso"));
	    	notaCriterios.setCellValueFactory(new PropertyValueFactory<>("nota"));
	    	
	    	carregarItens();
	    	   	
	    	//exibirDialogoInformacao("Teste Informação");
	    	
	    	
	    }
	    
	    @FXML
	    void gerenciarAbas() { 
	    	//Exibir a aba atualizar acionada pela aba cadastrar
	    	if(principal.isSelected()) {
	    		atualizarNota.setDisable(true);
	    		//limparCadastroAtualizacaoCriterios();
	    		
	    	}
	  
        }
        

	    

	    @FXML
	    void limparDado() {

	    }
	    
	    
	    
	  
	   @FXML
   void salvarNota() {
//	    	Criterio Criterio = new Criterio();
//	    	Criterio.setValor(new Double(nomeNota.getText()));
//	    	
//	    	try {
//	    		dao2.cadastrarNota(Criterio);
//	    		exibirDialogoInformacao("Nota Cadastrado com Sucesso");
//	    		
//	    		
//	    	} catch(Exception e) {
//	    		exibirDialogoErro("Falha Cadastrar Nota");
//	    		e.printStackTrace();
//	    	}
//
//		    	
 }
	    

        @FXML
        void selecionarItem() {
        	
        	//Verificar se possui alguns item selecionado
	    	itemSelecionado = cbItens.getSelectionModel().getSelectedItem();
        	
        	//Verificar se possui alguns item selecionado
        	if(itemSelecionado == null) {
        		
	    		exibirDialogoErro("Não há item selecionado");
	    		
		    	}else {
		    	
		    		//exibir dialogo confirmacao de item seleciona
		    		exibirDialogoConfirmacao("Item selecionado: "+itemSelecionado.getNome());
		    		
		    	
        	}
        
        }
        @FXML
		void AtualizacaoNota() {
        	

		
		}
        @FXML
        void salvarNovaNota() {

        }
		
		@FXML
		void consultarCriteriosPrincipal() {
			try {
	    		
	    		//Retorna um list de item
		    	List<Criterio> resultado = dao2.consultar(nomeConsultaCriterio.getText());
		    	//Verificar se a lista está vazia
		    	if(resultado.isEmpty()) {
		    		exibirDialogoInformacao("Nenhum Criterio Encontado");
		    		
		    	}else {
		    		//colocar Itens na tabela
		    		criteriosPrincipal.setItems(FXCollections.observableList(resultado));
		    		
		    	}
		    	
			} catch (Exception e) {
				exibirDialogoInformacao("Falha no realizar a consulta");
				//Exibir a informação na tela 
	    		e.printStackTrace();
	    		
			}
	    	
			
		
		}


	    
	    public void carregarItens() {
	    	try {
	    		
	    		//Retorna um list de item
	    		List<Item> resultado = dao.consultarComboBox(cbItens.getPromptText());
	    		
		    	//colocar Itens no combobox com o metodo ITEMS
		    	cbItens.setItems(FXCollections.observableList(resultado));
		    	
		    	
			} catch (Exception e) {
				//Exibir a informação na tela 
	    		e.printStackTrace();
	    		
			}
	    	
	    	  

	    	
	    	
}
	    
	    	
	 
		    
		    

	  

		
	    
	    

	   
	
	 
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //Alerta de informa
	    private void exibirDialogoInformacao(String informacao) {
	    	Alert alert = new Alert (AlertType.INFORMATION);
	    	alert.setTitle("Informação");
	    	alert.setHeaderText(null);
	    	alert.setContentText(informacao);
	    	
	    	alert.showAndWait();
	    }
	    
	    //Alerta de erro
	    private void exibirDialogoErro(String erro) {
	    	Alert alert = new Alert (AlertType.ERROR);
	    	alert.setTitle("Erro");
	    	alert.setHeaderText(null);
	    	alert.setContentText(erro);
	    	
	    	alert.showAndWait();
	    }
	    
	    //Alenta de confirmação
	    private boolean exibirDialogoConfirmacao(String confirmacao) {
	    	Alert alert = new Alert (AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmação");
	    	alert.setHeaderText(null);
	    	alert.setContentText(confirmacao);
	    	
	    	Optional<ButtonType> opcao = alert.showAndWait();
	    	
	    	if(opcao.get() == ButtonType.OK)
	    		return true;
	    	
	    	return false;
	    	
	    }
	    
 }

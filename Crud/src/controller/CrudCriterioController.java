package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.ConexãoBD;
import model.Criterio;
import model.CriterioDAO;
import model.Item;

import java.net.URL;
import java.security.Principal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class CrudCriterioController implements Initializable {
	
	    @FXML
	    private TableView<Criterio> criterios;

	    @FXML
	    private TableColumn<Criterio, String> nomeCriterios;

	    @FXML
	    private TableColumn<Criterio, String> DescricaoCriterios;

	    @FXML
	    private TableColumn<Criterio, Integer> idCriterios;
	    
	    @FXML
	    private TableColumn<Criterio, Integer> PesoCriterios;

	    
	    @FXML
	    private TextField nomeNovoCriterio;

	    @FXML
	    private TextField nomeCriterio;

	    @FXML
	    private Tab cadastrar;

	    @FXML
	    private Tab atualizar;

	    @FXML
	    private TextField descricaoCriterio;

	    @FXML
	    private TextField descricaoNovoCriterio;

	    @FXML
	    private Tab consultar;

	    @FXML
	    private TextField nomeConsultaCriterio;
	    
	    @FXML
	    private TextField pesoNovoCriterio;

	    @FXML
	    private TextField pesoCriterio;

	    @FXML
	    private TabPane abas;

	    
	    private CriterioDAO dao2;
	    
	    private Criterio criterioSelecionado;
	
	    //Controlador
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	dao2 = new CriterioDAO();
	    	
	    	idCriterios.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	nomeCriterios.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    	DescricaoCriterios.setCellValueFactory(new PropertyValueFactory<>("descricao"));
	    	PesoCriterios.setCellValueFactory(new PropertyValueFactory<>("peso"));
	    	
	    	   	
	    	//exibirDialogoInformacao("Teste Informação");
	    	
	    	
	    }

	    @FXML
	    void gerenciarAbas(MouseEvent event) {
	    	 //Exibir a aba atualizar acinada pela aba cadastrar
	    	if(cadastrar.isSelected() || consultar.isSelected()) {
	    		atualizar.setDisable(true);
	    		limparCadastroAtualizacaoCriterios();
	    		
	    	}
	    }

	    @FXML
	    void limparCadastroNovoCriterio() {
	    	nomeNovoCriterio.clear();
	    	descricaoNovoCriterio.clear();
	    	pesoNovoCriterio.clear();
	    	
	    }

	    @FXML
	    void salvarNovoCriterio() {
	    	Criterio Criterio = new Criterio();
	    	
	    	Criterio.setNome(nomeNovoCriterio.getText());
	    	Criterio.setDescricao(descricaoNovoCriterio.getText());
	    	Criterio.setPeso(new Integer(pesoNovoCriterio.getText()));
	
	    	
	    	
	    	
	    	try {
	    		dao2.cadastrar(Criterio);
	    		exibirDialogoInformacao("Criterios Cadastrado com Sucesso");
	    		limparCadastroNovoCriterio();
	    		//Enviar com a tabela atualizado
	    		consultarCriterios();
	    		
	    	} catch(Exception e) {
	    		exibirDialogoErro("Falha Cadastrar Criterios");
	    		e.printStackTrace();
	    	}
	    }
	    
	   
	    
	    @FXML
	    void consultarCriterios() {
	    	
	    	
	    	try {
	    		
	    		//Retorna um list de item
		    	List<Criterio> resultado = dao2.consultar(nomeConsultaCriterio.getText());
		    	//Verificar se a lista está vazia
		    	if(resultado.isEmpty()) {
		    		exibirDialogoInformacao("Nenhum Criterio Encontado");
		    		
		    	}else {
		    		//colocar Itens na tabela
		    		criterios.setItems(FXCollections.observableList(resultado));
		    		
		    	}
		    	
			} catch (Exception e) {
				exibirDialogoInformacao("Falha no realizar ua consulta");
				//Exibir a informação na tela 
	    		e.printStackTrace();
	    		
			}
	    	
	    	
	    }

	   

	    @FXML
	    void deletarCriterio() {
	    	
	    	//Verificar se possui alguns item selecionado
	    	if(criterios.getSelectionModel().getSelectedItem() == null) {
	    		exibirDialogoErro("Não há item selecionado");
	    		
	    	}else {
	    		
	    		//Cofirma a exclusão do funcionario
	    		if(exibirDialogoConfirmacao("Corfirma a exclusão do item selecionado?")) {
	    			
	    			try {
	    				//retorna o funcionario
						dao2.deletar(criterios.getSelectionModel().getSelectedItem().getId());
						exibirDialogoInformacao("Item deletado com sucesso");
						//Chamar a função de atulização para atualizar a tablea
						consultarCriterios();
						
					} catch (Exception e) {
						exibirDialogoErro("Falha no deletar o item");
						//Exibir a informação na tela 
			    		e.printStackTrace();
					}
	    			
	    		}
	    		
	    	}
	    }
	    
	    
	    @FXML
	    void exibirAbaAtualizacao() {
	    	
	    	//Verificar se possui alguns item selecionado
	    	criterioSelecionado = criterios.getSelectionModel().getSelectedItem();
	    	
	    	//Verificar se item selecionado não é nulo para entar na aba atualizar
	    	if(criterioSelecionado == null) {
	    		exibirDialogoErro("Não há item selecionado");
	    		
	    	//Entrar na aba atualizar	
	    	}else {
	    		atualizar.setDisable(false);
	    		
	    		
	    		//abrir a aba atualizar quando o item selecionado e puxado para o atualizar
	    		abas.getSelectionModel().select(atualizar);
	    		
	    		//passar os campos da aba atualizar
	    		nomeCriterio.setText(criterioSelecionado.getNome());
	    		descricaoCriterio.setText(criterioSelecionado.getDescricao());
	    		pesoCriterio.setText(criterioSelecionado.getPeso().toString());
	    		
	    	}
	    }
	    
	    
	    @FXML
	    void salvarCriterio() {
	    	criterioSelecionado.setNome(nomeCriterio.getText());
	    	criterioSelecionado.setDescricao(descricaoCriterio.getText());
	    	criterioSelecionado.setPeso(new Integer(pesoCriterio.getText()));
	    	
	   
	    	
	    	try {
	    		dao2.atualizar(criterioSelecionado);
	    		exibirDialogoInformacao("Criterios Atualizado com Sucesso");
	    		//Enviar o usuário de volta para a aba consulta
	    		abas.getSelectionModel().select(consultar);
	    		//Enviar com a tabela atualizado
	    		consultarCriterios();
	    		
	    	} catch(Exception e) {
	    		exibirDialogoErro("Falha no atualizar Criterios");
	    		e.printStackTrace();
	    	}
	    	
	    }
	    
	    
	    private void limparCadastroAtualizacaoCriterios() {
	    	nomeCriterio.clear();
	    	descricaoCriterio.clear();
	    	pesoCriterio.clear();
	    	
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

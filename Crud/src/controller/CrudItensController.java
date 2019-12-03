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
import model.Item;
import model.ItemDAO;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class CrudItensController implements Initializable {
	
	    @FXML
	    private TableView<Item> itens;

	    @FXML
	    private TableColumn<Item, String> nomeItens;

	    @FXML
	    private TableColumn<Item, String> DescricaoItens;

	    @FXML
	    private TableColumn<Item, Integer> idItens;


	    @FXML
	    private TextField nomeNovoItem;

	    @FXML
	    private TextField nomeItem;
	    
	    

	    @FXML
	    private Tab cadastrar;

	    @FXML
	    private Tab atualizar;

	    @FXML
	    private TextField descricaoItem;

	    @FXML
	    private TextField descricaoNovoItem;

	    @FXML
	    private Tab consultar;

	    @FXML
	    private TextField nomeConsultaItem;

	    @FXML
	    private TabPane abas;

	    
	    private ItemDAO dao;
	    
	    private Item itemSelecionado;
	
	    //Controlador
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	dao = new ItemDAO();
	    	
	    	idItens.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	nomeItens.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    	DescricaoItens.setCellValueFactory(new PropertyValueFactory<>("descricao"));
	    	   	
	    	//exibirDialogoInformacao("Teste Informação");
	    	
	    	
	    }

	    @FXML
	    void gerenciarAbas(MouseEvent event) {
	    	 //Exibir a aba atualizar acinada pela aba cadastrar
	    	if(cadastrar.isSelected() || consultar.isSelected()) {
	    		atualizar.setDisable(true);
	    		limparCadastroAtualizacaoItens();
	    		
	    	}
	    }

	    @FXML
	    void limparCadastroNovoItem() {
	    	nomeNovoItem.clear();
	    	descricaoNovoItem.clear();
	    }

	    @FXML
	    void salvarNovoItem() {
	    	Item item = new Item();
	    	
	    	item.setNome(nomeNovoItem.getText());
	    	item.setDescricao(descricaoNovoItem.getText());
	    	
	    	
	    	
	    	try {
	    		dao.cadastrar(item);
	    		exibirDialogoInformacao("Itens Cadastrado com Sucesso");
	    		limparCadastroNovoItem();
	    		//Enviar com a tabela atualizado
	    		consultarItens();
	    		
	    	} catch(Exception e) {
	    		exibirDialogoErro("Falha Cadastrar Itens");
	    		e.printStackTrace();
	    	}
	    }
	    
	   
	    
	    @FXML
	    void consultarItens() {
	    	
	    	
	    	try {
	    		
	    		//Retorna um list de item
		    	List<Item> resultado = dao.consultar(nomeConsultaItem.getText());
		    	//Verificar se a lista está vazia
		    	if(resultado.isEmpty()) {
		    		exibirDialogoInformacao("Nenhum Item Encontado");
		    		
		    	}else {
		    		//colocar Itens na tabela
		    		itens.setItems(FXCollections.observableList(resultado));
		    		
		    	}
		    	
			} catch (Exception e) {
				exibirDialogoInformacao("Falha no realizar ua consulta");
				//Exibir a informação na tela 
	    		e.printStackTrace();
	    		
			}
	    	
	    	
	    }

	   

	    @FXML
	    void deletarItem() {
	    	
	    	//Verificar se possui alguns item selecionado
	    	if(itens.getSelectionModel().getSelectedItem() == null) {
	    		exibirDialogoErro("Não há item selecionado");
	    		
	    	}else {
	    		
	    		//Cofirma a exclusão do funcionario
	    		if(exibirDialogoConfirmacao("Corfirma a exclusão do item selecionado?")) {
	    			
	    			try {
	    				//retorna o funcionario
						dao.deletar(itens.getSelectionModel().getSelectedItem().getId());
						exibirDialogoInformacao("Item deletado com sucesso");
						//Chamar a função de atulização para atualizar a tablea
						consultarItens();
						
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
	    	itemSelecionado = itens.getSelectionModel().getSelectedItem();
	    	
	    	//Verificar se item selecionado não é nulo para entar na aba atualizar
	    	if(itemSelecionado == null) {
	    		exibirDialogoErro("Não há item selecionado");
	    		
	    	//Entrar na aba atualizar	
	    	}else {
	    		atualizar.setDisable(false);
	    		
	    		
	    		//abrir a aba atualizar quando o item selecionado e puxado para o atualizar
	    		abas.getSelectionModel().select(atualizar);
	    		
	    		//passar os campos da aba atualizar
	    		nomeItem.setText(itemSelecionado.getNome());
	    		descricaoItem.setText(itemSelecionado.getDescricao());
	    		
	    	}
	    }
	    
	    
	    @FXML
	    void salvarItem() {
	    	itemSelecionado.setNome(nomeItem.getText());
	    	itemSelecionado.setDescricao(descricaoItem.getText());
	    	
	    	try {
	    		dao.atualizar(itemSelecionado);
	    		exibirDialogoInformacao("Itens Atualizado com Sucesso");
	    		//Enviar o usuário de volta para a aba consulta
	    		abas.getSelectionModel().select(consultar);
	    		//Enviar com a tabela atualizado
	    		consultarItens();
	    		
	    	} catch(Exception e) {
	    		exibirDialogoErro("Falha no atualizar Itens");
	    		e.printStackTrace();
	    	}
	    	
	    }
	    
	    
	    private void limparCadastroAtualizacaoItens() {
	    	nomeItem.clear();
	    	descricaoItem.clear();
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

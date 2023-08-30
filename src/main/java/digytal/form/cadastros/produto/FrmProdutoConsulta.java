package digytal.form.cadastros.produto;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.context.Context;
import digytal.model.acessos.empresa.conta.EmpresaContaResponse;
import digytal.model.cadastros.produto.ProdutoResponse;
import digytal.model.params.BancoResponse;
import digytal.service.cadastro.ProdutoService;
import digytal.utils.Formato;
import digytal.utils.desktop.FormularioConsulta;
import digytal.utils.desktop.ss.SSCampoTexto;
import digytal.utils.desktop.ss.SSMensagem;
import digytal.utils.desktop.ss.SSPosicaoRotulo;
import digytal.utils.model.Sessao;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmProdutoConsulta extends FormularioConsulta {
	@Autowired
	private ProdutoService service;
	private SSCampoTexto cNome = new SSCampoTexto();
	public FrmProdutoConsulta() {
		//setSize(500, 600);
		setTitulo("Produtos e Serviços");
		setDescricao("Listagem de Produtos e Serviços");
		
		cNome.setRotulo("Nome");
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		//cNome.setColunas(20);
		
		JPanel filtros = new JPanel(new GridBagLayout());
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.weightx = 1.0;
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.insets = new Insets(3, 3, 0, 0);
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		filtros.add(cNome, gbc_cNome);
		
		filtros.add(bBuscar, getGbcBuscar(1, 0));
		getConteudo().add(filtros,BorderLayout.NORTH);
		
		getTabela().adicionarColuna(0, "ID", "id");
		getTabela().adicionarColuna(1, "Nome", "nome");
		getTabela().adicionarColuna(2, "UND", "unidadeMedida");
		getTabela().adicionarColuna(3, "Serviço", "servico");
		getTabela().adicionarColuna(4, "R$ Valor", "valor");
		
		getTabela().getModeloColuna().setFormato(4, Formato.MOEDA);
		getTabela().definirLarguraColunas(30, 220, 40, 70, 70);
		
		bIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir(Context.getBean(FrmProdutoCadastro.class));
			}
		});
		
		bAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(Context.getBean(FrmProdutoCadastro.class));
			}
		});
		setScrollSize(600, 400);
	}
	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				SSMensagem.avisa("Informe um nome do produto para a pesquisa");
				return;
			}
			List<ProdutoResponse> registros = service.listar(Context.getOrganizacaoId(), nome);
			exibirRegistros(registros);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}
	}
}

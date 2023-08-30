package digytal.form.cadastros.cadastro;

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
import digytal.form.cadastros.produto.FrmProdutoCadastro;
import digytal.model.cadastros.cadastro.CadastroResponse;
import digytal.model.cadastros.produto.ProdutoResponse;
import digytal.service.cadastro.CadastroService;
import digytal.service.cadastro.ProdutoService;
import digytal.utils.Formato;
import digytal.utils.desktop.FormularioConsulta;
import digytal.utils.desktop.ss.SSCampoTexto;
import digytal.utils.desktop.ss.SSMensagem;
import digytal.utils.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmCadastroConsulta extends FormularioConsulta {
	@Autowired
	private CadastroService service;
	private SSCampoTexto cNome = new SSCampoTexto();
	public FrmCadastroConsulta() {
		//setSize(500, 600);
		setTitulo("Clientes");
		setDescricao("Listagem dos cadastros de cliente");
		
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
		getTabela().adicionarColuna(1, "CPF\\CNPJ", "cpfCnpj");
		getTabela().adicionarColuna(2, "Nome", "nomeFantasia");
		
		getTabela().definirLarguraColunas(30, 110, 220);
		
		bIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir(Context.getBean(FrmCadastroCompleto.class));
			}
		});
		
		bAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(Context.getBean(FrmCadastroCompleto.class));
			}
		});
		setScrollSize(600, 400);
	}
	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				SSMensagem.avisa("Informe um nome para a pesquisa");
				return;
			}
			List<CadastroResponse> registros = service.listar(Context.getOrganizacaoId(), nome);
			exibirRegistros(registros);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}
	}
}

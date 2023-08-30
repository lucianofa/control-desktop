package digytal.form.cadastros.produto;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.AbstractButton;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.context.Context;
import digytal.model.acessos.empresa.conta.EmpresaContaRequest;
import digytal.model.acessos.empresa.conta.EmpresaContaCadastro;
import digytal.model.cadastros.produto.ProdutoRequest;
import digytal.model.cadastros.produto.ProdutoResponse;
import digytal.service.acesso.EmpresaService;
import digytal.service.cadastro.ProdutoService;
import digytal.service.publico.CepService;
import digytal.utils.Formato;
import digytal.utils.business.BusinessException;
import digytal.utils.desktop.FormularioCadastro;
import digytal.utils.desktop.ss.SSCampoMascara;
import digytal.utils.desktop.ss.SSCampoNumero;
import digytal.utils.desktop.ss.SSCampoSelecao;
import digytal.utils.desktop.ss.SSCampoTexto;
import digytal.utils.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmProdutoCadastro extends FormularioCadastro {
	private SSCampoTexto cCodigoBarras = new SSCampoTexto();
	private SSCampoSelecao cServico = new SSCampoSelecao();
	private SSCampoTexto cUnidadeMedida = new SSCampoTexto();
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCampoNumero cValor = new SSCampoNumero();
	private SSCampoSelecao cAtualizaEstoque = new SSCampoSelecao();
	private SSCampoNumero cSaldo = new SSCampoNumero();
	@Autowired
	private ProdutoService service;
	private ProdutoResponse registro;
	public FrmProdutoCadastro() {
		
			setTitulo("Produto e Serviço");
			setDescricao("Inclusão e Alteração de Produto");
			GridBagConstraints gbc_cCodigoBarras = new GridBagConstraints();
			gbc_cCodigoBarras.gridwidth = 2;
			gbc_cCodigoBarras.insets = new Insets(5, 5, 0, 0);
			gbc_cCodigoBarras.anchor = GridBagConstraints.NORTHWEST;
			gbc_cCodigoBarras.gridx = 0;
			gbc_cCodigoBarras.gridy = 0;
			conteudo.add(cCodigoBarras, gbc_cCodigoBarras);

			GridBagConstraints gbc_pnlUsarComo = new GridBagConstraints();
			gbc_pnlUsarComo.insets = new Insets(20, 0, 0, 0);
			gbc_pnlUsarComo.gridwidth = 3;
			gbc_pnlUsarComo.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlUsarComo.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlUsarComo.gridx = 2;
			gbc_pnlUsarComo.gridy = 0;

			GridBagConstraints gbc_cValor = new GridBagConstraints();
			gbc_cValor.anchor = GridBagConstraints.NORTHWEST;
			gbc_cValor.weightx = 1.0;
			gbc_cValor.insets = new Insets(5, 5, 0, 5);
			gbc_cValor.fill = GridBagConstraints.HORIZONTAL;
			gbc_cValor.gridx = 2;
			gbc_cValor.gridy = 2;
			conteudo.add(cValor, gbc_cValor);

			GridBagConstraints gbc_cNome = new GridBagConstraints();
			gbc_cNome.gridwidth = 3;
			gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
			gbc_cNome.weightx = 1.0;
			gbc_cNome.insets = new Insets(5, 5, 0, 5);
			gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
			gbc_cNome.gridx = 0;
			gbc_cNome.gridy = 1;
			conteudo.add(cNome, gbc_cNome);
			GridBagConstraints gbc_cUnidadeMedida = new GridBagConstraints();
			gbc_cUnidadeMedida.gridwidth = 2;
			gbc_cUnidadeMedida.anchor = GridBagConstraints.NORTHWEST;
			gbc_cUnidadeMedida.insets = new Insets(5, 5, 0, 0);
			gbc_cUnidadeMedida.fill = GridBagConstraints.HORIZONTAL;
			gbc_cUnidadeMedida.gridx = 0;
			gbc_cUnidadeMedida.gridy = 2;
			conteudo.add(cUnidadeMedida, gbc_cUnidadeMedida);

			GridBagConstraints gbc_cServico = new GridBagConstraints();
			gbc_cServico.weightx = 0.3;
			gbc_cServico.fill = GridBagConstraints.HORIZONTAL;
			gbc_cServico.anchor = GridBagConstraints.NORTHWEST;
			gbc_cServico.insets = new Insets(5, 5, 0, 5);
			gbc_cServico.gridx = 0;
			gbc_cServico.gridy = 3;
			conteudo.add(cServico, gbc_cServico);

			GridBagConstraints gbc_cAtualizaEstoque = new GridBagConstraints();
			gbc_cAtualizaEstoque.anchor = GridBagConstraints.NORTHWEST;
			gbc_cAtualizaEstoque.fill = GridBagConstraints.HORIZONTAL;
			gbc_cAtualizaEstoque.insets = new Insets(5, 5, 5, 0);
			gbc_cAtualizaEstoque.gridx = 1;
			gbc_cAtualizaEstoque.gridy = 3;
			conteudo.add(cAtualizaEstoque, gbc_cAtualizaEstoque);

			GridBagConstraints gbc_cSaldo = new GridBagConstraints();
			gbc_cSaldo.anchor = GridBagConstraints.NORTHWEST;
			gbc_cSaldo.fill = GridBagConstraints.HORIZONTAL;
			gbc_cSaldo.insets = new Insets(5, 5, 5, 5);
			gbc_cSaldo.gridx = 2;
			gbc_cSaldo.gridy = 3;
			conteudo.add(cSaldo, gbc_cSaldo);
		
		definirValorPadrao();
		definirEventos();
	}

	private void definirValorPadrao() {
		// cLocal.setItens(LocalEndereco.values(),"nome");
		cCodigoBarras.setColunas(10);
		cUnidadeMedida.setRotulo("UND");
		cUnidadeMedida.setColunas(10);
		cValor.setColunas(10);

		cServico.setRotulo("Serviço");

		cNome.setRotulo("Nome");
		cValor.setRotulo("R$ Valor");
		cCodigoBarras.setRotulo("Código Barras");

		cSaldo.setRotulo("Saldo");
		cAtualizaEstoque.setRotulo("Estoque");

		cValor.setFormato(Formato.DECIMAL2);
		cValor.setMascara(Formato.DECIMAL2);
		cSaldo.setFormato(Formato.DECIMAL2);
		cSaldo.setEditavel(false);
	}

	private void definirEventos() {
		ActionListener cCreditoActionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        checarServico(selected);
	      }
	    };
	    cServico.addActionListener(cCreditoActionListener);
	}
	private void checarServico(boolean selected) {
		if(selected)
			cAtualizaEstoque.setSelected(false);
		
		
		cAtualizaEstoque.setEditavel(!selected);
	}
	@Override
	public void exibirRegistro(Object entidade) {
		if(entidade!=null) {
			 registro = (ProdutoResponse) entidade;
			cCodigoBarras.setText(registro.getCodigoBarras());
			cNome.setText(registro.getNome());
			cUnidadeMedida.setText(registro.getUnidadeMedida());
			cServico.setSelected(registro.isServico());
			cAtualizaEstoque.setSelected(registro.isAtualizaSaldo());
			cSaldo.setValue(registro.getSaldo());
			cValor.setValue(registro.getValor());
			checarServico(registro.isServico());
		}

	}

	@Override
	public void confirmar() {
		try {
			if (SSMensagem.pergunta("Confirma concluir o cadastro do produto?")) {
				ProdutoRequest request = criarRequest();
				if(registro!=null) {
					service.alterar(registro.getId(), request);
				}else {
					service.incluir(request);
				}
				SSMensagem.informa("Cadastro de produto realizado com sucesso");
				fechar();
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			//LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}

	}
	private ProdutoRequest criarRequest() {
		ProdutoRequest request = new ProdutoRequest();
		request.setAtualizaSaldo(cAtualizaEstoque.isSelected());
		request.setServico(cServico.isSelected());
		request.setCodigoBarras(cCodigoBarras.getText());
		request.setNome(cNome.getText());
		request.setUnidadeMedida(cUnidadeMedida.getText());
		request.setOrganizacao(Context.getOrganizacaoId());
		request.setValor(cValor.getDouble());
		return request;
	}
}
package digytal.form.comercializacao.pagamento;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.model.comum.MeioPagamento;
import digytal.model.comum.TipoCartao;
import digytal.model.contratos.contratos.pagamentos.meio.MeioPagamentoRequest;
import digytal.utils.Formato;
import digytal.utils.desktop.ss.SSBotao;
import digytal.utils.desktop.ss.SSCaixaCombinacao;
import digytal.utils.desktop.ss.SSCampoNumero;
import digytal.utils.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoCartao extends FrmPagamento {
	private SSCampoNumero cValorPago = new SSCampoNumero();
	private SSCampoNumero cNumeroParcelas = new SSCampoNumero();
	private SSCaixaCombinacao cTipoCartao = new SSCaixaCombinacao();
	
	private SSBotao bPagamento = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private Double valorRestante;
	private Double valorParcela=0.0;
	
	public FrmPagamentoCartao() {
		init();
	}

	public void init() {
		//setSize(400, 400);
		super.setTitulo("Cartão");
		super.setDescricao("Pagamento via Débito ou Crédito");
		
		GridBagConstraints gbc_cTipoCartao = new GridBagConstraints();
		gbc_cTipoCartao.gridwidth = 2;
		gbc_cTipoCartao.weightx = 1.0;
		gbc_cTipoCartao.insets = new Insets(5, 5, 5, 5);
		gbc_cTipoCartao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTipoCartao.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTipoCartao.gridx = 0;
		gbc_cTipoCartao.gridy = 0;
		cTipoCartao.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		getConteudo().add(cTipoCartao, gbc_cTipoCartao);
		
		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.insets = new Insets(5, 5, 5, 0);
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 1;
		cValorPago.getComponente().setFont(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setColunas(6);
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteNegrito(true);
		getConteudo().add(cValorPago, gbc_cValorPago);
		
		GridBagConstraints gbc_bPagamento = new GridBagConstraints();
		gbc_bPagamento.gridwidth = 2;
		gbc_bPagamento.weighty = 1.0;
		gbc_bPagamento.weightx = 1.0;
		gbc_bPagamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_bPagamento.insets = new Insets(5, 5, 5, 5);
		gbc_bPagamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPagamento.gridx = 0;
		gbc_bPagamento.gridy = 2;
		
		getConteudo().add(bPagamento, gbc_bPagamento);
		
		GridBagConstraints gbc_bNrParcelas = new GridBagConstraints();
		gbc_bNrParcelas.insets = new Insets(5, 5, 5, 5);
		gbc_bNrParcelas.anchor = GridBagConstraints.NORTHWEST;
		gbc_bNrParcelas.fill = GridBagConstraints.HORIZONTAL;
		gbc_bNrParcelas.gridx = 1;
		gbc_bNrParcelas.gridy = 1;
		cNumeroParcelas.setColunas(2);
		cNumeroParcelas.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cNumeroParcelas.setRotulo("N° Parcelas");
		getConteudo().add(cNumeroParcelas, gbc_bNrParcelas);
		
		
		cTipoCartao.setItens(TipoCartao.values(),"nome");
		
		getRodape().add(bFechar);
		definiRotulos();
		definirEventos();
		
	}
	//private List<MeioPagamentoRequest> pagamentos;
	public void iniciar(Double valorRestante) {
		this.valorRestante = valorRestante;
		cValorPago.setValue(valorRestante);
		cNumeroParcelas.setNumero(1);
		cValorPago.requestFocus();
		
	}
	private void definiRotulos() {
		cValorPago.setRotulo("R$ Á Pagar");
		
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);
		
		bPagamento.setText("Pagamento");
		bPagamento.setIcone("date");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
		cTipoCartao.setRotulo("Cartão");
		cTipoCartao.setValue(TipoCartao.DEBITO);
		exibirCampos(TipoCartao.DEBITO);
	}
	private void exibirCampos(TipoCartao tipoCartao) {
		boolean credito = tipoCartao ==TipoCartao.CREDITO;
		cNumeroParcelas.setVisible(credito);
		if(credito) {
			bPagamento.setText("Pagamento no Crédito");
			bPagamento.setIcone("date");
		}else {
			bPagamento.setText("Pagamento no Débito");
			bPagamento.setIcone("today");
		}
	}
	private void definirEventos() {
		bPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		
		cNumeroParcelas.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

			}

			public void focusLost(FocusEvent e) {
				calcularValorParcela();
			}
		});
		
		cValorPago.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

			}

			public void focusLost(FocusEvent e) {
				calcularValorParcela();
			}
		});
		
		cTipoCartao.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {
		        TipoCartao item = (TipoCartao) cTipoCartao.getValue();
		        exibirCampos(item);
		    }
		});
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
	}
	private void calcularValorParcela() {
		valorParcela = cValorPago.getBigDecimal().divide(cNumeroParcelas.getBigDecimal(), RoundingMode.HALF_EVEN).doubleValue();
		bPagamento.setText(String.format("Em %d x de %.2f", cNumeroParcelas.getInteger(), valorParcela));
		TipoCartao item = (TipoCartao) cTipoCartao.getValue();
		bPagamento.setIcone(item==TipoCartao.DEBITO?"calendar":"date");
	}
	private void confirmar() {
		if(SSMensagem.confirma("Confirma adicionar esta pagamento")) {
			/*
			if(pagamentos.stream().filter(p-> p.getFormaPagamento()== formaPagamento).count()>0) {
				SSMensagem.avisa("Já existe um registro de pagamento via " + formaPagamento.getDescricao());
				return;
			}
			*/
			
			if(valorRestante.compareTo(cValorPago.getDouble()) < 0) {
				SSMensagem.avisa("Valor informado é superior ao valor restante");
				return;
			}
			TipoCartao tipo = (TipoCartao) cTipoCartao.getValue();
			MeioPagamento formaPagamento = tipo == TipoCartao.CREDITO ? MeioPagamento.CREDITO : MeioPagamento.DEBITO;
			MeioPagamentoRequest novoPagamento = new MeioPagamentoRequest();
			novoPagamento.setMeioPagamento(formaPagamento);
			novoPagamento.setDataPrimeiroVencimento(LocalDate.now());
			novoPagamento.setNumeroParcelas(cNumeroParcelas.getInteger());
			novoPagamento.setValor(cValorPago.getDouble());
			novoPagamento.setValorParcela(formaPagamento == MeioPagamento.CREDITO ? valorParcela : cValorPago.getDouble());
			
			fechar(novoPagamento);
		}
	}

}

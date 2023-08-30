package digytal.form.comercializacao.pagamento;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.model.comum.MeioPagamento;
import digytal.model.contratos.contratos.pagamentos.meio.MeioPagamentoRequest;
import digytal.utils.Formato;
import digytal.utils.desktop.ss.SSBotao;
import digytal.utils.desktop.ss.SSCampoDataHora;
import digytal.utils.desktop.ss.SSCampoNumero;
import digytal.utils.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoBoleto extends FrmPagamento {
	private SSCampoNumero cValorPago = new SSCampoNumero();
	private SSCampoDataHora cDataVencimento = new SSCampoDataHora();
	
	private SSBotao bBoleto = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private Double valorRestante;
	
	public FrmPagamentoBoleto() {
		init();
	}

	public void init() {
		
		super.setTitulo("Boleto");
		super.setDescricao("Pagamento via Boleto Bancário");
		
		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.insets = new Insets(5, 5, 5, 0);
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 0;
		cValorPago.getComponente().setFont(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cValorPago.setComponenteNegrito(true);
		getConteudo().add(cValorPago, gbc_cValorPago);
		
		GridBagConstraints gbc_bBoleto = new GridBagConstraints();
		gbc_bBoleto.gridwidth = 2;
		gbc_bBoleto.weighty = 1.0;
		gbc_bBoleto.weightx = 1.0;
		gbc_bBoleto.anchor = GridBagConstraints.NORTHWEST;
		gbc_bBoleto.insets = new Insets(5, 5, 5, 5);
		gbc_bBoleto.fill = GridBagConstraints.HORIZONTAL;
		gbc_bBoleto.gridx = 0;
		gbc_bBoleto.gridy = 1;
		
		getConteudo().add(bBoleto, gbc_bBoleto);
		
		GridBagConstraints gbc_bDataVencimento = new GridBagConstraints();
		gbc_bDataVencimento.insets = new Insets(5, 5, 5, 5);
		gbc_bDataVencimento.anchor = GridBagConstraints.NORTHWEST;
		gbc_bDataVencimento.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDataVencimento.gridx = 1;
		gbc_bDataVencimento.gridy = 0;
		cDataVencimento.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cDataVencimento.setRotulo("Vencimento");
		getConteudo().add(cDataVencimento, gbc_bDataVencimento);
		
		
		getRodape().add(bFechar);
		definiRotulos();
		definirEventos();
		
	}
	//private List<MeioPagamentoRequest> pagamentos;
	public void iniciar(Double valorRestante) {
		this.valorRestante = valorRestante;
		cValorPago.setValue(valorRestante);
		cDataVencimento.setData(LocalDate.now().plusMonths(1));
		cValorPago.requestFocus();
		
	}
	private void definiRotulos() {
		cValorPago.setRotulo("R$ Á Pagar");
		
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);
		
		bBoleto.setText("Pagar no Boleto");
		bBoleto.setIcone("barcode");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
	}
	private void definirEventos() {
		bBoleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.BOLETO);
			}
		});
		
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
	}
	
	private void confirmar(MeioPagamento formaPagamento) {
		if(SSMensagem.confirma("Confirma adicionar esta pagamento")) {
			if(valorRestante.compareTo(cValorPago.getDouble()) < 0) {
				SSMensagem.avisa("Valor informado é superior ao valor restante");
				return;
			}
			MeioPagamentoRequest novoPagamento = new MeioPagamentoRequest();
			novoPagamento.setMeioPagamento(formaPagamento);
			novoPagamento.setDataPrimeiroVencimento(cDataVencimento.getLocalDate());
			novoPagamento.setNumeroParcelas(1);
			novoPagamento.setValor(cValorPago.getDouble());
			novoPagamento.setValorParcela(cValorPago.getDouble());
			
			fechar(novoPagamento);
		}
	}

}

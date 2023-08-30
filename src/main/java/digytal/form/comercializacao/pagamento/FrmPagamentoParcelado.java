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
import digytal.utils.desktop.ss.util.SSFormatador;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoParcelado extends FrmPagamento {
	private SSCampoNumero cValorPago = new SSCampoNumero();
	private SSCampoNumero cNumeroParcelas = new SSCampoNumero();
	private SSCampoDataHora cDataVencimento = new SSCampoDataHora();
	
	private SSBotao bParcelamento = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private Double valorRestante;
	private Double valorParcela=0.0;
	
	public FrmPagamentoParcelado() {
		init();
	}

	public void init() {
		//setSize(400, 400);
		super.setTitulo("À Prazo");
		super.setDescricao("Pagamento parcelado");
		
		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.insets = new Insets(5, 5, 5, 0);
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 0;
		cValorPago.getComponente().setFont(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setColunas(6);
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteNegrito(true);
		getConteudo().add(cValorPago, gbc_cValorPago);
		
		GridBagConstraints gbc_bBoleto = new GridBagConstraints();
		gbc_bBoleto.gridwidth = 3;
		gbc_bBoleto.weighty = 1.0;
		gbc_bBoleto.weightx = 1.0;
		gbc_bBoleto.anchor = GridBagConstraints.NORTHWEST;
		gbc_bBoleto.insets = new Insets(5, 5, 5, 5);
		gbc_bBoleto.fill = GridBagConstraints.HORIZONTAL;
		gbc_bBoleto.gridx = 0;
		gbc_bBoleto.gridy = 1;
		
		getConteudo().add(bParcelamento, gbc_bBoleto);
		
		GridBagConstraints gbc_bDataVencimento = new GridBagConstraints();
		gbc_bDataVencimento.insets = new Insets(5, 5, 5, 5);
		gbc_bDataVencimento.anchor = GridBagConstraints.NORTHWEST;
		gbc_bDataVencimento.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDataVencimento.gridx = 2;
		gbc_bDataVencimento.gridy = 0;
		cDataVencimento.setColunas(6);
		cDataVencimento.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cDataVencimento.setRotulo("1° Parcela");
		getConteudo().add(cDataVencimento, gbc_bDataVencimento);
		
		GridBagConstraints gbc_bNrParcelas = new GridBagConstraints();
		gbc_bNrParcelas.insets = new Insets(5, 5, 5, 0);
		gbc_bNrParcelas.anchor = GridBagConstraints.NORTHWEST;
		gbc_bNrParcelas.fill = GridBagConstraints.HORIZONTAL;
		gbc_bNrParcelas.gridx = 1;
		gbc_bNrParcelas.gridy = 0;
		cNumeroParcelas.setColunas(2);
		cNumeroParcelas.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cNumeroParcelas.setRotulo("N° Parcelas");
		getConteudo().add(cNumeroParcelas, gbc_bNrParcelas);
		
		
		getRodape().add(bFechar);
		definiRotulos();
		definirEventos();
		
	}
	//private List<MeioPagamentoRequest> pagamentos;
	public void iniciar(Double valorRestante) {
		this.valorRestante = valorRestante;
		cValorPago.setValue(valorRestante);
		cNumeroParcelas.setNumero(1);
		cDataVencimento.setData(LocalDate.now().plusMonths(1));
		cValorPago.requestFocus();
		
	}
	private void definiRotulos() {
		cValorPago.setRotulo("R$ Á Pagar");
		
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);
		
		bParcelamento.setText("Pagamento Parcelado");
		bParcelamento.setIcone("date");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
	}
	private void definirEventos() {
		bParcelamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.PARCELADO);
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
		
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
	}
	private void calcularValorParcela() {
		valorParcela = cValorPago.getBigDecimal().divide(cNumeroParcelas.getBigDecimal(), RoundingMode.HALF_EVEN).doubleValue();
		//String vParcela = SSFormatador.formatar(valorParcela);

		bParcelamento.setText(String.format("Pagamento em %d x de %.2f", cNumeroParcelas.getInteger(), valorParcela));
		bParcelamento.setIcone("date");
	}
	private void confirmar(MeioPagamento formaPagamento) {
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
			MeioPagamentoRequest novoPagamento = new MeioPagamentoRequest();
			novoPagamento.setMeioPagamento(formaPagamento);
			novoPagamento.setDataPrimeiroVencimento(cDataVencimento.getLocalDate());
			novoPagamento.setNumeroParcelas(cNumeroParcelas.getInteger());
			novoPagamento.setValor(cValorPago.getDouble());
			novoPagamento.setValorParcela(valorParcela);
			
			fechar(novoPagamento);
		}
	}

}

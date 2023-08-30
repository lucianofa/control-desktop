package digytal.form.comercializacao.pagamento;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.model.comum.MeioPagamento;
import digytal.model.contratos.contratos.pagamentos.meio.MeioPagamentoRequest;
import digytal.utils.Formato;
import digytal.utils.desktop.ss.SSBotao;
import digytal.utils.desktop.ss.SSCampoNumero;
import digytal.utils.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoAVista extends FrmPagamento {
	private SSCampoNumero cValorPago = new SSCampoNumero();
	
	private SSBotao bDinheiro = new SSBotao();
	private SSBotao bPix = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private Double valorRestante;
	
	public FrmPagamentoAVista() {
		init();
	}

	public void init() {
		
		super.setTitulo("Pagamento À Vista");
		super.setDescricao("Dinheiro ou PIX");
		
		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.gridwidth = 2;
		gbc_cValorPago.insets = new Insets(5, 5, 5, 0);
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 0;
		cValorPago.getComponente().setFont(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteNegrito(true);
		getConteudo().add(cValorPago, gbc_cValorPago);
		
		GridBagConstraints gbc_bDinheiro = new GridBagConstraints();
		gbc_bDinheiro.weightx = 0.5;
		gbc_bDinheiro.anchor = GridBagConstraints.NORTHWEST;
		gbc_bDinheiro.insets = new Insets(5, 5, 5, 0);
		gbc_bDinheiro.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDinheiro.gridx = 0;
		gbc_bDinheiro.gridy = 1;
		
		getConteudo().add(bDinheiro, gbc_bDinheiro);
		
		GridBagConstraints gbc_bPix = new GridBagConstraints();
		gbc_bPix.weightx = 0.5;
		gbc_bPix.insets = new Insets(5, 5, 5, 5);
		gbc_bPix.anchor = GridBagConstraints.NORTHWEST;
		gbc_bPix.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPix.gridx = 1;
		gbc_bPix.gridy = 1;
		bPix.setText("Pix");
		bPix.setIcone("pix");
		getConteudo().add(bPix, gbc_bPix);
		
		
		getRodape().add(bFechar);
		definiRotulos();
		definirEventos();
		
	}
	//private List<MeioPagamentoRequest> pagamentos;
	public void iniciar(Double valorRestante) {
		this.valorRestante = valorRestante;
		cValorPago.setValue(valorRestante);
		cValorPago.requestFocus();
		
	}
	private void definiRotulos() {
		cValorPago.setRotulo("R$ Á Pagar");
		
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);
	
		bDinheiro.setText("Dinheiro");
		bDinheiro.setIcone("money2");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
	}
	private void definirEventos() {
		bDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.DINHEIRO);
			}
		});
		bPix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.PIX);
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
			novoPagamento.setDataPrimeiroVencimento(LocalDate.now());
			novoPagamento.setNumeroParcelas(1);
			novoPagamento.setValor(cValorPago.getDouble());
			novoPagamento.setValorParcela(cValorPago.getDouble());
			
			fechar(novoPagamento);
		}
	}

}

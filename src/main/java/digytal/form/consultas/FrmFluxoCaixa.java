package digytal.form.consultas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.context.Context;
import digytal.model.comum.MeioPagamento;
import digytal.model.consultas.FluxoCaixaResponse;
import digytal.model.consultas.TransacaoTipo;
import digytal.service.consultas.ConsultaService;
import digytal.utils.Formato;
import digytal.utils.desktop.FormularioGestao;
import digytal.utils.desktop.ss.SSCampoDataHora;
import digytal.utils.desktop.ss.SSMensagem;
import digytal.utils.desktop.ss.util.SSFormatador;
import digytal.utils.http.Response;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmFluxoCaixa extends FormularioGestao {
	private SSCampoDataHora cDataInicial = new SSCampoDataHora();
	@Autowired
	private ConsultaService service;
	private final JLabel lblNewLabel = new JLabel("R$ Saldo Inicial");
	private final JLabel lblNewLabel_1 = new JLabel("R$ Dinheiro:");
	private final JLabel lblNewLabel_2 = new JLabel("R$ Pix:");
	private final JLabel cPix = new JLabel("R$ 37,71");
	private final JLabel lblNewLabel_4 = new JLabel("R$ Total Saídas:");
	private final JLabel cDespesas = new JLabel("R$ 10,45");
	private final JLabel lblNewLabel_6 = new JLabel("Saldo Caixa");
	private final JLabel cSaldoCaixa = new JLabel("R$ 10.235,87");
	private final JLabel cSaldoInicial = new JLabel("R$ 1.000,00");
	private final JLabel cDinheiro = new JLabel("R$ 2.265,99");
	private final JLabel lblNewLabel_3 = new JLabel("R$ Débito:");
	private final JLabel cDebito = new JLabel("R$ 125,38");
	private final JLabel lblNewLabel_5 = new JLabel("R$ Pagamentos:");
	private final JLabel lblNewLabel_7 = new JLabel("R$ Saldo Diário:");
	private final JLabel cPagamentos = new JLabel("R$ 75,37");
	private final JLabel cSaldoDia = new JLabel("R$ 1.145,87");
	public FrmFluxoCaixa() {
		setTitulo("Fluxo Caixa");
		setDescricao("Gestão do fluxo de caixa");
		
		
		GridBagConstraints gbc_cDataInicial = new GridBagConstraints();
		gbc_cDataInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataInicial.insets = new Insets(5, 5, 5, 0);
		gbc_cDataInicial.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataInicial.gridx = 0;
		gbc_cDataInicial.gridy = 0;
		cDataInicial.setEditavel(false);
		cDataInicial.setRotulo("Data Atual");
		pFiltros.add(cDataInicial, gbc_cDataInicial);
		
	
		
		GridBagConstraints gbc_cFiltrar = new GridBagConstraints();
		gbc_cFiltrar.weightx = 1.0;
		gbc_cFiltrar.insets = new Insets(10, 5, 3, 5);
		gbc_cFiltrar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_cFiltrar.gridx = 1;
		gbc_cFiltrar.gridy = 0;
		pFiltros.add(getbFiltrar(), gbc_cFiltrar);
		
		tabela.adicionarColuna(0, "Código", "id");
		tabela.adicionarColuna(1, "Data", "data.dia");
		tabela.adicionarColuna(2, "Descrição", "descricao");
		tabela.adicionarColuna(3, "Pagto", "meioPagamento");
		tabela.adicionarColuna(4, "R$ Entrada", "valorReceita");
		tabela.adicionarColuna(5, "R$ Saída", "valorDespesa");
		
		tabela.definirLarguraColunas(45, 65, 180,100, 100,100);
		tabela.getModeloColuna().setFormato(4, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(5, Formato.MOEDA);
		
		JPanel pTotais = new JPanel();
		pTotais.setBorder(new TitledBorder(null, "Totais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		conteudo.add(pTotais, BorderLayout.SOUTH);
		GridBagLayout gbl_pTotais = new GridBagLayout();
		pTotais.setLayout(gbl_pTotais);
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pTotais.add(lblNewLabel, gbc_lblNewLabel);
		
		GridBagConstraints gbc_cPagamentos = new GridBagConstraints();
		gbc_cPagamentos.anchor = GridBagConstraints.NORTHEAST;
		gbc_cPagamentos.insets = new Insets(5, 5, 0, 0);
		gbc_cPagamentos.gridx = 4;
		gbc_cPagamentos.gridy = 1;
		cPagamentos.setForeground(new Color(128, 0, 0));
		cPagamentos.setFont(new Font("Tahoma", Font.BOLD, 11));
		pTotais.add(cPagamentos, gbc_cPagamentos);
		
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_5.insets = new Insets(5, 10, 0, 0);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 1;
		pTotais.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_7.insets = new Insets(5, 10, 0, 0);
		gbc_lblNewLabel_7.gridx = 3;
		gbc_lblNewLabel_7.gridy = 2;
		pTotais.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		GridBagConstraints gbc_cSaldoDia = new GridBagConstraints();
		gbc_cSaldoDia.anchor = GridBagConstraints.NORTHEAST;
		gbc_cSaldoDia.insets = new Insets(5, 10, 0, 0);
		gbc_cSaldoDia.gridx = 4;
		gbc_cSaldoDia.gridy = 2;
		pTotais.add(cSaldoDia, gbc_cSaldoDia);
		
		cSaldoDia.setForeground(new Color(0, 0, 139));
		cSaldoDia.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GridBagConstraints gbc_cDebito = new GridBagConstraints();
		gbc_cDebito.anchor = GridBagConstraints.NORTHEAST;
		gbc_cDebito.insets = new Insets(5, 0, 5, 0);
		gbc_cDebito.gridx = 2;
		gbc_cDebito.gridy = 2;
		cDebito.setForeground(new Color(0, 0, 139));
		cDebito.setFont(new Font("Tahoma", Font.BOLD, 11));
		pTotais.add(cDebito, gbc_cDebito);
		
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_3.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		pTotais.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		GridBagConstraints gbc_cDinheiro = new GridBagConstraints();
		gbc_cDinheiro.weightx = 0.1;
		gbc_cDinheiro.anchor = GridBagConstraints.NORTHEAST;
		gbc_cDinheiro.insets = new Insets(5, 0, 0, 0);
		gbc_cDinheiro.gridx = 2;
		gbc_cDinheiro.gridy = 0;
		cDinheiro.setForeground(new Color(0, 0, 139));
		cDinheiro.setFont(new Font("Tahoma", Font.BOLD, 11));
		pTotais.add(cDinheiro, gbc_cDinheiro);
		
		GridBagConstraints gbc_cSaldoInicial = new GridBagConstraints();
		gbc_cSaldoInicial.weightx = 0.5;
		gbc_cSaldoInicial.insets = new Insets(5, 15, 5, 5);
		gbc_cSaldoInicial.gridheight = 3;
		gbc_cSaldoInicial.weighty = 1.0;
		gbc_cSaldoInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cSaldoInicial.gridx = 0;
		gbc_cSaldoInicial.gridy = 1;
		cSaldoInicial.setForeground(new Color(0, 0, 0));
		cSaldoInicial.setFont(new Font("Tahoma", Font.BOLD, 14));
		pTotais.add(cSaldoInicial, gbc_cSaldoInicial);
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		pTotais.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_2.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		pTotais.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		GridBagConstraints gbc_cPix = new GridBagConstraints();
		gbc_cPix.anchor = GridBagConstraints.NORTHEAST;
		gbc_cPix.insets = new Insets(5, 0, 0, 0);
		gbc_cPix.gridx = 2;
		gbc_cPix.gridy = 1;
		cPix.setForeground(new Color(0, 0, 139));
		cPix.setFont(new Font("Tahoma", Font.BOLD, 11));
		pTotais.add(cPix, gbc_cPix);
		
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_4.insets = new Insets(5, 10, 0, 0);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 0;
		pTotais.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		GridBagConstraints gbc_cDespesas = new GridBagConstraints();
		gbc_cDespesas.weightx = 0.1;
		gbc_cDespesas.anchor = GridBagConstraints.NORTHEAST;
		gbc_cDespesas.insets = new Insets(5, 5, 0, 0);
		gbc_cDespesas.gridx = 4;
		gbc_cDespesas.gridy = 0;
		cDespesas.setForeground(new Color(128, 0, 0));
		cDespesas.setFont(new Font("Tahoma", Font.BOLD, 11));
		pTotais.add(cDespesas, gbc_cDespesas);
		
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.weightx = 0.5;
		gbc_lblNewLabel_6.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_6.insets = new Insets(5, 5, 0, 20);
		gbc_lblNewLabel_6.gridx = 5;
		gbc_lblNewLabel_6.gridy = 0;
		pTotais.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		GridBagConstraints gbc_cSaldoCaixa = new GridBagConstraints();
		gbc_cSaldoCaixa.weightx = 0.5;
		gbc_cSaldoCaixa.anchor = GridBagConstraints.NORTHEAST;
		gbc_cSaldoCaixa.insets = new Insets(5, 5, 5, 20);
		gbc_cSaldoCaixa.gridheight = 3;
		gbc_cSaldoCaixa.gridx = 5;
		gbc_cSaldoCaixa.gridy = 1;
		cSaldoCaixa.setForeground(new Color(0, 0, 139));
		cSaldoCaixa.setFont(new Font("Tahoma", Font.BOLD, 14));
		pTotais.add(cSaldoCaixa, gbc_cSaldoCaixa);
		definirValorPadrao();
		
	}
	private void definirValorPadrao() {
		cDataInicial.setColunas(8);
		cDataInicial.setData(LocalDate.now());
		lblNewLabel.setVisible(false);
		cSaldoInicial.setVisible(false);
		
		zerarTotal();
		
	}
	private void zerarTotal() {
		cDinheiro.setText(SSFormatador.formatar(0.0));
		cPix.setText(SSFormatador.formatar(0.0));
		cDebito.setText(SSFormatador.formatar(0.0));
		cPagamentos.setText(SSFormatador.formatar(0.0));
		cDespesas.setText(SSFormatador.formatar(0.0));
		cSaldoCaixa.setText(SSFormatador.formatar(0.0));
		cSaldoDia.setText(SSFormatador.formatar(0.0));
	}
	@Override
	public void filtrar() {
		zerarTotal();
		List<FluxoCaixaResponse> response = service.listarFluxoCaixa(Context.getEmpresaId(), LocalDate.now(),LocalDate.now());
		if(response.size()==0) {
			SSMensagem.informa("Nenhum registro localizado na data de hoje");
			return;
		}else {
			tabela.setValue(response);
			List<FluxoCaixaResponse> receitas = response.stream().filter(e -> e.getTipo()==TransacaoTipo.RECEITA).collect(Collectors.toList());
			List<FluxoCaixaResponse> despesas = response.stream().filter(e -> e.getTipo()==TransacaoTipo.RECEITA).collect(Collectors.toList());
			
			
			Double dia = receitas.stream()
					  .filter(i->i.getMeioPagamento().getId().equals(MeioPagamento.DINHEIRO.getId()))
			  .mapToDouble(FluxoCaixaResponse::getValorReceita)
			  .sum();
			cDinheiro.setText(SSFormatador.formatar(dia));
			Double pix = receitas.stream()
					  .filter(i->i.getMeioPagamento().getId().equals(MeioPagamento.PIX.getId()))
			  .mapToDouble(FluxoCaixaResponse::getValorReceita)
			  .sum();
			cPix.setText(SSFormatador.formatar(pix));
			Double debito = receitas.stream()
					  .filter(i->i.getMeioPagamento().getId().equals(MeioPagamento.DEBITO.getId()))
			  .mapToDouble(FluxoCaixaResponse::getValorReceita)
			  .sum();
			cDebito.setText(SSFormatador.formatar(debito));
			cPagamentos.setText(SSFormatador.formatar(0.0));
			cDespesas.setText(SSFormatador.formatar(0.0));
			cSaldoDia.setText(SSFormatador.formatar(dia+pix+debito));
			Response<Double> saldoCaixa = service.buscarSaldoContaCaixa(Context.getEmpresaId());
			cSaldoCaixa.setText(SSFormatador.formatar(saldoCaixa.getBody()));
		}
	}
}

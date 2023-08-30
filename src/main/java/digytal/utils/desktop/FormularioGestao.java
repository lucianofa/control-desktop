package digytal.utils.desktop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import digytal.utils.desktop.ss.SSBotao;
import digytal.utils.desktop.ss.SSGrade;
import digytal.utils.desktop.ss.SSMensagem;
import digytal.utils.desktop.ss.commons.SSBotaoAlterar;
import digytal.utils.desktop.ss.commons.SSBotaoFechar;
import digytal.utils.desktop.ss.commons.SSBotaoIncluir;
import digytal.utils.enums.TipoOperacao;

public abstract  class FormularioGestao extends Formulario {
	protected SSGrade tabela = new SSGrade();
	protected JPanel pFiltros = new JPanel();
	private SSBotao bFiltrar = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private SSBotao bImprimir = new SSBotao();
	private SSBotao bExportar = new SSBotao();
	protected JScrollPane scroll = new JScrollPane();
	
	public FormularioGestao() {
		super(new BorderLayout());
		pFiltros.setLayout(new GridBagLayout());
		conteudo.add(pFiltros,BorderLayout.NORTH);
		scroll.setViewportView(tabela);
				
		this.tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		super.setAlinhamentoBotoes(FlowLayout.LEFT);
		conteudo.add(scroll,BorderLayout.CENTER);
		
		bFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrar();
			}
		});
		bFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		bFiltrar.setText("Buscar");
		bFiltrar.setIcone("search");
		
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
		
		//bImprimir.setVisible(false);
		bImprimir.setText("Imprimir");
		bImprimir.setIcone("print");
		
		//bExportar.setVisible(false);
		bExportar.setText("Exportar");
		bExportar.setIcone("xls");
		rodape.add(bImprimir);
		rodape.add(bExportar);
		rodape.add(bFechar);
		
	}
	protected void imprimirListener (ActionListener listener) {
		bImprimir.addActionListener(listener);
		bImprimir.setVisible(true);
	}
	protected void exportarListener (ActionListener listener) {
		bExportar.addActionListener(listener);
		bExportar.setVisible(true);
	}
	protected SSBotao getbFiltrar() {
		return bFiltrar;
	}
	protected SSBotao getbFechar() {
		return bFechar;
	}
	public abstract void filtrar();
}


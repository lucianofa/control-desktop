package digytal.component;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import digytal.utils.desktop.BotaoAcao;

public class PainelAcao extends JPanel {
	public PainelAcao() {
		setLayout(new FlowLayout());
	}
	public void adicionar(BotaoAcao botao) {
		add(botao);
	}
}

package digytal.utils.desktop;

import javax.swing.border.EmptyBorder;

import digytal.utils.desktop.ss.SSBotao;

public class BotaoAcao extends SSBotao {
	public BotaoAcao(String texto, String icone) {
		//setContentAreaFilled(false);
		//setBorder(new EmptyBorder(2, 2, 2, 2));
		setText(texto);
		setIcone(icone);
		
	}
}

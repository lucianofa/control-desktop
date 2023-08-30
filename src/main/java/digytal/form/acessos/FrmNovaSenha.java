package digytal.form.acessos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import digytal.context.Context;
import digytal.form.FrmPrincipal;
import digytal.model.acessos.AlteracaoSenhaRequest;
import digytal.model.acessos.CredencialResponse;
import digytal.service.publico.PublicService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import digytal.utils.Imagens;
import digytal.utils.business.BusinessException;
import digytal.utils.desktop.MDI;
import digytal.utils.desktop.ss.SSBotao;
import digytal.utils.desktop.ss.SSCabecalho;
import digytal.utils.desktop.ss.SSCampoSenha;
import digytal.utils.desktop.ss.SSCampoTexto;
import digytal.utils.desktop.ss.SSMensagem;
import digytal.utils.desktop.ss.SSRodape;
import digytal.utils.http.Response;
import digytal.utils.model.Sessao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

@Component
public class FrmNovaSenha extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(FrmNovaSenha.class);
	private JPanel painel = new JPanel();
	private SSBotao btOk = new SSBotao();
	private SSBotao btSair = new SSBotao();
	private SSCampoSenha cToken = new SSCampoSenha();
	private SSCampoSenha cSenha = new SSCampoSenha();
	private SSCampoSenha cNovaSenha = new SSCampoSenha();
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCabecalho cabecalho = new SSCabecalho();
	private SSRodape rodape = new SSRodape();
	private JPanel conteudo = new JPanel();
	private JLabel cLogin = new JLabel();
	

	@Autowired
	private PublicService service;
	
	private CredencialResponse credencial;
	public FrmNovaSenha() {
		painel.setLayout(new BorderLayout(0, 0));
		
		painel.add(cabecalho, BorderLayout.NORTH);
		painel.add(conteudo, BorderLayout.CENTER);
		painel.add(rodape, BorderLayout.SOUTH);
		cLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		cLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmar();
			}

			
		});
		
		cLogin.setText("LOGIN:");
		cLogin.setForeground(Color.BLUE.darker());
		cLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GridBagLayout gbl_conteudo = new GridBagLayout();
		conteudo.setLayout(gbl_conteudo);
		
		GridBagConstraints gbc_cLogin = new GridBagConstraints();
		gbc_cLogin.weighty = 1.0;
		gbc_cLogin.weightx = 1.0;
		gbc_cLogin.anchor = GridBagConstraints.NORTHWEST;
		gbc_cLogin.insets = new Insets(5, 5, 0, 5);
		gbc_cLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_cLogin.gridx = 0;
		gbc_cLogin.gridy = 4;
		conteudo.add(cNovaSenha, gbc_cLogin);
		
		GridBagConstraints gbc_cToken = new GridBagConstraints();
		gbc_cToken.weightx = 1.0;
		gbc_cToken.anchor = GridBagConstraints.NORTHEAST;
		gbc_cToken.insets = new Insets(5, 5, 5, 5);
		gbc_cToken.fill = GridBagConstraints.HORIZONTAL;
		gbc_cToken.gridx = 0;
		gbc_cToken.gridy = 2;
		conteudo.add(cToken, gbc_cToken);

		GridBagConstraints gbc_cSenha = new GridBagConstraints();
		gbc_cSenha.weightx = 1.0;
		gbc_cSenha.anchor = GridBagConstraints.NORTHEAST;
		gbc_cSenha.insets = new Insets(5, 5, 5, 5);
		gbc_cSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_cSenha.gridx = 0;
		gbc_cSenha.gridy = 3;
		conteudo.add(cSenha, gbc_cSenha);

		GridBagConstraints gbc_cEmpresa = new GridBagConstraints();
		gbc_cEmpresa.weightx = 1.0;
		gbc_cEmpresa.anchor = GridBagConstraints.NORTHEAST;
		gbc_cEmpresa.insets = new Insets(5, 5, 0, 5);
		gbc_cEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmpresa.gridx = 0;
		gbc_cEmpresa.gridy = 1;
		conteudo.add(cNome, gbc_cEmpresa);
		
		GridBagConstraints gbc_cLogin1 = new GridBagConstraints();
		gbc_cLogin1.anchor = GridBagConstraints.NORTHEAST;
		gbc_cLogin1.insets = new Insets(5, 5, 0, 5);
		gbc_cLogin1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cLogin1.gridx = 0;
		gbc_cLogin1.gridy = 0;
		conteudo.add(cLogin, gbc_cLogin1);

		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});

		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		
		rodape.add(btOk);
		rodape.add(btSair);
		setContentPane(painel);
		definir();

	}

	private void definir() {
		setTitle("Digytal");
		cNome.setRotulo("Nome");

		btOk.setText("CONFIRMAR");
		btOk.setIcone("ok");

		cNome.setEnabled(false);
		
		cNovaSenha.setTudoMaiusculo(false);
		cSenha.setTudoMaiusculo(false);
		cNovaSenha.setText("");
		cSenha.setText("");		
		cNovaSenha.setColunas(10);
		
		cNovaSenha.setRotulo("Repete Senha");
		cSenha.setRotulo("Senha");
		
		cabecalho.setTitulo("Senha Inicial");
		cabecalho.setDescricao("Defina uma senha inicial");

		
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		conteudo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		btSair.setText("SAIR");
		btSair.setIcone("out");
		cToken.setRotulo("Token");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(308, 356));
		setLocationRelativeTo(null);
		setIconImage(Imagens.png("app/app-img-32").getImage());

		//cNovaSenha.setText("a");
		//cSenha.setText("a");

	}

	private void confirmar() {
		try {
			AlteracaoSenhaRequest request = new AlteracaoSenhaRequest();
			request.setUsuario(credencial.getUsuario());
			request.setSenhaAtual(cToken.getText());
			request.setNovaSenha(cSenha.getText());
			request.setNovaSenhaConfirmacao(cNovaSenha.getText());
			Response<Sessao> response = service.alterarSenha(credencial.getExpiracao(), request);
			Sessao sessao = Context.getSessao();
			BeanUtils.copyProperties(response.getBody(), sessao );
			if (response.getStatus().isSuccess()) {
				SSMensagem.informa("Seja alterada com sucesso, seja Bem-Vindo");
				FrmPrincipal frm = Context.getBean(FrmPrincipal.class);
				frm.iniciar(sessao);
				frm.exibirFormularioInicial();
				this.dispose();
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception e) {
			SSMensagem.erro(e.getMessage());
			LOGGER.error(e.getMessage(),e);
		}
	}

	private void iniciar(Sessao sessao) {

	}

	public void exibir(CredencialResponse response) {
		this.credencial = response;
		this.setVisible(true);
		this.cLogin.setText("LOGIN: " + response.getLogin());
		this.cNome.setText(response.getNome());
	}

	
	
	private void fechar() {
		System.exit(0);
	}
}

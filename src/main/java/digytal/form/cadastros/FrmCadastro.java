package digytal.form.cadastros;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.springframework.beans.factory.annotation.Autowired;

import digytal.component.PainelEndereco;
import digytal.component.PainelPessoa;
import digytal.model.cadastratamento.CadastroCompletoRequest;
import digytal.model.cadastratamento.CadastroRequest;
import digytal.model.cadastratamento.EmpresaRequest;
import digytal.model.comum.Endereco;
import digytal.service.publico.CepService;
import digytal.utils.desktop.FormularioCadastro;
import digytal.utils.desktop.ss.SSBotao;


public abstract class FrmCadastro extends FormularioCadastro {
	@Autowired
	protected CepService cepService;

	protected PainelPessoa painelPessoa = new PainelPessoa();
	protected JTabbedPane tCampos = new JTabbedPane();
	public FrmCadastro(JPanel dadosComplementares) {
		this();
		tCampos.addTab("Dados Complementares", dadosComplementares);
	}
	public FrmCadastro() {
		setTitulo("Cadastro");
		setDescricao("Inclusão e Alteração");
		tCampos.addTab("Dados Pessoais", painelPessoa);
		
		GridBagConstraints gbc_tCampos = new GridBagConstraints();
		gbc_tCampos.weighty = 1.0;
		gbc_tCampos.weightx = 1.0;
		gbc_tCampos.anchor = GridBagConstraints.NORTHEAST;
		gbc_tCampos.fill = GridBagConstraints.BOTH;
		gbc_tCampos.gridx = 0;
		gbc_tCampos.gridy = 0;
		getConteudo().add(tCampos, gbc_tCampos);
		
		
		
		
	}	
	protected CadastroCompletoRequest atualizarDados() {
		CadastroCompletoRequest cadastro = new CadastroCompletoRequest();
		cadastro.setAniversario(painelPessoa.getcAniversario().getLocalDate());
		cadastro.setAtividadeComecialProfissional("");
		cadastro.setCpfCnpj(painelPessoa.getcCpfCnpj().getClipText());
		cadastro.setNomeFantasia(painelPessoa.getcNomeFantasia().getText());
		cadastro.setSobrenomeSocial(painelPessoa.getcNomeRazao().getText());
		cadastro.setEmail(painelPessoa.getPainelContato().getcEmail().getText());
		cadastro.getTelefone().setCelular(painelPessoa.getPainelContato().getcCelular()==null?null:painelPessoa.getPainelContato().getcCelular().getLongValue());
		cadastro.getTelefone().setCelularWhatsApp( (painelPessoa.getPainelContato().getcWhatsApp().isSelected()));
		cadastro.setRgIe(painelPessoa.getcRgIe().getText());
		PainelEndereco end = painelPessoa.getPainelEnderecoContato();
		
		
		Endereco endereco = new Endereco();
		endereco.setCep(end.getcCep().getClipText());
		endereco.setNumero(end.getcNumero().getText());
		endereco.setComplemento(end.getcComplemento().getText());
		endereco.setReferencia(end.getcReferencia().getText());
		endereco.setTelefone(end.getcTelefone() ==null ? null : end.getcTelefone().getLongValue());
		cadastro.setEndereco(endereco);
	
		return cadastro;
	}
	public void adicionarTab(String titulo, JPanel tab) {
		tCampos.addTab(titulo, tab);
	}
	public PainelPessoa getPainelPessoa() {
		return painelPessoa;
	}
	
}

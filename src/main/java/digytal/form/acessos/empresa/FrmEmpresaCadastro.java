package digytal.form.acessos.empresa;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.component.PainelEmpresaConta;
import digytal.component.PainelEndereco;
import digytal.component.PainelPessoa;
import digytal.context.Context;
import digytal.form.acessos.FrmNovaSenha;
import digytal.form.cadastros.FrmCadastro;
import digytal.model.acessos.CredencialResponse;
import digytal.model.acessos.empresa.EmpresaResponse;
import digytal.model.acessos.empresa.conta.EmpresaContaResponse;
import digytal.model.cadastratamento.EmpresaRequest;
import digytal.model.comum.Cidade;
import digytal.model.comum.Endereco;
import digytal.service.acesso.EmpresaService;
import digytal.service.publico.PublicService;
import digytal.utils.business.BusinessException;
import digytal.utils.desktop.ss.SSMensagem;
import digytal.utils.http.Response;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmEmpresaCadastro extends FrmCadastro {
	private static final Logger LOGGER = LogManager.getLogger(FrmEmpresaCadastro.class);
	private PainelEmpresaConta painelEmpresaConta = new PainelEmpresaConta();
	private EmpresaResponse registro;
	
	@Autowired
	private EmpresaService service;
	public FrmEmpresaCadastro() {
		setTitulo("Empresa");
		setDescricao("Cadastro das empresas do sistema");
		adicionarTab("Contas", painelEmpresaConta);
	}
	@Override
	public void exibirRegistro(Object registro) {
		this.registro = (EmpresaResponse) registro;
		if(registro!=null)
			this.exibirRegistro();
	}
	private void exibirRegistro() {
		PainelPessoa pPessoa = getPainelPessoa();
		pPessoa.setCpfCnpj(registro.getCpfCnpj());
		pPessoa.setId(registro.getId());
		
		pPessoa.getcNomeFantasia().setText(registro.getNomeFantasia());
		pPessoa.getcNomeRazao().setText(registro.getSobrenomeSocial());
		pPessoa.getcRgIe().setText(registro.getRgIe());
		pPessoa.getcAniversario().setData(registro.getAniversario());
		pPessoa.getPainelContato().getcEmail().setValue(registro.getEmail());
		pPessoa.getPainelContato().getcCelular().setText(Objects.toString(registro.getTelefone().getCelular(),""));
		pPessoa.getPainelContato().getcWhatsApp().setSelected(registro.getTelefone().isCelularWhatsApp());
		
		
		Endereco endereco = registro.getEndereco();
		PainelEndereco pEndereco = getPainelPessoa().getPainelEnderecoContato();
		pEndereco.getcCep().setText(endereco.getCep());
		pEndereco.getcLogradouro().setText(endereco.getLogradouro());
		pEndereco.getcNumero().setText(endereco.getNumero());
		pEndereco.getcComplemento().setText(endereco.getComplemento());
		pEndereco.getcReferencia().setText(endereco.getReferencia());
		pEndereco.getcBairro().setText(endereco.getBairro());
		pEndereco.getcTelefone().setText(Objects.toString(endereco.getTelefone(),""));
		
		Cidade cidade = endereco.getCidade();
		if(cidade!=null) {
			pEndereco.getcCidade().setText(cidade.getNome());
			pEndereco.getcUf().setText(cidade.getUf());
			pEndereco.getcPais().setText("BRASIL");
		}
		
		painelEmpresaConta.exibirContas(registro.getId());
	}

	@Override
	public void confirmar() {
		try {
			if (SSMensagem.pergunta("Confirma realizar esta operação?")) {
			
				EmpresaRequest cadastro = new EmpresaRequest();
				BeanUtils.copyProperties(atualizarDados(),cadastro);
				cadastro.setOrganizacao(Context.getOrganizacaoId());
				Response<Integer> response= null;
				if(registro!=null)
					response = service.alterar(registro.getId(), cadastro);
				else
					response = service.incluir(cadastro);
				
				SSMensagem.informa(response.getStatus().getMessage());
				
				this.fechar();
				
				
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}
	}

}

package digytal.form.acessos.empresa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.context.Context;
import digytal.service.acesso.UsuarioService;
import digytal.utils.desktop.FormularioLocaliza;
import digytal.utils.desktop.ss.SSMensagem;
import digytal.utils.model.Empresa;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLocalizaEmpresa extends FormularioLocaliza {
	@Autowired
	private UsuarioService service;
	
	public FrmLocalizaEmpresa() {
		init();
	}

	public void init() {
		super.setTitulo("Empresas");
		super.setDescricao("Selecione uma empresa");
		
		
		getTabela().adicionarColuna(0, "CPF\\CNPJ", "cpfCnpj");
		getTabela().adicionarColuna(1, "Nome Fantasia", "nomeFantasia");
		
		getTabela().definirLarguraColunas(100, 210);
		
		super.setAlinhamentoBotoes(FlowLayout.RIGHT);
		//super.setFiltros(pFiltros, 1, 0);
	
		bConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		setScrollSize(350, 130);
		
	}
	
	private void ok() {
		Empresa entidade = (Empresa) getTabela().getLinhaSelecionada();
		fechar(entidade);
	}
	
	public void filtrar() {
		try {
			List<Empresa> lista = service.listarEmpresas(Context.getUsuarioId());
			if (lista.size() == 0) {
				SSMensagem.informa("Não há empresas vinculada à este usuário");
			}
			getTabela().setValue(lista);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}

	}

}

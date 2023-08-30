package digytal.form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JToolBar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import digytal.context.Context;
import digytal.form.acessos.FrmTabs;
import digytal.form.acessos.empresa.FrmEmpresaCadastro;
import digytal.form.cadastros.cadastro.FrmCadastroConsulta;
import digytal.form.cadastros.produto.FrmProdutoConsulta;
import digytal.form.comercializacao.FrmVendaCadastro;
import digytal.form.comercializacao.FrmVendaGestao;
import digytal.form.consultas.FrmFluxoCaixa;
import digytal.form.consultas.FrmParcelamentoReceita;
import digytal.model.acessos.empresa.EmpresaResponse;
import digytal.service.acesso.EmpresaService;
import digytal.utils.Imagens;
import digytal.utils.business.BusinessException;
import digytal.utils.desktop.MDI;
import digytal.utils.desktop.ss.SSMensagem;

@Component
public class FrmPrincipal extends MDI {
	private JToolBar toolBar = new JToolBar();
	 private JButton cmdEmissores = new JButton();
    private JButton cmdLotes = new JButton();
    private JButton cmdNfes = new JButton();
    private JButton cmdSair = new JButton();
	private static final Logger LOGGER = LogManager.getLogger(FrmPrincipal.class);
	@Autowired
	private EmpresaService empresaService;
    public FrmPrincipal(){
        JMenu mCadastros = new JMenu("Cadastros");
        JMenu mLancamentos = new JMenu("Lançamentos");
        JMenu mConsultas = new JMenu("Consultas");
        JMenu mRelatorios = new JMenu("Relatórios");
        JMenu mAjuda = new JMenu("Ajuda");
        
        getBarraMenu().add(mCadastros);
        getBarraMenu().add(mLancamentos);
        getBarraMenu().add(mConsultas);
        getBarraMenu().add(mRelatorios);
        getBarraMenu().add(mAjuda);
        
        JMenuItem miProdutos = new JMenuItem("Produtos");
        mCadastros.add(miProdutos);
        
        JMenuItem miCadastros = new JMenuItem("Clientes");
        mCadastros.add(miCadastros);
        
        JMenuItem miEmpresa = new JMenuItem("Empresa");
        mCadastros.add(miEmpresa);
        
        JMenuItem miVenda = new JMenuItem("Venda");
        mLancamentos.add(miVenda);
        
        JMenuItem miVendaGestao = new JMenuItem("Vendas");
        mConsultas.add(miVendaGestao);
        
        JMenuItem miCaixaLancto = new JMenuItem("Caixa \\ Lancto.");
        mConsultas.add(miCaixaLancto);
        
        JMenu miPagamento = new JMenu("Pagamentos");
        mConsultas.add(miPagamento);
        
        JMenuItem miRecebe = new JMenuItem("Receber");
        miPagamento.add(miRecebe);
        
        JMenuItem miPaga = new JMenuItem("Pagar");
        miPagamento.add(miPaga);
        
        miProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirProdutos();
			}
		});
        
        miEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirEmpresas();
			}
		});
        
        miCadastros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirCadastros();
			}
		});
        miVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirVenda();
			}
		});
        miVendaGestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirVendas();
			}
		});
        miCaixaLancto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirFluxoCaixa();
			}
		});
        miRecebe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirParcelamentoReceita();
			}
		});
        
        mCadastros.setIcon(Imagens.png("app/menu/0cadastros"));
        mLancamentos.setIcon(Imagens.png("app/menu/1lancamentos"));
        mConsultas.setIcon(Imagens.png("app/menu/2consultas"));
        mRelatorios.setIcon(Imagens.png("app/menu/3relatorios"));
        
        
        miCadastros.setIcon(Imagens.png("app/menu/cadastro"));
        miEmpresa.setIcon(Imagens.png("app/menu/empresa"));
        miVenda.setIcon(Imagens.png("app/menu/venda"));
        miVendaGestao.setIcon(Imagens.png("app/menu/venda_gestao"));
        miProdutos.setIcon(Imagens.png("app/menu/produto"));
        miPagamento.setIcon(Imagens.png("app/menu/pagamento"));
        miCaixaLancto.setIcon(Imagens.png("app/menu/caixa"));
        miRecebe.setIcon(Imagens.png("app/menu/recebe"));
        miPaga.setIcon(Imagens.png("app/menu/paga"));
        
        cmdEmissores.setToolTipText( "Cadastro de Emissores de Notas Fiscais" );
        cmdEmissores.setIcon( Imagens.png("app/bar/cadastro"));
        cmdEmissores.setText("Emissores");
        
        toolBar.add(cmdEmissores);
        toolBar.add(cmdNfes);
        toolBar.add(cmdLotes);
        toolBar.add(cmdSair);
        //this.getContentPane().add(toolBar, BorderLayout.NORTH);
        
    }
    public void exibirFormularioInicial() {
    	//tabs();
    }
    
    private void exibirEmpresas() {
    	try {
    		EmpresaResponse response = empresaService.buscar(Context.getEmpresaId());
    		FrmEmpresaCadastro frm= Context.getBean(FrmEmpresaCadastro.class);
			frm.exibirRegistro(response);
    		exibir(frm);
    	}catch (BusinessException be) {
    		SSMensagem.avisa(be.getErrorCode(), be.getMessage());
    	}catch (Exception e) {
    		SSMensagem.erro(e.getMessage());
			LOGGER.error(e.getMessage(),e);
		}
	}
    
   
    private void exibirProdutos() {
    	FrmProdutoConsulta frm= Context.getBean(FrmProdutoConsulta.class);
		exibir(frm);
    }
    private void exibirCadastros() {
    	FrmCadastroConsulta frm= Context.getBean(FrmCadastroConsulta.class);
		exibir(frm);
    }
    private void exibirVenda() {
    	FrmVendaCadastro frm= Context.getBean(FrmVendaCadastro.class);
    	frm.iniciar();
		exibir(frm);
    }
    private void exibirVendas() {
    	FrmVendaGestao frm= Context.getBean(FrmVendaGestao.class);
    	exibir(frm);
    	//SSMensagem.informa("Em breve");
    }
    private void exibirFluxoCaixa() {
    	FrmFluxoCaixa frm= Context.getBean(FrmFluxoCaixa.class);
    	exibir(frm);
    }
    private void exibirParcelamentoReceita() {
    	FrmParcelamentoReceita frm= Context.getBean(FrmParcelamentoReceita.class);
    	exibir(frm);
    }
   
    private void tabs() {
    	
    	JPanel bean = Context.getBean(FrmTabs.class);
    	
		JInternalFrame internal = new JInternalFrame("Tabs");
		getAreaTrabalho().add(internal);
		getAreaTrabalho().getDesktopManager().activateFrame(internal);
		
		internal.setFrameIcon(Imagens.png("app"));
		internal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		internal.setContentPane(bean);
		//internal.setMaximizable(true);
		internal.setVisible(true);
		
		
		//internal.setSize(bean.getWidth(), frm.getHeight());
		try {
			internal.setMaximum(true);
			internal.setSelected(true);
			//if(frm.getWidth() ==0  && frm.getHeight()==0)
			//internal.pack();
			//centralizar(internal);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		 
		
    }
}

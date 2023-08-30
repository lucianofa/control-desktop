package digytal.model.acessos.empresa.conta;

import digytal.model.comum.MeioPagamento;
import lombok.Data;

@Data
public class EmpresaContaRequest {
	private String numero;
    private String agencia;
    private String legenda;
    private Integer banco;
    private boolean contaCredito;
    private boolean contaPadrao;
    private EmpresaContaFatura fatura;
    private String chavePix;
    private MeioPagamento formaPagamento;
    private Double formaPagamentoTaxa;
    private String descricao;
}

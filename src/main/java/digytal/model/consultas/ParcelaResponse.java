package digytal.model.consultas;

import lombok.Data;

@Data
public class ParcelaResponse {
	private Integer id;
    private String descricao;
    private ParcelaAliquota aliquota = new ParcelaAliquota();
    private ParcelamentoNegociacao negociacao = new ParcelamentoNegociacao();
    private PacelaNegociacaoPendencia pendencia = new PacelaNegociacaoPendencia();
    private ParcelaBoleto boleto = new ParcelaBoleto();
    private ParcelaQuitacao quitacao = new ParcelaQuitacao();
    private Integer parcelamento;
    private String observacao;
}

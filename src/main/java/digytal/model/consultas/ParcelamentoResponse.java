package digytal.model.consultas;

import lombok.Data;

@Data
public class ParcelamentoResponse extends LancamentoAbstractResponse  {
	 private ParcelamentoNegociacao negociacao = new ParcelamentoNegociacao();
    private PacelaNegociacaoPendencia pendencia = new PacelaNegociacaoPendencia();
    private boolean quitada;

}
	
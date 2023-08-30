package digytal.model.consultas;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ParcelamentoNegociacao {

	private Integer numeroParcela = 0;
	private LocalDate dataVencimento = LocalDate.now();
	private Double valorOriginal = 0.0;
	private Double valorMulta = 0.0;
	private Double valorJuros = 0.0;
	private Double valorCorrecao = 0.0;
	private Double valorAmortizado = 0.0;
	private Double valorAtual = 0.0;
	private Double valorDesconto = 0.0;
}

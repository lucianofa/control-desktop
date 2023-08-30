package digytal.model.consultas;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PacelaNegociacaoPendencia {
	private boolean atrasada;
	private Integer diasAtraso = 0;
	private Integer numeroParcelas = 0;
	private boolean negociada;
	private LocalDate dataProximoVencimento;
}

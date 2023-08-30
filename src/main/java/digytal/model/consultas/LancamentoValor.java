package digytal.model.consultas;

import lombok.Data;

@Data
public class LancamentoValor {
	private Double valorInformado;
    private Double valorReal;
    private Double valorOperacional;
    private Double saldoAnterior;
}

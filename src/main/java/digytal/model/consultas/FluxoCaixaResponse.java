package digytal.model.consultas;

import lombok.Data;

@Data
public class FluxoCaixaResponse extends LancamentoResponse {
    private Double valorReceita;
    private Double valorDespesa;
}
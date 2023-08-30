package digytal.model.gerencial;

import java.time.LocalDate;

import digytal.model.comum.MeioPagamento;
import lombok.Data;

@Data
public class ContratoPagamentoAnalitico {
    private Integer id;
    private String descricao;
    private LocalDate data;
    private Double valorPrevisto;
    private Double valorAplicado;
    private MeioPagamento meioPagamento;
    private Double valorPago;
    private Integer numeroParcelas;
    private Double valorParcela;
    private LocalDate parcelaPrimeiroVencimento;
}

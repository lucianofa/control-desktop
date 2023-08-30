package digytal.model.gerencial.venda;

import java.time.LocalDate;

import digytal.model.comum.MeioPagamento;
import lombok.Data;
@Data
public class ContratoVendaPagamento {
    private MeioPagamento meioPagamento;
    private Double valorPago;
    private Integer numeroParcelas;
    private Double valorParcela;
    private LocalDate parcelaPrimeiroVencimento;

}

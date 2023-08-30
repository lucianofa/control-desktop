package digytal.model.acessos.empresa.pagamento;

import digytal.model.comum.MeioPagamento;
import lombok.Data;

@Data
public class EmpresaContaMeioPagamentoRequest {
    private MeioPagamento meioPagamento;
    private Double taxa;
}

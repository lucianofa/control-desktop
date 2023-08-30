package digytal.model.contratos.contratos.comercializacao;

import digytal.model.cadastros.produto.ProdutoItem;
import lombok.Data;

@Data
public class ComercializacaoItemRequest {
    private ProdutoItem produto;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorAplicado;
}

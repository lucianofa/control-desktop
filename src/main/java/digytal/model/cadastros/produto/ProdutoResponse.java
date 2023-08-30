package digytal.model.cadastros.produto;

import lombok.Data;

@Data
public class ProdutoResponse extends ProdutoRequest {
    private Integer id;
    private Double saldo;
}

package digytal.model.cadastros.produto;

import lombok.Data;

@Data
public class ProdutoRequest {
    private String nome;
    private String unidadeMedida;
    private String codigoBarras;
    private String sku;
    private Double valor;
    private boolean servico;
    private boolean atualizaSaldo;
    private Integer organizacao;
}

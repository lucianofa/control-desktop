package digytal.model.cadastros.produto;

import lombok.Data;

import java.util.Objects;

@Data
public class ProdutoItem {
    private Integer id;
    private String codBarras;
    private String sku;
    private String nome;
    private String und;
    private Double valor;
    private Double saldo;

    public ProdutoItem(){

    }
    public static ProdutoItem of(Integer id, String nome, String und, Double valor,String codBarras, String sku,Double saldo ){
        ProdutoItem instancia = new ProdutoItem();
        instancia.id = id;
        instancia.codBarras = codBarras;
        instancia.nome = nome;
        instancia.valor=valor;
        instancia.und = und;
        instancia.sku= Objects.toString(sku, codBarras);
        instancia.saldo=saldo;
        return instancia;
    }
}

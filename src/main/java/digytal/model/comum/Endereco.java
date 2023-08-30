package digytal.model.comum;

import lombok.Data;

@Data
public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String referencia;
    private Long telefone;
    private Integer ibge;
    private Cidade cidade = new Cidade();
}

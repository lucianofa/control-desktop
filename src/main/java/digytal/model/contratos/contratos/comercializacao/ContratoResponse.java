package digytal.model.contratos.contratos.comercializacao;

import lombok.Data;


@Data
public class ContratoResponse {
    private Integer id;
    private String descricao;
    private ContratoTipo tipo;
    private ContratoSituacao situacao;
    private Double valorPrevisto;
    private Double valorAplicado;
    private Double valorDesconto;
    private RegistroPartes partes = new RegistroPartes();
    private RegistroData data;

}

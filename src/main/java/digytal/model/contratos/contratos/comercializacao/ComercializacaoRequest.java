package digytal.model.contratos.contratos.comercializacao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import digytal.model.comum.RegistroParteRequest;
import digytal.model.contratos.contratos.pagamentos.meio.MeioPagamentoRequest;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComercializacaoRequest {
    private LocalDate data;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime hora = LocalTime.now();
    private String descricao;
    private RegistroParteRequest partes;
    private Integer intermediador;
    private List<ComercializacaoItemRequest> itens = new ArrayList<>();
    private List<MeioPagamentoRequest> pagamentos = new ArrayList<>();
}

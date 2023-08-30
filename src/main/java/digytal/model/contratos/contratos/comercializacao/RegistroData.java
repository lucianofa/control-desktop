package digytal.model.contratos.contratos.comercializacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class RegistroData {
    private LocalDateTime dataHora;
    private LocalDate dia;
    private Integer mes;
    private Integer ano;
    private Integer periodo;
    private Integer competencia;
   
}

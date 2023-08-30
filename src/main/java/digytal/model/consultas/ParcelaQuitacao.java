package digytal.model.consultas;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ParcelaQuitacao {
	private boolean efetuada;
    private LocalDate data;
}

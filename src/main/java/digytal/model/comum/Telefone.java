package digytal.model.comum;

import lombok.Data;

@Data
public class Telefone {
    private Long fixo;
    private Long celular;
    private boolean celularWhatsApp;
}

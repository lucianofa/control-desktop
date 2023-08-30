package digytal.model.consultas;

import lombok.Data;

@Data
public class ParcelaBoleto {
  private boolean solicitado;
  private String numeroAutorizacao;
  private ParcelaBoletoStatus status;
  private String urlImpressao;
  private String linhaDigitavel;
}

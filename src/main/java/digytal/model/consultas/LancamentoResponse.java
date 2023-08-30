package digytal.model.consultas;


import lombok.Data;

@Data
public class LancamentoResponse extends LancamentoAbstractResponse  {
	 private LancamentoValor valor = new LancamentoValor();
}

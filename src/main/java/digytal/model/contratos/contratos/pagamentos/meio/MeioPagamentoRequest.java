package digytal.model.contratos.contratos.pagamentos.meio;

import lombok.Data;
import java.time.LocalDate;
import java.util.Objects;

import digytal.model.comum.MeioPagamento;

@Data
public class MeioPagamentoRequest {
    private MeioPagamento meioPagamento;
    private Double valor;
    private Integer numeroParcelas;
    private Double valorParcela;
    private LocalDate dataPrimeiroVencimento;
	private Long hash;
    public MeioPagamentoRequest() {
    	this.hash = System.currentTimeMillis();
    }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeioPagamentoRequest other = (MeioPagamentoRequest) obj;
		return Objects.equals(hash, other.hash);
	}
	@Override
	public int hashCode() {
		return Objects.hash(hash);
	}
    
}

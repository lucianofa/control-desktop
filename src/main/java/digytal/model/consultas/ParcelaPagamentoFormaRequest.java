package digytal.model.consultas;

import java.util.Objects;

import digytal.model.comum.MeioPagamento;
import lombok.Data;

@Data
public class ParcelaPagamentoFormaRequest {
	private Double valor;
	private MeioPagamento meioPagamento;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParcelaPagamentoFormaRequest other = (ParcelaPagamentoFormaRequest) obj;
		return meioPagamento == other.meioPagamento;
	}
	@Override
	public int hashCode() {
		return Objects.hash(meioPagamento);
	}
	
}

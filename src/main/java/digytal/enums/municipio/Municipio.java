package digytal.enums.municipio;

import digytal.enums.Enumeracao;

public interface Municipio extends Enumeracao {
	String getSigla();
	String getEstadoNome();
	String getEstadoSigla();
	Integer getUf();
	Integer getIbge();
}

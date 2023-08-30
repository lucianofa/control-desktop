package digytal.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {
	public static int ESCALA_PADRAO = 2;
	public static BigDecimal multiplicar(BigDecimal valor1, BigDecimal valor2) {
		return multiplicar(valor1, valor2,ESCALA_PADRAO);
	}
	public static BigDecimal multiplicar(BigDecimal valor1, BigDecimal valor2, int escala) {
		BigDecimal result = valor1.multiply(valor2);
		return result.setScale(escala, RoundingMode.HALF_EVEN);
	}
	public static BigDecimal multiplicar(Double valor1, Double valor2, int escala) {
		return multiplicar(new BigDecimal(valor1), new BigDecimal(valor2),escala);
	}
	public static BigDecimal multiplicar(Double valor1, Double valor2) {
		return multiplicar(valor1, valor2, ESCALA_PADRAO);
	}
}

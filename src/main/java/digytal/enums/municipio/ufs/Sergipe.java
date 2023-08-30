package digytal.enums.municipio.ufs;

import digytal.enums.municipio.Municipio;

public enum Sergipe implements Municipio {
	AMPARO_DE_SAO_FRANCISCO (2800100,"AMPARO DE SAO FRANCISCO"),
	AQUIDABA (2800209,"AQUIDABA"),
	ARACAJU (2800308,"ARACAJU"),
	ARAUA (2800407,"ARAUA"),
	AREIA_BRANCA (2800506,"AREIA BRANCA"),
	BARRA_DOS_COQUEIROS (2800605,"BARRA DOS COQUEIROS"),
	BOQUIM (2800670,"BOQUIM"),
	BREJO_GRANDE (2800704,"BREJO GRANDE"),
	CAMPO_DO_BRITO (2801009,"CAMPO DO BRITO"),
	CANHOBA (2801108,"CANHOBA"),
	CANINDE_DE_SAO_FRANCISCO (2801207,"CANINDE DE SAO FRANCISCO"),
	CAPELA (2801306,"CAPELA"),
	CARIRA (2801405,"CARIRA"),
	CARMOPOLIS (2801504,"CARMOPOLIS"),
	CEDRO_DE_SAO_JOAO (2801603,"CEDRO DE SAO JOAO"),
	CRISTINAPOLIS (2801702,"CRISTINAPOLIS"),
	CUMBE (2801900,"CUMBE"),
	DIVINA_PASTORA (2802007,"DIVINA PASTORA"),
	ESTANCIA (2802106,"ESTANCIA"),
	FEIRA_NOVA (2802205,"FEIRA NOVA"),
	FREI_PAULO (2802304,"FREI PAULO"),
	GARARU (2802403,"GARARU"),
	GENERAL_MAYNARD (2802502,"GENERAL MAYNARD"),
	GRACHO_CARDOSO (2802601,"GRACHO CARDOSO"),
	ILHA_DAS_FLORES (2802700,"ILHA DAS FLORES"),
	INDIAROBA (2802809,"INDIAROBA"),
	ITABAIANA (2802908,"ITABAIANA"),
	ITABAIANINHA (2803005,"ITABAIANINHA"),
	ITABI (2803104,"ITABI"),
	ITAPORANGA_DAJUDA (2803203,"ITAPORANGA DAJUDA"),
	JAPARATUBA (2803302,"JAPARATUBA"),
	JAPOATA (2803401,"JAPOATA"),
	LAGARTO (2803500,"LAGARTO"),
	LARANJEIRAS (2803609,"LARANJEIRAS"),
	MACAMBIRA (2803708,"MACAMBIRA"),
	MALHADA_DOS_BOIS (2803807,"MALHADA DOS BOIS"),
	MALHADOR (2803906,"MALHADOR"),
	MARUIM (2804003,"MARUIM"),
	MOITA_BONITA (2804102,"MOITA BONITA"),
	MONTE_ALEGRE_DE_SERGIPE (2804201,"MONTE ALEGRE DE SERGIPE"),
	MURIBECA (2804300,"MURIBECA"),
	NEOPOLIS (2804409,"NEOPOLIS"),
	NOSSA_SENHORA_APARECIDA (2804458,"NOSSA SENHORA APARECIDA"),
	NOSSA_SENHORA_DA_GLORIA (2804508,"NOSSA SENHORA DA GLORIA"),
	NOSSA_SENHORA_DAS_DORES (2804607,"NOSSA SENHORA DAS DORES"),
	NOSSA_SENHORA_DE_LOURDES (2804706,"NOSSA SENHORA DE LOURDES"),
	NOSSA_SENHORA_DO_SOCORRO (2804805,"NOSSA SENHORA DO SOCORRO"),
	PACATUBA (2804904,"PACATUBA"),
	PEDRA_MOLE (2805000,"PEDRA MOLE"),
	PEDRINHAS (2805109,"PEDRINHAS"),
	PINHAO (2805208,"PINHAO"),
	PIRAMBU (2805307,"PIRAMBU"),
	POCO_REDONDO (2805406,"POCO REDONDO"),
	POCO_VERDE (2805505,"POCO VERDE"),
	PORTO_DA_FOLHA (2805604,"PORTO DA FOLHA"),
	PROPRIA (2805703,"PROPRIA"),
	RIACHAO_DO_DANTAS (2805802,"RIACHAO DO DANTAS"),
	RIACHUELO (2805901,"RIACHUELO"),
	RIBEIROPOLIS (2806008,"RIBEIROPOLIS"),
	ROSARIO_DO_CATETE (2806107,"ROSARIO DO CATETE"),
	SALGADO (2806206,"SALGADO"),
	SANTA_LUZIA_DO_ITANHY (2806305,"SANTA LUZIA DO ITANHY"),
	SANTA_ROSA_DE_LIMA (2806503,"SANTA ROSA DE LIMA"),
	SANTANA_DO_SAO_FRANCISCO (2806404,"SANTANA DO SAO FRANCISCO"),
	SANTO_AMARO_DAS_BROTAS (2806602,"SANTO AMARO DAS BROTAS"),
	SAO_CRISTOVAO (2806701,"SAO CRISTOVAO"),
	SAO_DOMINGOS (2806800,"SAO DOMINGOS"),
	SAO_FRANCISCO (2806909,"SAO FRANCISCO"),
	SAO_MIGUEL_DO_ALEIXO (2807006,"SAO MIGUEL DO ALEIXO"),
	SIMAO_DIAS (2807105,"SIMAO DIAS"),
	SIRIRI (2807204,"SIRIRI"),
	TELHA (2807303,"TELHA"),
	TOBIAS_BARRETO (2807402,"TOBIAS BARRETO"),
	TOMAR_DO_GERU (2807501,"TOMAR DO GERU"),
	UMBAUBA (2807600,"UMBAUBA"),

	;
	private Integer codigo;
	private String nome;
	private Sergipe(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public String getEstadoNome() {
		return "SERGIPE";
	}
	public String getEstadoSigla() {
		return "SE";
	}
	@Override
	public String getSigla() {
		return "NÃO INFORMADO";
	}
	public Integer getUf() {
		return 28;
	}
	@Override
	public Integer getIbge() {
		return Integer.valueOf(codigo);
	}
}

package digytal.enums.municipio.ufs;

import digytal.enums.municipio.Municipio;

public enum RioJaneiro implements Municipio {
	ANGRA_DOS_REIS (3300100,"ANGRA DOS REIS"),
	APERIBE (3300159,"APERIBE"),
	ARARUAMA (3300209,"ARARUAMA"),
	AREAL (3300225,"AREAL"),
	ARMACAO_DOS_BUZIOS (3300233,"ARMACAO DOS BUZIOS"),
	ARRAIAL_DO_CABO (3300258,"ARRAIAL DO CABO"),
	BARRA_DO_PIRAI (3300308,"BARRA DO PIRAI"),
	BARRA_MANSA (3300407,"BARRA MANSA"),
	BELFORD_ROXO (3300456,"BELFORD ROXO"),
	BOM_JARDIM (3300506,"BOM JARDIM"),
	BOM_JESUS_DO_ITABAPOANA (3300605,"BOM JESUS DO ITABAPOANA"),
	CABO_FRIO (3300704,"CABO FRIO"),
	CACHOEIRAS_DE_MACACU (3300803,"CACHOEIRAS DE MACACU"),
	CAMBUCI (3300902,"CAMBUCI"),
	CAMPOS_DOS_GOYTACAZES (3301009,"CAMPOS DOS GOYTACAZES"),
	CANTAGALO (3301108,"CANTAGALO"),
	CARAPEBUS (3300936,"CARAPEBUS"),
	CARDOSO_MOREIRA (3301157,"CARDOSO MOREIRA"),
	CARMO (3301207,"CARMO"),
	CASIMIRO_DE_ABREU (3301306,"CASIMIRO DE ABREU"),
	COMENDADOR_LEVY_GASPARIAN (3300951,"COMENDADOR LEVY GASPARIAN"),
	CONCEICAO_DE_MACABU (3301405,"CONCEICAO DE MACABU"),
	CORDEIRO (3301504,"CORDEIRO"),
	DUAS_BARRAS (3301603,"DUAS BARRAS"),
	DUQUE_DE_CAXIAS (3301702,"DUQUE DE CAXIAS"),
	ENGENHEIRO_PAULO_DE_FRONTIN (3301801,"ENGENHEIRO PAULO DE FRONTIN"),
	GUAPIMIRIM (3301850,"GUAPIMIRIM"),
	IGUABA_GRANDE (3301876,"IGUABA GRANDE"),
	ITABORAI (3301900,"ITABORAI"),
	ITAGUAI (3302007,"ITAGUAI"),
	ITALVA (3302056,"ITALVA"),
	ITAOCARA (3302106,"ITAOCARA"),
	ITAPERUNA (3302205,"ITAPERUNA"),
	ITATIAIA (3302254,"ITATIAIA"),
	JAPERI (3302270,"JAPERI"),
	LAJE_DO_MURIAE (3302304,"LAJE DO MURIAE"),
	MACAE (3302403,"MACAE"),
	MACUCO (3302452,"MACUCO"),
	MAGE (3302502,"MAGE"),
	MANGARATIBA (3302601,"MANGARATIBA"),
	MARICA (3302700,"MARICA"),
	MENDES (3302809,"MENDES"),
	MESQUITA (3302858,"MESQUITA"),
	MIGUEL_PEREIRA (3302908,"MIGUEL PEREIRA"),
	MIRACEMA (3303005,"MIRACEMA"),
	NATIVIDADE (3303104,"NATIVIDADE"),
	NILOPOLIS (3303203,"NILOPOLIS"),
	NITEROI (3303302,"NITEROI"),
	NOVA_FRIBURGO (3303401,"NOVA FRIBURGO"),
	NOVA_IGUACU (3303500,"NOVA IGUACU"),
	PARACAMBI (3303609,"PARACAMBI"),
	PARAIBA_DO_SUL (3303708,"PARAIBA DO SUL"),
	PARATY (3303807,"PARATY"),
	PATY_DO_ALFERES (3303856,"PATY DO ALFERES"),
	PETROPOLIS (3303906,"PETROPOLIS"),
	PINHEIRAL (3303955,"PINHEIRAL"),
	PIRAI (3304003,"PIRAI"),
	PORCIUNCULA (3304102,"PORCIUNCULA"),
	PORTO_REAL (3304110,"PORTO REAL"),
	QUATIS (3304128,"QUATIS"),
	QUEIMADOS (3304144,"QUEIMADOS"),
	QUISSAMA (3304151,"QUISSAMA"),
	RESENDE (3304201,"RESENDE"),
	RIO_BONITO (3304300,"RIO BONITO"),
	RIO_CLARO (3304409,"RIO CLARO"),
	RIO_DAS_FLORES (3304508,"RIO DAS FLORES"),
	RIO_DAS_OSTRAS (3304524,"RIO DAS OSTRAS"),
	RIO_DE_JANEIRO (3304557,"RIO DE JANEIRO"),
	SANTA_MARIA_MADALENA (3304607,"SANTA MARIA MADALENA"),
	SANTO_ANTONIO_DE_PADUA (3304706,"SANTO ANTONIO DE PADUA"),
	SAO_FIDELIS (3304805,"SAO FIDELIS"),
	SAO_FRANCISCO_DE_ITABAPOANA (3304755,"SAO FRANCISCO DE ITABAPOANA"),
	SAO_GONCALO (3304904,"SAO GONCALO"),
	SAO_JOAO_DA_BARRA (3305000,"SAO JOAO DA BARRA"),
	SAO_JOAO_DE_MERITI (3305109,"SAO JOAO DE MERITI"),
	SAO_JOSE_DE_UBA (3305133,"SAO JOSE DE UBA"),
	SAO_JOSE_DO_VALE_DO_RIO_PRETO (3305158,"SAO JOSE DO VALE DO RIO PRETO"),
	SAO_PEDRO_DA_ALDEIA (3305208,"SAO PEDRO DA ALDEIA"),
	SAO_SEBASTIAO_DO_ALTO (3305307,"SAO SEBASTIAO DO ALTO"),
	SAPUCAIA (3305406,"SAPUCAIA"),
	SAQUAREMA (3305505,"SAQUAREMA"),
	SEROPEDICA (3305554,"SEROPEDICA"),
	SILVA_JARDIM (3305604,"SILVA JARDIM"),
	SUMIDOURO (3305703,"SUMIDOURO"),
	TANGUA (3305752,"TANGUA"),
	TERESOPOLIS (3305802,"TERESOPOLIS"),
	TRAJANO_DE_MORAES (3305901,"TRAJANO DE MORAES"),
	TRES_RIOS (3306008,"TRES RIOS"),
	VALENCA (3306107,"VALENCA"),
	VARRE_SAI (3306156,"VARRE-SAI"),
	VASSOURAS (3306206,"VASSOURAS"),
	VOLTA_REDONDA (3306305,"VOLTA REDONDA"),

	;
	private Integer codigo;
	private String nome;
	private RioJaneiro(Integer codigo, String nome) {
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
		return "RIO DE JANEIRO";
	}
	public String getEstadoSigla() {
		return "RJ";
	}
	@Override
	public String getSigla() {
		return "NÃO INFORMADO";
	}
	public Integer getUf() {
		return 33;
	}
	@Override
	public Integer getIbge() {
		return Integer.valueOf(codigo);
	}
}

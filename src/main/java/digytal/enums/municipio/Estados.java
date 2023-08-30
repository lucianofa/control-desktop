package digytal.enums.municipio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import digytal.enums.municipio.ufs.Acre;
import digytal.enums.municipio.ufs.Alagoas;
import digytal.enums.municipio.ufs.Amapa;
import digytal.enums.municipio.ufs.Amazonas;
import digytal.enums.municipio.ufs.Bahia;
import digytal.enums.municipio.ufs.Ceara;
import digytal.enums.municipio.ufs.DistritoFederal;
import digytal.enums.municipio.ufs.EspiritoSanto;
import digytal.enums.municipio.ufs.Exterior;
import digytal.enums.municipio.ufs.Goias;
import digytal.enums.municipio.ufs.Maranhao;
import digytal.enums.municipio.ufs.MatoGrosso;
import digytal.enums.municipio.ufs.MatoGrossoSul;
import digytal.enums.municipio.ufs.MinasGerais;
import digytal.enums.municipio.ufs.Para;
import digytal.enums.municipio.ufs.Paraiba;
import digytal.enums.municipio.ufs.Parana;
import digytal.enums.municipio.ufs.Pernambuco;
import digytal.enums.municipio.ufs.Piaui;
import digytal.enums.municipio.ufs.RioGrandeNorte;
import digytal.enums.municipio.ufs.RioGrandeSul;
import digytal.enums.municipio.ufs.RioJaneiro;
import digytal.enums.municipio.ufs.Rondonia;
import digytal.enums.municipio.ufs.Roraima;
import digytal.enums.municipio.ufs.SantaCatarina;
import digytal.enums.municipio.ufs.SaoPaulo;
import digytal.enums.municipio.ufs.Sergipe;
import digytal.enums.municipio.ufs.Tocantins;

public class Estados {
	public static List<Municipio> MUNICICPIOS;
	static {
		MUNICICPIOS = new ArrayList<Municipio>();
		MUNICICPIOS.addAll(Arrays.asList(Acre.values()));
		MUNICICPIOS.addAll(Arrays.asList(Alagoas.values()));
		MUNICICPIOS.addAll(Arrays.asList(Amazonas.values()));
		MUNICICPIOS.addAll(Arrays.asList(Amapa.values()));
		MUNICICPIOS.addAll(Arrays.asList(Bahia.values()));
		MUNICICPIOS.addAll(Arrays.asList(Ceara.values()));
		MUNICICPIOS.addAll(Arrays.asList(DistritoFederal.values()));
		MUNICICPIOS.addAll(Arrays.asList(EspiritoSanto.values()));
		MUNICICPIOS.addAll(Arrays.asList(Goias.values()));
		MUNICICPIOS.addAll(Arrays.asList(Maranhao.values()));
		MUNICICPIOS.addAll(Arrays.asList(MinasGerais.values()));
		MUNICICPIOS.addAll(Arrays.asList(MatoGrossoSul.values()));
		MUNICICPIOS.addAll(Arrays.asList(MatoGrosso.values()));
		MUNICICPIOS.addAll(Arrays.asList(Para.values()));
		MUNICICPIOS.addAll(Arrays.asList(Paraiba.values()));
		MUNICICPIOS.addAll(Arrays.asList(Pernambuco.values()));
		MUNICICPIOS.addAll(Arrays.asList(Piaui.values()));
		MUNICICPIOS.addAll(Arrays.asList(Parana.values()));
		MUNICICPIOS.addAll(Arrays.asList(RioJaneiro.values()));
		MUNICICPIOS.addAll(Arrays.asList(RioGrandeNorte.values()));
		MUNICICPIOS.addAll(Arrays.asList(Rondonia.values()));
		MUNICICPIOS.addAll(Arrays.asList(Roraima.values()));
		MUNICICPIOS.addAll(Arrays.asList(RioGrandeSul.values()));
		MUNICICPIOS.addAll(Arrays.asList(SantaCatarina.values()));
		MUNICICPIOS.addAll(Arrays.asList(Sergipe.values()));
		MUNICICPIOS.addAll(Arrays.asList(SaoPaulo.values()));
		MUNICICPIOS.addAll(Arrays.asList(Tocantins.values()));
		MUNICICPIOS.addAll(Arrays.asList(Exterior.values()));
	}
	public static Cidade cidade(Integer ibge){
		for (Municipio municipio : MUNICICPIOS) {
			if (municipio.getCodigo().equals(ibge)) {
				return cidade(municipio);
			}
		}
		return null;
	}
	public static List<Cidade> cidades(String nome){
		List<Cidade>list = new ArrayList<Cidade>();
		for (Municipio municipio : MUNICICPIOS) {
			if (municipio.getNome().contains(nome.toUpperCase())) {
				list.add(cidade(municipio));
			}
		}
		return list;
	}
	public static Cidade cidade(Municipio municipio) {
		return new Cidade(municipio.getCodigo(), municipio.getNome(), municipio.getSigla(), municipio.getEstadoNome(), municipio.getEstadoSigla(), municipio.getUf(), municipio.getIbge());
	}
}

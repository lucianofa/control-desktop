package digytal.service.consultas;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import digytal.model.consultas.FluxoCaixaResponse;
import digytal.model.consultas.ParcelaResponse;
import digytal.model.consultas.ParcelamentoResponse;
import digytal.model.contratos.contratos.comercializacao.ContratoResponse;
import digytal.model.gerencial.venda.ContratoVenda;
import digytal.service.core.HttpClient;
import digytal.utils.http.Response;

@Service
public class ConsultaService extends HttpClient {
	public ConsultaService() {
        super("consultas");
    }
	
	public List<FluxoCaixaResponse> listarFluxoCaixa(Integer empresa, LocalDate dataInicial, LocalDate dataFinal ){
        return get("fluxo-caixa","empresa",empresa,"data-inicial", dataInicial, "data-final", dataFinal ).list(FluxoCaixaResponse.class);
    }
	public List<ParcelaResponse> listarParcelamentoParcelas(Integer parcelamento){
        return get("parcelamentos",parcelamento,"parcelas" ).list(ParcelaResponse.class);
    }
	public List<ParcelamentoResponse> listarParcelamentoReceita(Integer empresa, LocalDate dataInicial, LocalDate dataFinal,Integer cadastro ){
        if(cadastro==null)
        	return get("parcelamentos","receita","empresa",empresa,"data-inicial", dataInicial, "data-final", dataFinal ).list(ParcelamentoResponse.class);
        else
        	return get("parcelamentos","receita","empresa",empresa,"data-inicial", dataInicial, "data-final", dataFinal,"cadastro", cadastro  ).list(ParcelamentoResponse.class);
	}
	public Response<Double> buscarSaldoContaCaixa(Integer empresa){
        return get("empresa",empresa,"conta-caixa").body();
    }
	public List<ContratoVenda> listarVendaPagamentos(Integer empresa, LocalDate data ){
        return get("contratos","vendas","empresa",empresa,"data", data ).list(ContratoVenda.class);
    }
	
}

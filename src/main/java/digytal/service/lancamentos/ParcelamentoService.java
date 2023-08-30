package digytal.service.lancamentos;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.model.consultas.BoletoResponse;
import digytal.model.consultas.ParcelaPagamentoFormaRequest;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import digytal.utils.http.Response;

@Service
public class ParcelamentoService extends HttpClient{
	public ParcelamentoService() {
        super("parcelamentos");
    }
	public Response realizarPagamento(Integer parcela, Integer empresa,Integer usuario, List<ParcelaPagamentoFormaRequest> request) throws BusinessException {
        return post(request,"parcelas", parcela, "empresa", empresa,"usuario", usuario,"pagamento").body();
    }
	public BoletoResponse gerarBoleto(Integer parcela, Double valorBoleto) throws BusinessException {
        return patch(null,"parcelas", parcela,"valor", valorBoleto).one(BoletoResponse.class);
    }
}

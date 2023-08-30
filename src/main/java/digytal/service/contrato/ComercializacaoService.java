package digytal.service.contrato;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import digytal.model.contratos.contratos.comercializacao.ComercializacaoRequest;
import digytal.model.contratos.contratos.comercializacao.ContratoResponse;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import digytal.utils.http.Response;

@Service
public class ComercializacaoService extends HttpClient {
	public ComercializacaoService() {
        super("comercializacoes");
    }
	
	public Response concluirVendaDireta(ComercializacaoRequest request) throws BusinessException {
        return post(request,"venda-direta").body();
    }
	
	
}

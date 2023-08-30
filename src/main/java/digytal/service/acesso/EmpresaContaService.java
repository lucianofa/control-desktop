package digytal.service.acesso;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.model.acessos.empresa.conta.EmpresaContaResponse;
import digytal.model.acessos.empresa.pagamento.EmpresaContaMeioPagamentoRequest;
import digytal.model.acessos.empresa.pagamento.EmpresaContaMeioPagamentoResponse;
import digytal.model.cadastratamento.EmpresaRequest;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import digytal.utils.http.Response;

@Service
public class EmpresaContaService extends HttpClient  {
	public EmpresaContaService() {
        super("empresa-contas");
    }

	public Response <Integer> incluir(Integer conta, EmpresaContaMeioPagamentoRequest request) throws BusinessException {
        return post(request,conta,"formas-pagamento").body();
    }
   
    public List<EmpresaContaMeioPagamentoResponse> listarFormasPagamento(Integer id ){
        return get(id,"formas-pagamento").list(EmpresaContaMeioPagamentoResponse.class);
    }
    
}

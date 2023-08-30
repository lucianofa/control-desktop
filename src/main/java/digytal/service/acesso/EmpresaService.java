package digytal.service.acesso;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.model.acessos.empresa.EmpresaResponse;
import digytal.model.acessos.empresa.conta.EmpresaContaRequest;
import digytal.model.acessos.empresa.conta.EmpresaContaCadastro;
import digytal.model.acessos.empresa.conta.EmpresaContaResponse;
import digytal.model.cadastratamento.EmpresaRequest;
import digytal.model.params.BancoResponse;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import digytal.utils.http.Response;

@Service
public class EmpresaService extends HttpClient  {
	public EmpresaService() {
        super("empresas");
    }

	public Response alterar(Integer id, EmpresaRequest request) throws BusinessException {
        return put(request,id).body();
    }
	public Response incluir(EmpresaRequest request) throws BusinessException {
        return post(request).body();
    }
    public EmpresaResponse buscar(Integer id ){
        return get(id).one(EmpresaResponse.class);
    }
    public List<EmpresaContaResponse> listarContas(Integer id ){
        return get(id,"contas").list(EmpresaContaResponse.class);
    }
    public List<BancoResponse> listarBancos(String nome ){
        return get("bancos",nome).list(BancoResponse.class);
    }
    
    public Response alterarConta(Integer id, EmpresaContaRequest request) throws BusinessException {
        return put(request,"contas",id).body();
    }
    
    public Response incluirConta(Integer empresa, EmpresaContaCadastro request) throws BusinessException {
        return post(request,empresa,"contas").body();
    }
}

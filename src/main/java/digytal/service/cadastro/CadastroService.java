package digytal.service.cadastro;


import java.util.List;

import org.springframework.stereotype.Service;

import digytal.model.cadastratamento.CadastroRequest;
import digytal.model.cadastros.cadastro.CadastroResponse;
import digytal.model.cadastros.produto.ProdutoResponse;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import digytal.utils.http.Response;

@Service
public class CadastroService extends HttpClient  {
	public CadastroService() {
        super("cadastros");
    }

	public Response alterar(Integer id, CadastroRequest request) throws BusinessException {
        return put(request,id).body();
    }
	public Response incluir(CadastroRequest request) throws BusinessException {
        return post(request).body();
    }
	public List<CadastroResponse> listar(Integer organizacao, String nome ){
        return get("organizacao", organizacao, "nome", nome).list(CadastroResponse.class);
    }
    
}

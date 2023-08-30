package digytal.service.cadastro;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.model.acessos.empresa.conta.EmpresaContaCadastro;
import digytal.model.cadastros.produto.ProdutoRequest;
import digytal.model.cadastros.produto.ProdutoResponse;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import digytal.utils.http.Response;

@Service
public class ProdutoService extends HttpClient{
	public ProdutoService() {
        super("produtos");
    }
	public List<ProdutoResponse> listar(Integer organizacao, String nome ){
        return get("organizacao", organizacao, "nome", nome).list(ProdutoResponse.class);
    }
	public Response incluir(ProdutoRequest request) throws BusinessException {
        return post(request).body();
    }
	public Response alterar(Integer id, ProdutoRequest request) throws BusinessException {
        return put(request, id).body();
    }
}

package digytal.service.publico;

import org.springframework.stereotype.Service;

import digytal.model.acessos.AlteracaoSenhaRequest;
import digytal.model.acessos.CredencialResponse;
import digytal.model.comum.CadastroBasicoRequest;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import digytal.utils.http.Response;
import digytal.utils.model.Login;
import digytal.utils.model.Sessao;

@Service
public class PublicService extends HttpClient {
	//https://www.baeldung.com/java-string-interpolation
	public PublicService() {
        super("/public");
	}
	
	public Response realizarPrimeiroAcesso(String cpfCnpj, CadastroBasicoRequest request) throws BusinessException {
        return post(request,"empresa","primeiro-acesso",cpfCnpj).body().one(CredencialResponse.class);
    }
	
	public Response alterarSenha(Long expiracao, AlteracaoSenhaRequest request) throws BusinessException {
        return patch(request, "/alteracao-senha", expiracao).body().one(Sessao.class);
    }
	
	public Response logar(Login login) throws BusinessException {
	    return post(login,"/login").body().one(Sessao.class);
	}
	
	public Response solicitarNovaSenha(String login) throws BusinessException {
	    return post(login,"solicitacao-nova-senha/login", login).body().one(CredencialResponse.class);
	}
	
	
	
	
}

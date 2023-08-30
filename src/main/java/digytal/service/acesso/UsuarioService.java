package digytal.service.acesso;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.service.core.HttpClient;
import digytal.utils.model.Empresa;

@Service
public class UsuarioService extends HttpClient {
	public UsuarioService() {
        super("usuarios");
    }
	public List<Empresa> listarEmpresas(Integer id ){
        return get(id,"empresas").list(Empresa.class);
    }
	
}

package digytal.service.publico;

import digytal.model.params.CodigoPostal;
import digytal.service.core.HttpClient;
import digytal.utils.business.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class CepService extends HttpClient {
    public CepService() {
        super("ceps");
    }

    public CodigoPostal buscarCep(String cep ) throws BusinessException {
        return get(cep).one(CodigoPostal.class);
    }
}

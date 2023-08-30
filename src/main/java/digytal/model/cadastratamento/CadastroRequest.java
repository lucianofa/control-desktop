package digytal.model.cadastratamento;

import lombok.Data;

@Data
public class CadastroRequest extends CadastroCompletoRequest {
    private CadastroPerfil perfil = new CadastroPerfil();
}

package digytal.model.cadastros.cadastro;

import digytal.model.cadastratamento.CadastroPerfil;
import digytal.model.comum.RegistroCadastralResponse;
import lombok.Data;

@Data
public class CadastroResponse extends RegistroCadastralResponse {
    private CadastroPerfil perfil;
}

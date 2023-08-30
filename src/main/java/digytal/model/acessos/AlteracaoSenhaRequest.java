package digytal.model.acessos;

import lombok.Data;

@Data
public class AlteracaoSenhaRequest {
	private Integer usuario;
	private String senhaAtual;
	private String novaSenha;
	private String novaSenhaConfirmacao;
}

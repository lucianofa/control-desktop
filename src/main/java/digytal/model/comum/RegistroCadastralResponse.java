package digytal.model.comum;

import lombok.Data;

import java.time.LocalDate;

@Data
public abstract class RegistroCadastralResponse {
	private Integer id;
    private String cpfCnpj;
    private String email;
    private String rgIe;
    private String nomeFantasia;
    private String sobrenomeSocial;
    private LocalDate aniversario;
    private String atividadeComecialProfissional;
    private Telefone telefone;
    private Endereco endereco;
    private boolean incompleto;
}

package digytal.model.cadastratamento;

import lombok.Data;

import java.time.LocalDate;

import digytal.model.comum.Endereco;
import digytal.model.comum.Telefone;

@Data
public class CadastroCompletoRequest extends CadastroSimplificadoRequest {
    private Integer organizacao;
    private Telefone telefone = new Telefone();
    private String cpfCnpj;
    private String rgIe;
    private LocalDate aniversario;
    private String atividadeComecialProfissional;
    private Endereco endereco = new Endereco();
}

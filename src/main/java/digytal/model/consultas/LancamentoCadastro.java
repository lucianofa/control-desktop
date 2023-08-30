package digytal.model.consultas;

import lombok.Data;

@Data
public class LancamentoCadastro {
    private Integer id;
    private String cpfCnpj;
    private String nomeFantasia;
    private String sobrenomeSocial;
    private Long celular;
}
package digytal.model.acessos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CredencialResponse {
    private Long expiracao;
    private Integer usuario;
    private String login;
    private String nome;
    private String token;
}
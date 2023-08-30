package digytal.model.comum;

import lombok.Data;

@Data
public class RegistroParteRequest {
    private Integer empresa;
    private Integer cadastro;
    private Integer usuario;
    public RegistroParteRequest(){

    }
    public static RegistroParteRequest of(Integer empresa, Integer cadastro, Integer usuario){
        RegistroParteRequest instancia = new RegistroParteRequest();
        instancia.cadastro=cadastro;
        instancia.empresa=empresa;
        instancia.usuario=usuario;
        return instancia;
    }
}

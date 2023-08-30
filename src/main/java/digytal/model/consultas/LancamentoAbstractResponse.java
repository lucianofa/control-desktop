package digytal.model.consultas;

import digytal.model.comum.MeioPagamento;
import digytal.model.contratos.contratos.comercializacao.RegistroData;
import digytal.model.contratos.contratos.comercializacao.RegistroPartes;
import lombok.Data;

@Data
public class LancamentoAbstractResponse {
	private Integer id;
    private String numeroDocumento;
    private String descricao;
    private RegistroData data = new RegistroData();
    private RegistroPartes partes = new RegistroPartes();
    private TransacaoTipo tipo;
    private LancamentoCadastro cadastro = new LancamentoCadastro();
    private MeioPagamento meioPagamento;
}

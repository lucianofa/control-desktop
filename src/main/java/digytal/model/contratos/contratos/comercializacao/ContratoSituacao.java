package digytal.model.contratos.contratos.comercializacao;


public enum ContratoSituacao {
    NOVO("N,","Novo"),
    DIGITANDO("D","Digitando"),
    ANALISANDO("A","Analisando"),
    REJEITADO("R","Rejeitado"),
    CONCLUIDO("C","Concluido"),
    BLOQUEADO("B","Bloqueada"),
    ANULADO("U","Anulado");
    private String id;
    private String descricao;
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private ContratoSituacao(String id,String descricao){
        this.id = id;
        this.descricao=descricao;
    }

  
    public String getUpper() {
        return descricao.toUpperCase();
    }

  
}

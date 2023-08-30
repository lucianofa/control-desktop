package digytal.model.comum;

public enum MeioPagamento {
	DINHEIRO("A","Dinheiro",true),
    PIX("X","Pix",true),
    DEBITO("D","Débito",true),
    BOLETO("B","Boleto",false),
    CREDITO("C","Crédito",false),
    PARCELADO("Z","Parcelado",false),
    SALDO ("Y","Saldo",true),
    ;
    private String id;
    private String descricao;
    private boolean atualizaSaldo;
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private MeioPagamento(String id, String descricao, boolean atualizaSaldo){
        this.id = id;
        this.descricao=descricao;
        this.atualizaSaldo=atualizaSaldo;
    }

    public boolean isAtualizaSaldo() {
        return atualizaSaldo;
    }

    public String getUpper() {
        return descricao.toUpperCase();
    }

}
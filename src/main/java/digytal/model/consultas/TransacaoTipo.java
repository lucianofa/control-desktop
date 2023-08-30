package digytal.model.consultas;

public enum TransacaoTipo {
	 RECEITA("R","Receita"),
	    DESPESA("D","Desesa");
	    private String id;
	    private String descricao;
	    private TransacaoTipo(String id, String descricao){
	        this.id = id;
	        this.descricao = descricao;
	    }
	    public String getId() {
	        return id;
	    }

	    
	    public String getDescricao() {
	        return descricao;
	    }

	  
	    public String getUpper() {
	        return descricao.toUpperCase();
	    }
}

package br.com.alura.forum.config.dtovalidacao;

public class ErroDeFornularioDto {

    private String campo;
    private String erro;

    public ErroDeFornularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}

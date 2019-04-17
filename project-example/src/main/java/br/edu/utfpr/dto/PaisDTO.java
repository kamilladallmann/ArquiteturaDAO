package br.edu.utfpr.dto;

import br.edu.utfpr.dao.PaisDAO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaisDTO {
    private int idPais;
    private String nome;
    private String sigla;
    private int codigoTelefone;

    public PaisDTO() {
    }

    public PaisDTO(int idPais, String nome, String sigla, int codigoTelefone) {
        this.idPais = idPais;
        this.nome = nome;
        this.sigla = sigla;
        this.codigoTelefone = codigoTelefone;
    }
}
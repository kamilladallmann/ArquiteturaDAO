package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;

@Data
@Builder
public class ClienteDTO {
    private int idCliente;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private PaisDTO pais;

    public ClienteDTO(){
    }

    public ClienteDTO(int idCliente, String nome, int idade, String telefone, double limiteCredito, PaisDTO pais) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.limiteCredito = limiteCredito;
        this.pais = pais;
    }

    public void setNome(String nome) throws NomeClienteMenor5CaracteresException{
        if (nome.length() < 5)
            throw new NomeClienteMenor5CaracteresException(nome);
        this.nome = nome;
    }

    public void setLimiteCredito(double limiteCredito) {
        if(idade > 0 && idade < 18){
            limiteCredito = 100.00;
        }else{
            if(idade > 18 && idade < 35){
                limiteCredito = 300.00;
            }else{
                if(idade > 35){
                    limiteCredito = 500.00;
                }
            }
        }

        if(pais.getNome().equals("Brasil")){
            limiteCredito += 100.00;
        }

        this.limiteCredito = limiteCredito;
    }
}
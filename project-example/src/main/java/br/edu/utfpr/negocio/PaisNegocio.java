package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomeClienteJaExisteException;
import br.edu.utfpr.excecao.NomePaisJaExisteException;

public class PaisNegocio {

    public void incluir(PaisDTO pais) throws NomePaisJaExisteException {
        if (this.listar().stream().anyMatch(p -> p.getNome().equalsIgnoreCase(pais.getNome())))
            throw new NomePaisJaExisteException(pais.getNome());

        PaisDAO dao = new PaisDAO();
        dao.inserePais(pais);
    }

    public List<PaisDTO> listar() {
        PaisDAO dao = new PaisDAO();
        dao.listaTodos();
        throw new UnsupportedOperationException();
    }

    public void pesquisar(int id){
        PaisDAO dao = new PaisDAO();
        dao.buscaPais(id);
    }

    public void atualizar(PaisDTO pais){
        PaisDAO dao = new PaisDAO();
        dao.updatePais(pais);
    }

    public void remover(PaisDTO pais){
        PaisDAO dao = new PaisDAO();
        dao.deletaPais(pais);
    }

}
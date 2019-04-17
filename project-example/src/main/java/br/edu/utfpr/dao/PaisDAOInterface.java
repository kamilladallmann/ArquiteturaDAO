package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;

import java.util.List;

public interface PaisDAOInterface {

    public boolean inserePais(PaisDTO pais);

    public PaisDTO buscaPais(int id);

    public boolean updatePais(PaisDTO pais);

    public boolean deletaPais(PaisDTO pais);

    public List<PaisDTO> listaTodos();
}

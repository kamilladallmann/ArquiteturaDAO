package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteJaExisteException;

public class ClienteNegocio {

    public void incluir(ClienteDTO cliente) throws NomeClienteJaExisteException {

        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome())))
            throw new NomeClienteJaExisteException(cliente.getNome());

        ClienteDAO dao = new ClienteDAO();
        dao.insereCliente(cliente);
    }

    public List<ClienteDTO> listar() {

        ClienteDAO dao = new ClienteDAO();
        dao.listaTodos();

        throw new UnsupportedOperationException();
    }

    public void pesquisar(int id){
        ClienteDAO dao = new ClienteDAO();
        dao.buscaCliente(id);
    }

    public void atualizar(ClienteDTO dto){
        ClienteDAO dao = new ClienteDAO();
        dao.updateCliente(dto);
    }

    public void remover(ClienteDTO dto){
        ClienteDAO dao = new ClienteDAO();
        dao.deletaCliente(dto);
    }
}
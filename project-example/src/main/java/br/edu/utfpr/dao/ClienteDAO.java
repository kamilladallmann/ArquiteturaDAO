package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;
import br.edu.utfpr.negocio.ClienteNegocio;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public class ClienteDAO implements ClienteDAOInterface{

    private Connection conn = null;
    private Statement query;
    private String sql;

    // Responsável por criar a tabela Cliente no banco.
    public ClienteDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?user=root&password=admin")) {

            log.info("Criando tabela cliente ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE cliente (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cliente_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"telefone varchar(30)," + 
						"idade int," + 
                        "limiteCredito double," +
                        "id_pais int)");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insereCliente(ClienteDTO cliente) {

        sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, cliente.getIdCliente());
            query.setString(2, cliente.getNome());
            query.setString(3, cliente.getTelefone());
            query.setInt(4, cliente.getIdade());
            query.setDouble(5, cliente.getLimiteCredito());
            query.setObject(6, cliente.getPais());

            query.execute();
            query.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ClienteDTO buscaCliente(int idCliente) {

        ClienteDTO cliente = new ClienteDTO();

        sql = "SELECT * FROM cliente WHERE idCliente = " + idCliente;

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setLimiteCredito(rs.getDouble("limiteCredito"));
                cliente.setIdade(rs.getInt("idade"));
                cliente.setPais((PaisDTO) rs.getObject("pais"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NomeClienteMenor5CaracteresException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public boolean updateCliente(ClienteDTO cliente) {
        sql = "UPDATE cliente SET idCliente = ?, nome = ?, telefone = ?, idade = ?, " +
                "limiteCredito = ?, pais = ? WHERE idCliente = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, cliente.getIdCliente());
            query.setString(2, cliente.getNome());
            query.setString(3, cliente.getTelefone());
            query.setInt(4, cliente.getIdade());
            query.setDouble(5, cliente.getLimiteCredito());
            query.setObject(6, cliente.getPais());
            query.setInt(7, cliente.getIdCliente());

            query.execute();
            query.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletaCliente(ClienteDTO cliente) {
        sql = "DELETE FROM cliente WHERE idCliente = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, cliente.getIdCliente());
            query.execute();
            query.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ClienteDTO> listaTodos() {
        List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
        ClienteDTO cliente = new ClienteDTO();

        sql = "SELECT * FROM cliente";

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setLimiteCredito(rs.getDouble("limiteCredito"));
                cliente.setIdade(rs.getInt("idade"));
                cliente.setPais((PaisDTO) rs.getObject("pais"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NomeClienteMenor5CaracteresException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
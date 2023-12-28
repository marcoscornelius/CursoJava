/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
     private conectaDAO conexao;
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
         
    
          public void cadastrarProduto(ProdutosDTO produto) throws SQLException {
               conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=root");
        try  {
            if (conn != null) {
                String query = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                    preparedStatement.setString(1, produto.getNome());
                    preparedStatement.setInt(2, produto.getValor());
                    preparedStatement.setString(3, produto.getStatus());

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Produto salvo com sucesso");
                }
            } else {
                System.err.println("Falha ao conectar ao banco de dados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Lide com exceções de banco de dados adequadamente
        }
    }
        
    
    
    public ArrayList<ProdutosDTO> listarProdutos() throws SQLException{
        conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=root");
        try {
        List<ProdutosDTO> listagem = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * from prdotuos");
           
        ResultSet rs = st.executeQuery();       
            while (rs.next()) {
                ProdutosDTO f = new ProdutosDTO();
                f.setValor(rs.getInt("valor"));
                f.setNome(rs.getString("nome"));                        
                f.setStatus(rs.getString("status"));
                 f.setId(rs.getInt("id"));
            }            

            if (listagem.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Sem produtos");
        } 
        } catch (SQLException ex) {
          
            System.out.println("Erro ao conectar: " + ex.getMessage());
           
        }
        
        return listagem;
    }
    
    
    
        
}


package br.edu.senac.repositories;

import br.edu.senac.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query(value = "CALL SPU_GERARPEDIDOS(:nome, :descricao, :quantidade, :valor, :idPedido, :idProduto)", nativeQuery = true)
    void gerarPedidoItem(
            @Param("nome") String nome,
            @Param("descricao") String descricao,
            @Param("quantidade") int quantidade,
            @Param("valor") double valor,
            @Param("idPedido") Long idPedido,
            @Param("idProduto") Long idProduto
    );
}

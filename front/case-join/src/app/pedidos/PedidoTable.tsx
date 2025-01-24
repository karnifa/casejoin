'use client';

import React from 'react';

interface Pedido {
  id: number;
  dataCriacao: string;
  total: number;
  produtos: { produtoId: number; nome: string; quantidade: number; preco: number }[] | null;
}

interface PedidoTableProps {
  pedidos: Pedido[];
}

const PedidoTable: React.FC<PedidoTableProps> = ({ pedidos }) => {
  return (
    <table border={1} style={{ width: '100%', textAlign: 'left', marginTop: '20px' }}>
      <thead>
        <tr>
          <th>ID</th>
          <th>Data de Criação</th>
          <th>Total</th>
          <th>Produtos</th>
        </tr>
      </thead>
      <tbody>
        {pedidos.map((pedido) => (
          <tr key={pedido.id}>
            <td>{pedido.id}</td>
            <td>{new Date(pedido.dataCriacao).toLocaleDateString()}</td>
            <td>{pedido.total.toFixed(2)}</td>
            <td>
              {pedido.produtos ? (
                pedido.produtos.map((item) => (
                  <span key={item.produtoId}>
                    {item.nome} - Quantidade: {item.quantidade}
                    <br />
                  </span>
                ))
              ) : (
                <span>Sem produtos</span>
              )}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default PedidoTable;

'use client';

import React from 'react';

interface Produto {
  id: number;
  nome: string;
  preco: number;
  quantidade: number;
  categoria: number | { id: number; nome: string }; // Categoria pode ser um número ou objeto
}

interface ProdutoTableProps {
  produtos: Produto[];
  onEdit: (produto: Produto) => void;
  onDelete: (id: number) => void;
}

const ProdutoTable: React.FC<ProdutoTableProps> = ({ produtos, onEdit, onDelete }) => {
  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Preço</th>
            <th>Quantidade</th>
            <th>Categoria</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {produtos.map((produto) => (
            <tr key={produto.id}>
              <td>{produto.id}</td>
              <td>{produto.nome}</td>
              <td>{produto.preco.toFixed(2)}</td>
              <td>{produto.quantidade}</td>
              <td>
                {typeof produto.categoria === 'object' && produto.categoria !== null
                  ? produto.categoria.nome // Se categoria for um objeto, mostre o nome
                  : produto.categoria} {/* Caso contrário, mostre o número */}
              </td>
              <td>
                <button onClick={() => onEdit(produto)}>Editar</button>
                <button onClick={() => onDelete(produto.id)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ProdutoTable;

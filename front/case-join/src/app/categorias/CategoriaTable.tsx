'use client';

import React from 'react';

interface Categoria {
  id: number;
  nome: string;
}

interface CategoriaTableProps {
  categorias: Categoria[];
  onEdit: (categoria: Categoria) => void;
  onDelete: (id: number) => void;
}

const CategoriaTable: React.FC<CategoriaTableProps> = ({ categorias, onEdit, onDelete }) => {
  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {categorias.map((categoria) => (
            <tr key={categoria.id}>
              <td>{categoria.id}</td>
              <td>{categoria.nome}</td>
              <td>
                <button onClick={() => onEdit(categoria)}>Editar</button>
                <button onClick={() => onDelete(categoria.id)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CategoriaTable;
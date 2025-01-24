'use client';

import React, { useState, useEffect } from 'react';
import { Produto } from '../interfaces/Produto';
import { Categoria } from '../interfaces/Categoria';
import { listarCategorias } from '../services/CategoriaService';

interface ProdutoFormProps {
  produtoInicial: Produto;
  onSubmit: (produto: Produto) => void;
}

const ProdutoForm: React.FC<ProdutoFormProps> = ({ produtoInicial, onSubmit }) => {
  const [produto, setProduto] = useState<Produto>(produtoInicial);
  const [categorias, setCategorias] = useState<Categoria[]>([]);

  useEffect(() => {
    const carregarCategorias = async () => {
      const data = await listarCategorias();
      setCategorias(data);
    };

    carregarCategorias();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setProduto({ ...produto, [name]: name === 'preco' || name === 'quantidade' ? parseFloat(value) : value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(produto);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Nome:
        <input
          type="text"
          name="nome"
          value={produto.nome}
          onChange={handleChange}
        />
      </label>
      <br />
      <label>
        Preço:
        <input
          type="number"
          step="0.01"
          name="preco"
          value={produto.preco}
          onChange={handleChange}
        />
      </label>
      <br />
      <label>
        Quantidade:
        <input
          type="number"
          name="quantidade"
          value={produto.quantidade}
          onChange={handleChange}
        />
      </label>
      <br />
      <label>
        Categoria:
        <select
          name="categoria"
          value={produto.categoria}
          onChange={handleChange}
        >
          <option value="">Selecione uma categoria</option>
          {categorias.map((categoria) => (
            <option key={categoria.id} value={categoria.id}>
              {categoria.nome}
            </option>
          ))}
        </select>
      </label>
      <br />
      <div className="modal-footer">
        <button type="submit">Salvar</button>
      </div>
    </form>
  );
};

export default ProdutoForm;

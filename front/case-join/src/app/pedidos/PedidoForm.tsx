'use client';

import React, { useState, useEffect } from 'react';
import { PedidoItem } from '../interfaces/Pedido';
import { Produto } from '../interfaces/Produto';
import { listarProdutos } from '../services/produtoService';

interface PedidoFormProps {
  onSubmit: (itens: PedidoItem[]) => void;
  onCancel: () => void;
}

const PedidoForm: React.FC<PedidoFormProps> = ({ onSubmit, onCancel }) => {
  const [itens, setItens] = useState<PedidoItem[]>([]);
  const [produtos, setProdutos] = useState<Produto[]>([]);

  useEffect(() => {
    const carregarProdutos = async () => {
      const data = await listarProdutos();
      setProdutos(data);
    };

    carregarProdutos();
  }, []);

  const adicionarItem = () => {
    setItens([...itens, { produtoId: 0, quantidade: 1 }]);
  };

  const removerItem = (index: number) => {
    setItens(itens.filter((_, i) => i !== index));
  };

  const atualizarItem = (index: number, field: keyof PedidoItem, value: string | number) => {
    const novosItens = [...itens];
    novosItens[index][field] = field === 'quantidade' || field === 'produtoId' ? Number(value) : value;
    setItens(novosItens);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(itens);
  };

  return (
    <form onSubmit={handleSubmit} className="modal-body">
      <h2>Adicionar Itens ao Pedido</h2>
      {itens.map((item, index) => (
        <div className="item-row" key={index}>
          <label>Produto:</label>
          <select
            value={item.produtoId}
            onChange={(e) => atualizarItem(index, 'produtoId', e.target.value)}
          >
            <option value={0} disabled>
              Selecione um produto
            </option>
            {produtos.map((produto) => (
              <option key={produto.id} value={produto.id}>
                {produto.nome}
              </option>
            ))}
          </select>
          <label>Quantidade:</label>
          <input
            type="number"
            value={item.quantidade}
            onChange={(e) => atualizarItem(index, 'quantidade', e.target.value)}
          />
          <button
            type="button"
            className="remove-item"
            onClick={() => removerItem(index)}
          >
            Remover
          </button>
        </div>
      ))}
      <div className="item-actions">
        <button type="button" className="add-item" onClick={adicionarItem}>
          Adicionar Item
        </button>
      </div>
      <div className="modal-footer">
        <button type="button" className="cancel-btn" onClick={onCancel}>
          Cancelar
        </button>
        <button type="submit" className="save-btn">
          Salvar Pedido
        </button>
      </div>
    </form>
  );
};

export default PedidoForm;

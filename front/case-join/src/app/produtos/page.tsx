'use client';

import React, { useEffect, useState } from 'react';
import { Produto } from '../interfaces/Produto';
import { listarProdutos, criarProduto, atualizarProduto, deletarProduto } from '../services/produtoService';
import ProdutoTable from './ProdutoTable';
import ProdutoForm from './ProdutoForm';
import Home from '../home/home';
import './produtos.css';

const ProdutosPage: React.FC = () => {
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [busca, setBusca] = useState('');
  const [modalAberto, setModalAberto] = useState(false);
  const [produtoEditando, setProdutoEditando] = useState<Produto | null>(null);

  useEffect(() => {
    const carregarProdutos = async () => {
      const data = await listarProdutos();
      setProdutos(data);
    };

    carregarProdutos();
  }, []);

  const produtosFiltrados = produtos.filter((produto) =>
    produto.nome.toLowerCase().includes(busca.toLowerCase())
  );

  const abrirModal = (produto?: Produto) => {
    setProdutoEditando(produto || { id: 0, nome: '', preco: 0, quantidade: 0, categoria: 0 });
    setModalAberto(true);
  };

  const fecharModal = () => {
    setModalAberto(false);
    setProdutoEditando(null);
  };

  const handleSave = async (produto: Produto) => {
    try {
      const { id, categoria, ...produtoData } = produto;
  
      if (id) {
        // Atualizar produto existente
        await atualizarProduto(id, produtoData, categoria);
      } else {
        // Criar novo produto
        await criarProduto(produtoData, categoria);
      }
  
      const data = await listarProdutos();
      setProdutos(data);
      fecharModal();
    } catch (error) {
      console.error('Erro ao salvar produto:', error);
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await deletarProduto(id);
      const data = await listarProdutos();
      setProdutos(data);
    } catch (error) {
      console.error('Erro ao excluir produto:', error);
      alert('Não foi possível excluir o produto. Verifique se ele está associado a outros registros.');
    }
  };

  return (
    <Home>
      <div className="produtos-container">
        <div className="produtos-header">
          <h1>Produtos</h1>
          <button className="button-incluir" onClick={() => abrirModal()}>
            Incluir Novo Produto
          </button>
        </div>
        <input
          type="text"
          placeholder="Buscar produto"
          className="input-busca"
          value={busca}
          onChange={(e) => setBusca(e.target.value)}
        />
        <ProdutoTable
          produtos={produtosFiltrados}
          onEdit={abrirModal}
          onDelete={handleDelete}
        />
        {modalAberto && (
          <div className="modal-overlay">
            <div className="modal-content">
              <div className="modal-header">
                <h2>{produtoEditando?.id ? 'Editar Produto' : 'Novo Produto'}</h2>
                <button className="modal-close" onClick={fecharModal}>
                  ×
                </button>
              </div>
              <ProdutoForm
                produtoInicial={produtoEditando as Produto}
                onSubmit={handleSave}
              />
            </div>
          </div>
        )}
      </div>
    </Home>
  );
};

export default ProdutosPage;

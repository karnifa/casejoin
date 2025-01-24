'use client';

import React, { useEffect, useState } from 'react';
import { listarPedidos } from '../services/pedidoService';
import PedidoTable from './PedidoTable';
import PedidoForm from './PedidoForm';
import Home from '../home/home';
import './pedidos.css';

interface Pedido {
  id: number;
  dataCriacao: string;
  total: number;
  produtos: { produtoId: number; nome: string; quantidade: number; preco: number }[] | null;
}

const PedidosPage: React.FC = () => {
  const [pedidos, setPedidos] = useState<Pedido[]>([]);
  const [busca, setBusca] = useState('');
  const [modalAberto, setModalAberto] = useState(false);

  useEffect(() => {
    const carregarPedidos = async () => {
      const data = await listarPedidos();
      setPedidos(data);
    };

    carregarPedidos();
  }, []);

  const pedidosFiltrados = pedidos.filter((pedido) =>
    pedido.produtos
      ? pedido.produtos.some((item) =>
          item.nome?.toLowerCase().includes(busca.toLowerCase())
        )
      : true
  );

  const handleSave = async (itens: any) => {
    console.log('Salvar pedido:', itens); // Substitua pela lógica de salvar no backend
    setModalAberto(false);
  };

  return (
    <Home>
      <div className="pedidos-container">
        <div className="pedidos-header">
          <h1>Pedidos</h1>
          <button className="button-incluir" onClick={() => setModalAberto(true)}>
            Novo Pedido
          </button>
        </div>
        <input
          type="text"
          placeholder="Buscar pedido por produto"
          className="input-busca"
          value={busca}
          onChange={(e) => setBusca(e.target.value)}
        />
        <div className="table-container">
          <PedidoTable pedidos={pedidosFiltrados} />
        </div>
        {modalAberto && (
          <div className="modal-overlay">
            <div className="modal-content">
              <div className="modal-header">
                <h2>Novo Pedido</h2>
                <button className="modal-close" onClick={() => setModalAberto(false)}>
                  ×
                </button>
              </div>
              <PedidoForm onSubmit={handleSave} onCancel={() => setModalAberto(false)} />
            </div>
          </div>
        )}
      </div>
    </Home>
  );
};

export default PedidosPage;

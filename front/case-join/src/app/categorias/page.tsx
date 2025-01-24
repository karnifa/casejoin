'use client';

import React, { useEffect, useState } from 'react';
import { Categoria } from '../interfaces/Categoria';
import { listarCategorias, criarCategoria, atualizarCategoria, deletarCategoria } from '../services/CategoriaService';
import CategoriaTable from './CategoriaTable';
import CategoriaForm from './CategoriaForm';
import Home from '../home/home';
import './categorias.css';

const CategoriasPage: React.FC = () => {
  const [categorias, setCategorias] = useState<Categoria[]>([]);
  const [busca, setBusca] = useState('');
  const [modalAberto, setModalAberto] = useState(false);
  const [categoriaEditando, setCategoriaEditando] = useState<Categoria | null>(null);

  useEffect(() => {
    const carregarCategorias = async () => {
      const data = await listarCategorias();
      setCategorias(data);
    };

    carregarCategorias();
  }, []);

  const categoriasFiltradas = categorias.filter((categoria) =>
    categoria.nome.toLowerCase().includes(busca.toLowerCase())
  );

  const abrirModal = (categoria?: Categoria) => {
    setCategoriaEditando(categoria || { nome: '' }); // Inicializa sem o campo `id`
    setModalAberto(true);
  };

  const fecharModal = () => {
    setModalAberto(false);
    setCategoriaEditando(null);
  };

  const handleSave = async (categoria: Categoria) => {
    try {
      if (categoria.id) {
        console.log('Atualizando categoria:', { id: categoria.id, nome: categoria.nome });
        await atualizarCategoria(categoria.id, { nome: categoria.nome });
      } else {
        console.log('Criando nova categoria:', { nome: categoria.nome });
        await criarCategoria({ nome: categoria.nome });
      }
  
      const data = await listarCategorias();
      setCategorias(data);
      fecharModal();
    } catch (error) {
      console.error('Erro ao salvar categoria:', error);
    }
  };
  

  const handleDelete = async (id: number) => {
    await deletarCategoria(id);
    const data = await listarCategorias();
    setCategorias(data);
  };

  return (
    <Home>
      <div className="categorias-container">
        <div className="categorias-header">
          <h1>Categorias</h1>
          <button className="button-incluir" onClick={() => abrirModal()}>
            Incluir Nova Categoria
          </button>
        </div>
        <input
          type="text"
          placeholder="Buscar categoria"
          className="input-busca"
          value={busca}
          onChange={(e) => setBusca(e.target.value)}
        />
        <CategoriaTable
          categorias={categoriasFiltradas}
          onEdit={abrirModal}
          onDelete={handleDelete}
        />
        {modalAberto && (
          <div className="modal-overlay">
            <div className="modal-content">
              <div className="modal-header">
                <h2>{categoriaEditando?.id ? 'Editar Categoria' : 'Nova Categoria'}</h2>
                <button className="modal-close" onClick={fecharModal}>
                  ×
                </button>
              </div>
              <CategoriaForm
                categoriaInicial={categoriaEditando as Categoria}
                onSubmit={handleSave}
              />
            </div>
          </div>
        )}
      </div>
    </Home>
  );
};

export default CategoriasPage;

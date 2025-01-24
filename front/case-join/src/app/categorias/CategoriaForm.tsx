'use client';

import React, { useState } from 'react';

interface Categoria {
  id: number;
  nome: string;
}

interface CategoriaFormProps {
  categoria?: Categoria;
  onSubmit: (categoria: Categoria) => void;
  onCancel: () => void;
}

const CategoriaForm: React.FC<CategoriaFormProps> = ({ categoria, onSubmit, onCancel }) => {
  const [nome, setNome] = useState(categoria?.nome || '');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({ id: categoria?.id || 0, nome });
  };

  return (
    <form onSubmit={handleSubmit} className="modal-body">
      <label>Nome:</label>
      <input
        type="text"
        value={nome}
        onChange={(e) => setNome(e.target.value)}
        required
      />
      <div className="modal-footer">
        <button type="button" className="cancel-btn" onClick={onCancel}>
          Cancelar
        </button>
        <button type="submit" className="save-btn">
          Salvar
        </button>
      </div>
    </form>
  );
};

export default CategoriaForm;

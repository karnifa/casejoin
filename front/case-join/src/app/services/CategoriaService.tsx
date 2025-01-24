import api from './api';
import { Categoria } from '../interfaces/Categoria';

// Listar categorias
export const listarCategorias = async (): Promise<Categoria[]> => {
  const response = await api.get<Categoria[]>('/categorias');
  return response.data;
};

// Criar categoria
export const criarCategoria = async (categoria: Categoria): Promise<Categoria> => {
  const response = await api.post<Categoria>('/categorias', categoria);
  return response.data;
};

// Atualizar categoria
export const atualizarCategoria = async (id: number, categoria: Categoria): Promise<Categoria> => {
  const response = await api.put<Categoria>(`/categorias/${id}`, categoria);
  return response.data;
};

// Deletar categoria
export const deletarCategoria = async (id: number): Promise<void> => {
  await api.delete(`/categorias/${id}`);
};

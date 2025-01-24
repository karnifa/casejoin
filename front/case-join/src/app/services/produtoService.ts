import api from './api';
import { Produto } from '../interfaces/Produto';

// Listar produtos
export const listarProdutos = async (): Promise<Produto[]> => {
  const response = await api.get<Produto[]>('/produtos');
  return response.data;
};

// Criar produto
export const criarProduto = async (
  produto: Omit<Produto, 'id' | 'categoria'>,
  categoriaId: number
): Promise<Produto> => {
  const response = await api.post<Produto>(`/produtos?categoriaId=${categoriaId}`, produto);
  return response.data;
};

// Atualizar produto (caso precise manter consistência para edição)
export const atualizarProduto = async (
  id: number,
  produto: Omit<Produto, 'categoria'>,
  categoriaId: number
): Promise<Produto> => {
  const response = await api.put<Produto>(`/produtos/${id}?categoriaId=${categoriaId}`, produto);
  return response.data;
};

// Deletar produto
export const deletarProduto = async (id: number): Promise<void> => {
  await api.delete(`/produtos/${id}`);
};

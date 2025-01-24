import api from './api';
import { Pedido, PedidoItem } from '../interfaces/Pedido';

// Listar pedidos
export const listarPedidos = async (): Promise<Pedido[]> => {
  const response = await api.get<Pedido[]>('/pedidos');
  return response.data;
};

// Criar pedido
export const criarPedido = async (itens: PedidoItem[]): Promise<Pedido> => {
  const response = await api.post<Pedido>('/pedidos', itens);
  return response.data;
};

// Excluir pedido
export const excluirPedido = async (id: number): Promise<void> => {
  await api.delete(`/pedidos/${id}`);
};

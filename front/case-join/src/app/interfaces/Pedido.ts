export interface Pedido {
  id: number;
  total: number;
  itens: PedidoItem[];
}

export interface PedidoItem {
  produtoId: number;
  nome?: string; // Nome é opcional porque será retornado no response
  quantidade: number;
  preco?: number; // Preço é opcional porque será retornado no response
}
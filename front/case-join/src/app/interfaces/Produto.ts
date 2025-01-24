export interface Produto {
  id: number;
  nome: string;
  preco: number; // Em TypeScript, usamos 'number' para valores decimais
  quantidade: number;
  categoria: number; // Relacionamento com Categoria
}
  
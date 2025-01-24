import Link from 'next/link';
import './Home.css';

const Home = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="home-layout">
      <header>
        <h1 className="home-title">Bem-vindo ao Sistema</h1>
        <nav className="home-nav">
        <Link href="/categorias" className="home-link">
            Categorias
          </Link>
          <Link href="/produtos" className="home-link">
            Produtos
          </Link>          
          <Link href="/pedidos" className="home-link">
            Pedidos
          </Link>
        </nav>
      </header>
      <main className="home-content">{children}</main>
    </div>
  );
};

export default Home;

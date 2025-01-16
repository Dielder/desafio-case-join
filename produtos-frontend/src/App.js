import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import ProdutoForm from './components/ProdutoForm';
import ProdutoList from './components/ProdutoList';
import EditarProduto from './components/EditarProduto';
import ReactLoading from 'react-loading';
import './App.css';

const App = () => {
  const [produtos, setProdutos] = useState([]);
  const [loading, setLoading] = useState(true);

  // Função para carregar produtos da API
  const loadProdutos = async () => {
    setLoading(true);
    try {
      const response = await fetch('http://localhost:8080/api/produtos');
      const data = await response.json();
      setProdutos(data);
    } catch (error) {
      console.error('Erro ao carregar produtos:', error);
    }
    setLoading(false);
  };

  // Carregar produtos quando o componente for montado
  useEffect(() => {
    loadProdutos();
  }, []);

  return (
    <Router>
      <div className="container mt-5">
        <h1 className="text-center mb-4">Cadastro de Produtos</h1>

        <div className="mb-4">
          <Link to="/cadastrar" className="btn btn-primary mr-2">
            Cadastrar Produto
          </Link>
          <Link to="/produtos" className="btn btn-secondary">
            Listar Produtos
          </Link>
        </div>

        {loading ? (
          <div className="loading-container">
            <ReactLoading type="spin" color="#f5f5f5" height={50} width={50} />
          </div>
        ) : (
          <Routes>
            {/* Rota inicial, redireciona para /cadastrar */}
            <Route path="/" element={<ProdutoForm setProdutos={setProdutos} produtos={produtos} />} />

            {/* Página para cadastrar produto */}
            <Route path="/cadastrar" element={<ProdutoForm setProdutos={setProdutos} produtos={produtos} />} />

            {/* Página para editar produto */}
            <Route path="/editar-produto/:id" element={<EditarProduto setProdutos={setProdutos} produtos={produtos} />} />

            {/* Página para listar produtos */}
            <Route path="/produtos" element={<ProdutoList produtos={produtos} />} />
          </Routes>
        )}
      </div>
    </Router>
  );
};

export default App;
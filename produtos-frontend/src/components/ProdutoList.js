import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../App.css';

const ListaProdutos = () => {
  const [produtos, setProdutos] = useState([]);
  const [paginaAtual, setPaginaAtual] = useState(1);
  const [produtosPorPagina] = useState(4);
  const [mensagem, setMensagem] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProdutos = async () => {
      try {
        const { data } = await axios.get('http://localhost:8080/api/produtos');
        setProdutos(data);
      } catch (error) {
        console.error('Erro ao buscar produtos:', error);
      }
    };

    fetchProdutos();
  }, []);

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/produtos/${id}`);
      setProdutos(produtos.filter((produto) => produto.id !== id));
      showMessage('Produto excluído com sucesso!');
    } catch (error) {
      console.error('Erro ao excluir produto:', error);
    }
  };

  const handleEdit = (id) => navigate(`/editar-produto/${id}`);

  const showMessage = (text) => {
    setMensagem(text);
    setTimeout(() => setMensagem(''), 1500);
  };

  // Função para mudar de página
  const nextPage = () => {
    if ((paginaAtual * produtosPorPagina) < produtos.length) {
      setPaginaAtual(paginaAtual + 1);
    }
  };

  const prevPage = () => {
    if (paginaAtual > 1) {
      setPaginaAtual(paginaAtual - 1);
    }
  };

  // Produtos a serem exibidos com base na página atual
  const indexOfLastProduto = paginaAtual * produtosPorPagina;
  const indexOfFirstProduto = indexOfLastProduto - produtosPorPagina;
  const currentProdutos = produtos.slice(indexOfFirstProduto, indexOfLastProduto);

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Lista de Produtos</h2>

      {mensagem && (
        <div className={`mensagem-overlay ${mensagem.tipo === 'erro' ? 'mensagem-erro' : 'mensagem-sucesso'}`}>
          <div className="mensagem-modal">
            <p>{mensagem}</p>
          </div>
        </div>
      )}

      <div className="list-group">
        {currentProdutos.map(({ id, nome, descricao, preco, quantidade }) => (
          <div
            key={id}
            className="list-group-item d-flex justify-content-between align-items-center border shadow-sm mb-2"
          >
            <div>
              <h5 className="mb-1">{nome}</h5>
              <p className="mb-1 text-muted">Descrição: {descricao}</p>
              <p className="mb-1 text-muted">Preço: R${preco}</p>
              <small>Quantidade: {quantidade}</small>
            </div>
            <div>
              <button
                className="btn btn-primary btn-sm me-2"
                onClick={() => handleEdit(id)}
              >
                Editar
              </button>
              <button
                className="btn btn-danger btn-sm"
                onClick={() => handleDelete(id)}
              >
                Excluir
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Paginação */}
      <div className="d-flex justify-content-between mt-4">
        <button className="btn btn-secondary" onClick={prevPage} disabled={paginaAtual === 1}>
          Anterior
        </button>
        <button
          className="btn btn-secondary"
          onClick={nextPage}
          disabled={paginaAtual * produtosPorPagina >= produtos.length}
        >
          Próxima
        </button>
      </div>
    </div>
  );
};

export default ListaProdutos;

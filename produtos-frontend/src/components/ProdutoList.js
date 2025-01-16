import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../App.css';

const ListaProdutos = () => {
  const [produtos, setProdutos] = useState([]);
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
        {produtos.map(({ id, nome, descricao, preco, quantidade }) => (
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
    </div>
  );
};

export default ListaProdutos;
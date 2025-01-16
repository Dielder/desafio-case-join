import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ProdutoForm = ({ produtoAtual, setProdutos }) => {
  const [produto, setProduto] = useState({
    nome: '',
    preco: '',
    quantidade: '',
    descricao: ''
  });

  const [mensagem, setMensagem] = useState('');

  useEffect(() => {
    if (produtoAtual) setProduto(produtoAtual);
  }, [produtoAtual]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    const validacoes = {
      preco: () => /^\d*\.?\d{0,2}$/.test(value) && value.length <= 8,
      quantidade: () => /^\d*$/.test(value) && value.length <= 5
    };

    if (name in validacoes) {
      if (validacoes[name]()) setProduto({ ...produto, [name]: value });
    } else {
      setProduto({ ...produto, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/produtos', produto);
      setMensagem({ texto: 'Produto salvo com sucesso!', tipo: 'sucesso' });
      setTimeout(() => setMensagem(''), 1500); // Limpar a mensagem após 1,5 segundos
      setProdutos((prevProdutos) => [...prevProdutos, produto]);
    } catch (error) {
      console.error(error);
      setMensagem({ texto: 'Já existe um produto com esse nome.', tipo: 'erro' });
    }
  };

  return (
    <div className="produto-form">
      {mensagem && (
        <div className={`mensagem ${mensagem.tipo}`}>{mensagem.texto}</div>
      )}

      <form onSubmit={handleSubmit} className="p-3 border rounded shadow-sm">
        {['nome', 'preco', 'quantidade', 'descricao'].map((campo) => (
          <div className="mb-3" key={campo}>
            <label htmlFor={campo} className="form-label">
              {campo.charAt(0).toUpperCase() + campo.slice(1)}
            </label>
            {campo === 'descricao' ? (
              <textarea
                id={campo}
                name={campo}
                value={produto[campo]}
                onChange={handleChange}
                className="form-control"
                maxLength="100"
                placeholder={`Ex: ${campo === 'descricao' ? 'Camisa de algodão' : ''}`}
              />
            ) : (
              <input
                type="text"
                id={campo}
                name={campo}
                value={produto[campo]}
                onChange={handleChange}
                className="form-control"
                required
                maxLength={campo === 'preco' ? '8' : campo === 'quantidade' ? '5' : '50'}
                placeholder={`Ex: ${campo === 'nome' ? 'Camisa polo' : campo === 'preco' ? '99.99' : campo === 'quantidade' ? '50' : ''}`}
              />
            )}
          </div>
        ))}
        <button type="submit" className="btn btn-primary">Salvar Produto</button>
      </form>
    </div>
  );
};

export default ProdutoForm;
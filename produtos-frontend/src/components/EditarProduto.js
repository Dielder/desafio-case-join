import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

const EditarProduto = ({ setProdutos, produtos }) => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [produto, setProduto] = useState({
    nome: '',
    descricao: '',
    preco: '',
    quantidade: '',
  });
  const [mensagem, setMensagem] = useState('');
  const [erro, setErro] = useState('');

  useEffect(() => {
    const fetchProduto = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/produtos/${id}`);
        setProduto(response.data);
      } catch (error) {
        setErro('Erro ao buscar os dados do produto.');
      }
    };
    fetchProduto();
  }, [id]);

  const handleChange = ({ target: { name, value } }) => {
    if (
      (name === 'nome' && value.length <= 50) ||
      (name === 'descricao' && value.length <= 100) ||
      (name === 'preco' && (/^\d*\.?\d*$/.test(value) && value.length <= 8)) ||
      (name === 'quantidade' && (/^\d*$/.test(value) && value.length <= 5))
    ) {
      setProduto({ ...produto, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErro('');
    const produtoFormatado = {
      ...produto,
      preco: parseFloat(produto.preco),
      quantidade: parseInt(produto.quantidade, 10),
    };

    try {
      const { data } = await axios({
        method: produto.id ? 'PUT' : 'POST',
        url: produto.id ? `http://localhost:8080/api/produtos/${produto.id}` : 'http://localhost:8080/api/produtos',
        data: produtoFormatado,
      });

      setMensagem('Produto salvo com sucesso!');
      setTimeout(() => setMensagem(''), 2000);

      setProdutos((prevProdutos) =>
        produto.id ? prevProdutos.map((prod) => (prod.id === produto.id ? data : prod)) : [...prevProdutos, data]
      );

      navigate('/produtos');
    } catch (error) {
      setErro(error.response?.data?.mensagem || 'Esse produto já existe na lista de produtos.');
    }
  };

  const handleCancel = () => {
    setProduto({ nome: '', descricao: '', preco: '', quantidade: '' });
    navigate('/produtos');
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Editar Produto</h2>

      {mensagem && <div className="alert alert-success text-center">{mensagem}</div>}
      {erro && <div className="alert alert-danger text-center">{erro}</div>}

      <form onSubmit={handleSubmit} className="border p-4 shadow-sm rounded">
        {['nome', 'descricao', 'preco', 'quantidade'].map((campo) => (
          <div key={campo} className="mb-3">
            <label htmlFor={campo} className="form-label">
              {campo.charAt(0).toUpperCase() + campo.slice(1)}
            </label>
            <input
              type="text"
              className="form-control"
              id={campo}
              name={campo}
              value={produto[campo]}
              onChange={handleChange}
              maxLength={campo === 'nome' ? 50 : campo === 'descricao' ? 100 : campo === 'preco' ? 8 : 5}
              required={campo !== 'descricao'}
            />
            <small className="text-muted">
              Máximo de {campo === 'nome' ? 50 : campo === 'descricao' ? 100 : campo === 'preco' ? 8 : 5} caracteres.
            </small>
          </div>
        ))}

        <button type="submit" className="btn btn-success">Salvar</button>
        <button type="button" className="btn btn-secondary ms-2" onClick={handleCancel}>Cancelar</button>
      </form>
    </div>
  );
};

export default EditarProduto;

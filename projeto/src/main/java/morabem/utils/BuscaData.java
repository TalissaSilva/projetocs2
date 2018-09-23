package morabem.utils;

import morabem.domain.Anuncio;
import morabem.domain.Endereco;
import morabem.domain.Imovel;

import java.util.ArrayList;
import java.util.List;

public class BuscaData {

    public String titulo;
    public String destricao;
    public Anuncio.Tipo tipo;

    public Object preco = new Object() {
        public Integer min, max;

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        @Override
        public String toString() {
            return "$classname{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    };

    public Object imovel = new Object() {
        public Imovel.Tipo tipo;
        public Double areaConstruida;
        public Double areaTotal;
        public List<String> caracteristicas = new ArrayList<>();

        public Object endereco = new Object() {
            public String logradouro, bairro, cidade, estado;

            public String getLogradouro() {
                return logradouro;
            }

            public void setLogradouro(String logradouro) {
                this.logradouro = logradouro;
            }

            public String getBairro() {
                return bairro;
            }

            public void setBairro(String bairro) {
                this.bairro = bairro;
            }

            public String getCidade() {
                return cidade;
            }

            public void setCidade(String cidade) {
                this.cidade = cidade;
            }

            public String getEstado() {
                return estado;
            }

            public void setEstado(String estado) {
                this.estado = estado;
            }

            @Override
            public String toString() {
                return "$classname{" +
                        "logradouro='" + logradouro + '\'' +
                        ", bairro='" + bairro + '\'' +
                        ", cidade='" + cidade + '\'' +
                        ", estado='" + estado + '\'' +
                        '}';
            }
        };

        public Imovel.Tipo getTipo() {
            return tipo;
        }

        public void setTipo(Imovel.Tipo tipo) {
            this.tipo = tipo;
        }

        public Double getAreaConstruida() {
            return areaConstruida;
        }

        public void setAreaConstruida(Double areaConstruida) {
            this.areaConstruida = areaConstruida;
        }

        public Double getAreaTotal() {
            return areaTotal;
        }

        public void setAreaTotal(Double areaTotal) {
            this.areaTotal = areaTotal;
        }

        public List<String> getCaracteristicas() {
            return caracteristicas;
        }

        public void setCaracteristicas(List<String> caracteristicas) {
            this.caracteristicas = caracteristicas;
        }

        public Object getEndereco() {
            return endereco;
        }

        public void setEndereco(Object endereco) {
            this.endereco = endereco;
        }

        @Override
        public String toString() {
            return "$classname{" +
                    "tipo=" + tipo +
                    ", areaConstruida=" + areaConstruida +
                    ", areaTotal=" + areaTotal +
                    ", caracteristicas=" + caracteristicas +
                    ", endereco=" + endereco +
                    '}';
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDestricao() {
        return destricao;
    }

    public void setDestricao(String destricao) {
        this.destricao = destricao;
    }

    public Anuncio.Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Anuncio.Tipo tipo) {
        this.tipo = tipo;
    }

    public Object getPreco() {
        return preco;
    }

    public void setPreco(Object preco) {
        this.preco = preco;
    }

    public Object getImovel() {
        return imovel;
    }

    public void setImovel(Object imovel) {
        this.imovel = imovel;
    }

    @Override
    public String toString() {
        return "BuscaData{" +
                "titulo='" + titulo + '\'' +
                ", destricao='" + destricao + '\'' +
                ", tipo=" + tipo +
                ", preco=" + preco +
                ", imovel=" + imovel +
                '}';
    }
}

package morabem.utils;

import morabem.domain.Imovel;

import java.util.ArrayList;
import java.util.List;

public class ImovelData {
    private Imovel.Tipo tipo;
    private Double areaConstruida;
    private Double areaTotal;
    private List<String> caracteristicas = new ArrayList<>();
    private EnderecoData endereco = new EnderecoData();

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

    public EnderecoData getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoData endereco) {
        this.endereco = endereco;
    }
}
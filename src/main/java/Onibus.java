public class Onibus {

    private String tipo;
    private double fatorEmissaoCO2;
    private double distanciaMensal;
    private double custoPorKm;

    public Onibus(String tipo, double fatorEmissaoCO2, double distanciaMensal, double custoPorKm) {
        this.tipo = tipo;
        this.fatorEmissaoCO2 = fatorEmissaoCO2;
        this.distanciaMensal = distanciaMensal;
        this.custoPorKm = custoPorKm;
    }

    public double calcularEmissao() {
        return fatorEmissaoCO2 * distanciaMensal;
    }

    public double calcularCusto() {
        return custoPorKm * distanciaMensal;
    }

    public String getTipo() { return tipo; }
    public double getFatorEmissaoCO2() { return fatorEmissaoCO2; }
    public double getDistanciaMensal() { return distanciaMensal; }
    public double getCustoPorKm() { return custoPorKm; }
}
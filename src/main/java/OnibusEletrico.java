public class OnibusEletrico extends Onibus {

    public OnibusEletrico(double distancia, double custoPorKm) {
        super("Elétrico", 0.0, distancia, custoPorKm);
    }

    @Override
    public double calcularEmissao() {
        System.out.println("Calculando emissão do ônibus elétrico...");
        return super.calcularEmissao();
    }

    @Override
    public double calcularCusto() {
        System.out.println("Calculando custo do ônibus elétrico...");
        return super.calcularCusto();
    }
}
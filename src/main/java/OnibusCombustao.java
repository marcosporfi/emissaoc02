public class OnibusCombustao extends Onibus {

    public OnibusCombustao(double distancia, double custoPorKm) {
        super("Combustão", 1500.0, distancia, custoPorKm);
    }

    @Override
    public double calcularEmissao() {
        System.out.println("Calculando emissão do ônibus a combustão...");
        return super.calcularEmissao();
    }

    @Override
    public double calcularCusto() {
        System.out.println("Calculando custo do ônibus a combustão...");
        return super.calcularCusto();
    }
}
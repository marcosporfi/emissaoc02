public class CalculadoraEmissao {

    private static final double META_CO2 = 5000.0;
    private static final double REDUCAO_MENSAL = 200.0;

    private double distancia;
    private double custoCombustao;
    private double custoEletrico;

    public CalculadoraEmissao(double distancia, double custoCombustao, double custoEletrico) {
        this.distancia = distancia;
        this.custoCombustao = custoCombustao;
        this.custoEletrico = custoEletrico;
    }

    public double calcularEmissaoTotal(int qtdCombustao, int qtdEletricos) {
        Onibus combustao = new OnibusCombustao(distancia, custoCombustao);
        Onibus eletrico = new OnibusEletrico(distancia, custoEletrico);

        double totalCombustao = qtdCombustao * combustao.calcularEmissao();
        double totalEletrico = qtdEletricos * eletrico.calcularEmissao();

        return totalCombustao + totalEletrico;
    }

    public double calcularCustoTotal(int qtdCombustao, int qtdEletricos) {
        Onibus combustao = new OnibusCombustao(distancia, custoCombustao);
        Onibus eletrico = new OnibusEletrico(distancia, custoEletrico);

        double custoCombustaoTotal = qtdCombustao * combustao.calcularCusto();
        double custoEletricoTotal = qtdEletricos * eletrico.calcularCusto();

        return custoCombustaoTotal + custoEletricoTotal;
    }

    public boolean metaAlcancada(double emissaoMensal) {
        return emissaoMensal <= META_CO2;
    }

    public void calcularTempoParaMeta(double emissaoMensal) {
        double diferenca = emissaoMensal - META_CO2;

        if (REDUCAO_MENSAL > 0) {
            double mesesParaMeta = diferenca / REDUCAO_MENSAL;
            int anos = (int) mesesParaMeta / 12;
            int meses = (int) mesesParaMeta % 12;
            System.out.println("A meta será alcançada em " + anos + " anos e " + meses + " meses.");
        } else {
            System.out.println("A redução mensal não é suficiente para alcançar a meta.");
        }
    }

    public double getMetaCO2() { return META_CO2; }
}
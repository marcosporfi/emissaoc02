import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class EmissaoCO2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quilometragem mensal por ônibus (km): ");
        double distancia = scanner.nextDouble();

        System.out.print("Digite o custo por km do ônibus a combustão (R$): ");
        double custoCombustao = scanner.nextDouble();

        System.out.print("Digite o custo por km do ônibus elétrico (R$): ");
        double custoEletrico = scanner.nextDouble();

        System.out.print("Digite a quantidade de ônibus a combustão: ");
        int onibusCombustao = scanner.nextInt();

        System.out.print("Digite a quantidade de ônibus elétricos: ");
        int onibusEletricos = scanner.nextInt();

        CalculadoraEmissao calculadora = new CalculadoraEmissao(distancia, custoCombustao, custoEletrico);

        double emissaoMensal = calculadora.calcularEmissaoTotal(onibusCombustao, onibusEletricos);
        double custoTotal = calculadora.calcularCustoTotal(onibusCombustao, onibusEletricos);

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Emissão mensal de CO2: " + emissaoMensal + " gramas.");
        System.out.printf("Custo operacional total mensal: R$ %.2f%n", custoTotal);

        if (calculadora.metaAlcancada(emissaoMensal)) {
            System.out.println("Meta de emissão de CO2 foi alcançada!");
        } else {
            System.out.println("Meta de emissão de CO2 não foi alcançada.");
            calculadora.calcularTempoParaMeta(emissaoMensal);
        }

        exibirGraficoEmissao(emissaoMensal, calculadora.getMetaCO2());
    }

    public static void exibirGraficoEmissao(double emissaoMensal, double meta) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(emissaoMensal, "Emissão", "Mês Atual");
        dataset.addValue(meta, "Meta", "Mês Atual");

        JFreeChart chart = ChartFactory.createBarChart(
                "Emissão de CO2",
                "Período",
                "Emissão de CO2 (gramas)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame("Gráfico de Emissão de CO2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
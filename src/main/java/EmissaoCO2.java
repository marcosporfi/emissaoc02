import java.util.Scanner;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class EmissaoCO2 {

    private static final double CO2_COMBUSTAO = 1500.0;
    private static final double CO2_ELETRICO = 0.0;
    private static final double META_CO2 = 5000.0;
    private static final double DISTANCIA = 1000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade à combustão: ");
        int onibusCombustao = scanner.nextInt();
        System.out.print("Digite a quantidade de elétricos: ");
        int onibusEletricos = scanner.nextInt();

        double emissaoMensal = calcularEmissaoMensal(onibusCombustao, onibusEletricos);
        System.out.println("Emissão mensal de CO2: " + emissaoMensal + " gramas.");

        if (emissaoMensal <= META_CO2) {
            System.out.println("Meta de emissão de CO2 foi alcançada!");
        } else {
            System.out.println("Meta de emissão de CO2 não foi alcançada.");
            calcularTempoParaMeta(emissaoMensal);
        }
        
        exibirGraficoEmissao(emissaoMensal);
    }

    public static double calcularEmissaoMensal(int onibusCombustao, int onibusEletricos) {
        double emissaoCombustao = onibusCombustao * CO2_COMBUSTAO * DISTANCIA;
        double emissaoEletricos = onibusEletricos * CO2_ELETRICO * DISTANCIA;
        return emissaoCombustao + emissaoEletricos;
    }

    public static void calcularTempoParaMeta(double emissaoMensal) {
        double diferenca = emissaoMensal - META_CO2;
        double reducaoMensal = 200.0;

        if (reducaoMensal > 0) {
            double mesesParaMeta = diferenca / reducaoMensal;
            int anos = (int) mesesParaMeta / 12;
            int meses = (int) mesesParaMeta % 12;
            System.out.println("A meta será alcançada em " + anos + " anos e " + meses + " meses.");
        } else {
            System.out.println("A redução mensal não é suficiente para alcançar a meta.");
        }
    }

    public static void exibirGraficoEmissao(double emissaoMensal) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(emissaoMensal, "Emissão", "Mês Atual");
        dataset.addValue(META_CO2, "Meta", "Mês Atual");

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
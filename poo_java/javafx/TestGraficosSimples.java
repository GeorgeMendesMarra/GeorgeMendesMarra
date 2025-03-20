import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TestGraficosSimples extends Application {

	private static final int GRAFICO_ALTURA = 300;
	private static final int GRAFICO_LARGURA = 300;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage s) throws Exception {
		s.setScene(new Scene(new FlowPane(criarGraficoPizza(),
				criarGraficoLinha(), criarGraficoBarra())));
		s.setTitle("Gráficos básicos do JavaFX");
		s.setWidth(910);
		s.setHeight(350);
		s.show();

	}

	private Node criarGraficoPizza() {
		PieChart graficoPizza = new PieChart();
		graficoPizza.getData().addAll(new PieChart.Data("Trimestre 1", 11),
				new PieChart.Data("Trimestre 2", 1),
				new PieChart.Data("Trimestre 3", 34),
				new PieChart.Data("Trimestre 5", 12));
		graficoPizza.setTitle("Lucros por Trimestre");
		graficoPizza.setPrefSize(GRAFICO_LARGURA, GRAFICO_ALTURA);
		return graficoPizza;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node criarGraficoLinha() {
		LineChart<String, Number> graficoLinha = new LineChart<>(
				new CategoryAxis(), new NumberAxis());
		final String T1 = "T1";
		final String T2 = "T2";
		final String T3 = "T3";
		final String T4 = "T4";

		XYChart.Series prod1 = new XYChart.Series();
		prod1.setName("Produto 1");

		prod1.getData().add(new XYChart.Data(T1, 5));
		prod1.getData().add(new XYChart.Data(T2, -2));
		prod1.getData().add(new XYChart.Data(T3, 3));
		prod1.getData().add(new XYChart.Data(T4, 15));

		XYChart.Series prod2 = new XYChart.Series();
		prod2.setName("Produto 2");

		prod2.getData().add(new XYChart.Data(T1, -5));
		prod2.getData().add(new XYChart.Data(T2, -1));
		prod2.getData().add(new XYChart.Data(T3, 12));
		prod2.getData().add(new XYChart.Data(T4, 8));

		XYChart.Series prod3 = new XYChart.Series();
		prod3.setName("Produto 3");

		prod3.getData().add(new XYChart.Data(T1, 12));
		prod3.getData().add(new XYChart.Data(T2, 15));
		prod3.getData().add(new XYChart.Data(T3, 12));
		prod3.getData().add(new XYChart.Data(T4, 20));
		graficoLinha.getData().addAll(prod1, prod2, prod3);
		graficoLinha.setPrefSize(GRAFICO_LARGURA, GRAFICO_ALTURA);
		return graficoLinha;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node criarGraficoBarra() {
		BarChart<String, Number> graficoBarra = new BarChart<>(
				new CategoryAxis(), new NumberAxis());
		final String T1 = "T1";
		final String T2 = "T2";
		final String T3 = "T3";
		final String T4 = "T4";

		XYChart.Series prod1 = new XYChart.Series();
		prod1.setName("Produto 1");

		prod1.getData().add(new XYChart.Data(T1, 5));
		prod1.getData().add(new XYChart.Data(T2, -2));
		prod1.getData().add(new XYChart.Data(T3, 3));
		prod1.getData().add(new XYChart.Data(T4, 15));

		XYChart.Series prod2 = new XYChart.Series();
		prod2.setName("Produto 2");

		prod2.getData().add(new XYChart.Data(T1, -5));
		prod2.getData().add(new XYChart.Data(T2, -1));
		prod2.getData().add(new XYChart.Data(T3, 12));
		prod2.getData().add(new XYChart.Data(T4, 8));

		XYChart.Series prod3 = new XYChart.Series();
		prod3.setName("Produto 3");

		graficoBarra.getData().addAll(prod1, prod2, prod3);
		graficoBarra.setPrefSize(GRAFICO_LARGURA, GRAFICO_ALTURA);
		return graficoBarra;
	}
}

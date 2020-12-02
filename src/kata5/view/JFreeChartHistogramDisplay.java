package kata5.view;

import kata5.view.HistogramDisplay;
import java.awt.Container;
import javax.swing.JPanel;
import kata5.model.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class JFreeChartHistogramDisplay extends ApplicationFrame implements HistogramDisplay {
    Histogram histogram = null;
    
    public JFreeChartHistogramDisplay(String title, Histogram<String> histogram) {
        super(title);
        this.histogram = histogram;
        this.setContentPane(createPanel());
        this.pack();
    }

    @Override
    public void execute() {
        this.setVisible(true);
    }

    private JPanel createPanel() {
        ChartPanel chartPanel = new ChartPanel(createChart(createDataset()));
        return chartPanel;
    }
    
    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object key : histogram.keySet()) {
            dataset.addValue(histogram.get((String) key),"", (String) key);
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart freeChart = ChartFactory.createBarChart("JFreeChart Histogram",
                "email domains",
                "nº emails",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                rootPaneCheckingEnabled);
        return freeChart;
    }

}

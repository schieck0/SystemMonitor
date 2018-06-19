package chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Usage
        extends JPanel {

    private TimeSeries cpu;
    private TimeSeries mem;

    public Usage(int paramInt) {
        super(new BorderLayout());
        this.cpu = new TimeSeries("CPU");
        this.cpu.setMaximumItemAge(paramInt);
        this.mem = new TimeSeries("Memory");
        this.mem.setMaximumItemAge(paramInt);
        TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection();
        localTimeSeriesCollection.addSeries(this.cpu);
        localTimeSeriesCollection.addSeries(this.mem);
        DateAxis localDateAxis = new DateAxis();
        NumberAxis localNumberAxis = new NumberAxis();
//        localDateAxis.setTickLabelFont(new Font("SansSerif", 0, 12));
//        localNumberAxis.setTickLabelFont(new Font("SansSerif", 0, 12));
//        localDateAxis.setLabelFont(new Font("SansSerif", 0, 14));
//        localNumberAxis.setLabelFont(new Font("SansSerif", 0, 14));
        localNumberAxis.setAutoRange(false);
        localNumberAxis.setRange(0, 100);
        XYLineAndShapeRenderer localXYLineAndShapeRenderer = new XYLineAndShapeRenderer(true, false);
        localXYLineAndShapeRenderer.setSeriesPaint(0, Color.red);
        localXYLineAndShapeRenderer.setSeriesPaint(1, Color.green);
        localXYLineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(3.0F, 0, 2));
        localXYLineAndShapeRenderer.setSeriesStroke(1, new BasicStroke(3.0F, 0, 2));
        XYPlot localXYPlot = new XYPlot(localTimeSeriesCollection, localDateAxis, localNumberAxis, localXYLineAndShapeRenderer);
        localDateAxis.setAutoRange(true);
        localDateAxis.setLowerMargin(0.0D);
        localDateAxis.setUpperMargin(0.0D);
        localDateAxis.setTickLabelsVisible(true);
        localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        JFreeChart localJFreeChart = new JFreeChart("System Usage", new Font("SansSerif", 1, 24), localXYPlot, true);
        ChartUtilities.applyCurrentTheme(localJFreeChart);
        ChartPanel localChartPanel = new ChartPanel(localJFreeChart, true);
        localChartPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.black)));
        add(localChartPanel);
    }

    public void addCpuValue(int value) {
        this.cpu.add(new Millisecond(), value);
    }
    
    public void addMemValue(int value) {
        this.mem.add(new Millisecond(), value);
    }

}

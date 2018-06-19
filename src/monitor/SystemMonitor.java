package monitor;

import chart.Usage;
import com.sun.management.OperatingSystemMXBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;
import javax.swing.Timer;

public class SystemMonitor extends Timer
        implements ActionListener {

    private Usage chart;
    private final OperatingSystemMXBean osBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
    private final long totalMem = osBean.getTotalPhysicalMemorySize();

    public SystemMonitor(int paramInt, Usage mud) {
        super(1000, null);
        addActionListener(this);
        this.chart = mud;
    }

    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {
        chart.addCpuValue((int)(osBean.getSystemCpuLoad()*100));
        chart.addMemValue((int) ((100*(totalMem - osBean.getFreePhysicalMemorySize())) / totalMem));
    }
}

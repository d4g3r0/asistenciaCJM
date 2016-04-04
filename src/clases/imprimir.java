package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class imprimir {
    
    Connection conexx;
    Statement newst;

    public imprimir() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexx = DriverManager.getConnection("jdbc:mysql://192.168.1.25/notas_2016", "maestro", "teacher");
            this.newst = this.conexx.createStatement();
        } catch (ClassNotFoundException | SQLException eq) {
            JOptionPane.showMessageDialog(null, eq, "ADVERTENCIA", 2);
        }
    }

    public void mostrar_reporte(String nReporte) throws Exception {
        JasperReport reporte = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reportes/"+nReporte));
        JasperPrint print = JasperFillManager.fillReport(reporte, null, conexx);
        JasperViewer view = new JasperViewer(print, false);
        view.setVisible(true);
        view.setTitle("Reporte de Asistencia Global");
        view.setExtendedState(0);
    }

}

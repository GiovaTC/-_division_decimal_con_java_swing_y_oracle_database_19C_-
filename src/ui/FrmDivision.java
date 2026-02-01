package ui;

import dao.DivisionDAO;
import model.Division;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FrmDivision extends JFrame {

    private JTextArea area;

    public FrmDivision() {
        setTitle("division decimal 0.0000");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        area = new JTextArea();
        JButton btn = new JButton("ejecutar 6 divisiones");

        btn.addActionListener(e -> ejecutar());

        add(new JScrollPane(area), "Center");
        add(btn, "South");
    }

    private void ejecutar() {
        try {
            DivisionDAO dao = new DivisionDAO();

            double[][] datos = {
                    {0.1, 1000},
                    {0.25, 5000},
                    {0.5, 20000},
                    {1, 30000},
                    {2, 80000},
                    {3, 120000}
            };

            for (double[] d : datos) {
                BigDecimal a = BigDecimal.valueOf(d[0]);
                BigDecimal b = BigDecimal.valueOf(d[1]);

                BigDecimal res = a.divide(b, 4, RoundingMode.HALF_UP);

                dao.insertar(new Division(a, b, res));
                area.append(a + " / " + b + " = " + res + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new FrmDivision().setVisible(true);
    }
}   



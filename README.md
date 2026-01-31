# -_division_decimal_con_java_swing_y_oracle_database_19C_- :.
# -_division_decimal_con_java_swing_y_oracle_database_19C_-:

<img width="1024" height="1024" alt="image" src="https://github.com/user-attachments/assets/63f3ab78-d2c3-4d5b-b8d1-eccc1e3027f6" />   

- A continuacion tienes una solución completa, clara y ejecutable en Java (IntelliJ IDEA) + Oracle Database 19c , que cumple con el requerimiento planteado.

## 🎯 Objetivo del programa:

- Realizar 6 ejemplos de división entre dos números decimales
- Forzar que el resultado sea exactamente 0.0000 (4 decimales)
- Registrar cada operación en Oracle 19c
- Usar tabla + procedimiento almacenado
- Aplicación Java con interfaz gráfica (Swing)

* Incluir concepto de logo minimalista (prompt listo para generar)

## 1️⃣ Ejemplos de divisiones (resultado = 0.0000):
* #	Número A	Número B	Resultado
* 1	0.1	1000	0.0000
* 2	0.25	5000	0.0000
* 3	0.5	20000	0.0000
* 4	1.0	30000	0.0000
* 5	2.0	80000	0.0000
* 6	3.0	120000	0.0000

- El resultado se redondea a 4 decimales usando BigDecimal.

## 2️⃣ Oracle 19c – Tabla:  
```
CREATE TABLE DIVISION_DECIMAL (
ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
NUMERO_A NUMBER(10,4),
NUMERO_B NUMBER(10,4),
RESULTADO NUMBER(10,4),
FECHA_REGISTRO DATE DEFAULT SYSDATE
);
```
## 3️⃣ Procedimiento almacenado:
```
CREATE OR REPLACE PROCEDURE SP_INSERTAR_DIVISION (
P_A IN NUMBER,
P_B IN NUMBER,
P_RESULTADO IN NUMBER
) AS
BEGIN
INSERT INTO DIVISION_DECIMAL (NUMERO_A, NUMERO_B, RESULTADO)
VALUES (P_A, P_B, P_RESULTADO);
COMMIT;
END;
/
```
## 4️⃣ Java – Conexión Oracle:
```
ackage db;


import java.sql.Connection;
import java.sql.DriverManager;


public class ConexionOracle {


public static Connection getConexion() throws Exception {
String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
String user = "USUARIO";
String pass = "CLAVE";
return DriverManager.getConnection(url, user, pass);
}
}
```  
## 5️⃣ Modelo:
```
package model;


import java.math.BigDecimal;


public class Division {


private BigDecimal a;
private BigDecimal b;
private BigDecimal resultado;


public Division(BigDecimal a, BigDecimal b, BigDecimal resultado) {
this.a = a;
this.b = b;
this.resultado = resultado;
}


public BigDecimal getA() { return a; }
public BigDecimal getB() { return b; }
public BigDecimal getResultado() { return resultado; }
}
```
## 6️⃣ DAO – Llamada al procedimiento:
```
import db.ConexionOracle;
import model.Division;


import java.sql.CallableStatement;
import java.sql.Connection;


public class DivisionDAO {


public void insertar(Division d) throws Exception {
String sql = "{ call SP_INSERTAR_DIVISION(?,?,?) }";
try (Connection cn = ConexionOracle.getConexion();
CallableStatement cs = cn.prepareCall(sql)) {


cs.setBigDecimal(1, d.getA());
cs.setBigDecimal(2, d.getB());
cs.setBigDecimal(3, d.getResultado());
cs.execute();
}
}
}
```
## 7️⃣ Interfaz gráfica (Swing):
```
package ui;


import dao.DivisionDAO;
import model.Division;


import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class FrmDivision extends JFrame {


private JTextArea area;


public FrmDivision() {
setTitle("División Decimal 0.0000");
setSize(400, 300);
setDefaultCloseOperation(EXIT_ON_CLOSE);


area = new JTextArea();
JButton btn = new JButton("Ejecutar 6 divisiones");


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
```
## 8️⃣ Dependencia Oracle (Maven):
```
<dependency>
<groupId>com.oracle.database.jdbc</groupId>
<artifactId>ojdbc8</artifactId>
<version>19.3.0.0</version>
</dependency>
```
:. . / .

package dao;

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

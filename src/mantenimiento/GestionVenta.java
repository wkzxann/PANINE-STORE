package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.VentaInterface;
import model.Boleta;
import model.DetalleBoleta;
import utils.MySQLConexion;

public class GestionVenta implements VentaInterface{

	@Override
	public String generaNumBoleta() {
		String codigo = "B0000";
		// Plantilla de BD
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "select substring(max(cod_bol),3) from tb_boleta";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				codigo = String.format("B%04d", rs.getInt(1) + 1);
			}
		} catch (Exception e) {
			System.out.println("Error en generar Numero de Boleta " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return codigo;
	}

	@Override
	public int realizarVenta(Boleta b, ArrayList<DetalleBoleta> db) {
		int rs = 0;
		// Plantilla de BD
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		try {
			con = MySQLConexion.getConexion();
			// Desactivamos la confirmacion automatica
			con.setAutoCommit(false);
			
			String sql1 = "insert into tb_boleta values(?,?,?,?,?)";
			pst1 = con.prepareStatement(sql1);
			pst1.setString(1, b.getNumbol());
			pst1.setString(2, b.getFecha());
			pst1.setString(3, b.getCodcliente());
			pst1.setInt(4, b.getCodvendedor());
			pst1.setDouble(5, b.getTotalbol());
			
			rs = pst1.executeUpdate();
			
			String sql2 = "insert into tb_det_boleta values (?,?,?,?,?)";
			String sql3 = "update tb_productos set stock = stock - ? where idprod = ?";
			
			for (DetalleBoleta d : db) {
				
				pst2 = con.prepareStatement(sql2);
				pst2.setString(1, b.getNumbol());
				pst2.setString(2, d.getIdproducto());
				pst2.setInt(3, d.getCantidad());
				pst2.setDouble(4, d.getPrecioventa());
				pst2.setDouble(5, d.getImporte());
				rs += pst2.executeUpdate();
				// Prepara los datos del producto para que sean actualizados
				pst3 = con.prepareStatement(sql3);
				pst3.setInt(1, d.getCantidad());
				pst3.setString(2, d.getIdproducto());
				rs += pst3.executeUpdate();
			}
			// Confirmamos las transacciones, si no hay problemas
			con.commit();
				
		} catch (Exception e) {
			System.out.println("Error en Realizar la Venta " + e.getMessage());
			rs = 0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Error al deshacer: " +e.getMessage());
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: :" + e .getMessage());
			}
		}
		return rs;
	}

}

package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ReporteInterface;
import model.Reportes;
import utils.MySQLConexion;

public class GestionReportes implements ReporteInterface{

	@Override
	public ArrayList<Reportes> listado(String codCliente) {
		ArrayList<Reportes> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "call usp_ReporteXCliente(?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, codCliente);
			
			rs = pst.executeQuery();
			lista = new ArrayList<Reportes>();
			
			while (rs.next()) {
				Reportes r = new Reportes();
				r.setCodbol(rs.getString(1));
				r.setFecha(rs.getString(2));
				r.setNomCliente(rs.getString(3));
				r.setNomVendedor(rs.getString(4));
				r.setImporte(rs.getDouble(5));
				
				lista.add(r);
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en listado de Reportes " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cargar :" + e.getMessage());
			}
		}
		
		return lista;
	}

	@Override
	public ArrayList<Reportes> listadoXFecha(String fecha) {
		ArrayList<Reportes> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
					
			String sql = "call usp_ReporteXFecha(?)";
					
			pst = con.prepareStatement(sql);
			pst.setString(1, fecha);
					
			rs = pst.executeQuery();
			lista = new ArrayList<Reportes>();
					
			while (rs.next()) {
				Reportes r = new Reportes();
				r.setCodbol(rs.getString(1));
				r.setFecha(rs.getString(2));
				r.setNomCliente(rs.getString(3));
				r.setNomVendedor(rs.getString(4));
				r.setImporte(rs.getDouble(5));
						
				lista.add(r);
						
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en listado de Reportes " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cargar :" + e.getMessage());
			}
		}
		return lista;
	}

}

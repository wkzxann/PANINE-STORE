package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.TipoInterface;
import model.TipoPersonal;
import model.TipoProducto;
import utils.MySQLConexion;

public class GestionTipo implements TipoInterface{

	@Override
	public ArrayList<TipoPersonal> listado() {
		ArrayList<TipoPersonal> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_tipos";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			lista  = new ArrayList<TipoPersonal>();
			while (rs.next()) {
				TipoPersonal tp = new TipoPersonal();
				tp.setIdTipoPersonal(rs.getInt(1));
				tp.setDescripcion(rs.getString(2));
				
				lista.add(tp);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en listado de Tipos de Personal: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return lista ;
	}

	@Override
	public ArrayList<TipoProducto> listadoTipoProducto() {
		ArrayList<TipoProducto> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
					
			String sql = "select * from tb_categorias";
			pst = con.prepareStatement(sql);
					
			rs = pst.executeQuery();
					
			lista  = new ArrayList<TipoProducto>();
			while (rs.next()) {
				TipoProducto tp = new TipoProducto();
				tp.setIdtipo(rs.getInt(1));
				tp.setDescripcion(rs.getString(2));
						
				lista.add(tp);
			}
					
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en listado de Tipos de Producto: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return lista;
	}

	

}

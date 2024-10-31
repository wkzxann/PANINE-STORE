package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ProductoInterface;
import model.Producto;
import utils.MySQLConexion;

public class GestionProducto implements ProductoInterface{

	@Override
	public int registrar(Producto p) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_productos values (?,?,?,?,?)";
					
			pst = con.prepareStatement(sql);
			pst.setString(1,  p.getIdProducto());
			pst.setString(2, p.getNombre());
			pst.setInt(3, p.getCategoria());
			pst.setDouble(4, p.getPrecio());
			pst.setInt(5, p.getStock());
					
			rs = pst.executeUpdate();
					
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en registrar Producto: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return rs ;
	}

	@Override
	public int actualizarProducto(Producto p) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
				
		try {
			con = MySQLConexion.getConexion();
			String sql = "update tb_productos set nombre_producto = ?, categoria = ?, precio = ?, stock = ? where idprod = ?";
					
			pst = con.prepareStatement(sql);
			pst.setString(1,  p.getNombre());
			pst.setInt(2, p.getCategoria());
			pst.setDouble(3, p.getPrecio());
			pst.setInt(4, p.getStock());
			pst.setString(5, p.getIdProducto());
					
					
			rs = pst.executeUpdate();
					
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en actualizar Producto: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return rs;
	}

	@Override
	public int eliminarProducto(Producto p) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
				
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from tb_productos where idprod = ?";
					
			pst = con.prepareStatement(sql);
			pst.setString(1,  p.getIdProducto());
					
					
			rs = pst.executeUpdate();
					
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar Producto: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return rs;
	}

	@Override
	public ArrayList<Producto> listado() {
		ArrayList<Producto> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
				
		try {
			con = MySQLConexion.getConexion();
					
			String sql = "select * from tb_productos";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
					
			// Pasamos el contenido del rs a la lista
			lista = new ArrayList<Producto>();
			while (rs.next()) {
				lista.add(new Producto(rs.getString(1),
									   rs.getString(2),
									   rs.getInt(3),
									   rs.getDouble(4),
									   rs.getInt(5)));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el listado de Productos: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Producto> listado(int tipo) {
		ArrayList<Producto> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
					
			String sql = "select * from tb_productos where categoria = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, tipo);
					
			rs = pst.executeQuery();
					
			lista  = new ArrayList<Producto>();
			while (rs.next()) {
				Producto p = new Producto();
				p.setIdProducto(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setCategoria(rs.getInt(3));
				p.setPrecio(rs.getDouble(4));
				p.setStock(rs.getInt(5));
						
				lista.add(p);
			}
					
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en listado de Tipos de Productos: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Producto> listadoXNombre(String nombre) {
		ArrayList<Producto> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "call usp_consulProNom(?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, nombre);
			
			rs = pst.executeQuery();
			
			// Pasamos el contenido del rs a la lista
			lista = new ArrayList<Producto>();
			while (rs.next()) {
				lista.add(new Producto(rs.getString(1),
									   rs.getString(2),
									   rs.getInt(3),
									   rs.getDouble(4),
									   rs.getInt(5)));

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el listado de Productos: " + e.getMessage());
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

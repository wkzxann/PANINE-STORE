package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.UsuarioInterface;
import model.Cliente;
import utils.MySQLConexion;

public class GestionUsuario implements UsuarioInterface{

	@Override
	public int registrar(Cliente u) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_clientes values (?,?,?,?,?,?,?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getIdCliente());
			pst.setString(2,  u.getNombre());
			pst.setString(3, u.getApellido());
			pst.setInt(4, u.getDni());
			pst.setInt(5, u.getTelefono());
			pst.setString(6, u.getDireccion());
			pst.setString(7, u.getFechanac());
			
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en registrar cliente: " + e.getMessage());
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
	public int actualizarUsuario(Cliente u) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "update tb_clientes set nombre = ?, apellido = ?, telefono = ?, fechanac = ? where cod_cliente = ?";
			
			pst = con.prepareStatement(sql);
			pst.setString(1,  u.getNombre());
			pst.setString(2, u.getApellido());
			pst.setInt(3, u.getTelefono());
			pst.setString(4, u.getFechanac());
			pst.setString(5, u.getIdCliente());
			
			
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en actualizar cliente: " + e.getMessage());
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
	public int eliminarUsuario(Cliente u) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from tb_clientes where cod_cliente = ?";
			
			pst = con.prepareStatement(sql);
			pst.setString(1,  u.getIdCliente());
			
			
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar cliente: " + e.getMessage());
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
	public ArrayList<Cliente> listado() {
		ArrayList<Cliente> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_clientes";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			// Pasamos el contenido del rs a la lista
			lista = new ArrayList<Cliente>();
			while (rs.next()) {
				lista.add(new Cliente(rs.getString(1),
									  rs.getString(2),
									  rs.getString(3),
									  rs.getInt(4),
									  rs.getInt(5),
									  rs.getString(6),
									  rs.getString(7)));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el listado de Clientes: " + e.getMessage());
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
	public ArrayList<Cliente> listado(String IdCliente) {
		ArrayList<Cliente> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_clientes where cod_cliente = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, IdCliente);
			
			rs = pst.executeQuery();
			
			// Pasamos el contenido del rs a la lista
			lista = new ArrayList<Cliente>();
			while (rs.next()) {
				lista.add(new Cliente(rs.getString(1),
									  rs.getString(2),
									  rs.getString(3),
									  rs.getInt(4),
									  rs.getInt(5),
									  rs.getString(6),
									  rs.getString(7)));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el listado de Clientes: " + e.getMessage());
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

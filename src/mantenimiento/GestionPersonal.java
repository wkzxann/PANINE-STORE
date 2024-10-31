package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.PersonalInterface;
import model.Personal;
import utils.MySQLConexion;

public class GestionPersonal implements PersonalInterface{

	@Override
	public int registrar(Personal p) {
		int rs = 0;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "insert into tb_personal values (null,?,?,?,?,?,?,?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1,  p.getNombre());
			pst.setString(2, p.getApellido());
			pst.setInt(3, p.getTipoPersonal());
			pst.setInt(4, p.getDni());
			pst.setInt(5, p.getTelefono());
			pst.setString(6, p.getUsuario());
			pst.setString(7, p.getClave());
			
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en registrar Personal: " + e.getMessage());
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
	public int actualizarPersonal(Personal p) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "update tb_personal set nombre = ?, apellido = ?, tipo = ?, telefono = ?, clave = ? where cod_personal = ?";
			
			pst = con.prepareStatement(sql);
			pst.setString(1,  p.getNombre());
			pst.setString(2, p.getApellido());
			pst.setInt(3, p.getTipoPersonal());
			pst.setInt(4, p.getTelefono());
			pst.setString(5, p.getClave());
			pst.setInt(6, p.getIdPersonal());
			
			
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
	public int eliminarPersonal(Personal p) {
		int rs = 0;
		// Plantilla 
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from tb_personal where cod_personal = ?";
			
			pst = con.prepareStatement(sql);
			pst.setInt(1,  p.getIdPersonal());
			
			
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en eliminar Personal: " + e.getMessage());
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
	public Personal validarAcceso(String usuario, String clave) {
		Personal p = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "{call usp_validarAccesoPersonal(?,?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, usuario);
			pst.setString(2, clave);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				p = new Personal();
				p.setIdPersonal(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setApellido(rs.getString(3));
				p.setTipoPersonal(rs.getInt(4));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en validar el acceso: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return p;
	}

	@Override
	public ArrayList<Personal> listado() {
		ArrayList<Personal> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
				
		try {
			con = MySQLConexion.getConexion();
					
			String sql = "select * from tb_personal";
			pst = con.prepareStatement(sql);
					
			rs = pst.executeQuery();
					
			// Pasamos el contenido del rs a la lista
			lista = new ArrayList<Personal>();
			while (rs.next()) {
				lista.add(new Personal(rs.getInt(1),
									   rs.getString(2),
									   rs.getString(3),
									   rs.getInt(4),
									   rs.getInt(5),
									   rs.getInt(6),
									   rs.getString(7),
									   rs.getString(8)));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el listado de los Personales: " + e.getMessage());
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

package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.CategoriaInterface;
import model.Categoria;
import utils.MySQLConexion;

public class GestionCategoria implements CategoriaInterface {

	@Override
	public ArrayList<Categoria> listado() {
		ArrayList<Categoria> lista = null;
		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_categorias";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			lista  = new ArrayList<Categoria>();
			while (rs.next()) {
				Categoria c = new Categoria();
				c.setIdTipo(rs.getInt(1));
				c.setDescripcion(rs.getString(2));
				
				lista.add(c);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en listado de Categoria: " + e.getMessage());
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

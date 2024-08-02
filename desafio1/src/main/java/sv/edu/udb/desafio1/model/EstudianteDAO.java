package sv.edu.udb.desafio1.model;

import sv.edu.udb.desafio1.faces.EstudianteBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO extends AppConnection{

    public void insertarEstudiante(EstudianteBean estudiante) throws SQLException{
        try{
            connect();
            pstmt = conn.prepareStatement("INSERT INTO estudiante (dui, nombre_completo, direccion, email, fecha_nacimiento, telefono, sexo) VALUES (?,?,?,?,?,?,?)");
            pstmt.setString(1, estudiante.getDui());
            pstmt.setString(2, estudiante.getNombreCompleto());
            pstmt.setString(3, estudiante.getDireccion());
            pstmt.setString(4, estudiante.getEmail());
            pstmt.setDate(5, new Date(estudiante.getFechaNacimiento().getTime())); // Usado para convertir java.util.Date a java.sql.Date
            pstmt.setString(6, estudiante.getTelefono());
            pstmt.setBoolean(7, estudiante.getSexo());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EstudianteBean> obtenerEstudiantes() throws SQLException {
        List<EstudianteBean> estudiantes = new ArrayList<>();
        try {
            connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM estudiante");

            while (rs.next()) {
                EstudianteBean estudiante = new EstudianteBean();
                estudiante.setId(rs.getInt("id"));
                estudiante.setDui(rs.getString("dui"));
                estudiante.setNombreCompleto(rs.getString("nombre_completo"));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setEmail(rs.getString("email"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setSexo(rs.getBoolean("sexo"));
                estudiantes.add(estudiante);
            }
        } finally {
            close();
        }
        return estudiantes;
    }

}

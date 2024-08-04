package sv.edu.udb.desafio1.faces;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import sv.edu.udb.desafio1.model.EstudianteDAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class EstudianteBean implements Serializable {

    private int id;
    private String dui;
    private String nombreCompleto;
    private String direccion;
    private String email;
    private Date fechaNacimiento;
    private String telefono;
    private Boolean sexo;
    private List<EstudianteBean> estudiantes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public List<EstudianteBean> getEstudiantes() {
        return estudiantes;
    }

    public void submit() throws SQLException {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        estudianteDAO.insertarEstudiante(this);
        cargarEstudiantes();
        resetForm();
    }

    @PostConstruct
    public void init() {
        try {
            cargarEstudiantes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarEstudiantes() throws SQLException {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        this.estudiantes = estudianteDAO.obtenerEstudiantes();
    }

    private void resetForm() {
        this.id = 0;
        this.dui = "";
        this.nombreCompleto = "";
        this.direccion = "";
        this.email = "";
        this.fechaNacimiento = null;
        this.telefono = "";
        this.sexo = null;
    }

}

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmDistribucionFrecuencias extends JFrame {

    // Método constructor
    // Crea un objeto de la clase JFrame para mostrar la ventana
    public FrmDistribucionFrecuencias() {
        setTitle("Tabla de Distribución de frecuencias");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        // Crea un objeto de la clase JLabel para mostrar el título
        JLabel lblTituloPregunta = new JLabel("Pregunta: ");

        // Posiciona el componente lblTituloPregunta y lo añade al panel
        lblTituloPregunta.setBounds(10, 10, 100, 25);
        add(lblTituloPregunta);

        // Crea un objeto de la clase JTextArea para mostrar el cuerpo de la pregunta
        JTextArea txtPregunta = new JTextArea(
                "¿Cómo considera la calidad de la señal de internet que entra al barrio?");
        txtPregunta.setBounds(110, 10, 310, 50);
        txtPregunta.setEditable(false);
        txtPregunta.setLineWrap(true);
        add(txtPregunta);

        // Crea un objeto de la clase JLabel para mostrarla el cuerpo de la respuesta
        JLabel lblTituloRespuesta = new JLabel("Respuesta: ");
        lblTituloRespuesta.setBounds(10, 65, 100, 25);
        add(lblTituloRespuesta);

        // Crea un objeto de la clase JComboBox para mostrar el combobox de respuestas
        JComboBox cmbRespuesta = new JComboBox();
        cmbRespuesta.setBounds(110, 65, 100, 25);
        add(cmbRespuesta);

        // crea un objeto para las opciones de respuesta
        String[] opcionesRespuesta = { "Excelente", "Buena", "Regular", "Mala" };
        cmbRespuesta.setModel(new DefaultComboBoxModel(opcionesRespuesta));

        // Crea un objeto de la clase JButton para mostrar el botón de agregar y quitar
        JButton btnAgregar = new JButton(">>");
        btnAgregar.setBounds(10, 95, 100, 25);
        add(btnAgregar);

        JButton btnQuitar = new JButton("<<");
        btnQuitar.setBounds(10, 125, 100, 25);
        add(btnQuitar);

        // Crea un objeto de la clase JList para mostrar la lista de respuestas
        JList lstRespuesta = new JList();
        lstRespuesta.setBounds(110, 95, 100, 100);
        add(lstRespuesta);

        // Crea un objeto de la clase JTable para mostrar la tabla de distribución de
        // frecuencias
        JTable tblDistribucion = new JTable();
        JScrollPane spDistribucion = new JScrollPane(tblDistribucion);
        spDistribucion.setBounds(10, 200, 450, 100);
        add(spDistribucion);

        String[] encabezados = { "Variable", "Frecuencia absoluta (f)", "Frecuencia acumulada (F)",
                "Frecuencia relativa (fr)", "Frecuencia porcentual (%f)" };

        tblDistribucion.setModel(new DefaultTableModel(null, encabezados));
    }
}

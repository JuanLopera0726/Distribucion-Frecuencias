
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmDistribucionFrecuencias extends JFrame {

    private JComboBox cmbRespuesta;
    private JList lstRespuesta;
    private String[] opcionesRespuesta = {"Excelente", "Buena", "Regular", "Mala"};
    private JTable tblDistribucion;
    String[] encabezados = {"Variable", "Frecuencia absoluta (f)", "Frecuencia acumulada (F)",
        "Frecuencia relativa (fr)", "Frecuencia porcentual (%f)"};

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
        cmbRespuesta = new JComboBox();
        cmbRespuesta.setBounds(110, 65, 100, 25);
        add(cmbRespuesta);

        // crea un objeto para las opciones de respuesta
        cmbRespuesta.setModel(new DefaultComboBoxModel(opcionesRespuesta));

        // Crea un objeto de la clase JButton para mostrar el botón de agregar y quitar
        JButton btnAgregar = new JButton(">>");
        btnAgregar.setBounds(10, 95, 100, 25);
        add(btnAgregar);

        JButton btnQuitar = new JButton("<<");
        btnQuitar.setBounds(10, 125, 100, 25);
        add(btnQuitar);

        // Crea un objeto de la clase JList para mostrar la lista de respuestas
        lstRespuesta = new JList();
        JScrollPane spRespuestas = new JScrollPane(lstRespuesta);
        spRespuestas.setBounds(130, 95, 100, 100);
        add(spRespuestas);

        // Crea un objeto de la clase JButton para mostrar el botón de calcular
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(10, 200, 100, 25);
        add(btnCalcular);

        // Crea un objeto de la clase JTable para mostrar la tabla de distribución de
        // frecuencias
        tblDistribucion = new JTable();
        JScrollPane spDistribucion = new JScrollPane(tblDistribucion);
        spDistribucion.setBounds(10, 240, 450, 100);
        add(spDistribucion);

        tblDistribucion.setModel(new DefaultTableModel(null, encabezados));

        // Agregar eventos a los botones
        btnAgregar.addActionListener(e -> {
            agregarRespuesta();
        });

        btnQuitar.addActionListener(e -> {
            quitarRespuesta();
        });

        btnCalcular.addActionListener(e -> {
            calcularDistribucion();
        });

    }

    private String[] respuestas = new String[1000];
    private int contadorRespuestas = -1;

    // Métodos de eventos
    private void agregarRespuesta() {
        // Lógica para agregar la respuesta seleccionada al JList
        contadorRespuestas++;
        respuestas[contadorRespuestas] = cmbRespuesta.getSelectedItem().toString();
        mostrarRespuestas();
    }

    private void mostrarRespuestas() {
        String[] respuestasAMostrar = new String[contadorRespuestas + 1];
        for (int i = 0; i <= contadorRespuestas; i++) {
            respuestasAMostrar[i] = respuestas[i];
        }
        lstRespuesta.setListData(respuestasAMostrar);
    }

    private void quitarRespuesta() {
        // Lógica para quitar la respuesta seleccionada del JList
        if (lstRespuesta.getSelectedIndex() >= 0) {
            for (int i = lstRespuesta.getSelectedIndex(); i < contadorRespuestas; i++) {
                respuestas[i] = respuestas[i + 1];
            }
            contadorRespuestas--;
            mostrarRespuestas();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el elemento  eliminar");
        }
    }

    private void calcularDistribucion() {
        // Lógica para calcular la distribución de frecuencias y mostrarla en la JTable
        double[][] tablaFrecuencias = new double[opcionesRespuesta.length][4];

        // *************Calcular frecuencias de la tabla*************
        for (int i = 0; i <= contadorRespuestas; i++) {
            for (int j = 0; j < opcionesRespuesta.length; j++) {
                if (respuestas[i].equals(opcionesRespuesta[j])) {
                    tablaFrecuencias[j][0]++; // Frecuencia absoluta
                    break;
                }
            }
        }


        // Mostrar y terminar de calcular resultados en la tabla 
        String[][] datosTabla = new String[opcionesRespuesta.length][5];
        for (int i = 0; i < opcionesRespuesta.length; i++) {
            
            // Calcular fr acum
            if (i == 0){
                tablaFrecuencias[i][1] = tablaFrecuencias[i][0];
            } else {
                tablaFrecuencias[i][1] = tablaFrecuencias[i][0]+tablaFrecuencias[i - 1][1];
            }

            // Calcular fr relativa
            tablaFrecuencias[i][2] = tablaFrecuencias[i][0] / (contadorRespuestas + 1); 

            // Calcular fr porcentual
            tablaFrecuencias[i][3] = tablaFrecuencias[i][2] * 100;


            // Asignar a matriz de resultados 
            datosTabla[i][0] = opcionesRespuesta[i];
            datosTabla[i][1] = String.valueOf((int) tablaFrecuencias[i][0]); // Frecuencia absoluta
            datosTabla[i][2] = String.valueOf((int) tablaFrecuencias[i][1]); // Frecuencia acumulada 
            datosTabla[i][3] = String.valueOf(tablaFrecuencias[i][2]); // Frecuencia relativa 
            datosTabla[i][4] = String.valueOf(tablaFrecuencias[i][3]); // Frecuencia porcentual 

        }
        DefaultTableModel dtm = new DefaultTableModel(datosTabla, encabezados);
        tblDistribucion.setModel(dtm);

        JOptionPane.showMessageDialog(null, "Distribución calculada");
    }

}

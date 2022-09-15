// Nombre: Lopez Ontiveros Javier Eduardo
// materia: topicos avanzados de programacion
// semestre: 5to
// maestro: Clemente Garcia Gerardo
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class App extends JFrame implements ActionListener
{
    JComboBox Combobox1;
    JButton Btnjugar, primerPar;
    JPanel panel2;
    int cont;
    public static void main(String[] args) throws Exception
    {
        new App();
    }
    public App()
    {
        super("Pares");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        Interfaz();
        Eventos();
    }
    private void Interfaz()
    {
        String[] nivel = { "Seleccione", "Facil", "Medio", "Dificil" };
        setSize(800, 850);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();// panel donde se guarda el combo y el boton
        Combobox1 = new JComboBox(nivel);
        Btnjugar = new JButton("Jugar");
        Combobox1.setBackground(new Color(140, 150, 170));
        panel1.setLayout(new FlowLayout(10, 10, 10));
        panel1.add(Combobox1);
        panel1.add(Btnjugar);
        add(panel1, BorderLayout.NORTH);
        setVisible(true);
    }
    private void Eventos()
    {
        Btnjugar.addActionListener(this);
    }
    @Override public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() != Btnjugar) actionPares(e);// al pulsar una carta manda a este metodo(se ejecuta mas veces)
        else actionCombo();// en caso de que el boton sea el de jugar manda a este metodo(se ejecuta menos)
    }
    private void actionPares(ActionEvent e)
    {
        JButton aux = (JButton) e.getSource();
        aux.setIcon(new ImageIcon("lib/Imagenes/" + aux.getName() + ".jpg"));// al darles click se le pone la imagen que tenga su nombre
        if (cont == 0)// primer click
        {
            primerPar = aux;// se guarda el aux para poder comparalo al segundo click
            cont = 1;
            return;
        }
        if (!primerPar.getName().equals(aux.getName()))// segundo click valida si los 2 pares son diferentes para "voltearlos"
        {
            aux.update(aux.getGraphics());
            primerPar.setIcon(new ImageIcon("lib/Imagenes/41.jpeg"));
            aux.setIcon(new ImageIcon("lib/Imagenes/41.jpeg"));
            try
            {
                Thread.sleep(300);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
        }
        else if (aux == primerPar)// en caso de ser iguales valida que no se haya clickeado seguido el mismo boton
        {
            aux.setIcon(new ImageIcon("lib/Imagenes/41.jpeg"));
        }
        else// al tener igual el Name y al no ser el mismo boton se desabilita el par
        {
            aux.setEnabled(false);
            primerPar.setEnabled(false);
        }
        repaint();
        cont = 0;// hace 0 el contador para volver a escojer otro par
    }
    private void actionCombo()// se valida que elemento tiene selleccionado el combobox
    {
        if (Combobox1.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una dificultad para poder jugar");
            return;
        }
        if (Combobox1.getSelectedIndex() == 1)
        {
            creaPares(4, 450, 800);// manda los parametros adecuados dependiendo el nivel
            return;// numero de pares, altura y ancho del JFrame
        }
        if (Combobox1.getSelectedIndex() == 2)
        {
            creaPares(8, 850, 800);
            return;
        }
        if (Combobox1.getSelectedIndex() == 3)
        {
            creaPares(16, 850, 1600);
            return;
        }
    }
    private void creaPares(int pares, int Altura, int Ancho)
    {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        desordena(numeros, pares);// se crean y validan los pares de la matriz
        setSize(Ancho, Altura);// se ajusta el tamaño dependiendo los pares
        setLocationRelativeTo(null);
        if (panel2 != null) remove(panel2); // si al entrar el panel tiene contenido lo elimina para poner una nueva matriz
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(0, pares == 4 ? pares : pares / 2));// se crea la matriz en caso de ser 4 pares, usa 4 columnas como maximo
        for (int i = 0; i < numeros.size(); i++)// al ser diferente de 4 las columas seran la mitad de los pares
        {
            ImageIcon imagen = new ImageIcon("lib/Imagenes/41.jpeg");// se añade una imagen por default
            JButton b1 = new JButton(imagen);
            b1.setBorder(new MatteBorder(4, 4, 4, 4, Color.white));
            b1.setName((numeros.get(i)) + "");// se le añade a cada boton un indentificador dependiendo de la arraylist
            b1.addActionListener(this); // solo puede estar 2 veces el mismo numero
            panel2.add(b1);
        }
        add(panel2);
        setVisible(true);
    }
    private void desordena(ArrayList<Integer> numeros, int pares)
    {
        for (int i = 0; i < pares * 2; i += 2)
        {
            int numero = (int) ((Math.random() * 40) + 1);
            if (numeros.contains(numero))// se estaran creando numeros hasta tener todos los pares
            {
                i -= 2;
                continue;
            }
            numeros.add(numero);// al ser un arraylist se pueden añadir seguido el numero
            numeros.add(numero);
        }
        for (int i = 0; i < numeros.size() * 2; i++)
        {// se mezclan todos los numeros de manera manual
            int num1 = (int) (Math.random() * numeros.size());
            int num2 = (int) (Math.random() * numeros.size());
            int aux1 = numeros.get(num1);
            int aux2 = numeros.get(num2);
            numeros.set(num1, aux2);
            numeros.set(num2, aux1);
        }
    }
}
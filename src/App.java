// Nombre: Lopez Ontiveros Javier Eduardo
// materia: topicos avanzados de programacion
// semestre: 5to
// maestro: Clemente Garcia Gerardo
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
public class App extends JFrame implements ActionListener
{
    JComboBox Combobox1;
    JButton Btnjugar;
    JPanel panel2;
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
        JPanel panel1 = new JPanel();
        Combobox1 = new JComboBox(nivel);
        Btnjugar = new JButton("Jugar");
        Combobox1.setBackground(new Color(141, 153, 175));
        Combobox1.getComponent(1).setBackground(new Color(77, 200, 255));
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
    @Override public void actionPerformed(ActionEvent e)// en este programa solo se require el evento del boton
    {
        if (Combobox1.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione una dificultad para poder jugar");
            return;
        }
        if (Combobox1.getSelectedIndex() == 1)
        {
            creaPares(4, 450, 800);
            return;
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
        desordena(numeros, pares);
        setSize(Ancho, Altura);
        setLocationRelativeTo(null);
        if (panel2 != null) remove(panel2); // si al entrar el panel tiene contenido lo elimina para poner una nueva matriz
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(0, pares == 4 ? pares : pares / 2));
        for (int i = 0; i < numeros.size(); i++)
        {
            ImageIcon imagen = new ImageIcon("lib/Imagenes/" + numeros.get(i) + ".jpg");
            JButton b1 = new JButton(imagen);
            b1.setBorder(null);
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
            if (numeros.contains(numero))
            {
                i -= 2;
                continue;
            }
            numeros.add(numero);
            numeros.add(numero);
        }
        for (int i = 0; i < numeros.size() * 2; i++)
        {
            int num1 = (int) (Math.random() * numeros.size());
            int num2 = (int) (Math.random() * numeros.size());
            int aux1 = numeros.get(num1);
            int aux2 = numeros.get(num2);
            numeros.set(num1, aux2);
            numeros.set(num2, aux1);
        }
    }
}
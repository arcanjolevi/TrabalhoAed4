package window;

import generic.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Window implements Listener, Speaker {

    private JFrame window;
    private JPanel contentPane;
    private JTextField textPathToFile;
    private JTextField textSubWordToSearch;
    private JPanel panelUp;
    private JLabel labelPathToFile;
    private JButton buttonChooseFile;
    private JButton buttonLoadWordsFile;
    private JButton buttonLoadStopWordsFile;
    private JPanel panelDown;
    private JLabel labelSubWordToSearch;
    private JPanel panelDownButtons;
    private JButton buttonConsult;
    private JButton buttonPrintDictionary;
    private String stringToPrint;
    private ArrayList<Listener> listeners;

    public Window() {
        this.stringToPrint = "";
        this.listeners = new ArrayList<Listener>();
        this.subscribe(listeners);
        this.window = new JFrame();

        this.window.setBackground(Color.LIGHT_GRAY);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setBounds(100, 100, 641, 311);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.window.setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0};
        gbl_contentPane.rowHeights = new int[]{46, 12, 56, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        panelUp = new JPanel();
        panelUp.setBackground(Color.LIGHT_GRAY);
        panelUp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        GridBagConstraints gbc_panelUp = new GridBagConstraints();
        gbc_panelUp.insets = new Insets(0, 0, 5, 0);
        gbc_panelUp.fill = GridBagConstraints.BOTH;
        gbc_panelUp.gridx = 0;
        gbc_panelUp.gridy = 0;
        contentPane.add(panelUp, gbc_panelUp);
        GridBagLayout gbl_panelUp = new GridBagLayout();
        gbl_panelUp.columnWidths = new int[]{5, 0, 0, 0, 5, 0};
        gbl_panelUp.rowHeights = new int[]{5, 0, 0, 0, 5, 0};
        gbl_panelUp.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelUp.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelUp.setLayout(gbl_panelUp);

        labelPathToFile = new JLabel("Caminho do Arquivo");
        GridBagConstraints gbc_labelPathToFile = new GridBagConstraints();
        gbc_labelPathToFile.insets = new Insets(0, 0, 5, 5);
        gbc_labelPathToFile.gridx = 2;
        gbc_labelPathToFile.gridy = 1;
        panelUp.add(labelPathToFile, gbc_labelPathToFile);

        textPathToFile = new JTextField();
        textPathToFile.setFont(new Font("Dialog", Font.PLAIN, 16));
        GridBagConstraints gbc_textPathToFile = new GridBagConstraints();
        gbc_textPathToFile.insets = new Insets(0, 0, 5, 5);
        gbc_textPathToFile.fill = GridBagConstraints.HORIZONTAL;
        gbc_textPathToFile.gridx = 2;
        gbc_textPathToFile.gridy = 2;
        panelUp.add(textPathToFile, gbc_textPathToFile);
        textPathToFile.setColumns(10);

        buttonChooseFile = new JButton("Escolher arquivo");
        GridBagConstraints gbc_buttonChooseFile = new GridBagConstraints();
        gbc_buttonChooseFile.insets = new Insets(0, 0, 5, 5);
        gbc_buttonChooseFile.gridx = 1;
        gbc_buttonChooseFile.gridy = 3;
        buttonChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                selectFile();
            }
        });
        panelUp.add(buttonChooseFile, gbc_buttonChooseFile);

        buttonLoadWordsFile = new JButton("Carregar Arquivo de Palavras");
        GridBagConstraints gbc_buttonLoadWordsFile = new GridBagConstraints();
        gbc_buttonLoadWordsFile.insets = new Insets(0, 0, 5, 5);
        gbc_buttonLoadWordsFile.gridx = 2;
        gbc_buttonLoadWordsFile.gridy = 3;
        buttonLoadWordsFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                readWordsFile();
            }
        });
        panelUp.add(buttonLoadWordsFile, gbc_buttonLoadWordsFile);

        buttonLoadStopWordsFile = new JButton("Carregar Stop Words");
        GridBagConstraints gbc_buttonLoadStopWordsFile = new GridBagConstraints();
        gbc_buttonLoadStopWordsFile.insets = new Insets(0, 0, 5, 5);
        gbc_buttonLoadStopWordsFile.gridx = 3;
        gbc_buttonLoadStopWordsFile.gridy = 3;
        panelUp.add(buttonLoadStopWordsFile, gbc_buttonLoadStopWordsFile);

        panelDown = new JPanel();
        panelDown.setBackground(Color.LIGHT_GRAY);
        panelDown.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        GridBagConstraints gbc_panelDown = new GridBagConstraints();
        gbc_panelDown.fill = GridBagConstraints.BOTH;
        gbc_panelDown.gridx = 0;
        gbc_panelDown.gridy = 2;
        contentPane.add(panelDown, gbc_panelDown);
        GridBagLayout gbl_panelDown = new GridBagLayout();
        gbl_panelDown.columnWidths = new int[]{5, 0, 5, 0};
        gbl_panelDown.rowHeights = new int[]{5, 0, 0, 0, 5, 0};
        gbl_panelDown.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelDown.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        panelDown.setLayout(gbl_panelDown);

        labelSubWordToSearch = new JLabel("Sub-Palavra Para Pesquisar");
        GridBagConstraints gbc_labelSubWordToSearch = new GridBagConstraints();
        gbc_labelSubWordToSearch.insets = new Insets(0, 0, 5, 5);
        gbc_labelSubWordToSearch.gridx = 1;
        gbc_labelSubWordToSearch.gridy = 1;
        panelDown.add(labelSubWordToSearch, gbc_labelSubWordToSearch);

        textSubWordToSearch = new JTextField();
        textSubWordToSearch.setFont(new Font("Dialog", Font.PLAIN, 16));
        GridBagConstraints gbc_textSubWordToSearch = new GridBagConstraints();
        gbc_textSubWordToSearch.insets = new Insets(0, 0, 5, 5);
        gbc_textSubWordToSearch.fill = GridBagConstraints.HORIZONTAL;
        gbc_textSubWordToSearch.gridx = 1;
        gbc_textSubWordToSearch.gridy = 2;
        panelDown.add(textSubWordToSearch, gbc_textSubWordToSearch);
        textSubWordToSearch.setColumns(10);

        panelDownButtons = new JPanel();
        panelDownButtons.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc_panelDownButtons = new GridBagConstraints();
        gbc_panelDownButtons.insets = new Insets(0, 0, 5, 5);
        gbc_panelDownButtons.fill = GridBagConstraints.BOTH;
        gbc_panelDownButtons.gridx = 1;
        gbc_panelDownButtons.gridy = 3;
        panelDown.add(panelDownButtons, gbc_panelDownButtons);
        GridBagLayout gbl_panelDownButtons = new GridBagLayout();
        gbl_panelDownButtons.columnWidths = new int[]{5, 0, 0, 5, 0};
        gbl_panelDownButtons.rowHeights = new int[]{5, 0, 5, 0};
        gbl_panelDownButtons.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelDownButtons.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelDownButtons.setLayout(gbl_panelDownButtons);

        buttonConsult = new JButton("Consultar Semelhante");
        GridBagConstraints gbc_buttonConsult = new GridBagConstraints();
        gbc_buttonConsult.fill = GridBagConstraints.BOTH;
        gbc_buttonConsult.insets = new Insets(0, 0, 5, 5);
        gbc_buttonConsult.gridx = 1;
        gbc_buttonConsult.gridy = 1;
        buttonConsult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String aux = textSubWordToSearch.getText();
                if (aux.isEmpty()) {
                    aux = "NULL";
                }
                speak("consultButtonPressed," + aux);
            }
        });
        panelDownButtons.add(buttonConsult, gbc_buttonConsult);

        buttonPrintDictionary = new JButton("Imprimir Dicionário");
        GridBagConstraints gbc_buttonPrintDictionary = new GridBagConstraints();
        gbc_buttonPrintDictionary.fill = GridBagConstraints.BOTH;
        gbc_buttonPrintDictionary.insets = new Insets(0, 0, 5, 5);
        gbc_buttonPrintDictionary.gridx = 2;
        gbc_buttonPrintDictionary.gridy = 1;
        buttonPrintDictionary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                speak("printButtonPressed,");
            }
        });
        panelDownButtons.add(buttonPrintDictionary, gbc_buttonPrintDictionary);
        this.window.setVisible(true);
    }

    public void readWordsFile() {
        this.speak("buttonReadNewWordsFile," + this.textPathToFile.getText());
    }

    public void successfullyReadFile() {
        JOptionPane.showMessageDialog(null, "Arquivo lido com sucesso !", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorReadingFile() {
        JOptionPane.showMessageDialog(null, "Erro ao ler arquivo !", "Mensagem", JOptionPane.ERROR_MESSAGE);
    }

    public void selectFile() {
        JFileChooser j = new JFileChooser();
        String aux;
        j.showOpenDialog(null);
        try {
            aux = j.getSelectedFile().getAbsoluteFile().toString();
            this.textPathToFile.setText(aux);
            this.buttonLoadWordsFile.setEnabled(true);
            this.buttonLoadStopWordsFile.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Arquivo selecionado com sucesso !", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "Arquivo não selecionado !", "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showOutPutWindow(String windowName, String msgToPrint) {
        JFrame showOutput = new JFrame(windowName);
        showOutput.setVisible(true);
        showOutput.setBackground(Color.LIGHT_GRAY);
        showOutput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showOutput.setBounds(100, 100, 500, 354);
        showOutput.setAlwaysOnTop(true);

        JPanel ContentPane = new JPanel();
        ContentPane.setBackground(Color.LIGHT_GRAY);
        ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        showOutput.setContentPane(ContentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        ContentPane.setLayout(gbl_contentPane);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        ContentPane.add(scrollPane, gbc_scrollPane);

        JTextArea textOutput = new JTextArea();
        textOutput.setEditable(false);
        textOutput.setText(msgToPrint);
        scrollPane.setViewportView(textOutput);
    }

    public void printDerivatedWords() {
        String aux[] = this.stringToPrint.split("\n");
        String derivatedWords = "";
        int i = 0;
        for (String a : aux) {
            if (i == 10) {
                break;
            }
            derivatedWords += (i + 1) + ":" + a + "\n";
            i++;
        }
        this.showOutPutWindow("Palavras derivadas", derivatedWords);
    }

    public void printDictionary() {
        String aux[] = this.stringToPrint.split("\n");
        String derivatedWords = "";
        int i = 0;
        for (String a : aux) {
            derivatedWords += (i + 1) + ":" + a + "\n";
            i++;
        }
        this.showOutPutWindow("Dicionário", derivatedWords);
    }

    /* Method that listen a message from a speaker
     * Input:        Message listen
     * Return:       None
     * Precondition: None
     */
    @Override
    public void listen(String msg) {
        String aux[] = msg.split(",");

        if (aux[0].compareTo("newWord") == 0) {
            if (!this.stringToPrint.contains(aux[1])) {
                this.stringToPrint += aux[1];
            }
        }

        if (aux[0].compareTo("newDerivatedWord") == 0) {
            if (!this.stringToPrint.contains(aux[1])) {
                this.stringToPrint += aux[1];
            }
        }

        if (aux[0].compareTo("dictionaryEnd") == 0) {
            this.printDictionary();
        }

        if (aux[0].compareTo("derivatedWordEnd") == 0) {
            if (aux.length == 2 && aux[1].compareTo("Não existem paralavras similares") == 0) {
                this.showOutPutWindow("Palavras derivadas", "Não existem palavras derivadas para a entrada");
            } else {
                this.printDerivatedWords();
            }
        }

        if (aux[0].compareTo("newWordsFileSuccessfullyRead") == 0) {
            this.successfullyReadFile();
        }
        
        if(aux[0].compareTo("errorOnReadingNewWordsFile") == 0){
            this.errorReadingFile();
        }
    }

    /* Method that subscribe a listener to this class
     * Input:        Listenter to be subscribed
     * Return:       None
     * Precondition: None
     */
    @Override
    public void subscribe(Object listener) throws Exception {
        if (listener instanceof Listener) {
            this.listeners.add((Listener) listener);
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that subscribe a ArrayList of listener to this class
     * Input:        Listenter to be subscribed
     * Return:       None
     * Precondition: None
     */
    public void subscribe(ArrayList<Listener> listener) {
        for (Listener l : listeners) {
            this.listeners.add(l);
        }
    }

    /* Method that unscribe a listener from this class
     * Input:        Listener to be unscribed
     * Return:       None
     * Precondition: None
     */
    @Override
    public void unscribe(Object listener) throws Exception {
        if (listener instanceof Listener) {
            this.listeners.remove((Listener) listener);
        } else {
            throw new Exception("A classe não é um listener");
        }
    }

    /* Method that speak to all connected listeners
     * Input:        Message to speak
     * Return:       None
     * Precondition: None
     */
    @Override
    public void speak(String msg) {
        for (Listener l : this.listeners) {
            l.listen(msg);
        }
    }
}

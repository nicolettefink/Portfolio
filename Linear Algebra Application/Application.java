import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controls the application window and allows to switch
 * between the main panels (home, about, QR factorization,
 * Gauss-Newton, power method, graphs, and animations).
 * By default, assume all input information is deleted after clicking
 * the appropriate calculate button. Likewise, results from
 * those calculations will be deleted when you either run
 * the same operation with a new set of inputs or switch
 * to a different panel.
 *
 * @author Nicolette Fink
 * @version 3.1
 */
public class Application extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private Procedures operator = new Procedures();
    // fonts
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font mainFont = new Font("Times New Roman", Font.PLAIN, 16);
    // QR input fields
    private JTextArea Minput;
    // Power Method input fields
    private JTextArea input;
    private JTextArea guessInput;
    private JTextField toleranceInput;
    private JTextField iterationsInput;
    // Gauss-Newton input fields
    private JTextField field;
    private JTextField aField;
    private JTextField bField;
    private JTextField cField;
    private JTextField nField;
    private JComboBox<String> equationBox;
    private JComboBox<String> qrFactBox;
    // display for animation
    private Display display;
    // points1 sets up the starting points of the
    // letter 'A' which will rotate about the z-axis
    private Location[] points1 = {
        new Location(150,290,"right",10,50),
        new Location(190,150,"right",2,10),
        new Location(190,150,"right",2,10),
        new Location(210,150,"left",2,10),
        new Location(210,150,"left",2,10),
        new Location(250,290,"left",10,50),
        new Location(250,290,"left",10,50),
        new Location(230,290,"left",6,30),
        new Location(230,290,"left",6,30),
        new Location(215,235,"left",3,15),
        new Location(215,235,"left",3,15),
        new Location(185,235,"right",3,15),
        new Location(185,235,"right",3,15),
        new Location(170,290,"right",6,30),
        new Location(210,215,"left",2,10),
        new Location(190,215,"right",2,10),
        new Location(190,215,"right",2,10),
        new Location(195,190,"right",1,5),
        new Location(195,190,"right",1,5),
        new Location(205,190,"left",1,5),
        new Location(205,190,"left",1,5),
        new Location(210,215,"left",2,10),
        new Location(150,290,"right",10,50),
        new Location(170,290,"right",6,30)};
    // points2 sets up the starting points of the
    // letter 'b' which will rotate about the y-axis
    private Location[] points2 = {
        new Location(160,170,"down",12,60),
        new Location(160,290,"up",12,60),
        new Location(160,290,"up",12,60),
        new Location(180,290,"up",12,60),
        new Location(180,290,"up",12,60),
        new Location(180,270,"up",8,40),
        new Location(180,270,"up",8,40),
        new Location(200,290,"up",12,60),
        new Location(200,290,"up",12,60),
        new Location(220,290,"up",12,60),
        new Location(220,290,"up",12,60),
        new Location(240,270,"up",8,40),
        new Location(240,270,"up",8,40),
        new Location(240,250,"up",4,20),
        new Location(240,250,"up",4,20),
        new Location(220,230,"none",0,0),
        new Location(220,230,"none",0,0),
        new Location(200,230,"none",0,0),
        new Location(200,230,"none",0,0),
        new Location(180,250,"up",4,20),
        new Location(180,250,"up",4,20),
        new Location(180,170,"down",12,60),
        new Location(180,170,"down",12,60),
        new Location(160,170,"down",12,60),
        new Location(215,250,"up",4,20),
        new Location(205,250,"up",4,20),
        new Location(205,250,"up",4,20),
        new Location(200,255,"up",5,25),
        new Location(200,255,"up",5,25),
        new Location(200,265,"up",7,35),
        new Location(200,265,"up",7,35),
        new Location(205,270,"up",8,40),
        new Location(205,270,"up",8,40),
        new Location(215,270,"up",8,40),
        new Location(215,270,"up",8,40),
        new Location(220,265,"up",7,35),
        new Location(220,265,"up",7,35),
        new Location(220,255,"up",5,25),
        new Location(220,255,"up",5,25),
        new Location(215,250,"up",4,20)};
    // points3 sets up the starting points of the
    // letter 'C' which will rotate about the x-axis
    private Location[] points3 = {
        new Location(245,210,"none",0,0),
        new Location(245,185,"none",0,0),
        new Location(245,185,"none",0,0),
        new Location(215,155,"none",0,0),
        new Location(215,155,"none",0,0),
        new Location(185,155,"none",0,0),
        new Location(185,155,"none",0,0),
        new Location(155,185,"none",0,0),
        new Location(155,185,"none",0,0),
        new Location(155,275,"none",0,0),
        new Location(155,275,"none",0,0),
        new Location(185,305,"none",0,0),
        new Location(185,305,"none",0,0),
        new Location(215,305,"none",0,0),
        new Location(215,305,"none",0,0),
        new Location(245,275,"none",0,0),
        new Location(245,275,"none",0,0),
        new Location(245,250,"none",0,0),
        new Location(245,210,"none",0,0),
        new Location(225,210,"none",0,0),
        new Location(225,210,"none",0,0),
        new Location(225,195,"none",0,0),
        new Location(225,195,"none",0,0),
        new Location(210,180,"none",0,0),
        new Location(210,180,"none",0,0),
        new Location(190,180,"none",0,0),
        new Location(190,180,"none",0,0),
        new Location(175,195,"none",0,0),
        new Location(175,195,"none",0,0),
        new Location(175,265,"none",0,0),
        new Location(175,265,"none",0,0),
        new Location(190,280,"none",0,0),
        new Location(190,280,"none",0,0),
        new Location(210,280,"none",0,0),
        new Location(210,280,"none",0,0),
        new Location(225,265,"none",0,0),
        new Location(225,265,"none",0,0),
        new Location(225,250,"none",0,0),
        new Location(225,250,"none",0,0),
        new Location(245,250,"none",0,0)};
    
    /**
     * Constructor for the main application window.
     */
    public Application() {
        super("Final Project Application");
        System.out.println("Building window...");
        setLocationRelativeTo(null);
        setSize(800,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        setContentPane(new JPanel());
        resetMainPanel();
        setVisible(true);
        
        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);
    }
    
    /**
     * Sets up the home screen that contains basic instructions
     * about how to navigate the interface.
     */
    private void resetMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        JLabel title = new JLabel("Final Project Application");
        title.setFont(titleFont);
        
        JTextArea information = new JTextArea("This is an application to perform "
            + "the three main tasks involved in the final project for Math 2605."
            + "\n\nThose tasks include implementing a Modified "
            + "Gauss Newton Method (using QR-factorization), \nthe Power Method (including a plot " 
            + " of data collected from running the power method on a large \nnumber of matrices), "
            + "and a simple animation illustrating various transformations."
            + "\n\nAccess the different tasks by clicking the appropriate selection from "
            + "the task menu located on the \nmenu bar. Go back to this screen by clicking "
            + "the home button.");
        information.setLineWrap(true);
        information.setEditable(false);
        information.setFont(mainFont);
        
        mainPanel.add(title);
        mainPanel.add(information);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * Sets up the about screen containing information
     * about the application.
     */
    private void aboutPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6,1));
        JLabel title = new JLabel("About");
        title.setFont(titleFont);
        
        JTextArea about = new JTextArea("Author: Nicolette Fink"
            + "\nVersion: 3.1"
            + "\nBuild Time: 37 hours 42 minutes");
        about.setEditable(false);
        about.setFont(mainFont);
        mainPanel.add(title);
        mainPanel.add(about);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * Sets up the QR Factorization window. On this
     * panel, the user can input a matrix and the
     * QR decomposition will be shown to the right of the
     * input boxes. The user has the option of selecting
     * to calculate the QR using Householder Reflections
     * or Given's Rotations.
     */
    private void QRFactorizationPanel() {
        mainPanel = new JPanel(new GridLayout(3,1));
        JLabel title = new JLabel("QR Factorization");
        title.setFont(titleFont);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton calculate = new JButton("Calculate");
        calculate.setActionCommand("Calculate QR");
        calculate.addActionListener(this);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel matrix = new JLabel("Matrix:");
        matrix.setFont(mainFont);
        Minput = new JTextArea(8,15);
        JScrollPane inputScroll = new JScrollPane(Minput);
        JPanel matrixPanel = new JPanel(new BorderLayout());
        matrixPanel.add(matrix,BorderLayout.NORTH);
        matrixPanel.add(inputScroll,BorderLayout.CENTER);
        inputPanel.add(matrixPanel);
        
        JPanel qrFactPanel = new JPanel(new FlowLayout());
        String[] qrFacts = {"Householder Reflections", "Given's Rotations"};
        qrFactBox = new JComboBox<String>(qrFacts);
        qrFactPanel.add(qrFactBox);
        buttonPanel.add(qrFactPanel);
        buttonPanel.add(calculate);
        
        mainPanel.add(title);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setPreferredSize(new Dimension(700,450));
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * This is the QR Factorization window that will be shown
     * when the user has asked to calculate the QR
     * of the input matrix. It will show the results to the
     * right of the input fields. The input parameters are
     * used in the calculations.
     * @param matrixFile The file containing the input matrix.
     * @param operation Which method to use to calculate QR.
     */
    private void QRFactorizationPanel(File matrixFile, String operation) {
        mainPanel = new JPanel(new GridLayout(3,1));
        JLabel title = new JLabel("QR Factorization");
        title.setFont(titleFont);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton calculate = new JButton("Calculate");
        calculate.setActionCommand("Calculate QR");
        calculate.addActionListener(this);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel matrix = new JLabel("Matrix:");
        matrix.setFont(mainFont);
        Minput = new JTextArea(8,15);
        JScrollPane inputScroll = new JScrollPane(Minput);
        JPanel matrixPanel = new JPanel(new BorderLayout());
        matrixPanel.add(matrix,BorderLayout.NORTH);
        matrixPanel.add(inputScroll,BorderLayout.CENTER);
        inputPanel.add(matrixPanel);
        
        JPanel qrFactPanel = new JPanel(new FlowLayout());
        String[] qrFacts = {"Householder Reflections", "Given's Rotations"};
        qrFactBox = new JComboBox<String>(qrFacts);
        qrFactPanel.add(qrFactBox);
        buttonPanel.add(qrFactPanel);
        buttonPanel.add(calculate);
        
        int mLength = getLineNumber(matrixFile);
        Double[][] _matrix = new Double[mLength][];
        ArrayList<Double[][]> qr = new ArrayList<Double[][]>(2);
        try {
            BufferedReader br = new BufferedReader(new FileReader(matrixFile));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(" ");
                Double[] currentRow = new Double[row.length];
                for (int i = 0; i < row.length; i++) {
                    double next = Double.parseDouble(row[i]);
                    currentRow[i] = next;
                }
                _matrix[counter] = currentRow;
                counter++;
            }
            br.close();
            matrixFile.delete();
            if (operation.equals("Householder Reflections")) {
                qr = operator.qr_fact_househ(_matrix);
            } else if (operation.equals("Given's Rotations")) {
                qr = operator.qr_fact_givens(_matrix);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Could  not read file: "
                + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException ind) {
            JOptionPane.showMessageDialog(this, "Invalid matrix: "
                + "array index out of bounds at " + ind.getMessage());
        }
        
        JPanel resultsPanel = new JPanel();
        if (!qr.isEmpty()) {
            resultsPanel.setLayout(new BorderLayout());
            JTextArea resultsArea = new JTextArea("Q: \n" + operator.toString(qr.get(0))
                + "\n\nR: \n" + operator.toString(qr.get(1)));
            resultsArea.setFont(mainFont);
            resultsArea.setEditable(false);
            JScrollPane resultsScroll = new JScrollPane(resultsArea);
            resultsPanel.add(resultsScroll,BorderLayout.CENTER);
        }
        
        JPanel secondary = new JPanel(new GridLayout(1,2));
        secondary.add(inputPanel);
        if (!qr.isEmpty()) {
            secondary.add(resultsPanel);
        }
        
        mainPanel.add(title);
        mainPanel.add(secondary);
        mainPanel.add(buttonPanel);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setPreferredSize(new Dimension(700,450));
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }

    /**
     * Sets up the window where the user can input the
     * information required to run a modified version
     * of the Gauss-Newton method to approximate the
     * equation of a curve fitting a set of data points.
     */
    private void modifiedGNPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));
        JLabel title = new JLabel("Modified Gauss-Newton Method");
        title.setFont(titleFont);
        JPanel filePathPanel = new JPanel();
        filePathPanel.setLayout(new FlowLayout());
        JLabel instructions = new JLabel("Provide the complete file pathway: ");
        instructions.setFont(mainFont);
        field = new JTextField();
        field.setColumns(40);
        filePathPanel.add(instructions);
        filePathPanel.add(field);
        JPanel initialGuessPanel = new JPanel();
        initialGuessPanel.setLayout(new FlowLayout());
        JLabel guess = new JLabel("Provide an initial guess: ");
        guess.setFont(mainFont);
        JLabel a = new JLabel("a = ");
        aField = new JTextField();
        aField.setColumns(5);
        JLabel b = new JLabel("b = ");
        bField = new JTextField();
        bField.setColumns(5);
        JLabel c = new JLabel("c = ");
        cField = new JTextField();
        cField.setColumns(5);
        initialGuessPanel.add(guess);
        initialGuessPanel.add(a);
        initialGuessPanel.add(aField);
        initialGuessPanel.add(b);
        initialGuessPanel.add(bField);
        initialGuessPanel.add(c);
        initialGuessPanel.add(cField);
        JPanel iterationsPanel = new JPanel();
        iterationsPanel.setLayout(new FlowLayout());
        JLabel iterations = new JLabel("Number of iterations to run: ");
        iterations.setFont(mainFont);
        JLabel n = new JLabel("N = ");
        nField = new JTextField();
        nField.setColumns(5);
        iterationsPanel.add(iterations);
        iterationsPanel.add(n);
        iterationsPanel.add(nField);
        
        JPanel equationPanel = new JPanel(new FlowLayout());
        String[] equations = {"Quadratic", "Exponential", "Logarithmic", "Rational"};
        equationBox = new JComboBox<String>(equations);
        equationPanel.add(equationBox);
        
        JPanel qrFactPanel = new JPanel(new FlowLayout());
        String[] qrFacts = {"Householder Reflections", "Given's Rotations"};
        qrFactBox = new JComboBox<String>(qrFacts);
        qrFactPanel.add(qrFactBox);
        
        JPanel calculatePanel = new JPanel(new FlowLayout());
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        calculatePanel.add(calculateButton);
        
        mainPanel.add(title);
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.setLayout(new FlowLayout());
        secondaryPanel.add(filePathPanel);
        secondaryPanel.add(initialGuessPanel);
        secondaryPanel.add(iterationsPanel);
        secondaryPanel.add(equationPanel);
        secondaryPanel.add(qrFactPanel);
        secondaryPanel.add(calculatePanel);
        mainPanel.add(secondaryPanel);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setPreferredSize(new Dimension(700,450));
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * This is the panel that will show the results of the
     * Gauss-Newton method given the input parameters from
     * the user. 
     * @param filePath The path to the file containing the points.
     * @param aVal The initial value for a.
     * @param bVal The initial value for b.
     * @param cVal The initial value for c.
     * @param nVal The initial value for n.
     * @param operation Quadratic, exponential, logarithmic, or rational.
     * @param qroperation Householder Reflections or Given's Rotations.
     */
    private void modifiedGNPanel(String filePath,String aVal,String bVal,String cVal,
        String nVal, String operation, String qroperation) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));
        JLabel title = new JLabel("Modified Gauss-Newton Method");
        title.setFont(titleFont);
        JPanel filePathPanel = new JPanel();
        filePathPanel.setLayout(new FlowLayout());
        JLabel instructions = new JLabel("Provide the complete file pathway: ");
        instructions.setFont(mainFont);
        field = new JTextField();
        field.setColumns(40);
        filePathPanel.add(instructions);
        filePathPanel.add(field);
        JPanel initialGuessPanel = new JPanel();
        initialGuessPanel.setLayout(new FlowLayout());
        JLabel guess = new JLabel("Provide an initial guess: ");
        guess.setFont(mainFont);
        JLabel a = new JLabel("a = ");
        aField = new JTextField();
        aField.setColumns(5);
        JLabel b = new JLabel("b = ");
        bField = new JTextField();
        bField.setColumns(5);
        JLabel c = new JLabel("c = ");
        cField = new JTextField();
        cField.setColumns(5);
        initialGuessPanel.add(guess);
        initialGuessPanel.add(a);
        initialGuessPanel.add(aField);
        initialGuessPanel.add(b);
        initialGuessPanel.add(bField);
        initialGuessPanel.add(c);
        initialGuessPanel.add(cField);
        JPanel iterationsPanel = new JPanel();
        iterationsPanel.setLayout(new FlowLayout());
        JLabel iterations = new JLabel("Number of iterations to run: ");
        iterations.setFont(mainFont);
        JLabel n = new JLabel("N = ");
        nField = new JTextField();
        nField.setColumns(5);
        iterationsPanel.add(iterations);
        iterationsPanel.add(n);
        iterationsPanel.add(nField);
        
        JPanel equationPanel = new JPanel(new FlowLayout());
        String[] equations = {"Quadratic", "Exponential", "Logarithmic", "Rational"};
        equationBox = new JComboBox<String>(equations);
        equationPanel.add(equationBox);
        
        JPanel qrFactPanel = new JPanel(new FlowLayout());
        String[] qrFacts = {"Householder Reflections", "Given's Rotations"};
        qrFactBox = new JComboBox<String>(qrFacts);
        qrFactPanel.add(qrFactBox);
        
        JPanel calculatePanel = new JPanel(new FlowLayout());
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        calculatePanel.add(calculateButton);
        
        //calculations
        ArrayList<Double> x_coord = new ArrayList<Double>();
        ArrayList<Double> y_coord = new ArrayList<Double>();
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
               String[] coordinates = line.split(",");
               double x = Double.parseDouble(coordinates[0]);
               x_coord.add(x);
               double y = Double.parseDouble(coordinates[1]);
               y_coord.add(y);
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Could  not read file: "
                + e.getMessage());
        }
        
        Double[][] results = new Double[3][];
        if (!x_coord.isEmpty()) {
            Double[][] points = new Double[2][x_coord.size()];
            for (int i = 0; i < x_coord.size(); i++) {
                points[0][i] = x_coord.get(i);
                points[1][i] = y_coord.get(i);
            }
            
            double b1 = Double.parseDouble(aVal);
            double b2 = Double.parseDouble(bVal);
            double b3 = Double.parseDouble(cVal);
            Double[][] initial = {{b1},{b2},{b3}};
            int iter = Integer.parseInt(nVal);
            
            if (operation.equals("Quadratic")) {
                if (qroperation.equals("Householder Reflections")) {
                    results = operator.gn_qua(points,initial,iter,"h");
                } else if (qroperation.equals("Given's Rotations")) {
                    results = operator.gn_qua(points,initial,iter,"g");
                }
            } else if (operation.equals("Exponential")) {
                if (qroperation.equals("Householder Reflections")) {
                    results = operator.gn_exp(points,initial,iter,"h");
                } else if (qroperation.equals("Given's Rotations")) {
                    results = operator.gn_exp(points,initial,iter,"g");
                }
            } else if (operation.equals("Logarithmic")) {
                if (qroperation.equals("Householder Reflections")) {
                    results = operator.gn_log(points,initial,iter,"h");
                } else if (qroperation.equals("Given's Rotations")) {
                    results = operator.gn_log(points,initial,iter,"g");
                }
            } else if (operation.equals("Rational")) {
                if (qroperation.equals("Householder Reflections")) {
                    results = operator.gn_rat(points,initial,iter,"h");
                } else if (qroperation.equals("Given's Rotations")) {
                    results = operator.gn_rat(points,initial,iter,"g");
                }
            }
        }
        
        JPanel resultsPanel = new JPanel();
        if (!x_coord.isEmpty()) {
            resultsPanel.setLayout(new BorderLayout());
            JTextArea resultsArea = new JTextArea("The estimated "
                + "values of a, b, and c are:\na = " + results[0][0]
                + "\nb = " + results[1][0] + "\nc = " + results[2][0]);
            resultsArea.setFont(mainFont);
            resultsPanel.add(resultsArea,BorderLayout.CENTER);
        }
        
        mainPanel.add(title);
        JPanel secondaryPanel = new JPanel();
        secondaryPanel.setLayout(new FlowLayout());
        secondaryPanel.add(filePathPanel);
        secondaryPanel.add(initialGuessPanel);
        secondaryPanel.add(iterationsPanel);
        secondaryPanel.add(equationPanel);
        secondaryPanel.add(qrFactPanel);
        secondaryPanel.add(calculatePanel);
        mainPanel.add(secondaryPanel);
        if (!x_coord.isEmpty()) {
            mainPanel.add(resultsPanel);
        }
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setPreferredSize(new Dimension(700,450));
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * Sets up the Power Method window where the user
     * can input a matrix and calculate the largest
     * eigenvalue and it's corresponding eigenvalue
     * using the Power Method.
     */
    private void PMPanel() {
        mainPanel = new JPanel(new GridLayout(3,1));
        JLabel title = new JLabel("Power Method");
        title.setFont(titleFont);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton calculate = new JButton("Calculate");
        calculate.setActionCommand("Calculate Eigenvalue");
        calculate.addActionListener(this);
        buttonPanel.add(calculate);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel matrix = new JLabel("Matrix:");
        matrix.setFont(mainFont);
        input = new JTextArea(8,15);
        JScrollPane inputScroll = new JScrollPane(input);
        JPanel matrixPanel = new JPanel(new BorderLayout());
        matrixPanel.add(matrix,BorderLayout.NORTH);
        matrixPanel.add(inputScroll,BorderLayout.CENTER);
        inputPanel.add(matrixPanel);
        
        guessInput = new JTextArea(8,4);
        JScrollPane guessInputScroll = new JScrollPane(guessInput);
        JPanel guessPanel = new JPanel(new BorderLayout());
        JLabel guessLabel = new JLabel("Guess:");
        guessPanel.add(guessLabel,BorderLayout.NORTH);
        guessPanel.add(guessInputScroll,BorderLayout.CENTER);
        inputPanel.add(guessPanel);
        
        JPanel otherInfoPanel = new JPanel(new GridLayout(4,1));
        JLabel toleranceLabel = new JLabel("Tolerance Parameter:");
        toleranceInput = new JTextField();
        toleranceInput.setColumns(5);
        JLabel iterationsLabel = new JLabel("Maximum Iterations:");
        iterationsInput = new JTextField();
        iterationsInput.setColumns(5);
        otherInfoPanel.add(toleranceLabel);
        otherInfoPanel.add(toleranceInput);
        otherInfoPanel.add(iterationsLabel);
        otherInfoPanel.add(iterationsInput);
        inputPanel.add(otherInfoPanel);
        
        mainPanel.add(title);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * This is the panel that will show the results of
     * the calculations performed by the power method
     * given the matrix provided by the user.
     * @param m A file containing the input matrix.
     * @param g A file containing the input guess.
     * @param tparam The input tolerance parameter.
     * @param iterate The maximum number of iterations to run.
     */
    private void PMPanel(File m, File g, String tparam, String iterate) {
        mainPanel = new JPanel(new GridLayout(3,1));
        JLabel title = new JLabel("Power Method");
        title.setFont(titleFont);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton calculate = new JButton("Calculate");
        calculate.setActionCommand("Calculate Eigenvalue");
        calculate.addActionListener(this);
        buttonPanel.add(calculate);
        
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel matrix = new JLabel("Matrix:");
        matrix.setFont(mainFont);
        input = new JTextArea(8,15);
        JScrollPane inputScroll = new JScrollPane(input);
        JPanel matrixPanel = new JPanel(new BorderLayout());
        matrixPanel.add(matrix,BorderLayout.NORTH);
        matrixPanel.add(inputScroll,BorderLayout.CENTER);
        inputPanel.add(matrixPanel);
        
        guessInput = new JTextArea(8,4);
        JScrollPane guessInputScroll = new JScrollPane(guessInput);
        JPanel guessPanel = new JPanel(new BorderLayout());
        JLabel guessLabel = new JLabel("Guess:");
        guessPanel.add(guessLabel,BorderLayout.NORTH);
        guessPanel.add(guessInputScroll,BorderLayout.CENTER);
        inputPanel.add(guessPanel);
        
        JPanel otherInfoPanel = new JPanel(new GridLayout(4,1));
        JLabel toleranceLabel = new JLabel("Tolerance Parameter:");
        toleranceInput = new JTextField();
        toleranceInput.setColumns(5);
        JLabel iterationsLabel = new JLabel("Maximum Iterations:");
        iterationsInput = new JTextField();
        iterationsInput.setColumns(5);
        otherInfoPanel.add(toleranceLabel);
        otherInfoPanel.add(toleranceInput);
        otherInfoPanel.add(iterationsLabel);
        otherInfoPanel.add(iterationsInput);
        inputPanel.add(otherInfoPanel);
        
        int mLength = getLineNumber(m);
        int gLength = getLineNumber(g);
        Double[][] _matrix = new Double[mLength][];
        Double[][] _guess = new Double[gLength][1];
        Double[][] results = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(m));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(" ");
                Double[] currentRow = new Double[row.length];
                for (int i = 0; i < row.length; i++) {
                    double next = Double.parseDouble(row[i]);
                    currentRow[i] = next;
                }
                _matrix[counter] = currentRow;
                counter++;
            }
            br.close();
            m.delete();
            br = new BufferedReader(new FileReader(g));
            counter = 0;
            while ((line = br.readLine()) != null) {
                double next = Double.parseDouble(line);
                _guess[counter][0] = next;
                counter++;
            }
            br.close();
            g.delete();
            int it = Integer.parseInt(iterate);
            double param = Double.parseDouble(tparam);
            results = operator.power_method(_matrix,_guess,it,param);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Could  not read file: "
                + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException ind) {
            JOptionPane.showMessageDialog(this, "Invalid Matrix: "
                + "array index out of bounds at " + ind.getMessage());
        }

        JPanel resultsPanel = new JPanel();
        if (results != null) {
            String vec = "\n";
            for (int v = 0; v < results[1].length; v++) {
                vec = vec + results[1][v] + "\n";
            }
            resultsPanel.setLayout(new BorderLayout());
            JTextArea resultsArea = new JTextArea("Estimated eigenvalue: "
                + results[0][0] + "\nCorresponding eigenvector: " + vec
                + "\n\n Number of Iterations: " + (int) Math.ceil(results[2][0]));
            resultsArea.setFont(mainFont);
            resultsArea.setEditable(false);
            JScrollPane resultsScroll = new JScrollPane(resultsArea);
            resultsPanel.add(resultsScroll,BorderLayout.CENTER);
        }
        
        JPanel secondary = new JPanel(new GridLayout(1,2));
        secondary.add(inputPanel);
        if (results != null) {
            secondary.add(resultsPanel);
        }
        
        mainPanel.add(title);
        mainPanel.add(secondary);
        mainPanel.add(buttonPanel);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * Gets the number of lines in a file.
     * @param file The input file.
     * @return The number of lines in the file.
     */
    private int getLineNumber(File file) {
        int counter = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
               counter++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Could not read file: "
                + e.getMessage());
        }
        return counter;
    }
    
    /**
     * Sets up the window where the user can run an
     * operation that will plot data collected by
     * running the power method on a large number
     * of matrices. Two scatter plots will be displayed
     * and these plots are saved as images within the
     * current directory.
     */
    private void GraphPMPanel() {
        mainPanel = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel("Graph Power Method");
        title.setFont(titleFont);
        JTextArea instructions = new JTextArea("This method will generate "
            + "1000 2x2 matrices composed of floating-point real numbers "
            + "uniformly distributed in the \ninterval [-2,2]. For each matrix, "
            + "it will calculate the largest and smallest eigenvalues "
            + "and their associated eigenvectors. \nIf it takes more than 100 "
            + "iterations, the program will stop iterating on that matrix and it's "
            + "eigenvalue will be considered \nnull. Once the calculations are complete "
            + "the data collected will be plotted and these plots will be saved "
            + "as images within \nthe current directory.");
        instructions.setFont(mainFont);
        instructions.setEditable(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton run = new JButton("Run");
        run.addActionListener(this);        
        
        mainPanel.add(title);
        mainPanel.add(instructions);
        buttonPanel.add(run);
        mainPanel.add(buttonPanel);
        mainPanel.setPreferredSize(new Dimension(700,450));
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * Sets up a window where the user can play an
     * animation that shows letters rotating around
     * each coordinate axis.
     */
    private void animationPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
        JPanel infoPanel = new JPanel(new GridLayout(4,1));
        JLabel title = new JLabel("Animation");
        title.setFont(titleFont);
        JTextArea instructions = new JTextArea("Click the play button to "
            + "begin an animation that\nwill show different letters rotating "
            + "around each axis. \n\n\"A\" will rotate twice about the y-axis"
            + "\n\"b\" will rotate five times about the x-axis"
            + "\n\"C\" will rotate three times about the z-axis");
        instructions.setFont(mainFont);
        instructions.setEditable(false);
        infoPanel.add(title);
        infoPanel.add(instructions);
        display = new Display(points1,points2,points3);
        display.setLayout(new BorderLayout());
        display.setBackground(Color.BLACK);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(mainPanel.WIDTH, 40));
        controlPanel.setLayout(new BorderLayout());
        JButton play = new JButton("Play/Pause");
        play.addActionListener(this);
        controlPanel.add(play, BorderLayout.CENTER);
        display.add(controlPanel, BorderLayout.SOUTH);
        mainPanel.add(infoPanel);
        mainPanel.add(display);
        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setVisible(true);
        setContentPane(scroll);
        setVisible(true);
    }
    
    /**
     * Creates the menu that will be at the top of the application
     * frame. From this menu, the user can select which type of
     * calculation or operation to run.
     */
    private JMenuBar createJMenuBar() {
        JMenuItem qrFactorizationMethod = new JMenuItem("QR Factorization");
        qrFactorizationMethod.addActionListener(this);
        JMenuItem modifiedGaussNewtonMethod = new JMenuItem("Modified Gauss Newton Method");
        modifiedGaussNewtonMethod.addActionListener(this);
        JMenuItem powerMethod = new JMenuItem("Power Method");
        powerMethod.addActionListener(this);
        JMenuItem graphPowerMethod = new JMenuItem("Graph Power Method");
        graphPowerMethod.addActionListener(this);
        JMenuItem animation = new JMenuItem("Animation");
        animation.addActionListener(this);
        
        JMenu taskMenu = new JMenu("Task");
        taskMenu.add(qrFactorizationMethod);
        taskMenu.add(modifiedGaussNewtonMethod);
        taskMenu.add(powerMethod);
        taskMenu.add(graphPowerMethod);
        taskMenu.add(animation);
        
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(this);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(homeButton);
        menuBar.add(aboutButton);
        menuBar.add(taskMenu);
        return menuBar;
    }
    
    /**
     * Action listener that controls what happens
     * when each button is pressed by the user.
     */
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button == "QR Factorization") {
            getContentPane().removeAll();
            QRFactorizationPanel();
            setVisible(true);
        } else if (button == "Calculate QR") {
            File ma = null;
            try {
                ma = new File("ma.txt");
                ma.createNewFile();
                FileWriter maWriter = new FileWriter(ma);
                Minput.write(maWriter);
            } catch (IOException io) {
                System.out.println("Could not save file: "
                    + io.getMessage());
            }
            String qrOperation = (String) qrFactBox.getSelectedItem();
            getContentPane().removeAll();
            QRFactorizationPanel(ma,qrOperation);
            setVisible(true);
        } else if (button == "Modified Gauss Newton Method") {
            getContentPane().removeAll();
            modifiedGNPanel();
            setVisible(true);
        } else if (button == "Calculate") {
            String filePath = field.getText();
            String aVal = aField.getText();
            String bVal = bField.getText();
            String cVal = cField.getText();
            String nVal = nField.getText();
            String operation = (String) equationBox.getSelectedItem();
            String qroperation = (String) qrFactBox.getSelectedItem();
            getContentPane().removeAll();
            modifiedGNPanel(filePath,aVal,bVal,cVal,nVal,operation,qroperation);
            setVisible(true);
        } else if (button == "Power Method") {
            getContentPane().removeAll();
            PMPanel();
            setVisible(true);
        } else if (button == "Calculate Eigenvalue") {
            File m = null;
            File g = null;;
            try {
                m = new File("m.txt");
                m.createNewFile();
                FileWriter mWriter = new FileWriter(m);
                input.write(mWriter);
                g = new File("g.txt");
                g.createNewFile();
                FileWriter gWriter = new FileWriter(g);
                guessInput.write(gWriter);
            } catch (IOException ioe) {
                System.out.println("Could not save file: "
                    + ioe.getMessage());
            }
            String tparam = toleranceInput.getText();
            String iterate = iterationsInput.getText();
            getContentPane().removeAll();
            PMPanel(m,g,tparam,iterate);
            setVisible(true);
        } else if (button == "Graph Power Method") {
            getContentPane().removeAll();
            GraphPMPanel();
            setVisible(true);
        } else if (button == "Run") {
            Double[][] data = operator.graph_power_method();
            operator.createPlot(data);
        } else if (button == "Animation") {
            getContentPane().removeAll();
            animationPanel();
            setVisible(true);
        } else if (button == "Play/Pause") {
            if (display.getTimer().isRunning()) {
                display.pauseTimer();
            } else {
                display.startTimer();
            }
            display.updateDisplay();
        } else if (button == "Home") {
            getContentPane().removeAll();
            resetMainPanel();
            setVisible(true);
        } else if (button == "About") {
            getContentPane().removeAll();
            aboutPanel();
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        System.out.println("Loading...");
        System.out.println("Importing...");
        Application app = new Application();
        System.out.println("Loaded successfully.");
        app.setVisible(true);
        app.setResizable(false);
    }
}
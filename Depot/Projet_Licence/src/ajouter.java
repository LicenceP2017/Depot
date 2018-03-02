import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.io.File;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ajouter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private File file;
	
	
	
	
	private void openFile()
	    {      
       JFileChooser fileChooser = new JFileChooser();
  
	       fileChooser.setFileSelectionMode(
	          JFileChooser.FILES_ONLY );
	       int result = fileChooser.showOpenDialog( this );
	  
	       // user clicked Cancel button on dialog
	       if ( result == JFileChooser.CANCEL_OPTION )
          file = null;
       else
       {
    	   System.out.println("You chose to open this file: " +
                   fileChooser.getSelectedFile().getName());
          file = fileChooser.getSelectedFile();
	    }
	    }
	
	


	/**
	 * Create the frame.
	 */
	public ajouter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Intitulé :");
		lblNewLabel.setBounds(253, 65, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Vidéo et images :");
		lblNewLabel_1.setBounds(181, 185, 160, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(399, 59, 200, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton openFile = new JButton( "Parcourir..." );
	      openFile.addActionListener(
	         new ActionListener() {
	            public void actionPerformed( ActionEvent e )
	            {
	               openFile();
	            }
	         }
	      );
		
		

	
		
		contentPane.add( openFile, BorderLayout.NORTH );
        setSize( 962, 565 );	
		
		openFile.setBounds(400, 180, 160, 25);
		contentPane.add(openFile);
		
		JLabel lblCatgorie = new JLabel("Catégorie :");
		lblCatgorie.setBounds(230, 115, 111, 15);
		contentPane.add(lblCatgorie);
		
		JComboBox combo = new JComboBox();
		combo.setBounds(399, 110, 169, 25);
		combo.addItem("test1");
		combo.addItem("test2");
		combo.addItem("test3");
		contentPane.add(combo);


		
		
		JLabel lblCommentaires = new JLabel("Commentaires :");
		lblCommentaires.setBounds(221, 341, 160, 15);
		contentPane.add(lblCommentaires);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(399, 341, 310, 113);
		contentPane.add(textArea);
		
		Button button = new Button("Ajouter");
		button.setFont(new Font("Dialog", Font.BOLD, 14));
		button.setBounds(433, 517, 98, 27);
		contentPane.add(button);
		
		List list = new List();
		list.setBounds(399, 247, 310, 60);
		contentPane.add(list);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, lblNewLabel_1, textField, openFile}));
	}
}

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyProject implements ActionListener {
    JFrame frame;

    JMenuBar menubar;

    JMenu file,edit;

//    file menu items
    JMenuItem newfile,openfile,savefile;

//    edit menu item
    JMenuItem cut,copy,paste,selectall,close;

    JTextArea textarea;
    MyProject(){
//        initialize frmae
        frame = new JFrame();

        textarea = new JTextArea();

//        initialize menubar
        menubar = new JMenuBar();

//        initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

//        initialize menuotems
        newfile = new JMenuItem("New File");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");

        // add actionlistenr to the menuitems
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

//        add to the file menu
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

//        initialize edit menuitem
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // add actionlistenr to the edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);


//        add edit menuitems to the edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

//        add menu to the menubar
        menubar.add(file);
        menubar.add(edit);

//        add menubar to the frame
        frame.setJMenuBar(menubar);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(textarea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);


        frame.add(panel);


        frame.setBounds(300,300,300,300);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textarea.cut();
        }
        if(actionEvent.getSource()==copy){
            textarea.copy();
        }
        if(actionEvent.getSource()==paste){
            textarea.paste();
        }
        if(actionEvent.getSource()==selectall){
            textarea.selectAll();
        }
        if(actionEvent.getSource()==close){
           System.exit(0);
        }
        if(actionEvent.getSource()==openfile){
            JFileChooser filechooser = new JFileChooser("C:");

            int chooseOption = filechooser.showOpenDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = filechooser.getSelectedFile();
                String filePath = file.getPath();

                try{
                    FileReader filereader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(filereader);

                    String Intermediate = "";
                    String output = "";


                    while((Intermediate=bufferedReader.readLine()) != null){
                        output+=Intermediate+"\n";
                    }
                    textarea.setText(output);
                }
                catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==savefile){
            JFileChooser filechooser = new JFileChooser("C:");

            int chooseOption = filechooser.showSaveDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(filechooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    FileWriter filewriter = new FileWriter(file);

                    BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

                    textarea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==newfile){
            MyProject Project = new MyProject();
        }

    }
    public static void main(String[] args) {
    MyProject project=new MyProject();
    }
}

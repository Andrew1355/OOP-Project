package com.individualproject.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class FileProcessor
{
    private File fileHandle;
    private String filename;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public FileProcessor(String filename)
    {
        setFilename(filename);
    }

    // Setters
    void setFilename(String filename)
    {
        this.filename = filename;
    }

    // Getters
    String getFilename()
    {
        return filename;
    }

    // Methods
    public void openFile()
    {
        fileHandle = new File(getFilename());
    }

    public void closeFile()
    {
        fileHandle = null;
    }

    public ArrayList<String[]> readFile()
    {
        openFile();
        ArrayList<String[]> rows = new ArrayList<>();
        try
        {
            try(Scanner scanner = new Scanner(fileHandle))
            {
                while(scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    String[] cells = line.split(",");
    
                    if (cells.length == 5)
                    {
                        rows.add(cells);
                    }
                    else
                    {
                        System.out.println("Skipping invalid row: " + line);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error reading file: " + e);
            e.printStackTrace();
        }
    
        closeFile();
        return rows;
    } // End of readFile()

    public void writeFile(ArrayList<String> lines)
    {
        if (fileWriter == null) {
            getFileWriter(getFilename());
        }
        getPrintWriter(fileWriter);

        for(String line : lines)
        {
            printWriter.append(line + "\n");
            // try
            // {
            //     fileWriter.write(line + "\n");
            // }
            // catch(IOException e)
            // {
            //     e.printStackTrace();
            // }
        }
        closeFileWriter();
        closePrintWriter();
    } // End of writeFile()

    public void getFileWriter(String path)
    {
        try
        {
            fileWriter = new FileWriter(path, true);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    } // End of getFileWriter()

    public void getPrintWriter(FileWriter fileWriter)
    {
        printWriter = new PrintWriter(fileWriter);
    }

    public void closeFileWriter()
    {
        try
        {
            if (fileWriter != null)
            {
                fileWriter.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void closePrintWriter()
    {
        printWriter.close();
    }
}
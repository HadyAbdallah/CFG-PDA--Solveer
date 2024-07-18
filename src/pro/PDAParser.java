package pro;
import java.io.*;
import java.util.*;

public class PDAParser {

	static Stack<Character> stack = new Stack<>();
    public static String P1(String input) {
    	boolean aflag=true;
        stack.clear();
        stack.push('$');
        for (char symbol : input.toCharArray()) {

            if(symbol=='a'&&aflag)
                stack.push(symbol);
            else if(symbol=='b')
            {
            	aflag=false;
                if(stack.peek()=='a')
                {
                    stack.pop();
                }
                else
                {
                    return "not accepted";
                }
             }
            else
                return "not accepted";
        }
        if(stack.peek()=='$')
        {
            stack.clear();
            return "accepted";
        }
        stack.clear();
        return "not accepted";
    }
    public static String P2(String input) {
    	boolean aflag=true;
        stack.clear();
        stack.push('$');
        int bcount=0,acount=0;
        for (char symbol : input.toCharArray())
        {
            if(symbol=='a'&&aflag)
            {
                stack.push(symbol);
            }
            else if(symbol=='b')
            {
            	aflag=false;
                bcount++;
                if(bcount==3 && stack.peek()=='a')
                {
                    stack.pop();
                    if(stack.peek()=='a')
                    {
                        stack.pop();
                        bcount=0;
                    }
                    else
                    {
                        return "not accepted";
                    }
                }
                else if(bcount==3 && stack.peek()!='a')
                {
                    return "not accepted";
                }
            }
            else if(symbol!='b' && bcount>0)
            {
                return "not accepted";
            }

        }
        if(stack.peek()=='$')
        {
            stack.clear();
            return "accepted";
        }
        stack.clear();
        return "not accepted";

    }

    public static String P3(String input) {
        stack.clear();
        stack.push('$');
        for (char symbol : input.toCharArray()) {

            if(symbol=='{')
                stack.push(symbol);
            else if(symbol=='}')
                if(stack.peek()=='{')
                {
                    stack.pop();
                }
                else {
                    return "not accepted";
                }
            else
                continue;

        }
        if(stack.peek()=='$')
        {
            stack.clear();
            return "accepted";
        }
        stack.clear();
        return "not accepted";
    }
    public static String P4(String input) {
    	boolean aflag=true,bflag=true;
        stack.clear();
        stack.push('$');
        for (char symbol : input.toCharArray()) {

            if(symbol=='a'&&aflag)
                stack.push(symbol);
            else if(symbol=='b' &&bflag)
            {
            	aflag=false;
                if(stack.peek()=='a')
                {
                    stack.pop();
                }
                else {
                    return "not accepted";
                }
             }
            else if (symbol=='c')
            {
            	bflag=false;
            	if(stack.peek()=='a')
                {
                    stack.pop();
                }
                else {
                    return "not accepted";
                }
            }
            else
                return "not accepted";

        }
        if(stack.peek()=='$')
        {
            stack.clear();
            return "accepted";
        }
        stack.clear();
        return "not accepted";
    }
    public static void main(String[] args)
    {
        String readfile;
        File file = new File("input_pda.txt");
        try {
            Scanner myReader = new Scanner(file);
            FileWriter myWriter = new FileWriter("output_pda.txt");
            while (myReader.hasNextLine()) {
                readfile=myReader.nextLine();
                if(readfile.equals("1")) {
                    myWriter.write(readfile+"\n");
                    readfile=myReader.nextLine();
                    while(!readfile.equals("end")) {
                        myWriter.write(P1(readfile)+"\n");
                        readfile=myReader.nextLine();
                    }
                    myWriter.write("end\n");
                }
                else if(readfile.equals("2")) {
                    myWriter.write(readfile+"\n");
                    readfile=myReader.nextLine();
                    while(!readfile.equals("end")) {
                        myWriter.write(P2(readfile)+"\n");
                        readfile=myReader.nextLine();
                    }
                    myWriter.write("end\n");
                }
                else if(readfile.equals("3")) {
                    myWriter.write(readfile+"\n");
                    readfile=myReader.nextLine();
                    while(!readfile.equals("end")) {
                        myWriter.write(P3(readfile)+"\n");
                        readfile=myReader.nextLine();
                    }
                    myWriter.write("end\n");
                }
                else if(readfile.equals("4")) {
                    myWriter.write(readfile+"\n");

                    readfile=myReader.nextLine();
                    while(!readfile.equals("end")) {
                        myWriter.write(P4(readfile)+"\n");
                        readfile=myReader.nextLine();
                    }
                    myWriter.write("end\n");
                }
                else if(readfile.equals("end"))
                {
                    break;
                }
            }
            myWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

package pro;
import java.io.*;
import java.util.*;


public class CFGParser {

	 public static boolean CFG1(String input)
	    {
		 	if (input=="") 
	            // Rule: S -> ε
	            return true;
		 	else if ((input.charAt(0) == 'a' && input.charAt(input.length() - 1) == 'b') ||
	                (input.charAt(0) == 'b' && input.charAt(input.length() - 1) == 'a')) {
	            // Rule: S -> aSb | bSa
	            return CFG1(input.substring(1, input.length() - 1));
		 	}
		 	else if(input.length()%2==0) {
		 		boolean flag;
		 		// Rule: S -> SS 
		 		for(int i=2;i<input.length();i++)
		 		{
		 			flag=(CFG1(input.substring(0, i))&&CFG1(input.substring(i)));
		 			if(flag)
		 				return flag;
		 		}
		 	}
		 	return false;
	        
	    }
	    public static boolean CFG2(String input)
	    {
	    	if (input=="") 
	            // Rule: S -> ε
	            return true;
	    	if(input.length()>=3) {
	        	if (input.substring(0,2).equals("aa")&&input.charAt(input.length() - 1) == 'b') 
		        {
		        	// Rule: S -> aaSb
		            return CFG2(input.substring(2,input.length() - 1));
		        }
	        	else if (input.charAt(0) == 'a'&&input.substring(input.length() - 2,input.length()).equals("ab")) 
		        {
		        	// Rule: S -> aSab
		            return CFG2(input.substring(1,input.length() - 2));
		        }
	        	else if (input.substring(input.length() - 3,input.length()).equals("aab")) 
		        {
		        	// Rule: S -> Saab
		            return CFG2(input.substring(0,input.length() - 3));
		        }
	        	else if (input.substring(0,3).equals("aab")) 
		        {
		        	// Rule: S -> aabS
		            return CFG2(input.substring(3,input.length()));
		        }
	        	else if (input.charAt(0) == 'b'&&input.substring(input.length() - 2,input.length()).equals("aa")) 
		        {
		        	// Rule: S -> bSaa
		            return CFG2(input.substring(1,input.length() - 2));
		        }
	        	else if (input.substring(0,2).equals("ba")&&input.charAt(input.length() - 1) == 'a') 
		        {
		        	// Rule: S -> baSa
		            return CFG2(input.substring(2,input.length() - 1));
		        }
	        	else if (input.substring(0,3).equals("baa")) 
		        {
		        	// Rule: S -> baaS
		            return CFG2(input.substring(3,input.length()));
		        }
	        	else if (input.substring(input.length() - 3,input.length()).equals("baa")) 
		        {
		        	// Rule: S -> Sbaa
		            return CFG2(input.substring(0,input.length() - 3));
		        }
	        	else if (input.charAt(0) == 'a'&&input.substring(input.length() - 2,input.length()).equals("ba")) 
		        {
		        	// Rule: S -> aSba
		            return CFG2(input.substring(1,input.length() - 2));
		        }
	        	else if (input.substring(input.length() - 3,input.length()).equals("aba")) 
		        {
		        	// Rule: S -> Saba
		            return CFG2(input.substring(0,input.length() - 3));
		        }
	        	else if (input.substring(0,2).equals("ab")&&input.charAt(input.length() - 1) == 'a') 
		        {
		        	// Rule: S -> abSa
		            return CFG2(input.substring(2,input.length() - 1));
		        }
	        	else if (input.substring(0,3).equals("aba")) 
		        {
		        	// Rule: S -> abaS
		            return CFG2(input.substring(3,input.length()));
		        }
	        	else
	        		return false;
	        	
	        }
			return false;
	    	
	    }
	    public static boolean CFG3(String input) {
	        if (input=="") {
	            // Rule: S -> ε
	            return true;
	        } else if (input.length() == 1) {
	            // Rule: S -> a | b
	            return input.equals("a") || input.equals("b");
	        } else if ((input.charAt(0) == 'a' && input.charAt(input.length() - 1) == 'a') ||
	                (input.charAt(0) == 'b' && input.charAt(input.length() - 1) == 'b')) {
	            // Rule: S -> aSa | bSb
	            return CFG3(input.substring(1, input.length() - 1));
	        } else {
	            return false;
	        }
	    }
	    private static boolean matchS(String input) {
	    	if (input=="") 
	            // Rule: S -> ε
	            return true;
	            
	        if (input.substring(0,3).equals("aaa")) {
	        		// Rule: S -> aaaT
	                return matchT(input.substring(3));
	        }
	        return false;
	    }
	    private static boolean matchT(String input) {
	        if (input=="") {
	        	// Rule: T -> ε
	            return true;
	        }
	        if(input.length()>=3) {
	        	if (input.substring(0,2).equals("aa")&&input.charAt(input.length() - 1) == 'b') 
		        {
		        	// Rule: T -> aaTb
		            return matchT(input.substring(2,input.length() - 1));
		        }
	        }
	        
	        return false;
	    }
	    public static void main(String[] args)
	    {
	        String readfile;
	        File file = new File("input_cfg.txt");
	        try {
	            Scanner myReader= new Scanner(file);
	            FileWriter myWriter = new FileWriter("output_cfg.txt");
	            while (myReader.hasNextLine()) {
	                readfile=myReader.nextLine();
	                if(readfile.equals("1")) {
	                    myWriter.write(readfile+"\n");
	                    readfile=myReader.nextLine();
	                    while(!readfile.equals("end")) {
	                        if(CFG1(readfile))
	                            myWriter.write("accepted"+"\n");
	                        else
	                            myWriter.write("not accepted"+"\n");
	                        readfile=myReader.nextLine();
	                    }
	                    myWriter.write("end\n");
	                }
	                else if(readfile.equals("2")) {
	                    myWriter.write(readfile+"\n");
	                    readfile=myReader.nextLine();
	                    while(!readfile.equals("end")) {
	                        if(CFG2(readfile))
	                            myWriter.write("accepted"+"\n");
	                        else
	                            myWriter.write("not accepted"+"\n");
	                        readfile=myReader.nextLine();
	                    }
	                    myWriter.write("end\n");
	                }
	                else if(readfile.equals("3")) {
	                    myWriter.write(readfile+"\n");
	                    readfile=myReader.nextLine();
	                    while(!readfile.equals("end")) {
	                        if(CFG3(readfile))
	                            myWriter.write("accepted"+"\n");
	                        else
	                            myWriter.write("not accepted"+"\n");
	                        readfile=myReader.nextLine();
	                    }
	                    myWriter.write("end\n");
	                }
	                else if(readfile.equals("4")) {
	                    myWriter.write(readfile+"\n");
	                    readfile=myReader.nextLine();
	                    while(!readfile.equals("end")) {
	                        if(matchS(readfile))
	                            myWriter.write("accepted"+"\n");
	                        else
	                            myWriter.write("not accepted"+"\n");
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

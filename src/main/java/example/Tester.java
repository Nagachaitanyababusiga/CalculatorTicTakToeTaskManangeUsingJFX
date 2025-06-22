package example;

public class Tester {
    public static double calculate(String s){
        s=s.replace("-","---");
        s=s.replace("+", "--");
        String[] Arrs=s.split("--");
        double finalResult=0;
        for(String st:Arrs){
            if(((st.contains("*"))|(st.contains("/")))&(!st.contains("-"))){
                finalResult=finalResult+method1(st); 
            }
            else if((!(st.contains("/")|st.contains("*")))){
                finalResult=finalResult+Double.parseDouble(st);
            }else if(st.contains("-")){
                String st1=st;
                st1= st1.replace("-","");
                finalResult=finalResult+(method1(st1)*-1);
            }
        }
        return finalResult;
    }
    private static double method1(String str){
        str=str.replace("*", "--");
        String[] arr=str.split("--");
        double result=1;

        for(String sty:arr){
            int count=0;
            for(int i=0;i<sty.length();i++){
                if(String.valueOf(sty.charAt(i)).equals("/"))
                count=count+1;
            }
            if(sty.contains("/")){
                if(count==1){
                String[] stt=sty.split("/");
                double num1=Double.parseDouble(stt[0]);
                double num2=Double.parseDouble(stt[1]);
                result=result*num1/num2;   
                }
            else{
                result=result*method2(sty);
            }
            }
            else{
                double num1=Double.parseDouble(String.valueOf(sty));
                result=result*num1;
            }
        }
        return result;  
    }
    private static double method2(String str){
        String[] arrdiv=str.split("/");
        double result=Double.parseDouble(arrdiv[0]);
        for(int i=1;i<arrdiv.length;i++){
            result=result/Double.parseDouble(arrdiv[i]);
        }
        return result;
    }
}
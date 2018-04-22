qpackage com.company;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Test1 {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null){
            StringTokenizer str = new StringTokenizer(line, ", ");
            int contMaxSum = 0, contCurrentSum = 0;
            while(str.hasMoreTokens()){
                String token = str.nextToken();
                int number = Integer.parseInt(token);
                contCurrentSum = contCurrentSum + number;
                if(contCurrentSum > contMaxSum){
                    contMaxSum = contCurrentSum;
                }
                else if(contCurrentSum < 0){
                    contCurrentSum = number;
                }
            }
            System.out.println(contMaxSum);
        }
    }
}

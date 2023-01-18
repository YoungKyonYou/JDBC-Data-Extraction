import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> wblNoList = new ArrayList<>();
    private static List<Integer> idList = new ArrayList<>();

    public static void main(String[] args) {
/**
 * C:/data-extraction/wblNo.txt 파일 넣고 하기!
 */
        getDataFromTxt();
/*        for (int i = 0; i < wblNoList.size(); i++) {
            System.out.println("id:"+idList.get(i)+" wblNo:"+wblNoList.get(i));
        }*/
        executeQuery();

    }

    private static void executeQuery() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/xe?characterEncoding=UTF-8&serverTimezone=UTC",
                    "hr",
                    "hr"
            );
            conn.setAutoCommit(false);
            int id=0;

            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM TRACKING";

            PreparedStatement pstat = null;

            List<String> wblNoDatas = new ArrayList<>();
            for(int i=0;i< wblNoList.size();i++){
                try {
                    pstat = conn.prepareStatement(query);
                    pstat.executeQuery();
                    wblNoDatas.add(wblNoList.get(i));
                }catch(Exception e){

                }
            }
            for (String s : wblNoDatas) {
                System.out.println(s);
            }


//            for(int i=0;i<wblNoList.size();i++){
//                pstat.setInt(1, i+1);
//                pstat.setString(2, wblNoList.get(i));
//                pstat.addBatch();
//
//            }
//            pstat.executeBatch();
            pstat.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //메모장에서 sql 내용 가져오기
    public static void getDataFromTxt() {
        File note = new File("C:/data-extraction/wblNo.txt");
        BufferedReader br = null;
        String dataStr = "";
        try {
            br = new BufferedReader(new FileReader(note));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                line = line;
                dataStr = line;
                String[] dataSplit = dataStr.split("\t");
                idList.add(Integer.parseInt(dataSplit[0]));
                wblNoList.add(dataSplit[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

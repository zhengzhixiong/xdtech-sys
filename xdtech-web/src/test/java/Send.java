

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class Send {
	public static void main(String[] args) throws Exception {
		//account=java4ever&password=zzxlp522&mobile=15859216627&content="+java.net.URLEncoder.encode("我想你了，这是通过短信接口发送。"))
		String PostData = "account=java4ever&password=java4ever&mobile=15859216627&content="+java.net.URLEncoder.encode("您的订单编码：您好，你孩子的本次月考数学成绩99分，班级名次第二。如需帮助请联系客服。","utf-8");
        System.out.println(PostData);
//        String ret = com.dxton.www.Send.SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx");
		System.out.println(SMS(PostData, "http://sms.106jiekou.com/utf8/sms.aspx"));
		
		System.out.println(SMS("account=java4ever&password=zzxlp522","http://www.dxton.com/webservice/sms.asmx/GetNum"));
	}

    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
}

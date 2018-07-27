import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FcmUtil {


	private static final Logger LOGGER = LoggerFactory.getLogger(FcmUtil.class);

	/**
	 * 알람을 보낸다. 
	 *
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public static String sendFcm() throws Exception {
		
//        List<MobileTokenVO> tokenList = fcmService.loadFCMInfoList(vo); 
        
//        String token = "fIHx_MwxZpc:APA91bGQVEMvEUubT1jT-SxU6QG1d4RCPHSL8zmgE3X-DESZxSYDzcxyULrV_i-KXv2ZMfl5lOlHHqynLnKI4bARJox6jMAjN5BexFMgwbf6vx6V9EWVfubMgj7qhV4R83SutwxfUXH77_aIw6tMQkPwq2veom9Cng";
        String token = "eRvBceWEQ9k:APA91bHMb1Ux1uUBGpleQILDOaJsFtG3nJkqfJj-V-Kfx9ou_ILB6T2zju1vctzrHet_ZLZtULKpboLJ1dxV_Bo76y0m7Ew6nM69sykvPfxX5boPSqW6ukl9chM_tnEE7ZOV-I0IWaVkMPqwBvhQ6hvKzt1XH3u_gg";
        
        final String apiKey = "AAAAfVESkrc:APA91bHrZKnpcDJIWy72mtIPo3SsDdfkfScg7ZROzg3zvUb2Cqb3i3t1iPILF0GrM92Zi3a7LIuOXXcDlKE_4-fLJGqLhNOOoYPtkI5GgjVKAbL4i_LXwKln6hZuab_eKnWwgcekP6KMJO4ttcPJOW-wGaZ_F-Ympg";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);
        
//        String userId =(String) request.getSession().getAttribute("ssUserId");

        // 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
//        String input = "{\"notification\" : {\"title\" : \"알림 \", \"body\" : \"내용\"}, \"to\":\"/topics/ALL\"}";
        
        // 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다  위에 둘중에 한개 골라서 날려주자
        String input = "{\"notification\" : {\"title\" : \" 제모오오오옥 \", \"body\" : \"내요오오오오오옹\"}, \"to\":\""+token+"\"}";

        OutputStream os = conn.getOutputStream();
        
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println(response.toString());

		return response.toString();  
	}
	
	public static void main(String[] args) throws Exception {
		
		sendFcm();
		
		//토큰
		//fIHx_MwxZpc:APA91bGQVEMvEUubT1jT-SxU6QG1d4RCPHSL8zmgE3X-DESZxSYDzcxyULrV_i-KXv2ZMfl5lOlHHqynLnKI4bARJox6jMAjN5BexFMgwbf6vx6V9EWVfubMgj7qhV4R83SutwxfUXH77_aIw6tMQkPwq2veom9Cng
	}
	

}

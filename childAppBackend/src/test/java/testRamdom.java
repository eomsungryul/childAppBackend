import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
/**
 * 코드 생성기는 데이터 테이블 이름을 기반으로 해당 모델, 매퍼, 서비스, 컨트롤러 간소화 된 개발을 생성합니다.
 */
public class testRamdom {
	    
	    public static void main(String[] args) throws IOException { 
	    	
	    	String qrcodePath = "C:\\childQR\\";
	    	String uuid = "awdawd";

			try {
				
				byte[] imageInByte;
		        
				BufferedImage originalImage = ImageIO.read(new File(qrcodePath+uuid+".png"));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(originalImage, "png", baos);
				baos.flush();
				 
				imageInByte = baos.toByteArray();
				 
				baos.close();
				StringBuffer ss = new StringBuffer();
				for(int i =0; i<imageInByte.length;i++) {
					ss.append(imageInByte[i]);
				}
				
	            String res = imageInByte.toString();
	            System.out.println("res"+res);
	            System.out.println("ss"+ss);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	
//	    	try {
//	            File file = null;
//	            // 큐알이미지를 저장할 디렉토리 지정
//	            file = new File(qrcodePath);
//	            if(!file.exists()) {
//	                file.mkdirs();
//	            }
//	            // 코드인식시 링크걸 URL주소
//	            // 큐알코드 바코드 생상값
//	            int qrcodeColor =   0xFF2e4e96;
//	            // 큐알코드 배경색상값
//	            int backgroundColor = 0xFFFFFFFF;
//	             
//	            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//	            // 3,4번째 parameter값 : width/height값 지정
//	            BitMatrix bitMatrix = qrCodeWriter.encode(uuid, BarcodeFormat.QR_CODE,200, 200);
//	            //
//	            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
//	            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
//	            // ImageIO를 사용한 바코드 파일쓰기
//
//	            File resFile = null;
//	            resFile = new File(qrcodePath+uuid+".png");
//	            
//	            
//	            ImageIO.write(bufferedImage, "png", new File(qrcodePath+uuid+".png"));
//
//		        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
//
//		        // write it to byte array in-memory (jpg format)
//		        ByteArrayOutputStream b = new ByteArrayOutputStream();
//		        ImageIO.write(image, "png", b);
//
//		        // do whatever with the array...
//		        byte[] jpgByteArray = b.toByteArray();
//
//		        // convert it to a String with 0s and 1s        
//		        StringBuilder sb = new StringBuilder();
//		        for (byte by : jpgByteArray)
//		            sb.append(Integer.toBinaryString(by & 0xFF));
//		        System.out.println(sb.toString());
//	            
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }  
	    	
	    }
    

}

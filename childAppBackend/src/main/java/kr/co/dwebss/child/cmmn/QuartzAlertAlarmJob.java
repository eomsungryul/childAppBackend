package kr.co.dwebss.child.cmmn;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.dwebss.child.model.Child;
import kr.co.dwebss.child.model.EventCheck;
import kr.co.dwebss.child.model.User;
import kr.co.dwebss.child.service.ChildService;
import kr.co.dwebss.child.service.ClassDailyEventService;
import kr.co.dwebss.child.service.EventCheckService;
import kr.co.dwebss.child.service.UserService;

@Component
public class QuartzAlertAlarmJob {

//    @Resource
//    private UserService userService;
//
//    @Resource
//    private EventCheckService eventCheckService;
//
//	@Resource
//	private ChildService childService;
//
//	@Resource
//	private ClassDailyEventService classDailyEventService;
//
//    @Value("${centerTitleMsg}") 
//    String centerTitleMsg;
//    @Value("${centerBodyMsg}") 
//    String centerBodyMsg;
//    
//    @Value("${tearcherTitleMsg}") 
//    String tearcherTitleMsg;
//    @Value("${tearcherBodyMsg}") 
//    String tearcherBodyMsg;
//    
//    @Value("${parentTitleMsg}") 
//    String parentTitleMsg;
//    @Value("${parentBodyMsg}") 
//    String parentBodyMsg;
//    
//    
//    @Resource
//    private FcmUtil fcmUtil;
    
//	@Scheduled(cron = "0 * * * * *")
//	public void alertAlarmSendService() throws Exception{
//		//1. 금일 도착시간이 null event_check 테이블 조회하고, 금일 클래스 이벤트에 등록 된 알람 시간을 기준으로   (현재시간-event_check출발시간) 이 큰 child_id들을 가져온다.
//		List<EventCheck> childList =  eventCheckService.selectAlertAlarmChildList();
//		String userTokenId = "";
//		String title = "";
//		String body = "";
//
////		String location = "";
//		String childNm = "";
//		Integer alertTime = 0;
//		
//		
//		for(int i = 0; i<childList.size(); i++) {
//			childList.get(i).getChildId();
//			// 2. 어린이의 비상연락망( 부모님, 어린이집원장님,어린이집선생님)의 리스트를 가져온다. selectAlarmUserList
//			List<User> userList = userService.selectAlarmUserList(childList.get(i).getChildId());
//
//			Child childParam = new Child();
//			childParam.setChildId(childList.get(i).getChildId());
//			//어린이 이름 가져오기
//			List<Child> child =  childService.selectChild(childParam);
//			childNm = child.get(0).getChildNm();
//			alertTime = childList.get(i).getEventAlarmStartT();
//			
//			// 1. 이벤트의 위치 정보 가져오기 
////			location = classDailyEventService.selectEventLocation(childList.get(i).getClassDailyEventId());
//			
//			//3. 비상연락망들의 usrRoleCd에 따라 알람 메시지를 변경한다. 
//			for(int j = 0; j<userList.size(); j++) {
//				int roldCd = userList.get(j).getUserRoleCd();
//				userTokenId = userList.get(j).getPushToken();
//				
//				if(userList.get(j).getPushToken()!=null||userList.get(j).getPushToken()!="") {
//
//					if(roldCd==100002) { //원장님
//						title = "[긴급]";
//						body = childNm+" 어린이가  출발한지 "+ alertTime+"분이 지나도록 도착하지 않고 있습니다. 확인이 필요합니다. ";
//					}else if(roldCd==100003){ //선생님
//						title = "[긴급]";
//						body = childNm+" 어린이가  출발한지 "+ alertTime+"분이 지나도록 도착하지 않고 있습니다. 확인이 필요합니다. ";
//					}else {//부모님
//						title = "[긴급]";
//						body = childNm+" 어린이가  출발한지 "+ alertTime+"분이 지나도록 도착하지 않고 있습니다. 확인이 필요합니다. ";
//					}
//					//[긴급]누구누구 어린이가 출발한지 몇분이 지나도록 도착하지 않고 있습니다. 확인이 필요합니다. 
//
//					//4. 파이어 베이스에 알람을 보내는 url을 보낸다.
//					fcmUtil.sendFcm(userTokenId, title, body);
//				}
//
//			}
//		}
//		//TODO 현재 쿼리를 2개를 이용하여 알람 을 보낸다. 1개로 하는 방법이 있다면 더 좋을듯
//	}
	
//	@Scheduled(fixedRate=10000)
//	public void test() throws Exception{
//		String userTokenId = "cLnAR68gnT4:APA91bGobuvRo67x9TaglkwLXwkvz0XnQbpmi3On__xkVhhPLooZbzqvvyBMqbBJrD_35KJpotu7p9JAQ6GYgaFuKN88RD9B8h8O8gDk74NZzdsPn9F75LIs40M_Ohm_OHX9xkbf2FMWpVRZKkdUE09xbyq2uJpSLA";
//		String title = "ㅎㅇㅎㅇㅎ";
//		String body = "ㅂㅇㅂㅇ";
//		fcmUtil.sendFcm(userTokenId, title, body);
//		System.out.println("보냄");
//	}
	 

	

}

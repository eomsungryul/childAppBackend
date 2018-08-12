package kr.co.dwebss.child.web;

import kr.co.dwebss.child.cmmn.FcmUtil;
import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.Child;
import kr.co.dwebss.child.model.ClassDailyEvent;
import kr.co.dwebss.child.model.ClassVO;
import kr.co.dwebss.child.model.EventCheck;
import kr.co.dwebss.child.model.User;
import kr.co.dwebss.child.service.ChildService;
import kr.co.dwebss.child.service.ClassDailyEventService;
import kr.co.dwebss.child.service.ClassService;
import kr.co.dwebss.child.service.EventCheckService;
import kr.co.dwebss.child.service.UserService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Created by 엄성렬 on 2018/07/22.
 */
@RestController
@RequestMapping("/child")
public class ChildController {

	@Resource
	private ChildService childService;

	@Resource
	private ClassService classService;

	@Resource
	private ClassDailyEventService classDailyEventService;

	@Resource
	private EventCheckService eventCheckService;
	
	@Resource
	private UserService userService;

    @Value("${systemVersion}") 
    String systemVersion;
    
    @Value("${dummyPhone}") 
    String dummyPhone;
    
    @Value("${dummyCenterId}") 
    String dummyCenterId;
    
    @Value("${dummyTeacherUserId}") 
    String dummyTeacherUserId;


    @Value("${centerTitleMsg}") 
    String centerTitleMsg;
    @Value("${centerBodyMsg}") 
    String centerBodyMsg;
    
    @Value("${tearcherTitleMsg}") 
    String tearcherTitleMsg;
    @Value("${tearcherBodyMsg}") 
    String tearcherBodyMsg;
    
    @Value("${parentTitleMsg}") 
    String parentTitleMsg;
    @Value("${parentBodyMsg}") 
    String parentBodyMsg;
    
    @Resource
    private FcmUtil fcmUtil;
	
	
    //qr코드 찍을 시 
	@PostMapping("/event_check")
	public Result add(@RequestBody EventCheck eventCheck) throws Exception {
		if(systemVersion.equals("dev")) {
		}else {
			
			String status = "";
			
			List<EventCheck> eventList= eventCheckService.selectEventCheck(eventCheck);
			if(eventList.size()>0) {
				eventCheck.setEventCheckId(eventList.get(0).getEventCheckId());
				eventCheckService.update(eventCheck);
				status= "도착";
			}else {
				eventCheckService.save(eventCheck);
				status= "출발";
			}
			// 이벤트 체크 테이블을 저장 하고  알람 보내기
			String userTokenId = "";
			String title = "";
			String body = "";
			
			String location = "";
			String childNm = "";
			
			Child childParam = new Child();
			childParam.setChildId(eventCheck.getChildId());
			//어린이 이름 가져오기
			List<Child> child =  childService.selectChild(childParam);
			childNm = child.get(0).getChildNm();
			// 1. 이벤트의 위치 정보 가져오기 

			location = classDailyEventService.selectEventLocation(eventCheck.getClassDailyEventId());
			
			
			// 2. 어린이의 비상연락망( 부모님, 어린이집원장님,어린이집선생님)의 리스트를 가져온다. selectAlarmUserList
			List<User> userList = userService.selectParentList(eventCheck.getChildId());
			//3. 비상연락망들의 usrRoleCd에 따라 알람 메시지를 변경한다. 
			for(int j = 0; j<userList.size(); j++) {
				int roldCd = userList.get(j).getUserRoleCd();
				userTokenId = userList.get(j).getPushToken();
				
				
				if(roldCd==100004) { 
					title = parentTitleMsg;
					body = childNm+" 어린이가 " +location+" (으)로"+ status+parentBodyMsg;

					//4. 파이어 베이스에 알람을 보내는 url을 보낸다.
					fcmUtil.sendFcm(userTokenId, title, body);
					
//					System.out.println("title : "+title);
//					System.out.println("body : "+body);
//					System.out.println("userTokenId : "+userTokenId);
				}

			}
			
		}
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		childService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody Child child) {
		childService.update(child);
		return ResultGenerator.genSuccessResult();
	}

	@GetMapping("/{id}")
	public Result detail(@PathVariable Integer id) {
		Child child = childService.findById(id);
		return ResultGenerator.genSuccessResult(child);
	}

	@GetMapping
	public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<Child> list = childService.findAll();
		PageInfo pageInfo = new PageInfo(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@GetMapping("/parent/{phone}/{date}")
	public Result getMyChildsCheckEventListWithDate(@PathVariable String phone,
	 @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
		EventCheck param = new EventCheck();
		param.setParentPhone(phone);
//		if(systemVersion.equals("dev")) {
//			param.setParentPhone(dummyPhone);
//		}
		
		List<EventCheck> classList = eventCheckService.selectEventCheckListForParent(param);
		

		if(classList.size()==0) {
			param.setParentPhone(dummyPhone);
			classList = eventCheckService.selectEventCheckListForParent(param);
		}
		
		
		JSONArray classArr = getMainCheckEventList(classList);
		
		return ResultGenerator.genSuccessResult(classArr);
	}
	
	@GetMapping(value={"/parent/{phone}"})
	public Result getMyChildsCheckEventList(@PathVariable String phone) {
		EventCheck param = new EventCheck();
		param.setParentPhone(phone);
//		if(systemVersion.equals("dev")) {
//			param.setParentPhone(dummyPhone);
//		}
		
		List<EventCheck> classList = eventCheckService.selectEventCheckListForParent(param);

		if(classList.size()==0) {
			param.setParentPhone(dummyPhone);
			classList = eventCheckService.selectEventCheckListForParent(param);
		}
		
		JSONArray classArr = getMainCheckEventList(classList);
		
		return ResultGenerator.genSuccessResult(classArr);
	}
	
	@GetMapping("/teacher/{userId}/{date}")
	public Result teacherMainWithDate(@PathVariable Integer userId,
			@PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
		EventCheck param = new EventCheck();
		param.setTeacherUserId(userId);

		if(systemVersion.equals("dev")) {
			param.setTeacherUserId(Integer.parseInt(dummyTeacherUserId));
		}
		
		List<EventCheck> classList = eventCheckService.selectEventCheckListForTeacher(param);
		JSONArray classArr = getMainCheckEventList(classList);
		
		return ResultGenerator.genSuccessResult(classArr);
	}
	
	@GetMapping(value={"/teacher/{userId}"})
	public Result teacherMain(@PathVariable Integer userId) {
		EventCheck param = new EventCheck();
		param.setTeacherUserId(userId);
		if(systemVersion.equals("dev")) {
			param.setTeacherUserId(Integer.parseInt(dummyTeacherUserId));
		}
		
		List<EventCheck> classList = eventCheckService.selectEventCheckListForTeacher(param);
		JSONArray classArr = getMainCheckEventList(classList);
		
		return ResultGenerator.genSuccessResult(classArr);
	}
	
	
	@GetMapping("/center/{centerId}/{date}")
	public Result centerMainWithDate(@PathVariable Integer centerId,
			@PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
		EventCheck param = new EventCheck();
		param.setCenterId(centerId);
		if(systemVersion.equals("dev")) {
			param.setCenterId(Integer.parseInt(dummyCenterId));
		}
		
		List<EventCheck> classList = eventCheckService.selectEventCheckListForCenter(param);
		JSONArray classArr = getMainCheckEventList(classList);
		
		return ResultGenerator.genSuccessResult(classArr);
	}
	
	@GetMapping(value={"/center/{centerId}"})
	public Result centerMain(@PathVariable Integer centerId) {
		EventCheck param = new EventCheck();
		param.setCenterId(centerId);
		if(systemVersion.equals("dev")) {
			param.setCenterId(Integer.parseInt(dummyCenterId));
		}
		
		List<EventCheck> classList = eventCheckService.selectEventCheckListForCenter(param);
		JSONArray classArr = getMainCheckEventList(classList);
		
		return ResultGenerator.genSuccessResult(classArr);
	}
	
	
	
//	@GetMapping("/parent/{phone}")
	// public Result getMyChildsCheckEventList(@PathVariable String phone,
	// @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
	public Result getMyChildsCheckEventListBk(@PathVariable String phone) {
		EventCheck param = new EventCheck();
		param.setParentPhone(phone);
		List<EventCheck> classList = eventCheckService.selectEventCheckListForParent(param);
		
		int beforeClassid = 0;
		int beforeDailyEventid = 0;
		String beforeDailyEventidCd = new String();
		
		JSONArray classArr = new JSONArray();
		JSONArray classDailyEventArr = new JSONArray();
		JSONArray eventChkArr = new JSONArray();
		
		JSONObject classObj = new JSONObject();
		JSONObject classDailyEventObj = new JSONObject();
		
		for (int i = 0; i < classList.size(); i++) {
			EventCheck eventCheck = classList.get(i);
			
			if (eventCheck.getClassId() != beforeClassid) {
				if (i != 0) {
					classArr.add(classObj);
					classObj = new JSONObject();
				}
				classObj.put("classId", eventCheck.getClassId());
				classObj.put("classNm", eventCheck.getClassNm());
				classObj.put("centerId", eventCheck.getCenterId());
				classObj.put("teacherUserId", eventCheck.getTeacherUserId());
				classObj.put("teacherNm", eventCheck.getTeacherNm());
			}
			beforeClassid = eventCheck.getClassId();
			
			// 하나의 데일리 이벤트 오브젝트는 출발, 도착 두개의 이벤트 체크 배열을 갖는다
			//
			
			if(eventCheck.getClassDailyEventId() == 968) {
				System.out.println("");
			}
			
			if (!(Integer.toString(eventCheck.getClassDailyEventId()) + Integer.toString(eventCheck.getEventCd()))
					.equals(beforeDailyEventidCd)) {
				if (beforeDailyEventid != eventCheck.getClassDailyEventId()) {
					if (i != 0) {
						// 이벤트 체크 배열을 dailyEventObj에 담아야 한다.
						classDailyEventObj.put("eventCheck", eventChkArr);
						eventChkArr = new JSONArray();
						classDailyEventArr.add(classDailyEventObj);
						
						classObj.put("classDailyEvent", classDailyEventArr);
						classDailyEventArr = new JSONArray();
					}
				}else {
					if (i != 0) {
						// 이벤트 체크 배열을 dailyEventObj에 담아야 한다.
						classDailyEventObj.put("eventCheck", eventChkArr);
						eventChkArr = new JSONArray();
						classDailyEventArr.add(classDailyEventObj);
					}
				}
				
				classDailyEventObj = new JSONObject();
				classDailyEventObj.put("classDailyEventId", eventCheck.getClassDailyEventId());
				classDailyEventObj.put("eventDate", eventCheck.getEventDate());
				classDailyEventObj.put("eventOrder", eventCheck.getEventOrder());
				classDailyEventObj.put("classId", eventCheck.getClassId());
				classDailyEventObj.put("destinyNm", eventCheck.getDestinyNm());
				classDailyEventObj.put("eventAlarmEndT", eventCheck.getEventAlarmEndT());
				classDailyEventObj.put("eventAlarmStartT", eventCheck.getEventAlarmStartT());
				classDailyEventObj.put("eventCarNeedYn", eventCheck.getEventCarNeedYn());
				classDailyEventObj.put("eventCd", eventCheck.getEventCd());
				
			}
			beforeDailyEventid = eventCheck.getClassDailyEventId();
			beforeDailyEventidCd = Integer.toString(eventCheck.getClassDailyEventId())
					+ Integer.toString(eventCheck.getEventCd());
			
			JSONObject eventChkObj = new JSONObject();
			eventChkObj.put("eventCheckId", eventCheck.getEventCheckId());
			eventChkObj.put("eventCheckDt", eventCheck.getEventCheckDt());
			eventChkObj.put("childId", eventCheck.getChildId());
			eventChkObj.put("classDailyEventId", eventCheck.getClassDailyEventId());
			eventChkObj.put("childNm", eventCheck.getChildNm());
			eventChkObj.put("checkerUserId", eventCheck.getCheckerUserId());
			eventChkArr.add(eventChkObj);
			
			if (i == classList.size() - 1) {
				classDailyEventObj.put("eventCheck", eventChkArr);
				classDailyEventArr.add(classDailyEventObj);
				classObj.put("classDailyEvent", classDailyEventArr);
				classArr.add(classObj);
			}
		}
		
		return ResultGenerator.genSuccessResult(classArr);
	}
	
	public JSONArray getMainCheckEventList(List<EventCheck> classList) {
		
		int beforeClassid = 0;
		int beforeDailyEventid = 0;
		String beforeDailyEventidCd = new String();

		JSONArray classArr = new JSONArray();
		JSONArray classDailyEventArr = new JSONArray();
		JSONArray eventChkArr = new JSONArray();

		JSONObject classObj = new JSONObject();
		JSONObject classDailyEventObj = new JSONObject();
		JSONObject eventChkObj = new JSONObject();
		
		
		// classArr
		for (int i = 0; i < classList.size(); i++) {
			EventCheck eventCheck = classList.get(i);
			if (eventCheck.getClassId() != beforeClassid) {
				classObj = new JSONObject();
				classObj.put("classId", eventCheck.getClassId());
				classObj.put("classNm", eventCheck.getClassNm());
				classObj.put("centerId", eventCheck.getCenterId());
				classObj.put("teacherUserId", eventCheck.getTeacherUserId());
				classObj.put("teacherNm", eventCheck.getTeacherNm());
				classArr.add(classObj);
			}
			beforeClassid = eventCheck.getClassId();
		}
		
		// classDailyEventArr
		for (int i = 0; i < classList.size(); i++) {
			EventCheck eventCheck = classList.get(i);
			
			if (!(Integer.toString(eventCheck.getClassDailyEventId()) + Integer.toString(eventCheck.getEventCd())+ Integer.toString(eventCheck.getClassId()))
					.equals(beforeDailyEventidCd)
					
					) {
				classDailyEventObj = new JSONObject();
				classDailyEventObj.put("classDailyEventId", eventCheck.getClassDailyEventId());
				classDailyEventObj.put("eventDate", eventCheck.getEventDate());
				classDailyEventObj.put("eventOrder", eventCheck.getEventOrder());
				classDailyEventObj.put("classId", eventCheck.getClassId());
				classDailyEventObj.put("destinyNm", eventCheck.getDestinyNm());
				classDailyEventObj.put("eventAlarmEndT", eventCheck.getEventAlarmEndT());
				classDailyEventObj.put("eventAlarmStartT", eventCheck.getEventAlarmStartT());
				classDailyEventObj.put("eventCarNeedYn", eventCheck.getEventCarNeedYn());
				classDailyEventObj.put("eventCd", eventCheck.getEventCd());
				classDailyEventArr.add(classDailyEventObj);
			}
			
			beforeDailyEventidCd = Integer.toString(eventCheck.getClassDailyEventId())
					+ Integer.toString(eventCheck.getEventCd())+ Integer.toString(eventCheck.getClassId());
		}

		for (int i = 0; i < classDailyEventArr.size(); i++) {
			classDailyEventObj = (JSONObject) classDailyEventArr.get(i);
			eventChkArr = new JSONArray();
			for (int j = 0; j < classList.size(); j++) {
				EventCheck eventCheck = classList.get(j);
				if((Integer.toString(eventCheck.getClassDailyEventId()) + Integer.toString(eventCheck.getEventCd())+ Integer.toString(eventCheck.getClassId()))
						.equals(classDailyEventObj.get("classDailyEventId").toString()+classDailyEventObj.get("eventCd").toString()+classDailyEventObj.get("classId").toString()
								)) {
					eventChkObj = new JSONObject();
					eventChkObj.put("eventCheckId", eventCheck.getEventCheckId());
					eventChkObj.put("eventCheckDt", eventCheck.getEventCheckDt());
					eventChkObj.put("childId", eventCheck.getChildId());
					eventChkObj.put("classDailyEventId", eventCheck.getClassDailyEventId());
					eventChkObj.put("childNm", eventCheck.getChildNm());
					eventChkObj.put("checkerUserId", eventCheck.getCheckerUserId());
					eventChkArr.add(eventChkObj);
				}
			}
			classDailyEventObj.put("eventCheck", eventChkArr);
		}

		JSONArray classDailyEvent = null;
		for (int i = 0; i < classArr.size(); i++) {
			classObj = (JSONObject) classArr.get(i);
			classDailyEvent = new JSONArray();
			for (int j = 0; j < classDailyEventArr.size(); j++) {
				classDailyEventObj = (JSONObject) classDailyEventArr.get(j);
				if(classObj.get("classId").equals(classDailyEventObj.get("classId"))) {
					classDailyEvent.add(classDailyEventObj);
				}
			}
			classObj.put("classDailyEvent", classDailyEvent);
		}
		return classArr;
		
	}
}

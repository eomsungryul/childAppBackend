package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.Child;
import kr.co.dwebss.child.model.ClassDailyEvent;
import kr.co.dwebss.child.model.ClassVO;
import kr.co.dwebss.child.model.EventCheck;
import kr.co.dwebss.child.service.ChildService;
import kr.co.dwebss.child.service.ClassDailyEventService;
import kr.co.dwebss.child.service.ClassService;
import kr.co.dwebss.child.service.EventCheckService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

	@PostMapping("/event_check")
	public Result add(@RequestBody EventCheck eventCheck) {
		eventCheckService.save(eventCheck);
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

	@GetMapping(value={"/parent/{phone}","/parent/{phone}/{date}"})
	 public Result getMyChildsCheckEventList(@PathVariable String phone,
	 @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
//	public Result getMyChildsCheckEventList(@PathVariable String phone) {
		EventCheck param = new EventCheck();
		param.setParentPhone(phone);
		
		List<EventCheck> classList = eventCheckService.selectEventCheck(param);

		
		
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
			
			if (!(Integer.toString(eventCheck.getClassDailyEventId()) + Integer.toString(eventCheck.getEventCd()))
					.equals(beforeDailyEventidCd)) {
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
					+ Integer.toString(eventCheck.getEventCd());
		}

		for (int i = 0; i < classDailyEventArr.size(); i++) {
			classDailyEventObj = (JSONObject) classDailyEventArr.get(i);
			eventChkArr = new JSONArray();
			for (int j = 0; j < classList.size(); j++) {
				EventCheck eventCheck = classList.get(j);
				if((Integer.toString(eventCheck.getClassDailyEventId()) + Integer.toString(eventCheck.getEventCd()))
						.equals(classDailyEventObj.get("classDailyEventId").toString()+classDailyEventObj.get("eventCd").toString())) {
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

		return ResultGenerator.genSuccessResult(classArr);
	}
	
//	@GetMapping("/parent/{phone}")
	// public Result getMyChildsCheckEventList(@PathVariable String phone,
	// @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
	public Result getMyChildsCheckEventListBk(@PathVariable String phone) {
		EventCheck param = new EventCheck();
		param.setParentPhone(phone);
		List<EventCheck> classList = eventCheckService.selectEventCheck(param);
		
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
}

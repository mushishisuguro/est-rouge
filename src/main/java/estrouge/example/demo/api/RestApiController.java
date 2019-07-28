package estrouge.example.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import estrouge.example.demo.model.Work;
import estrouge.example.demo.service.WorkService;

@RestController
@RequestMapping("/api")
public class RestApiController {
	public static Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	WorkService workService;
	
	@RequestMapping(value = "/work/", method = RequestMethod.GET)
	public ResponseEntity<List<Work>> listAllWork() {
		List<Work> listWork = workService.findAll();
		if(listWork.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Work>>(listWork, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/work/{workId}", method = RequestMethod.GET)
	public Work findWork(@PathVariable("workId") long workId) {
		Work work = workService.getOne(workId);
		if(work == null) {
			ResponseEntity.notFound().build();
		}
		return work;
	}
	
	@RequestMapping(value = "/work/", method = RequestMethod.POST)
	public Work saveWork(@Valid @RequestBody Work work) {
		return workService.save(work);
	}
	
	@RequestMapping(value = "/work/{workId}", method = RequestMethod.PUT)
	public ResponseEntity<Work> updateWork(@PathVariable(value = "workId") Long workId, 
	                                       @Valid @RequestBody Work workForm) {
		Work work = workService.getOne(workId);
	    if(work == null) {
	        return ResponseEntity.notFound().build();
	    }
	    work.setWorkName(workForm.getWorkName());
	    work.setStartingDate(workForm.getStartingDate());
	    work.setEndingDate(workForm.getEndingDate());
	    work.setStatus(workForm.getStatus());

	    Work updatedWork = workService.save(work);
	    return ResponseEntity.ok(updatedWork);
	}
	
	@RequestMapping(value = "/work/{workId}", method = RequestMethod.DELETE)
	public ResponseEntity<Work> deleteWork(@PathVariable(value = "workId") Long workId) {
		Work work = workService.getOne(workId);
	    if(work == null) {
	        return ResponseEntity.notFound().build();
	    }

	    workService.delete(work);
	    return ResponseEntity.ok().build();
	}
}

package estrouge.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import estrouge.example.demo.model.Work;
import estrouge.example.demo.enums.Direction;
import estrouge.example.demo.enums.OrderBy;
import estrouge.example.demo.exception.PaginationSortingException;
import estrouge.example.demo.exception.PagingSortingErrorResponse;
import estrouge.example.demo.service.PaginationService;

@RestController
@RequestMapping(value = "/pagination")
public class PaginationController {

	@Autowired
	private PaginationService paginationService;

	@RequestMapping(value = "/conditionalPagination", 
			params = { "orderBy", "direction", "page", "size" }, 
			method = RequestMethod.GET)
	@ResponseBody
	public Page<Work> findJsonDataByPageAndSize(@RequestParam("orderBy") String orderBy,
			@RequestParam("direction") String direction, @RequestParam("page") int page,
			@RequestParam("size") int size) {

		if (!(direction.equals(Direction.ASCENDING.getDirectionCode())
				|| direction.equals(Direction.DESCENDING.getDirectionCode()))) {
			throw new PaginationSortingException("Invalid sort direction");
		}

		if (!(orderBy.equals(OrderBy.WORKID.getOrderByCode()) 
				|| orderBy.equals(OrderBy.WORKNAME.getOrderByCode()))) {
			throw new PaginationSortingException("Invalid orderBy condition");
		}

		Page<Work> list = paginationService.findJsonDataByCondition(orderBy, direction, page, size);
		return list;
	}

	@ExceptionHandler(PaginationSortingException.class)
	public ResponseEntity<PagingSortingErrorResponse> exceptionHandler(Exception ex) {
		
		PagingSortingErrorResponse pagingSortingErrorResponse = new PagingSortingErrorResponse();
		pagingSortingErrorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		pagingSortingErrorResponse.setMessage(ex.getMessage());
		
		return new ResponseEntity<PagingSortingErrorResponse>(pagingSortingErrorResponse, HttpStatus.OK);
	}
}

package estrouge.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import estrouge.example.demo.dao.PaginationDao;
import estrouge.example.demo.model.Work;

@Service
public class PaginationService {

	@Autowired
	private PaginationDao paginationDao;

	@SuppressWarnings("deprecation")
	public Page<Work> findJsonDataByCondition(String orderBy, String direction, int page, int size) {
		Sort sort = null;

		if (direction.equals("ASC")) {
			sort = new Sort(new Sort.Order(Direction.ASC, orderBy));
		}

		if (direction.equals("DESC")) {
			sort = new Sort(new Sort.Order(Direction.DESC, orderBy));
		}
		
		Pageable pageable = new PageRequest(page, size, sort);
		Page<Work> data = paginationDao.findAll(pageable);

		return data;
     }
}
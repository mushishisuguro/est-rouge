package estrouge.example.demo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import estrouge.example.demo.model.Work;

public interface PaginationDao extends PagingAndSortingRepository<Work, Integer> {
}
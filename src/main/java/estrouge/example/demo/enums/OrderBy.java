package estrouge.example.demo.enums;

public enum OrderBy {

	WORKID("workId"),
	WORKNAME("workName");

	private String OrderByCode;

	private OrderBy(String orderBy) {
		this.OrderByCode = orderBy;
	}

	public String getOrderByCode() {
		return this.OrderByCode;
	}
}
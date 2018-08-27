package rsa.entity;

import java.math.BigDecimal;

public class Entity {
	public Entity(Long id, BigDecimal price, String content) {
		super();
		this.id = id;
		this.price = price;
		this.content = content;
	}

	private Long id;
	
	public Entity(Long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

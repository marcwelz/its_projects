package com.axa.ch.its.inventory.model;

import java.awt.image.BufferedImage;

public class Article {

	private String id;
	private String description;
	private BufferedImage productImage;

	public Article(String id, String description, BufferedImage bufferedImage) {
		this(id, description);
		this.productImage = bufferedImage;
	}

	public Article(String id, String description) {
		this(id);
		this.description = description;
	}
	
	public Article(String id) {
		this.id = id;		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BufferedImage getProductImage() {
		return productImage;
	}

	public void setProductImage(BufferedImage productImage) {
		this.productImage = productImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

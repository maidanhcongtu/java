package com.mhdanh.mytemplate.viewmodel;

import org.springframework.web.multipart.MultipartFile;

public class UploadTemplateDTO {

	private String titleTemplate;
	private String fileNameTemplate;
	private int categoryTemplateId;
	private MultipartFile fileTemplate;
	private MultipartFile fileThumbnail;
	private String fileNameThumbnail;
	private int costTemplate;

	public UploadTemplateDTO() {
	}

	public String getTitleTemplate() {
		return titleTemplate;
	}

	public void setTitleTemplate(String titleTemplate) {
		this.titleTemplate = titleTemplate;
	}

	public String getFileNameTemplate() {
		return fileNameTemplate;
	}

	public void setFileNameTemplate(String fileNameTemplate) {
		this.fileNameTemplate = fileNameTemplate;
	}

	public int getCategoryTemplateId() {
		return categoryTemplateId;
	}

	public void setCategoryTemplateId(int categoryTemplateId) {
		this.categoryTemplateId = categoryTemplateId;
	}

	public MultipartFile getFileTemplate() {
		return fileTemplate;
	}

	public void setFileTemplate(MultipartFile fileTemplate) {
		this.fileTemplate = fileTemplate;
	}

	public MultipartFile getFileThumbnail() {
		return fileThumbnail;
	}

	public void setFileThumbnail(MultipartFile fileThumbnail) {
		this.fileThumbnail = fileThumbnail;
	}

	public String getFileNameThumbnail() {
		return fileNameThumbnail;
	}

	public void setFileNameThumbnail(String fileNameThumbnail) {
		this.fileNameThumbnail = fileNameThumbnail;
	}

	public int getCostTemplate() {
		return costTemplate;
	}

	public void setCostTemplate(int costTemplate) {
		this.costTemplate = costTemplate;
	}

}

package com.mhdanh.mytemplate.service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mhdanh.mytemplate.dao.CategoryDao;
import com.mhdanh.mytemplate.domain.Category;
import com.mhdanh.mytemplate.domain.Template;
import com.mhdanh.mytemplate.service.IndexService;
import com.mhdanh.mytemplate.service.TemplateService;
import com.mhdanh.mytemplate.utility.Utility;
import com.mhdanh.mytemplate.viewmodel.FilterModel;
import com.mhdanh.mytemplate.viewmodel.HardCode;
import com.mhdanh.mytemplate.viewmodel.LazyLoadTemplateFilterIndex;

@Service
@Transactional
public class IndexServiceImpl implements IndexService{

	@Autowired
	private TemplateService templateService;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private Utility utility;
	
	@Override
	public String indexPage(Model model,LazyLoadTemplateFilterIndex lazyLoadingTemplate) {
		lazyLoadingTemplate = defaultLazyLoadingCategory(lazyLoadingTemplate);
		List<Template> templates = templateService.getLazyTemplatePublished(lazyLoadingTemplate);
		int totalTemplatePublished = templateService.countTotalTemplatePublishedAndLazyLoadinTemplate(lazyLoadingTemplate);
		List<Category> categories = categoryDao.getAll();
		List<FilterModel> filters = createFilters();
		model.addAttribute("filters", filters);
		model.addAttribute("templates", templates);
		model.addAttribute("categories", categories);
		model.addAttribute("lazyLoading",lazyLoadingTemplate);
		model.addAttribute("totalTemplatePublished", totalTemplatePublished);
		model.addAttribute("step", lazyLoadingTemplate.getStep());
		return "/index";
	}

	private LazyLoadTemplateFilterIndex defaultLazyLoadingCategory(LazyLoadTemplateFilterIndex lazyLoadingTemplate) {
		if(lazyLoadingTemplate.getStep() == 0){
			int itemOnPage = HardCode.itemOnPageIndex;
			lazyLoadingTemplate.setStep(itemOnPage);
		}
		if(lazyLoadingTemplate.getValueFilter() == null){
			lazyLoadingTemplate.setValueFilter("");
		}
		return lazyLoadingTemplate;
	}

	private List<FilterModel> createFilters() {
		List<FilterModel> filters = new ArrayList<FilterModel>();
		FilterModel filterTopDownload = new FilterModel();
		filterTopDownload.setValue(HardCode.topDownload);
		filterTopDownload.setDisplayName(utility.getMessage("msg.index.top.download"));
		filters.add(filterTopDownload);
		
		FilterModel filterTopFree = new FilterModel();
		filterTopFree.setValue(HardCode.topFreeDownload);
		filterTopFree.setDisplayName(utility.getMessage("msg.index.top.free.download"));
		filters.add(filterTopFree);
		
		FilterModel filterTopPremium = new FilterModel();
		filterTopPremium.setValue(HardCode.topPremiumDownload);
		filterTopPremium.setDisplayName(utility.getMessage("msg.index.top.premium.download"));
		filters.add(filterTopPremium);
		
		FilterModel filterNewest = new FilterModel();
		filterNewest.setValue(HardCode.newest);
		filterNewest.setDisplayName(utility.getMessage("msg.index.template.newest"));
		filters.add(filterNewest);
		
		FilterModel filterFreeNewest = new FilterModel();
		filterFreeNewest.setValue(HardCode.freeNewest);
		filterFreeNewest.setDisplayName(utility.getMessage("msg.index.template.free.newest"));
		filters.add(filterFreeNewest);
		
		FilterModel filterPremiumNewest = new FilterModel();
		filterPremiumNewest.setValue(HardCode.premiumNewest);
		filterPremiumNewest.setDisplayName(utility.getMessage("msg.index.template.premium.newest"));
		filters.add(filterPremiumNewest);
		
		return filters;
	}

	@Override
	public String indexLoadMore(Model model,
			LazyLoadTemplateFilterIndex valueFilter) {
		int nextPage = valueFilter.getPage() + 1;
		valueFilter.setPage(nextPage);
		List<Template> templates = templateService.getLazyTemplatePublished(valueFilter);
		int totalTemplatePublished = templateService.countTotalTemplatePublishedAndLazyLoadinTemplate(valueFilter);
		model.addAttribute("templates", templates);
		model.addAttribute("lazyLoading", valueFilter);
		model.addAttribute("totalTemplatePublished", totalTemplatePublished);
		return "/ajax/index-load-more";
	}

}

package com.mhdanh.mytemplate.dao.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mhdanh.mytemplate.dao.TemplateDao;
import com.mhdanh.mytemplate.domain.Template;
import com.mhdanh.mytemplate.domain.Template.TEMPLATE_STATUS;
import com.mhdanh.mytemplate.utility.Utility;
import com.mhdanh.mytemplate.viewmodel.HardCode;
import com.mhdanh.mytemplate.viewmodel.LazyLoadTemplateFilterIndex;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class TemplateDaoImpl extends CommonDaoImpl<Template> implements TemplateDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Utility utility;
	
	@Override
	public Template getUploadTemplateByCategoryAndFileNameOfOwner(
			int categoryId, String fileName) {
		Template templateOfOwner = (Template) sessionFactory.getCurrentSession()
				.createCriteria(Template.class)
				.add(Restrictions.eq("category.id", categoryId))
				.add(Restrictions.eq("fileName",fileName))
				.add(Restrictions.eq("owner.id", utility.getUserLogined().getId()))
				.setMaxResults(1)
				.uniqueResult();
		return templateOfOwner;
	}

	@Override
	public Template getUploadTemplateByCategoryAndFileNameNotOwner(
			int categoryId, String fileName) {
		Template templateNotOwner = (Template) sessionFactory.getCurrentSession()
				.createCriteria(Template.class)
				.add(Restrictions.eq("category.id", categoryId))
				.add(Restrictions.eq("fileName",fileName))
				.add(Restrictions.not(Restrictions.eq("owner.id", utility.getUserLogined().getId())))
				.setMaxResults(1)
				.uniqueResult();
		return templateNotOwner;
	}

	@Override
	public Template getTemplateById(int idTemplate) {
		return (Template) sessionFactory.getCurrentSession().createCriteria(Template.class)
				.add(Restrictions.eq("id", idTemplate))
				.uniqueResult();
	}

	@Override
	public List<Template> getTemplateByStatus(TEMPLATE_STATUS status) {
		return sessionFactory.getCurrentSession().createCriteria(Template.class)
				.add(Restrictions.eq("status", status))
				.addOrder(Order.desc("dateModified"))
				.list();
	}

	@Override
	public List<Template> getLazyTemplatePublished(
			LazyLoadTemplateFilterIndex lazyLoadingTemplate) {
		Criteria lazyLoadTemplateCriteria = sessionFactory.getCurrentSession()
				.createCriteria(Template.class)
				.add(Restrictions.eq("status", TEMPLATE_STATUS.PUBLISHED));
		Integer zero = 0;
		if(lazyLoadingTemplate.getIdCategory() != zero) {
			lazyLoadTemplateCriteria.add(Restrictions.eq("category.id", lazyLoadingTemplate.getIdCategory()));
		}
		
		if(HardCode.topDownload.equals(lazyLoadingTemplate.getValueFilter())){
			lazyLoadTemplateCriteria.addOrder(Order.desc("buy"));
		}
		
		if(HardCode.topFreeDownload.equals(lazyLoadingTemplate.getValueFilter())){
			lazyLoadTemplateCriteria.add(Restrictions.eq("sellOff", zero));
			lazyLoadTemplateCriteria.addOrder(Order.desc("buy"));
		}
		
		if(HardCode.topPremiumDownload.equals(lazyLoadingTemplate.getValueFilter())){
			lazyLoadTemplateCriteria.add(Restrictions.not(Restrictions.eq("sellOff", zero)));
			lazyLoadTemplateCriteria.addOrder(Order.desc("buy"));
		}
		
		if(HardCode.FreeNewest.equals(lazyLoadingTemplate.getValueFilter())){
			lazyLoadTemplateCriteria.add(Restrictions.eq("sellOff", zero));
			lazyLoadTemplateCriteria.addOrder(Order.desc("dateModified"));
		}
		
		if(HardCode.PremiumNewest.equals(lazyLoadingTemplate.getValueFilter())){
			lazyLoadTemplateCriteria.add(Restrictions.not(Restrictions.eq("sellOff", zero)));
			lazyLoadTemplateCriteria.addOrder(Order.desc("dateModified"));
		}
		
		lazyLoadTemplateCriteria.setMaxResults(lazyLoadingTemplate.getStep());
		return lazyLoadTemplateCriteria.list();
	}
}

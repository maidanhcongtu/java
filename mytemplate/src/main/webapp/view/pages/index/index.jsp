<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/my.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div class = "wrapper-content-template">
	<!-- filter template -->
	<div class = "wrap-filte-template">
		<form class="form-inline" name="valueFilter" method="get">
			<div class="form-group">
				<label for="exampleInputName2">Choose category:</label>
				<select class = "form-control mt-no-border-radius" name = "idCategory">
					<option value="0"><spring:message code="msg.index.text.all"/></option>
					<c:forEach items="${categories}" var = "category">
						<option value="${category.id}"><spring:message code="${category.name}"/></option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
		    	<label for="exampleInputEmail2"> Filter:</label>
		    	<select class = "form-control mt-no-border-radius" name = "valueFilter">
					<c:forEach items="${filters}" var = "filter">
						<option value="${filter.value}">${filter.displayName}</option>
					</c:forEach>
				</select>
		    </div>
		    <button type="submit" class="btn btn-danger mt-button">Filter</button>
		</form>
	</div><!-- end filter template -->
	<div class = "index-wrap-content-load-template">
		<c:forEach items="${templates}" var="template" varStatus="status">
			<c:choose>
				<c:when test="${status.index % 2== 0}">
				<div class = "row row-content-template">
					<div class = "col-md-6">
						<div class = "content-template">
							<div class = "wrap-title-img">
								<h4 class = "title-template"><c:out value="${template.title}" escapeXml="true"/></h4>
								<div class = "wrap-imag-viewdetail">
									<img src="<c:url value='/viewimg/${template.thumbnail}'/>" class="img-thumbnail" alt="Cinque Terre" width="420" height="236">
									<div class = "overlay-view-detail-button">
										<a href = "<c:url value = '/template-detail/${template.id}'/>" class = 'view-detail-button' target="_blank"><spring:message code = 'msg.upload-template-file-page.label.viewdetail'/></a>
									</div>
								</div><!-- end wrap img and delete -->
								<div class = "detail-item">
									<strong class = "cost-item">
										<c:choose>
											<c:when test="${template.sellOff eq 0}">
												<spring:message code='msg.upload-template-file-page.text.free'/>
											</c:when>
											<c:otherwise>
												${template.sellOff}	
											</c:otherwise>
										</c:choose>
									</strong>
									<strong class = "buy-item">
										<span class = "glyphicon glyphicon-shopping-cart icon"></span>
										<span class = "buy-item-text">${template.buy}</span>
									</strong>
								</div><!-- end detail item -->
								<div class = "wrap-livedemo-buy">
									<a href="${template.link}" target="_blank" class = "btn btn-primary mt-button"><spring:message code = 'msg.upload-template-file-page.label.livedemo' /></a>
									<c:choose>
										<c:when test="${template.sellOff eq 0}">
											<a href="<c:url value='/template/download-template/${template.id}'/>" class = "btn btn-primary mt-button"><spring:message code = 'msg.upload-template-file-page.label.downloadme' /></a>
										</c:when>
										<c:otherwise>
											<a href="${template.link}" target="_blank" class = "btn btn-primary mt-button"><spring:message code = 'msg.upload-template-file-page.label.buyme' /></a>	
										</c:otherwise>
									</c:choose>
								</div><!-- end purchase button and live demo -->
							</div>
						</div>
						<div class = "mt-clear-both"></div>
					</div>
				</c:when>
				<c:otherwise>
					<div class = "col-md-6">
						<div class = "content-template">
							<div class = "wrap-title-img">
								<h4 class = "title-template"><c:out value="${template.title}" escapeXml="true"/></h4>
								<div class = "wrap-imag-viewdetail">
									<img src="<c:url value='/viewimg/${template.thumbnail}'/>" class="img-thumbnail" alt="Cinque Terre" width="420" height="236">
									<div class = "overlay-view-detail-button">
										<button class = 'view-detail-button'><spring:message code = 'msg.upload-template-file-page.label.viewdetail'/></button>
									</div>
								</div><!-- end wrap img and delete -->
								<div class = "detail-item">
									<strong class = "cost-item">
										<c:choose>
											<c:when test="${template.sellOff eq 0}">
												<spring:message code='msg.upload-template-file-page.text.free'/>
											</c:when>
											<c:otherwise>
												${template.sellOff}	
											</c:otherwise>
										</c:choose>
									</strong>
									<strong class = "buy-item">
										<span class = "glyphicon glyphicon-shopping-cart icon"></span>
										<span class = "buy-item-text">${template.buy}</span>
									</strong>
								</div><!-- end detail item -->
								<div class = "wrap-livedemo-buy">
									<a href="${template.link}" target="_blank" class = "btn btn-primary mt-button"><spring:message code = 'msg.upload-template-file-page.label.livedemo' /></a>
									<c:choose>
										<c:when test="${template.sellOff eq 0}">
											<a href="<c:url value='/template/download-template/${template.id}'/>" class = "btn btn-primary mt-button"><spring:message code = 'msg.upload-template-file-page.label.downloadme' /></a>
										</c:when>
										<c:otherwise>
											<a href="${template.link}" target="_blank" class = "btn btn-primary mt-button"><spring:message code = 'msg.upload-template-file-page.label.buyme' /></a>	
										</c:otherwise>
									</c:choose>
								</div><!-- end purchase button and live demo -->
							</div>
						</div>
						<div class = "mt-clear-both"></div>
					</div>
				</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${templates.size() % 2 == 1}">
			<!-- enclose div if odd size -->
			</div>
		</c:if>
		<!-- param load more page -->
		<input type = "hidden" id = "current-page" value = "${lazyLoading.page}"/>
		<input type = "hidden" id = "current-step" value = "${lazyLoading.step}"/>
		<input type = "hidden" id = "current-category-id" value = "${lazyLoading.idCategory}"/>
		<input type = "hidden" id = "current-value-filter" value = "${lazyLoading.valueFilter}"/>
		
	</div><!-- end wrap content index -->
	<div class = "index-wrap-loadmore">
		<button class = "btn btn-danger mt-button" id = 'load-more-button'><spring:message code = "msg.index.text.loadmore"/></button>
	</div>
</div>
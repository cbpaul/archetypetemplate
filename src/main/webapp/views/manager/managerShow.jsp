<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">

	<div class="span12">
	<input type="hidden" id="baseUrl" value="${base}">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
		</h3>
		<ul class="breadcrumb">
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->

<!-- BEGIN PAGE CONTENT-->
<div class="row-fluid">
	<div class="span12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet box grey">
			<div class="portlet-title">
				<div class="caption">
					<i class="icon-search"></i>查看帐号
				</div>
				<div class="actions">
					<a href="${base}/list" type="get" class="btn red ajaxDef"><i class="icon-chevron-left"></i> 取消</a>
					<div class="btn-group"></div>
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form action="#" id="form-save" class="form-horizontal">
					<input type="hidden" id="id" name="id" value="${bean.id }" />
					<input type="hidden" id="hidden" name="hidden" value="${bean.hidden }" />
					<input type="hidden" id="createdOn" name="createdOn" value="${bean.createdOn }" />
					<h4 class="form-section">帐号信息</h4>
					<div class="control-group">
						<label class="control-label">帐号
						</label>
						<div class="controls">
							<input name="loginName" type="text" data-required="1" value="${bean.loginName }"
								class="large m-wrap required loginName" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">角色
						</label>
						<div class="controls">
							<select class="large m-wrap required" name="role.id">
								<option value="">选择角色...</option>
								<c:forEach items="${roleList}" var="roleBean">
									<option value="${roleBean.id }" ${roleBean.id == bean.role.id ? "selected":"" }>${roleBean.roleName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">姓名
						</label>
						<div class="controls">
							<input name="realName" type="text" maxlength="16" data-required="1" value="${bean.realName }"
								class="large m-wrap" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">性别</label>
						<div class="controls">
							<label class="radio">
							<input type="radio" name="sex" value="1" ${bean.sex == 1 ? "checked":"" } />
							男
							</label>
							<label class="radio">
							<input type="radio" name="sex" value="0" ${bean.sex == 0 ? "checked":"" } />
							女
							</label>  
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">手机
						</label>
						<div class="controls">
							<input name="mobile" type="text" data-required="1" value="${bean.mobile }"
								class="large m-wrap mobile" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">备注 </label>
						<div class="controls">
							<textarea name="remark" rows="3" maxlength="50"
								class="large m-wrap">${bean.remark}</textarea>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>

<!-- END PAGE CONTENT-->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<div class="table-header">
			账号管理
			<div style="position: absolute;right: 22px;top: -2px;">
				<a ajax-url="${base}/add" href="javascript:;" type="get" class="btn btn-success btn-sm ajaxDef"><i class="icon-pencil"></i>新建账户</a>
				<a ajax-url="${base}/delete" href="javascript:;"  type="get" class="btn btn-danger btn-sm"><i class="icon-pencil"></i>删除账户</a>
			</div>
		</div>
		<div class="table-responsive">
			<table id="data-list"
				class="table table-striped table-bordered table-hover"
				style="margin-bottom: 0px;">
				<thead>
					<th class="center"><label> <input type="checkbox"
							class="ace" /> <span class="lbl"></span>
					</label>
					</th>
					<th >登录名</th>
					<th>姓名</th>
					<th>性别</th>
					<th>电话</th>
					<th>操作</th>
				</thead>
				<tbody>
					<c:forEach items="${page.content}" var="bean">
						<tr>
							<td class="center"><label> <input class="ace"
									type="checkbox"> <span class="lbl"></span> </label></td>
							<td>${bean.loginName }</td>
							<td>${bean.realName }</td>
							<td>${bean.sex==0?"女":"男" }</td>
							<td>${bean.mobile }</td>
							<td>
								<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
									<button class="btn btn-xs btn-info ajaxDef list-edit" ajax-url="${base }/edit?id=${bean.id}">
										<i class="icon-edit bigger-120"></i>
									</button>
									<button class="btn btn-xs btn-danger list-delete" ajax-url="${base }/delete?id=${bean.id}">
										<i class="icon-trash bigger-120"></i>
									</button>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tags:page url="${base}/list" totalItems="${page.totalElements }" curpage="${page.number+1 }"/>
		</div>
	</div>
</div>

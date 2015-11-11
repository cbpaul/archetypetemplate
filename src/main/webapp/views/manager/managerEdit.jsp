<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<form role="form" class="form-horizontal">
			<div class="form-group">
				<label for="form-field-1"
					class="col-sm-3 control-label no-padding-right"> 账号
				</label>
				<div class="col-sm-9">
					<input type="text" class="col-xs-10 col-sm-5"
						placeholder="账号" name="account" id="form-field-1">
				</div>
			</div>

			<div class="space-4"></div>

			<div class="form-group">
				<label for="form-field-2"
					class="col-sm-3 control-label no-padding-right"> 密码
					 </label>

				<div class="col-sm-9">
					<input type="password" class="col-xs-10 col-sm-5"
						placeholder="密码" name="password" id="form-field-2"> 
				</div>
			</div>
			<div class="form-group">
				<label for="form-field-3"
					class="col-sm-3 control-label no-padding-right"> 确认密码
					 </label>

				<div class="col-sm-9">
					<input type="password" class="col-xs-10 col-sm-5"
						placeholder="确认密码" name="compassword" id="form-field-3"> 
				</div>
			</div>

			<div class="space-4"></div>
			<div class="form-group">
				<label for="form-field-7"
					class="col-sm-3 control-label no-padding-right"> 角色
				</label>
				<div class="col-sm-9">
					<select name="role.id" id="form-field-7" class="col-xs-10 col-sm-5">
						<option value=""> </option>
						<option value="AL">Alabama</option>
					</select>
				</div>
			</div>
			<div class="space-4"></div>
			<div class="form-group">
				<label for="form-field-4"
					class="col-sm-3 control-label no-padding-right"> 电话
				</label>
				<div class="col-sm-9">
					<input type="text" class="col-xs-10 col-sm-5"
						placeholder="电话" name="mobile" id="form-field-4">
				</div>
			</div>
			<div class="space-4"></div>
			<div class="form-group">
				<label for="form-field-5"
					class="col-sm-3 control-label no-padding-right"> 姓名
				</label>
				<div class="col-sm-9">
					<input type="text" class="col-xs-10 col-sm-5"
						placeholder="姓名" name="realName" id="form-field-5">
				</div>
			</div>
			<div class="space-4"></div>
			<div class="form-group">
				<label 
					class="col-sm-3 control-label no-padding-right"> 性别
				</label>
				<div class="col-sm-9">
					<label class="radio">
						<input type="radio" name="sex" value="1" ${bean.sex == 1 ? "checked":"" } />男
					</label>
					<label class="radio">
						<input type="radio" name="sex" value="0" ${bean.sex == 0 ? "checked":"" } />女
					</label>  
				</div>
			</div>
			<div class="form-group">
				<label for="form-field-6"
					class="col-sm-3 control-label no-padding-right"> 备注
				</label>
				<div class="col-sm-9">
					<textarea id="form-field-6" class="col-xs-10 col-sm-5" placeholder="备注"></textarea>
				</div>
			</div>
			<div class="space-4"></div>
			<div class="space-4"></div>
			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button type="button" class="btn btn-info">
						<i class="icon-ok bigger-110"></i> 提交
					</button>
					<button type="reset" class="btn">
						<i class="icon-undo bigger-110"></i> 取消
					</button>
				</div>
			</div>
		</form>
	</div>
</div>
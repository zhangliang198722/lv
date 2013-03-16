<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testlv</title>
<link rel="stylesheet" type="text/css" href="resources/styles/ext-all.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/base.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/fileuploadfield.css" />
<script type="text/javascript" src="resources/scripts/ext-base.js"></script>
<script type="text/javascript" src="resources/scripts/ext-all-debug.js"></script>
<script type="text/javascript" src="resources/scripts/base.js"></script>
<script type="text/javascript" src="resources/scripts/FileUploadField.js"></script>
<script type="text/javascript" src="resources/scripts/monthPicker.js"></script>
<script type="text/javascript" src="resources/scripts/base.js"></script>
<script type="text/javascript" src="resources/scripts/windowUtil.js"></script>
<!-- Cls files --时间格式 Y-m-d h:i-->
<link rel="stylesheet" type="text/css"
	href="resources/styles/DateTimePicker.css" />
<script type="text/javascript" src="resources/scripts/cls.js"></script>
<style>
.smart_pms_ui_project_localizer_empty {
	BACKGROUND-IMAGE: url(resources/scripts/bg-4.jpg) !important
}

#template {
	FONT-SIZE: 9pt;
	border-collapse: collapse;
	border: solid #000000;
	border-width: 0px 0px 1px 0px;
}

#template td {
	border: solid #000000;
	border-width: 1px 1px 0 1px;
	padding: 2px;
	height: 20px
}

#template2 {
	FONT-SIZE: 9pt;
	border-collapse: collapse;
	border: solid #000000;
	border-width: 0px 0px 1px 0px;
}

.form {
	border-collapse: collapse;
}

.form TD {
	padding: 10px;
	border-collapse: collapse;
	border: solid #CCCCCC;
	vertical-align: top;
	border-width: 0px 0px 1px 0px;
}

.form TH {
	text-align: right;
	border-collapse: collapse;
	border: solid #CCCCCC;
	font-weight: bold;
	border-width: 0px 0px 1px 0px;
	padding: 10px;
	vertical-align: top;
}

#temp {
	FONT-SIZE: 9pt;
	border-collapse: collapse;
	border: solid #ffffff;
	border-width: 0px 0px 0px 0px;
}

#temp td {
	border: solid #ffffff;
	border-width: 0px 0px 0px 0px;
	padding: 0px;
	height: 28px
}

#template2 td {
	border: solid #000000;
	border-width: 1px 1px 0 1px;
	padding: 2px;
	height: 20px
}
</style>
</head>
<body>
	<iframe name="smart:pms:result" id="smart:pms:result"
		style="display: none" width="0" height="0"></iframe>
	<form id="smart:system:exportExcel" method="post"
		target="smart:pms:result"></form>
	<form id="smart:pms:export" name="smart:pms:export" ACTION=""
		method="post" target="smart:pms:result">
		<INPUT TYPE="hidden" NAME="fileName" ID="fileName" value="" /> <INPUT
			TYPE="hidden" NAME="content" ID="content" value="" />
	</form>
	<div id="loading-mask" style=""></div>
	<div id="loading">
		<div class="loading-indicator">
			<img src="resources/scripts/extanim32.gif" width="32" height="32"
				style="margin-right: 8px;" align="absmiddle" />Loading...
		</div>
	</div>
	<TABLE id="header" cellSpacing=0 cellPadding=0 width="100%"
		align=center border=0>
		<TR class="t_head_bg">
			<TD valign="top"><img
				src="resources/scripts/login/ind2_19(left).png" /></TD>
			<TD></TD>
			<TD align="right" valign="top"><img
				src="resources/scripts/login/ind2_19(right).gif" /></TD>
		</TR>
	</TABLE>
	<SCRIPT LANGUAGE="JavaScript">
	<!--
		Ext.BLANK_IMAGE_URL = "resources/scripts/pom/icons/s.gif";
		Ext.CONTENT_PATH = "/";
		var menuPanel;
		var mainPanel;
		var helpPanel;
		var sysydate;
		doExportExcel = function(fileName, content) {
			var obj = Ext.getDom("smart:pms:export");
			obj.fileName.value = fileName;
			obj.content.value = content;
			obj.submit();
		}
		Ext.override(Ext.layout.BorderLayout.Region, {
			collapseClick : function(e) {
				if (!this.panel.isMain) {
					if (this.isSlid) {
						e.stopPropagation();
						this.slideIn();
					} else {
						e.stopPropagation();
						this.slideOut();
					}
				}
			}
		})
		Ext.onReady(function() {
									try {
										Ext.QuickTips.init();
										menuPanel = new Smart.ui.MenuPanel();
										menuPanel.isMain = true;
										mainPanel = new Smart.ui.MainPanel();
										helpPanel = new Smart.ui.HelpPanel();
										loadMenu = function(b, e) {
											var obj = b || this;
											menuPanel.expand();
											menuPanel.isMain = false;
											// helpPanel.model = obj.helpCode;
											if (obj.defaultMenu) {
												mainPanel.content = obj.defaultMenu;
											}
											// helpPanel.load({url:'',params:{model:(helpPanel.model?helpPanel.model:'')},scripts:true});
											Smart.app.loadComponts(obj);
										}
										loadMain = function(b, e) {
											menuPanel.collapse();
											var obj = b || this;
											// helpPanel.model = obj.helpCode;
											Smart.app.loadMainPanel(obj);
											menuPanel.isMain = true;
											// helpPanel.load({url:'',params:{model:(helpPanel.model?helpPanel.model:'')},scripts:true});
										}
										loadHelp = function(b, e) {
											e.stopEvent();
											helpPanel.expand();
											helpPanel
													.load({
														url : '',
														params : {
															model : (helpPanel.model ? helpPanel.model
																	: '')
														},
														scripts : true
													});
										}

										var toolBar = [];
										
										toolBar[toolBar.length] = {
											text : '返回首页',
											tooltip : '返回首页',
											iconCls : 'smart_pwms_ui_home',
											scope : this,
											handler : function() {
												menuPanel.isMain = true;
												menuPanel.collapse(false);
												helpPanel.model = "index";
												menuPanel.load({
													url : ''
												});
												menuPanel.setTitle("功能菜单");
												helpPanel.collapse(false);
												mainPanel.load({
													url : 'welcome.html',
													scripts : true
												});
											}
										};
										
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = {
												text : '驴驹信息',
												tooltip : '驴驹信息',
												id : "smart:pms:donkey_info:view",
												iconCls : 'smart_irms_ui_meeting',
												content : 'donkey/index',
												// helpCode:"pms_project",
												handler : loadMain
										};
										
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = {
												text : '情检记录',
												tooltip : '情检记录',
												id : "smart:pms:heatcheck_info:view",
												iconCls : 'smart_irms_ui_meeting',
												content : 'heatcheck/index',
												// helpCode:"pms_project",
												handler : loadMain
										};
										
										
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = {
												text : '配种记录',
												tooltip : '配种记录',
												id : "smart:pms:breed_info:view",
												iconCls : 'smart_irms_ui_meeting',
												content : 'breed/index',
												// helpCode:"pms_project",
												handler : loadMain
										};
										
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = {
												text : '孕检记录',
												tooltip : '孕检记录',
												id : "smart:pms:pregnancy_info:view",
												iconCls : 'smart_irms_ui_meeting',
												content : 'pregnancy/index',
												// helpCode:"pms_project",
												handler : loadMain
										};
										
										
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = {
												text : '繁殖记录',
												tooltip : '繁殖记录',
												id : "smart:pms:sire_info:view",
												iconCls : 'smart_irms_ui_meeting',
												content : 'sire/index',
												// helpCode:"pms_project",
												handler : loadMain
										};
										
										var mitem = [];
	
										mitem[mitem.length] = {
												iconCls : 'smart_system_security_org',
												content : 'roleManager',
												content : 'system/user/index.html',
												handler : loadMain,
												text : '用户管理',
												tooltip : '用户管理'
											};
										mitem[mitem.length] = {
												iconCls : 'smart_system_security_org',
												content : 'roleManager',
												content : 'system/role/contentPanel.html',
												url : 'system/role/menu.html',
												handler : loadMenu,
												text : '角色管理',
												tooltip : '角色管理'
											};
										mitem[mitem.length] = {
												iconCls : 'smart_system_security_org',
												content : 'system/dictionary/content.html',
												url : 'system/dictionary/menu.html',
												handler : loadMenu,
												text : '数据字典管理',
												tooltip : '数据字典管理'
											};
											var system_menu = new Ext.menu.Menu(
													{
														id : 'smart:system:menu',
														items : mitem
													});

											toolBar[toolBar.length] = '-';
											toolBar[toolBar.length] = {
												text : '系统管理',
												tooltip : '系统管理',
												id : 'smart:pwms:system',
												iconCls : 'smart_pwms_system',
												menu : system_menu,
												xtype : 'tbsplit',
												handler : function(){
													this.showMenu() ;
												}

											}
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = '->';
										toolBar[toolBar.length] = '';
										//toolBar[toolBar.length] = '<span id="clock">时间XX</span>';
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = {
											iconCls : 'icon-permission',
											tooltip : '修改密码',
											handler : function() {
												var xwindow = mainPanel
														.window({
															title : "修改用户密码",
															iconCls : "modify",
															width : 450,
															height : 300,
															params : {
																userName : ''
															},
															url : 'updatePwd.html',
															submit : function() {
																var top = Ext.getCmp("user_edit_form");
																if (top.form.isValid()) {
																	top.getForm().submit(
																					{
																						url : "../project/pms/user/updatePwd.action",
																						params : {},
																						success : function(
																								form_instance_create,
																								action) {
																							xwindow
																									.close();
																						},
																						waitMsg : '正在保存......',
																						failure : function(
																								form_instance_create,
																								action) {
																							var json_response = Ext
																									.decode(action.response.responseText);
																							Smart.app
																									.showError(json_response.message);
																						}
																					});
																}
															}
														});
											}
										};
										toolBar[toolBar.length] = '-';
										toolBar[toolBar.length] = {
											tooltip : '退出系统',
											iconCls : 'smart_system_ui_logout',
											handler : function() {
												Ext.MessageBox
														.confirm(
																'确认提示信息',
																'确认要退出系统吗?',
																function(btn) {
																	if (btn == "yes") {
																		Ext.Ajax
																				.request({
																					url : '../layout.action',
																					success : function(
																							result,
																							request) {
																						var ret = "ret="
																								+ result.responseText;
																						eval(ret);
																						if (ret.success) {
																							window.location = "/dongrun/login.html";
																							return;
																						}
																					}
																				});
																	}
																});
											}
										};
										toolBar[toolBar.length] = '-';

										var hd = new Ext.Panel({
											border : false,
											layout : 'anchor',
											region : 'north',
											cls : 'docs-header',
											height : 70,
											items : [ {
												xtype : 'box',
												el : 'header',
												border : false,
												anchor : 'none -25'
											}, new Ext.Toolbar({
												cls : 'top-toolbar',
												items : toolBar
											}) ]
										})

										var viewport = new Ext.Viewport({
											layout : 'border',
											items : [ hd, menuPanel, mainPanel,
													helpPanel ]
										});
										viewport.doLayout();
										setTimeout(function() {
											Ext.get('loading').remove();
											Ext.get('loading-mask').fadeOut({
												remove : true
											});
										}, 250);
										mainPanel.load({
											url : 'welcome.html',
											scripts : true
										});

										var msgCt;
										Smart.app.Main = {
											msg : function(title, format) {
												if (!msgCt) {
													msgCt = Ext.DomHelper.insertFirst(
																	document.body,
																	{
																		id : 'msg-div'
																	}, true);
												}
												msgCt.alignTo(document, 't-t');
												var s = String.format
														.apply(
																String,
																Array.prototype.slice
																		.call(
																				arguments,
																				1));
												var m = Ext.DomHelper.append(
																msgCt,
																{
																	html : Smart.app.Main
																			.createBox(
																					title,
																					s)
																}, true);
												m.slideIn('b').pause(1).ghost(
														"b", {
															remove : true
														});
											}
										}

										Smart.app.Main.createBox = function(t,
												s) {
											return [
													'<div class="msg">',
													'<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
													'<div class="x-box-ml"><div class="x-box-mr"><div class="x-box-mc"><h3>',
													t,
													'</h3>',
													s,
													'</div></div></div>',
													'<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
													'</div>' ].join('');
										}
									} catch (e) {
										alert(e)
									}
				});
		//window.onerror = function(){
		//return true;
		//}
	//-->
	</SCRIPT>
</body>
</html>
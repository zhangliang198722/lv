var windowUtil = {
	pickProject : function(multi, callback) {
		var xwindow = new Smart.ui.Window({
			id : 'select_project_panel',
			title : "选择项目",
			width : (500),
			height : (400),
			autoLoad : {
				url : 'window/selectProject.html',
				scripts : true
			},
			iconCls : 'icon-user',
			button : [{
				text : '添加',
				tooltip : '添加',
				iconCls : 'select',
				handler : function(btn) {
					var users = new Array();
					for (var i = 0; i < xwindow.sm.getSelections().length; i++) {
						users[users.length] = {
							id : xwindow.sm.getSelections()[i].data.kid,
							name : xwindow.sm.getSelections()[i].data.prjName,
							totalexpenses : xwindow.sm.getSelections()[i].data.totalexpenses == ""
									? 0
									: xwindow.sm.getSelections()[i].data.totalexpenses,
							type : xwindow.sm.getSelections()[i].data.prjType
						};
					}
					var len = users.length;
					switch (len) {
						case 0 :
							Ext.MessageBox.alert('提示窗口', "请选择项目");
							break;
						case 1 :
							//Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
								//	function(btn) {
									//	if (btn == "yes") {
											callback(users);
											xwindow.close();
									//	} 
								//	});
							break;
						default :
							if (multi) {
							//	Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
										//function(btn) {
											//if (btn == "yes") {
												callback(users);
												xwindow.close();
											//}
										//});
							} else {
								Ext.MessageBox.alert('提示窗口', "您只能选择一个项目");
							}
					}
				}
			}, {
				text : '关闭',
				iconCls : 'smart_system_ui_logout',
				handler : function() {
					xwindow.close();
				}
			}]
		});
		xwindow.show();
	},
	pickAreaProject : function(multi, area, checked, callback) {
		var xwindow = new Smart.ui.Window({
			id : 'select_area_project_panel',
			area : area,
			checked : checked,
			title : "选择项目",
			width : (500),
			height : (400),
			autoLoad : {
				url : 'window/selectAreaProject.html',
				scripts : true
			},
			iconCls : 'icon-user',
			button : [{
				text : '添加',
				tooltip : '添加',
				iconCls : 'select',
				handler : function(btn) {
					var users = new Array();
					for (var i = 0; i < xwindow.sm.getSelections().length; i++) {
						users[users.length] = {
							id : xwindow.sm.getSelections()[i].data.kid,
							name : xwindow.sm.getSelections()[i].data.prjName
						};
					}
					var len = users.length;
					switch (len) {
						case 0 :
							Ext.MessageBox.alert('提示窗口', "请选择项目");
							break;
						case 1 :
							//Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
									//function(btn) {
										//if (btn == "yes") {
											callback(users);
											xwindow.close();
										//}
									//});
							break;
						default :
							if (multi) {
								//Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
										//function(btn) {
											//if (btn == "yes") {
												callback(users);
												xwindow.close();
											//}
										//});
							} else {
								Ext.MessageBox.alert('提示窗口', "您只能选择一个项目");
							}
					}
				}
			}, {
				text : '关闭',
				iconCls : 'smart_system_ui_logout',
				handler : function() {
					xwindow.close();
				}
			}]
		});
		xwindow.show();
	},
	selectUser : function(panel, btn, url, targetId) {
		var xwindow = panel.xwindow({
			id : 'select_user_panel',
			title : "选择成员",
			width : (500),
			height : (400),
			autoLoad : {
				url : 'window/selectUser.html',
				scripts : true
			},
			iconCls : 'icon-user',
			button : [{
				text : '添加',
				tooltip : '添加',
				iconCls : 'select',
				handler : function(btn) {
					var ids = new Array();
					for (var i = 0; i < xwindow.sm.getSelections().length; i++) {
						ids[ids.length] = xwindow.sm.getSelections()[i].data.kid;
					}
					if (ids.length > 0) {
						//Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
							//	function(btn) {
									//if (btn == "yes") {
										Ext.Ajax.request({
											url : url,
											success : function(result, request) {
												panel.getStore().load({
															params : {
																start : 0,
																limit : 20
															}
														});
												xwindow.close();
											},
											failure : function(resp, opts) {
												var json_response = Ext
														.decode(resp.responseText);
												Vega.app
														.showError(json_response.error);
											},
											params : {
												ids : Ext.encode(ids),
												targetId : targetId
											}
										});

									//}
								//})
					}
				}
			}, {
				text : '关闭',
				iconCls : 'smart_system_ui_logout',
				handler : function() {
					xwindow.close();
				}
			}]
		});
	},
	selectMenu : function(panel, btn, url, targetId) {
		var xwindow = panel.xwindow({
			id : 'select_menu_panel',
			title : "选择权限",
			width : (500),
			height : (400),
			autoLoad : {
				url : 'window/selectMenu.html',
				scripts : true
			},
			iconCls : 'icon-user',
			button : [{
				text : '添加',
				tooltip : '添加',
				iconCls : 'select',
				handler : function(btn) {
					var ids = new Array();
					for (var i = 0; i < xwindow.sm.getSelections().length; i++) {
						ids[ids.length] = xwindow.sm.getSelections()[i].data.kid;
					}
					if (ids.length > 0) {
						//Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
								//function(btn) {
									//if (btn == "yes") {
										Ext.Ajax.request({
											url : url,
											success : function(result, request) {
												panel.getStore().reload();
												xwindow.close();
											},
											failure : function(resp, opts) {
												var json_response = Ext
														.decode(resp.responseText);
												Vega.app
														.showError(json_response.error);
											},
											params : {
												ids : Ext.encode(ids),
												targetId : targetId
											}
										});

									//}
								//})
					}
				}
			}, {
				text : '关闭',
				iconCls : 'smart_system_ui_logout',
				handler : function() {
					xwindow.close();
				}
			}]
		});
	},
	userComboxTree : function(name, text) {
		return new Ext.ux.ComboBoxTree({
			width : 200,
			hiddenName : name,
			fieldLabel : text,
			allowBlank : false,
			tree : {
				xtype : 'treepanel',
				loader : new Ext.tree.TreeLoader({
							dataUrl : 'tree/data/loadTree.txt',
							listeners : {
								beforeload : function(treeLoader, node) {
									treeLoader.baseParams.pid = node.attributes.pid;
								}
							}
						}),
				root : {
					nodeType : 'async',
					id : '0',
					text : '东润集团',
					expanded : true,
					type : 'root',
					iconCls : 'root_node',
					pid : '0'
				}
			},
			selectNodeModel : 'exceptRoot'
		}, function callback(node) {

		});
	},
	pickUser : function(multi, callback) {
		var xwindow = new Smart.ui.Window({
			id : 'select_user_panel',
			title : "选择成员",
			width : (500),
			height : (400),
			autoLoad : {
				url : 'window/selectUser.html',
				scripts : true
			},
			iconCls : 'icon-user',
			button : [{
				text : '添加',
				tooltip : '添加',
				iconCls : 'select',
				handler : function(btn) {
					var users = new Array();
					for (var i = 0; i < xwindow.sm.getSelections().length; i++) {
						users[users.length] = {
							userid : xwindow.sm.getSelections()[i].data.kid,
							username : xwindow.sm.getSelections()[i].data.c1
						};
					}
					var len = users.length;
					switch (len) {
						case 0 :
							Ext.MessageBox.alert('提示窗口', "请选择人员");
							break;
						case 1 :
							//Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
									//function(btn) {
									//	if (btn == "yes") {
											callback(users);
											xwindow.close();
									//	}
									//});
							break;
						default :
							if (multi) {
								//Ext.MessageBox.confirm('确认窗口', '确认要加入选中的数据吗?',
										//function(btn) {
											//if (btn == "yes") {
												callback(users);
												xwindow.close();
											//}
										//});
							} else {
								Ext.MessageBox.alert('提示窗口', "您只能选择一个人员");
							}
					}
				}
			}, {
				text : '关闭',
				iconCls : 'smart_system_ui_logout',
				handler : function() {
					xwindow.close();
				}
			}]
		});
		xwindow.show();
	},
	importExcel : function(url,grid) {
		var importWindow = new Smart.ui.Window({
			id : 'import_project_panel',
			title : "选择文件",
			width : (330),
			height : (100),
			autoLoad : {
				url : 'window/file.html',
				scripts : true
			},
			iconCls : 'smart_pwms_ui_export_excel',
			button : [{
				text : '导入',
				tooltip : '导入',
				iconCls : 'select',
				handler : function(btn) {
					var top = Ext.getCmp("form_import_form");
					if (top.form.isValid()) {
						top.getForm().submit({
									url : url,
									params: {
										uploadfile:top.findById("excel").getValue()
									},
									success : function(form_instance_create,action) {
										grid.getStore().reload();
										importWindow.close();
									},
									waitMsg : '正在保存......',
									failure : function(form_instance_create,action) {
										json_response = action.result;
										Smart.app.showError(json_response.error);
									}
								});
					}
				}
			}, {
				text : '关闭',
				iconCls : 'smart_system_ui_logout',
				handler : function() {
					importWindow.close();
				}
			}]
		});
		importWindow.show();
	}
};

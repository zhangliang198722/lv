<script>
Ext.onReady(function() {
	Ext.namespace("Smart.app.dongrun.project");
	
	var formatTime = function(obj){
		return obj<10?"0"+obj:obj;
	}
	var DateField2String = function(obj){
		if(obj==null||obj==""){
			return "";
		}
		return obj.getFullYear()+"-"+formatTime((obj.getMonth()+1))+"-"+formatTime(obj.getDate())
	}
	Smart.app.dongrun.project.Action = function(grid) {
		var keys = new Array();
		keys[keys.length] = "id";
		Smart.app.dongrun.project.operator = function(params) {
			params = params || {
				title : '增加',
				iconCls : 'add',
				operator_action:'add'
			};
			var hiddenButton = (params.operator_action == "detail")
					? true
					: false;
			var xwindow = grid.xwindow({
				params : params,
				title : "驴驹信息 >"+(params.title),
				iconCls : (params.iconCls),
				width : 750,
				height : 600,
				id : 'smart:dongrun:project:add:window',
				hiddenButton : hiddenButton,
				autoLoad : {
					url : 'donkey/toAdd',
					params : params,
					scripts : true
				},
				submit : function() {
					var top = Ext.getCmp("form_project_edit_form");
					if (top.form.isValid()) {
						top.getForm().submit({
									url : 'donkey/add',
									params : params,
									success : function(form_instance_create,
											action) {
										grid.getStore().reload();
										xwindow.close();
									},
									waitMsg : '正在保存......',
									failure : function(form_instance_create,
											action) {
										json_response = action.result;
										Smart.app.showError(json_response.error);
									}
								});
					}
				}
			});
		};

		this.add = new Ext.Action({
					text : '增加',
					iconCls : 'add',
					handler : function() {
						Smart.app.dongrun.project.operator();
					}
				});

		this.remove = new Ext.Action({
			text : '删除',
			iconCls : 'remove',
			handler : function() {
				var ids = new Array();
				var selections = [];
				if (grid.selectRow)
					selections.push({
								data : grid.selectRow
							});
				else
					selections = grid.getSelectionModel().getSelections();
				for (var i = 0; i < selections.length; i++) {
					ids[ids.length] = selections[i].data["id"];
				}
				ids = ids.join(",")
				if (ids.length > 0) {
					Ext.MessageBox.confirm('确认窗口', '确认要删除选中的数据吗?',
							function(btn) {
								if (btn == "yes") {
									Ext.Ajax.request({
										url : 'donkey/delete',
										success : function(result, request) {
											grid.getStore().reload();
											// Ext.getCmp("smart:dongrun:model:xuanzhi:menu-tree-panel").getSelectionModel().getSelectedNode().reload();
										},
										failure : function(resp, opts) {
											var json_response = Ext
													.decode(resp.responseText);
											Smart.app
													.showError(json_response.error);
										},
										params : {
											ids : ids
										}
									});
								}
							})
				}
			}
		});
		this.modify = new Ext.Action({
					text : '修改',
					iconCls : 'modify',
					handler : function(data) {
						var params = {
							title : "修改",
							iconCls : "modify"
						};
						data = grid.selectRow || data;
						params.operator_action = "modify";
						for (var k = 0; k < keys.length; k++) {
							params[keys[k]] = data[keys[k]];
						}
						Smart.app.dongrun.project.operator(params);
					}
				});
		this.imports = new Ext.Action({
			text : '导入',
			iconCls : 'smart_pwms_ui_export_excel',
			handler : function(data) {
				windowUtil.importExcel("project/imports.do",grid);
			}
		});

		this.detail = new Ext.Action({
					text : '详细信息',
					iconCls : 'info',
					handler : function(data) {
						var params = {
							title : "详细信息",
							iconCls : "info"
						};
						data = grid.selectRow || data;
						params.operator_action = "detail";
						for (var k = 0; k < keys.length; k++) {
							params[keys[k]] = data[keys[k]];
						}
						Smart.app.dongrun.project.operator(params);
					}
				});
		var menu = new Ext.menu.Menu({
					items : grid.searchFieldMenu
				});
		this.getMenus = (function() {
			var menus = [];

				menus[menus.length] = this.add;
				menus[menus.length] = '-';
				menus[menus.length] = this.remove;
				menus[menus.length] = '-';
				menus[menus.length] = this.modify;
				menus[menus.length] = '-';
			menus[menus.length] = this.detail;
			return menus;
		}).createDelegate(this);

		this.getActions = (function() {
			var menus = [];

				menus[menus.length] = this.add;
				menus[menus.length] = '-';
				//menus[menus.length] = this.imports;
				//menus[menus.length] = '-';
				menus[menus.length] = this.remove;
				menus[menus.length] = '-';

				menus[menus.length] ="购买/出生日期：";
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'donkey:birth:begin:id'};
				menus[menus.length]="至";
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'donkey:birth:end:id'};
				menus[menus.length]="-";
				menus[menus.length] = '出售/死亡日期：';
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'donkey:death:begin:id'};
				menus[menus.length]="至";
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'donkey:death:end:id'};
				menus[menus.length]="-";
			menus[menus.length] = {
				text : '关键词: ',
				xtype : 'tbsplit',
				menu : grid.searchFieldMenu
			};
			menus[menus.length] = grid.searchInput;
			return menus;
			// return [this.add,'-',this.remove,'-',{text : '搜索:
			// ',xtype:'tbsplit',menu:grid.searchFieldMenu},grid.searchInput ];
		}).createDelegate(this);
	}

	Smart.app.dongrun.project.Grid = function(config) {
		this.store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'donkey/list'
							}),
					/*baseParams : {
						sort : 'cdate',
						dir : 'DESC'
					},*/
					remoteSort : true,
					sortInfo : {
						field : 'id',
						direction : 'DESC'
					},
					reader : new Ext.data.JsonReader({
								root : 'data',
								totalProperty : 'totalCount'
							}, [{
										name : 'id',
										mapping : 'id',
										type : 'string'
									},{
										name : 'donkeynum',
										mapping : 'donkeynum',
										type : 'string'
									}, {
										name : 'sex',
										mapping : 'sex',
										type : 'string'
									}, {
										name : 'birth',
										mapping : 'birth',
										type : 'string'
									}, {
										name : 'sex',
										mapping : 'sex',
										type : 'string'
									}, {
										name : 'house',
										mapping : 'house',
										type : 'string'
									}, {
										name : 'monterId',
										mapping : 'monterId',
										type : 'string'
									}, {
										name : 'fatherId',
										mapping : 'fatherId',
										type : 'string'
									}, {
										name : 'coat',
										mapping : 'coat',
										type : 'string'
									}, {
										name : 'deliverId',
										mapping : 'deliverId',
										type : 'string'
									}, {
										name : 'death',
										mapping : 'death',
										type : 'string'
									}, {
										name : 'deathCause',
										mapping : 'deathCause',
										type : 'string'
									}, {
										name : 'remark',
										mapping : 'remark',
										type : 'string'
									}
							]),
					listeners : {
						beforeload : {
							scope : this,
							fn : function(store, options) {
								var birthBegin = DateField2String(Ext.getCmp("donkey:birth:begin:id").getValue());
								var birthend = DateField2String(Ext.getCmp("donkey:birth:end:id").getValue());
								var deathBegin = DateField2String(Ext.getCmp("donkey:death:begin:id").getValue());
								var deathEnd = DateField2String(Ext.getCmp("donkey:death:end:id").getValue());
								var search = this.getSearchFields();
								if (search.length > 0) {
									options.params.where = search.join(" or ");
								} else {
									options.params.where = "1=1 ";
								}
								if(birthBegin!=""&&birthBegin){
									options.params.where += " and to_date(birth,'yyyy-mm-dd hh24:mi:ss') >= to_date('"+birthBegin+" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
								}
								if(birthend!=""&&birthend){
									options.params.where += " and to_date(birth,'yyyy-mm-dd hh24:mi:ss') <= to_date('"+birthend+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
								}
								if(deathBegin!=""&&deathBegin){
									options.params.where += " and to_date(death,'yyyy-mm-dd hh24:mi:ss') >= to_date('"+deathBegin+" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
								}
								if(deathEnd!=""&&deathEnd){
									options.params.where += " and to_date(death,'yyyy-mm-dd hh24:mi:ss') <= to_date('"+deathEnd+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
								}
							}
						}
					}
				});
		this.searchFieldMenu = new Ext.menu.Menu({
					items : [{
								text : '驴驹号',
								column : 'donkeynum',
								checked : true
							}, {
								text : '驴圈',
								column : 'house',
								checked : true
							}, {
								text : '毛色',
								column : 'coat',
								checked : true
							}, {
								text : '性别',
								column : 'sex',
								checked : true
							}]
				});

		this.searchInput = new Ext.app.SearchField({
					store : this.store,
					width : 220
				})

		this.actions = new Smart.app.dongrun.project.Action(this);
		this.sm = new Ext.grid.CheckboxSelectionModel();
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
				this.sm, {
					header : '驴驹号',
					dataIndex : 'donkeynum',
					sortable : true,
					width:50
					
				},{
					header : '驴圈',
					dataIndex : 'house',
					sortable : true,
					width:50
					
				}, {
					header : '性别',
					dataIndex : 'sex',
					sortable : true,
					width:40
				}, {
					header : '毛色',
					dataIndex : 'coat',
					sortable : true,
					width:50
				}, {
					header : '购买/出生日期',
					dataIndex : 'birth',
					sortable : true,
					width:50
				}, {
					header : '出售/死亡日期',
					dataIndex : 'death',
					sortable : true,
					width:50
				}

		]);
		this.paging = new Ext.PagingToolbar({
					pageSize : 20,
					store : this.store,
					buttonAlign:'right'
				//	displayInfo : true

				});
		var cfg = {
				
			viewConfig : {
				autoFill:true,
				forceFit : true,
				getRowClass : function(record, index) {
					var c = record.get('ex');
					if (c < 0) {
						return;
					} else if (c > 0) {
						return 'project_exc';
					}
				}
			},

			listeners : {
				rowdblclick : {
					scope : this,
					fn : function(client, rowIndex, e) {
						e.stopEvent();
						var data = client.getStore().getAt(rowIndex).data
					/*	this.actions.detail.execute(data);*/
//						if (havaAuth("menu", "project_op")&&!(data["bor_state"]==1||data["bor_state"]==3)) {
	        				this.actions.modify.execute(data);
//	        			}
//	        			else{
//	        				
//	        				this.actions.detail.execute(data);
//	        			}
					}
				},
				rowcontextmenu : {
					scope : this,
					fn : function(client, rowIndex, e) {
						e.stopEvent();
						var data = client.getStore().getAt(rowIndex).data
						client.selectRow = data;
						var menu = new Ext.menu.Menu({
									listeners : {
										beforehide : function() {
											client.selectRow = null;;
										}
									},
									items : client.actions.getMenus()
								});
						var xy = new Array();
						xy = e.getXY();
						if (xy[1] < 500) {
							menu.showAt(e.getXY());
						} else {
							xy[1] = xy[1] - 100;
							menu.showAt(xy);
						}
					}
				}
			},
			title : '驴驹信息',
			id : 'smart:dongrun:project:grid:panel:object',
			tbar : this.actions.getActions(),
			bbar : this.paging
		}
		config = Ext.applyIf(config || {}, cfg);
		Smart.app.dongrun.project.Grid.superclass.constructor
				.call(this, config);
	}
	Ext.extend(Smart.app.dongrun.project.Grid, Smart.ui.GridPanel, {
				getSearchFields : function() {
					var fields = new Array();
					var value = this.searchInput.getValue();
					if (value)
						this.searchFieldMenu.items.each(function(item) {
									if (item.checked) {
										fields[fields.length] = item.column
												+ " like '%" + value + "%'";
									}
								});
					return fields;
				}
			})
	var smart_app_dongrun_project_parent_cmp = mainPanel;

	var smart_app_dongrun_project_gp = new Smart.app.dongrun.project.Grid({
				renderTo : smart_app_dongrun_project_parent_cmp.body,
				height : smart_app_dongrun_project_parent_cmp.getInnerHeight(),
				width : smart_app_dongrun_project_parent_cmp.getInnerWidth()
			});
	smart_app_dongrun_project_gp.customLoad = function(node) {
		smart_app_dongrun_project_gp.getStore().load({
					params : {
						start : 0,
						limit : 20
					}
				});
	}
	smart_app_dongrun_project_gp.getStore().load({
				params : {
					start : 0,
					limit : 20
				}
			});
	mainPanel.children_panel = smart_app_dongrun_project_gp;

});
</script>
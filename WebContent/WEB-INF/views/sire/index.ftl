<script>
Ext.onReady(function() {
	Ext.namespace("Smart.app.sire.project");
	
	var formatTime = function(obj){
		return obj<10?"0"+obj:obj;
	}
	var DateField2String = function(obj){
		if(obj==null||obj==""){
			return "";
		}
		return obj.getFullYear()+"-"+formatTime((obj.getMonth()+1))+"-"+formatTime(obj.getDate())
	}
	
	Smart.app.sire.project.Action = function(grid) {
		var keys = new Array();
		keys[keys.length] = "id";
		Smart.app.sire.project.operator = function(params) {
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
				title : "繁殖记录 >"+(params.title),
				iconCls : (params.iconCls),
				width : 750,
				height : 600,
				id : 'smart:sire:project:add:window',
				hiddenButton : hiddenButton,
				autoLoad : {
					url : 'sire/toAdd',
					params : params,
					scripts : true
				},
				submit : function() {
					var top = Ext.getCmp("form_project_edit_form");
					if (top.form.isValid()) {
						top.getForm().submit({
									url : 'sire/add',
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
						Smart.app.sire.project.operator();
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
										url : 'sire/delete',
										success : function(result, request) {
											grid.getStore().reload();
											// Ext.getCmp("smart:sire:model:xuanzhi:menu-tree-panel").getSelectionModel().getSelectedNode().reload();
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
						Smart.app.sire.project.operator(params);
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
						Smart.app.sire.project.operator(params);
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
				
				menus[menus.length] ="产驴日期：";
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'sire:producedate:begin:id'};
				menus[menus.length]="至";
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'sire:producedate:end:id'};
				menus[menus.length]="-";
				menus[menus.length] = '流产日期：';
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'sire:miscarry:begin:id'};
				menus[menus.length]="至";
				menus[menus.length] = {xtype:'datefield',format:'Y-m-d',id:'sire:miscarry:end:id'};
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

	Smart.app.sire.project.Grid = function(config) {
		this.store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'sire/list'
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
										name : 'breedid',
										mapping : 'breedid',
										type : 'string'
									}, {
										name : 'producedate',
										mapping : 'producedate',
										type : 'string'
									}, {
										name : 'sex',
										mapping : 'sex',
										type : 'string'
									}, {
										name : 'miscarry',
										mapping : 'miscarry',
										type : 'string'
									}, {
										name : 'deathdate',
										mapping : 'deathdate',
										type : 'string'
									}, {
										name : 'deliverid',
										mapping : 'deliverid',
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
								var produceBegin = DateField2String(Ext.getCmp("sire:producedate:begin:id").getValue());
								var produceEnd = DateField2String(Ext.getCmp("sire:producedate:end:id").getValue());
								var miscarryBegin = DateField2String(Ext.getCmp("sire:miscarry:begin:id").getValue());
								var miscarryEnd = DateField2String(Ext.getCmp("sire:miscarry:end:id").getValue());
								var search = this.getSearchFields();
								if (search.length > 0) {
									options.params.where = search.join(" or ");
								} else {
									options.params.where = "1=1 ";
								}
								if(produceBegin!=""&&produceBegin){
									options.params.where += " and to_date(producedate,'yyyy-mm-dd hh24:mi:ss') >= to_date('"+produceBegin+" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
								}
								if(produceEnd!=""&&produceEnd){
									options.params.where += " and to_date(producedate,'yyyy-mm-dd hh24:mi:ss') <= to_date('"+produceEnd+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
								}
								if(miscarryBegin!=""&&miscarryBegin){
									options.params.where += " and to_date(miscarry,'yyyy-mm-dd hh24:mi:ss') >= to_date('"+miscarryBegin+" 00:00:00','yyyy-mm-dd hh24:mi:ss')";
								}
								if(miscarryEnd!=""&&miscarryEnd){
									options.params.where += " and to_date(miscarry,'yyyy-mm-dd hh24:mi:ss') <= to_date('"+miscarryEnd+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
								}
							}
						}
					}
				});
		this.searchFieldMenu = new Ext.menu.Menu({
					items : [{
								text : '母驴号',
								column : 'breedid',
								checked : true
							}, {
								text : '性别',
								column : 'sex',
								checked : true
							}, {
								text : '接产人',
								column : 'deliverid',
								checked : true
							}]
				});

		this.searchInput = new Ext.app.SearchField({
					store : this.store,
					width : 220
				})

		this.actions = new Smart.app.sire.project.Action(this);
		this.sm = new Ext.grid.CheckboxSelectionModel();
		this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),
				this.sm, {
					header : '，母驴号',
					dataIndex : 'breedid',
					sortable : true,
					width:50
					
				},{
					header : '产驴日期',
					dataIndex : 'producedate',
					sortable : true,
					width:50
					
				},{
					header : '性别',
					dataIndex : 'sex',
					sortable : true,
					width:50
					
				}, {
					header : '流产日期',
					dataIndex : 'miscarry',
					sortable : true,
					width:40
				}, {
					header : '死亡日期',
					dataIndex : 'deathdate',
					sortable : true,
					width:50
				}, {
					header : '接产人',
					dataIndex : 'deliverid',
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
			title : '繁殖记录',
			id : 'smart:sire:project:grid:panel:object',
			tbar : this.actions.getActions(),
			bbar : this.paging
		}
		config = Ext.applyIf(config || {}, cfg);
		Smart.app.sire.project.Grid.superclass.constructor
				.call(this, config);
	}
	Ext.extend(Smart.app.sire.project.Grid, Smart.ui.GridPanel, {
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
	var smart_app_sire_project_parent_cmp = mainPanel;

	var smart_app_sire_project_gp = new Smart.app.sire.project.Grid({
				renderTo : smart_app_sire_project_parent_cmp.body,
				height : smart_app_sire_project_parent_cmp.getInnerHeight(),
				width : smart_app_sire_project_parent_cmp.getInnerWidth()
			});
	smart_app_sire_project_gp.customLoad = function(node) {
		smart_app_sire_project_gp.getStore().load({
					params : {
						start : 0,
						limit : 20
					}
				});
	}
	smart_app_sire_project_gp.getStore().load({
				params : {
					start : 0,
					limit : 20
				}
			});
	mainPanel.children_panel = smart_app_sire_project_gp;

});
</script>
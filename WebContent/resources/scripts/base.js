String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substring(this.length - str.length) == str)
		return true;
	else
		return false;
	return true;
}
String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
}
havaAuth = function(type, kcode) {
	var temp = false;
	var resources = auth[type];
	var l = resources.length;
	if (l <= 0) {
		return false;
	}
	// var regex = new RegExp("/" + kcode + "\\w*/");
	eval("var regex = /^" + kcode + "/;");
	for (var i = 0; i < l; i++) {
		if (resources[i] == null) {
			continue;
		}
		if (regex.test(resources[i].kcode)) {
			temp = true;
			break;
		}
	}
	return temp;
}
Ext.namespace("Smart.ui");
Smart.ui.MenuPanel = function() {
	Smart.ui.MenuPanel.superclass.constructor.call(this, {
				id : 'smart:ui:menu:panel',
				region : 'west',
				title : "\u529F\u80FD\u83DC\u5355",
				split : true,
				width : 185,
				minSize : 150,
				listeners : {
					beforeexpand : {
						scope : this,
						fn : function(panel, animate) {
							if (panel.isMain) {
								return false;
							}
							return true;
						}
					},
					expand : {
						scope : this,
						fn : function(panel, animate) {
							if (panel.isMain) {
								return false;
							}
							return true;
						}
					},
					bodyresize : function(panel, width, height) {
						try {
							panel.children_panel.setSize(width, height);
						} catch (e) {
						}
					}
				},
				collapsed : true,
				layout : "fit",
				maxSize : 300,
				margins : '0 0 0 2',
				collapsible : true
			});
}
Ext.extend(Smart.ui.MenuPanel, Ext.Panel, {
			loadMenu : function(obj) {
				this.expand(false);
				this.setTitle(obj.text + "\u89C6\u56FE");
				this.load({
							url : obj.url,
							scripts : true
						});
			}
		});
Smart.ui.MainPanel = function() {
	Smart.ui.MainPanel.superclass.constructor.call(this, {
				id : 'smart:ui:main:panel',
				layout : "fit",
				region : 'center',
				viewConfig : {
					forceFit : true
				},
				listeners : {
					bodyresize : function(panel, width, height) {
						try {
							panel.children_panel.setSize(width, height);
						} catch (e) {
						}
					}
				}
			});
}
Ext.extend(Smart.ui.MainPanel, Ext.Panel, {
			loadContent : function(obj) {
				if (!obj) {
					this.load({
								url : this.content,
								scripts : true
							});
					this.url = null;
					return;
				}
				this.load({
							url : obj.content,
							scripts : true
						});
			},
			window : function(config) {
				var config = config || {};
				var autoScroll = config.autoScroll || false;
				var bolder = config.bolder || false;
				var autoWidth = config.autoWidth || false;
				var wid = "open_windows";
				wid = config.wid || wid;
				var conf = {
					obj : this,
					id : wid,
					autoWidth : autoWidth,
					autoScroll : autoScroll,
					bolder : bolder,
					autoLoad : {
						url : config.url,
						params : config.params,
						scripts : true
					}
				}

				config = Ext.applyIf(config || {}, conf);
				if (this._xwindow) {
					this._xwindow.destroy();
					this._xwindow.remove();
				}
				this._xwindow = new Smart.ui.Window(config);
				this._xwindow.show();
				return this._xwindow;
			}
		});

Smart.ui.HelpPanel = function() {
	Smart.ui.HelpPanel.superclass.constructor.call(this, {
				id : 'smart:ui:help:panel',
				region : 'east',
				title : "\u5E2E\u52A9\u9762\u677F",
				collapseMode : 'mini',
				split : true,
				collapsed : true,
				autoScroll : true,
				width : 225,
				margins : '0 0 0 0',
				layout : 'fit',
				collapsible : true
			});
}
Ext.extend(Smart.ui.HelpPanel, Ext.Panel, {

});
Ext.namespace("Smart.app");

Smart.app.showInfo = function(msg) {
	Ext.MessageBox.show({
				title : '\u63D0\u793A\u4FE1\u606F',
				msg : msg,
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.INFO
			});
}
Smart.app.showError = function(msg) {
	Ext.MessageBox.show({
				title : '\u9519\u8BEF\u4FE1\u606F',
				msg : msg,
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.ERROR
			});
}
Smart.app.loadComponts = function(obj) {
	if (obj.scripts && obj.scripts.length > 0) {
		ScriptMgr.load({
					scripts : obj.scripts,
					callback : function() {
						Ext.getCmp("smart:ui:menu:panel").loadMenu(obj);
						Ext.getCmp("smart:ui:main:panel").loadContent(obj);
					}
				});
	} else {
		Ext.getCmp("smart:ui:menu:panel").loadMenu(obj);
		Ext.getCmp("smart:ui:main:panel").loadContent(obj);
	}
}
Smart.app.loadMainPanel = function(obj) {
	if (obj.scripts && obj.scripts.length > 0) {
		ScriptMgr.load({
					scripts : obj.scripts,
					callback : function() {
						Ext.getCmp("smart:ui:main:panel").loadContent(obj);
					}
				});
	} else {
		Ext.getCmp("smart:ui:main:panel").loadContent(obj);
	}
}
Smart.ui.Window = function(config) {
	var config = config || {};
	var id = config.id || "smart.ui.Window";
	var obj = config.obj || Ext.getCmp("main_panel");
	var buttons = config.button || [{
				id:id+'_btn_save',
				iconCls : 'system_datasources_save',
				text : '\u4FDD\u5B58',
				handler : function() {
					try {
						config.submit()
					} catch (e) {
					}
				}
			}, {
				iconCls : 'smart_system_ui_logout',
				text : '\u5173\u95ED',
				scope : this,
				handler : function() {
					try {
						this.close();
						this.destroy();
						this.remove();
					} catch (e) {
					}
				}
			}];
	if (config.hiddenButton) {
		var tmp = new Ext.Button({
			id:id+'_btn_save'
		});
		buttons = [{
					iconCls : 'smart_system_ui_logout',
					text : '\u5173\u95ED',
					scope : this,
					handler : function() {
						try {
							this.close();
							this.destroy();
							this.remove();
						} catch (e) {
						}
					}
				}];
	}
	if (config.hiddenAllButton) {
		buttons = null;
	}
	var cfg = {
		id : id,
		modal : (config.modal || true),
		title : (config.title || "\u6A21\u5F0F\u7A97\u53E3"),
		plain : (config.plain || false),
		resizable : (config.resizable || true),
		autoScroll : (config.autoScroll || true),
		buttonAlign : (config.buttonAlign || 'center'),
		layout : "fit",
		draggable : (config.draggable || true),
		buttons : buttons,
		border : (config.border || false),
		width : (config.width || 1024),
		height : (config.height || mainPanel.getInnerHeight() + 70)
	}
	config = Ext.applyIf(config || {}, cfg);
	Smart.ui.Window.superclass.constructor.call(this, config);
}
Ext.extend(Smart.ui.Window, Ext.Window, {

})
Smart.ui.GridPanel = function(config) {
	Smart.ui.GridPanel.superclass.constructor.call(this, config);
	this.on('rowcontextmenu', function(client, rowIndex, e) {
		e.stopEvent();
			// client.getSelectionModel().selectRow(rowIndex);
		}, this);
}

Ext.extend(Smart.ui.GridPanel, Ext.grid.GridPanel, {
	iconCls : 'grid',
	enableExport : false,
	trackMouseOver : false,
	menuDisabled : true,
	enableDragDrop : false,
	enableColumnMove : false,
	loadMask : {
		msg : "\u52A0\u8F7D\u4E2D,\u8BF7\u7A0D\u5019..."
	},
	border : false,
	initComponent : function() {
		Smart.ui.GridPanel.superclass.initComponent.call(this);
	},
	xwindow : function(config) {
		var config = config || {};
		config = Ext.applyIf(config || {}, {
					obj : this
				});
		if (this._xwindow) {
			this._xwindow.destroy();
			this._xwindow.remove();
		}
		this._xwindow = new Smart.ui.Window(config);
		this._xwindow.show();
		this._xwindow.on("beforeclose", function() {
			var isClose = true;
			if (!this.hiddenButton
					&& this.children
					&& (this.children.userForm.getForm().isDirty() || this.children.userGrid.change)) {
				Ext.MessageBox
						.confirm(
								"\u5173\u95ed\u63d0\u793a",
								"\u5355\u636e\u5c1a\u672a\u4fdd\u5b58,\u5173\u95ed\u540e\u60a8\u7684\u66f4\u6539\u5c06\u65e0\u6548,\u60a8\u786e\u5b9a\u8981\u5173\u95ed\u5417?",
								function(btn) {
									if (btn == "yes") {
										this.doClose();
									}
								}, this)
				isClose = false;
			}
			return isClose;
		}, this._xwindow);
		return this._xwindow;
	},
	xDetail : function(config) {
		var config = config || {};
		config.bodyStyle = 'background-color: #FFFFFF'
		config.exportDocument = config.exportDocument || function(type) {
			try {
				var wait = Ext.Msg.wait('\u6B63\u5728\u5BFC\u51FA...',
						'\u8BF7\u7A0D\u7B49');
				Ext.Ajax.request({
							url : config.autoLoad.url,
							success : function(result, request) {
								wait.getDialog().close();
								var fromDom = Ext.getDom("smart:pwms:export")
								fromDom.content.value = result.responseText;
								fromDom.fileName.value = config.title + type;
								fromDom.submit();
							},
							failure : function(resp, opts) {
								wait.getDialog().close();
								var json_response = Ext
										.decode(resp.responseText);
								Smart.app.showError(json_response.error);
							},
							params : config.autoLoad.params
						});
			} catch (e) {
			}
		}
		config.hiddenButton = true;
		var win = this;
		config.tbar = ['->', {
					text : '\u5BFC\u51FA',
					iconCls : 'smart_system_ui_export',
					menu : new Ext.menu.Menu({
								items : [{
											text : '\u5BFC\u51FAEXCEL',
											value : '.xls',
											iconCls : 'smart_pwms_ui_export_excel',
											handler : function() {
												config
														.exportDocument(this.value);
											}
										}, {
											text : '\u5BFC\u51FAWORD',
											value : '.doc',
											iconCls : 'smart_pwms_ui_export_word',
											handler : function() {
												config
														.exportDocument(this.value);
											}
										}, {
											text : '\u5BFC\u51FAPDF',
											value : '.pdf',
											iconCls : 'smart_pwms_ui_export_pdf',
											handler : function() {
												config
														.exportDocument(this.value);
											}
										}]
							})
				}, '-', {
					text : '\u6253\u5370',
					disabled : true,
					iconCls : 'smart_pwms_ui_print',
					handler : function() {
						try {
							var wait = Ext.Msg.wait(
									'\u6b63\u5728\u5bfc\u51fa...',
									'\u8bf7\u7a0d\u7b49');
							Ext.Ajax.request({
								url : config.autoLoad.url,
								success : function(result, request) {
									wait.getDialog().close();
									var fromDom = Ext
											.getDom("smart:pwms:print")
									fromDom.content.value = result.responseText;
									fromDom.submit();
								},
								failure : function(resp, opts) {
									wait.getDialog().close();
									var json_response = Ext
											.decode(resp.responseText);
									Smart.app.showError(json_response.error);
								},
								params : config.autoLoad.params
							});
						} catch (e) {
						}
					}
				}]
		config = Ext.applyIf(config || {}, {
					obj : this
				});
		if (this._xwindow) {
			this._xwindow.destroy();
			this._xwindow.remove();
		}
		this._xwindow = new Smart.ui.Window(config);
		this._xwindow.show();
		return this._xwindow;
	}
})

Smart.ui.EditorGridPanel = Ext.extend(Ext.grid.EditorGridPanel, {
			iconCls : 'grid',
			trackMouseOver : false,
			enableDragDrop : false,
			enableColumnMove : false,
			xwindow : function(config) {
				var config = config || {};
				config = Ext.applyIf(config || {}, {
							obj : this
						});
				if (this._xwindow) {
					this._xwindow.destroy();
					this._xwindow.remove();
				}
				this._xwindow = new Smart.ui.Window(config);
				this._xwindow.show();
				return this._xwindow;
			}
		})

ScriptLoader = function() {
	this.timeout = 30;
	this.scripts = [];
	this.disableCaching = false;
	this.loadMask = null;
};

ScriptLoader.prototype = {
	showMask : function() {
		if (!this.loadMask) {
			this.loadMask = new Ext.LoadMask(Ext.getBody());
			this.loadMask.show();
		}
	},

	hideMask : function() {
		if (this.loadMask) {
			this.loadMask.hide();
			this.loadMask = null;
		}
	},

	processSuccess : function(response) {
		this.scripts[response.argument.url] = true;
		window.execScript ? window.execScript(response.responseText) : window
				.eval(response.responseText);
		if (response.argument.options.scripts.length == 0) {
			this.hideMask();
		}
		if (typeof response.argument.callback == 'function') {
			response.argument.callback.call(response.argument.scope);
		}
	},
	processFailure : function(response) {
		this.hideMask();
		Ext.MessageBox.show({
					title : 'Application Error',
					msg : 'Script library could not be loaded.',
					closable : false,
					icon : Ext.MessageBox.ERROR,
					minWidth : 200
				});
		setTimeout(function() {
					Ext.MessageBox.hide();
				}, 3000);
	},
	load : function(url, callback) {
		var cfg, callerScope;
		if (typeof url == 'object') { // must be config object
			cfg = url;
			url = cfg.url;
			callback = callback || cfg.callback;
			callerScope = cfg.scope;
			if (typeof cfg.timeout != 'undefined') {
				this.timeout = cfg.timeout;
			}
			if (typeof cfg.disableCaching != 'undefined') {
				this.disableCaching = cfg.disableCaching;
			}
		}

		if (this.scripts[url]) {
			if (typeof callback == 'function') {
				callback.call(callerScope || window);
			}
			return null;
		}

		this.showMask();

		Ext.Ajax.request({
					url : url,
					success : this.processSuccess,
					failure : this.processFailure,
					scope : this,
					timeout : (this.timeout * 1000),
					disableCaching : this.disableCaching,
					argument : {
						'url' : url,
						'scope' : callerScope || window,
						'callback' : callback,
						'options' : cfg
					}
				});
	}
};

ScriptLoaderMgr = function() {
	this.loader = new ScriptLoader();

	this.load = function(o) {
		if (!Ext.isArray(o.scripts)) {
			o.scripts = [o.scripts];
		}

		o.url = o.scripts.shift();

		if (o.scripts.length == 0) {
			this.loader.load(o);
		} else {
			o.scope = this;
			this.loader.load(o, function() {
						this.load(o);
					});
		}
	};
};

ScriptMgr = new ScriptLoaderMgr();
Ext.app.SearchField = Ext.extend(Ext.form.TwinTriggerField, {
	initComponent : function() {
		Ext.app.SearchField.superclass.initComponent.call(this);
		this.on('specialkey', function(f, e) {
					if (e.getKey() == e.ENTER) {
						this.onTrigger2Click();
					}
				}, this);
	},

	validationEvent : false,
	validateOnBlur : false,
	trigger1Class : 'x-form-clear-trigger',
	trigger2Class : 'x-form-search-trigger',
	hideTrigger1 : true,
	width : 180,
	hasSearch : false,
	paramName : 'query',
	onTrigger1Click : function() {
		if (this.hasSearch) {
			this.el.dom.value = '';
			var limit = (this.store.lastOptions.params && this.store.lastOptions.params.limit)
					|| 20;
			var o = {
				start : 0,
				limit : limit
			};
			this.store.baseParams = this.store.baseParams || {};
			this.store.baseParams[this.paramName] = '';
			this.store.reload({
						params : o
					});
			this.triggers[0].hide();
			this.hasSearch = false;
		}
	},

	onTrigger2Click : function() {
		var v = this.getRawValue();
		//为了用searchField查询时可以带有其他查询条件，屏蔽空查询。miaojinliang-2013-3-16 14:52:08
//		if (v.length < 1) {
//			this.onTrigger1Click();
//			//return;
//		}
		var limit = (this.store.lastOptions.params && this.store.lastOptions.params.limit)
				|| 20;
		var o = {
			start : 0,
			limit : limit
		};
		this.store.baseParams = this.store.baseParams || {};
		this.store.baseParams[this.paramName] = v;
		this.store.reload({
					params : o
				});
		this.hasSearch = true;
		this.triggers[0].show();
	}
});

Ext.override(Ext.form.BasicForm, {
	findField : function(id) {
		var field = this.items.get(id);
		if (!field) {
			this.items.each(function(f) {
				if (f.isXType('radiogroup') || f.isXType('checkboxgroup')) {
					f.items.each(function(c) {
								if (c.isFormField
										&& (c.dataIndex == id || c.id == id || c
												.getName() == id)) {
									field = c;
									return false;
								}
							});
				}

				if (f.isFormField
						&& (f.dataIndex == id || f.id == id || f.getName() == id)) {
					field = f;
					return false;
				}
			});
		}
		return field || null;
	}
});

Ext.ux.ComboBoxTree = function() {
	this.treeId = Ext.id() + '-tree';
	this.maxHeight = arguments[0].maxHeight || arguments[0].height
			|| this.maxHeight;
	this.tpl = new Ext.Template('<tpl for="."><div style="height:'
			+ this.maxHeight + 'px"><div id="' + this.treeId
			+ '"></div></div></tpl>');
	this.store = new Ext.data.SimpleStore({
				fields : [],
				data : [[]]
			});
	this.selectedClass = '';
	this.mode = 'local';
	this.triggerAction = 'all';
	this.onSelect = Ext.emptyFn;
	this.editable = false;
	this.callback = arguments[1];
	this.selectNodeModel = arguments[0].selectNodeModel || 'exceptRoot';

	this.addEvents('afterchange');
	Ext.ux.ComboBoxTree.superclass.constructor.apply(this, arguments);
}

Ext.extend(Ext.ux.ComboBoxTree, Ext.form.ComboBox, {
	expand : function() {
		Ext.ux.ComboBoxTree.superclass.expand.call(this);
		if (this.tree.rendered) {
			return;
		}
		Ext.apply(this.tree, {
					height : this.maxHeight,
					border : false,
					autoScroll : true
				});
		if (this.tree.xtype) {
			this.tree = Ext.ComponentMgr.create(this.tree, this.tree.xtype);
		}
		this.tree.render(this.treeId);
		var root = this.tree.getRootNode();
		if (!root.isLoaded())
			root.reload();
		this.tree.expand();
		this.tree.on('click', function(node) {
					var selModel = this.selectNodeModel;

					var isLeaf = node.isLeaf();
					if ((node == root) && selModel != 'all') {
						return;
					} else if (selModel == 'folder' && isLeaf) {
						return;
					} else if (selModel == 'leaf' && !isLeaf) {
						return;
					} else if (typeof selModel == "object") {
						for (var i in selModel) {
							if (selModel[i] == eval("node." + i))
								return;
						}
					}

					var oldNode = this.getNode();
					if (this.fireEvent('beforeselect', this, node, oldNode) !== false) {
						this.setValue(node);
						this.collapse();

						this.fireEvent('select', this, node, oldNode);
						(oldNode !== node) ? this.fireEvent('afterchange',
								this, node, oldNode) : '';
					}
				}, this);
	},
	setValue : function(node) {
		try {
			var id = node.attributes.id;
			if (id == '0') {
				this.clearValue();
			} else {
				this.node = node;
				var text = node.text;
				this.lastSelectionText = text;
				if (this.hiddenField) {
					this.hiddenField.value = id;
				}
				this.value = id;
				if (this.callback) {
					this.callback(node);
				}
				Ext.form.ComboBox.superclass.setValue.call(this, text);
			}
		} catch (e) {
		}
	},
	initValue : function() {
		Ext.form.ComboBox.superclass.initValue.call(this, this.value);
	},
	getValue : function() {
		return typeof this.value != 'undefined' ? this.value : '';
	},

	getNode : function() {
		return this.node;
	},

	clearValue : function() {
		Ext.ux.ComboBoxTree.superclass.clearValue.call(this);
		this.node = null;
	},

	// private
	destroy : function() {
		Ext.ux.ComboBoxTree.superclass.destroy.call(this);
		Ext.destroy([this.node, this.tree]);
		delete this.node;
	}
});

Ext.reg('combotree', Ext.ux.ComboBoxTree);

Ext.namespace("Smart.app.project.Document");
Smart.app.project.Document.Action = function(grid) {
	this.add = new Ext.Action({
		text : '添加文档',
		iconCls : 'add',
		handler : function() {
			var params = {};
			// params.PROJECT_ID = grid.CUSTOM_PROPS.PROJECT_ID;
			params.TASK_ID = grid.CUSTOM_PROPS.TASK_ID;
			// params.PMSN_ID = grid.CUSTOM_PROPS.PMSN_ID;
			var xwindow = grid.xwindow({
				title : "添加文档",
				iconCls : "add",
				width : 600,
				height : 350,
				autoLoad : {
					url : grid.CUSTOM_PROPS.ADD_URL,
					params : params,
					scripts : true
				},
				submit : function() {
					var top = Ext.getCmp("form_Document_edit_form");
					if (top.form.isValid()) {
						top.getForm().submit({
							url : grid.CUSTOM_PROPS.SAVE_URL,
							params : params,
							success : function(form_instance_create, action) {
								xwindow.close();
								grid.getStore().baseParams.TASK_ID = grid.CUSTOM_PROPS.TASK_ID;
								grid.getStore().load({
											params : {
												start : 0,
												limit : 20
											}
										});
							},
							waitMsg : '正在保存......',
							failure : function(form_instance_create, action) {
								json_response = action.result;
								Smart.app.showError(json_response.error);
							}
						});
					}
				}
			});
		}
	});
	this.remove = new Ext.Action({
		text : '删除文档',
		iconCls : 'remove',
		handler : function() {
			var ids = new Array();
			var selections = grid.getSelectionModel().getSelections();
			for (var i = 0; i < selections.length; i++) {
				var data = selections[i].data;
				ids[ids.length] = data.KID;
			}
			if (ids.length > 0) {
				Ext.MessageBox.confirm('确认窗口', '确认要删除选中的数据吗?', function(btn) {
					if (btn == "yes") {
						Ext.Ajax.request({
									url : grid.CUSTOM_PROPS.REMOVE_URL,
									success : function(result, request) {
										grid.getStore().load({
													params : {
														start : 0,
														limit : 20
													}
												});
									},
									failure : function(resp, opts) {
										var json_response = Ext
												.decode(resp.responseText);
										Smart.app
												.showError(json_response.error);
									},
									params : {
										ids : Ext.encode(ids),
										TASK_ID : grid.CUSTOM_PROPS.TASK_ID
									}
								});
					}
				})
			}
		}
	});

	// showVersions = function(record){
	// var params = {};
	// params.DOC_UUID = record;
	// var xwindow = grid.xwindow({
	// title:"历史版本信息",
	// iconCls : "info",
	// width:800,
	// height:450,
	// hiddenAllButton:true,
	// autoLoad:{url:grid.CUSTOM_PROPS.VERSION_URL,params:params,scripts:true}
	// });
	// }
	// this.renderForVersion = function(value, cellmeta, record){
	// value = ((value || value.length>0)?value:"项目文档") +
	// "("+record.data["VERSION"]+")"
	// if(record.data["VERSION"] > 1){
	// return '<a title="查看版本信息"
	// onClick="showVersions(\''+record.data["DOC_UUID"]+'\')"
	// target="_balnk">'+value+'</a>';
	// }
	// return value;
	// }
	this.renderForDownload = function(value, cellmeta, record) {
		return '<img valign="bottom" src="' + record.data.DOC_ICON
				+ '"/><a href="' + grid.CUSTOM_PROPS.DOWNLOAD_URL + '?kid='
				+ record.data["KID"]
				+ '" target="_balnk" title="下载' + value + '">' + value + '</a>';
	}
	this.renderForLength = function(value, cellmeta, record) {
		return formatBytes(value);
	}
	this.getActions = (function() {
		if (grid.CUSTOM_PROPS.ACTION == 'modify') {
			return ['文档列表', '->', '-', this.add, '-', this.remove];
		} else if (grid.CUSTOM_PROPS.ACTION == 'view') {
			return ['文档列表'];
		} else if (grid.CUSTOM_PROPS.ACTION == 'add') {
			return ['文档列表', '->', '-', this.add];
		} else if (grid.CUSTOM_PROPS.ACTION == 'delete') {
			return ['文档列表', '->', '-', this.remove];
		}
		return [];

	}).createDelegate(this);
}

Smart.app.project.Document.Grid = function(config) {
	this.CUSTOM_PROPS = config;
	this.store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : config.LIST_DOCUMENT_URL
						}),
				baseParams : {
					TASK_ID : config.TASK_ID
				},
				remoteSort : true,
				reader : new Ext.data.JsonReader({
							root : 'records',
							totalProperty : 'totalCount'
						}, [{
									name : 'KID',
									mapping : 'kid',
									type : 'string'
								}, {
									name : 'NAME',
									mapping : 'name',
									type : 'string'
								}, {
									name : 'DOC_NAME',
									mapping : 'doc_name',
									type : 'string'
								},
								// {name:'VERSION',mapping:'VERSION',type:'long'},
								{
									name : 'DOC_TYPE',
									mapping : 'doc_type',
									type : 'string'
								},{
									name : 'KNAME',
									mapping : 'kname',
									type : 'string'
								}, {
									name : 'DOC_LENGTH',
									mapping : 'doc_length',
									type : 'string'
								}, {
									name : 'DOC_CONTENT_TYPE',
									mapping : 'doc_content_type',
									type : 'string'
								}, {
									name : 'DOC_ICON',
									mapping : 'doc_icon',
									type : 'string'
								},
								// {name:'DOC_UUID',mapping:'DOC_UUID',type:'string'},
								{
									name : 'FILE_NAME',
									mapping : 'file_name',
									type : 'string'
								}, {
									name : 'CREATER',
									mapping : 'creater',
									type : 'string'
								}, {
									name : 'CREATE_TIME',
									mapping : 'create_time',
									type : 'string'
								}, {
									name : 'OPERATER',
									mapping : 'operater',
									type : 'string'
								}, {
									name : 'FILE_PATH',
									mapping : 'file_path',
									type : 'string'
								},
								// {name:'PMSN_NAME',mapping:'PMSN_NAME',type:'string'},
								{
									name : 'REMARK',
									mapping : 'remark',
									type : 'string'
								}]),
				listeners : {
					beforeload : {
						scope : this,
						fn : function(store, options) {

						}
					}
				}
			});
	this.actions = new Smart.app.project.Document.Action(this);

	this.sm = new Ext.grid.CheckboxSelectionModel();
	this.cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
				header : '名称',
				dataIndex : 'NAME',
				sortable : true,
				renderer : this.actions.renderForDownload
			}, {
				header : '文档名称',
				dataIndex : 'DOC_NAME',
				sortable : true,
				renderer : this.actions.renderForDownload
			}, {
				header : '文件名称',
				dataIndex : 'FILE_NAME',
				sortable : true,
				renderer : this.actions.renderForDownload
			}, {
				header : '文件大小',
				dataIndex : 'DOC_LENGTH',
				sortable : true,
				hidden : true,
				renderer : this.actions.renderForLength
			}, {
				header : '文档类型',
				dataIndex : 'KNAME',
				sortable : true,
				hidden : true
			}, {
				header : '创建人',
				dataIndex : 'CREATER',
				sortable : true
			}, {
				header : '创建时间',
				dataIndex : 'CREATE_TIME',
				sortable : true,
				renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')
			}

	]);

	var cfg = {
		viewConfig : {
			forceFit : true
		},
		listeners : {
			rowcontextmenu : {
				scope : this,
				fn : function(client, rowIndex, e) {
					e.stopEvent();
				}
			}
		},
		menuDisabled : true,
		resizable : false,
		autoHeight : true,
		trackMouseOver : false,
		loadMask : {
			msg : "读取中..."
		},
		border : true
	}
	var tbar = this.actions.getActions();
	if (tbar.length > 0) {
		cfg.tbar = tbar;
	}
	config = Ext.apply(cfg || {}, config);
	Smart.app.project.Document.Grid.superclass.constructor.call(this, config);
}
Ext.extend(Smart.app.project.Document.Grid, Smart.ui.GridPanel, {

})
Ext.namespace('Smart.data');
Smart.data.DicStore = function(config) {
	config.autoDestroy = true;
	config.baseParams = {
		pid : config.pid
	};
	config.url = '../project/pms/dict/getByPid.action';
	config.storeId = config.pid;
	config.root = 'data';
	config.idProperty = 'kid';
	config.fields = ['kid', 'kname', 'kvalue', 'kcode'];

	Smart.data.DicStore.superclass.constructor.call(this, config);
}
Ext.extend(Smart.data.DicStore, Ext.data.JsonStore, {

})

formatBytes = function(size) {
	if (!size) {
		size = 0;
	}
	var suffix = ["B", "KB", "MB", "GB"];
	var result = size;
	size = parseInt(size, 10);
	result = size + " " + suffix[0];
	var loop = 0;
	while (size / 1024 > 1) {
		size = size / 1024;
		loop++;
	}
	result = size.toFixed(2) + " " + suffix[loop];
	return result;

	if (isNaN(bytes)) {
		return ('');
	}

	var unit, val;

	if (bytes < 999) {
		unit = 'B';
		val = (!bytes && this.progressRequestCount >= 1) ? '~' : bytes;
	} else if (bytes < 999999) {
		unit = 'kB';
		val = (bytes / 1000).toFixed(2);
	} else if (bytes < 999999999) {
		unit = 'MB';
		val = ((bytes / 100000) / 10).toFixed(2);
	} else if (bytes < 999999999999) {
		unit = 'GB';
		val = ((bytes / 100000000) / 10).toFixed(2);
	} else {
		unit = 'TB';
		val = ((bytes / 100000000000) / 10).toFixed(2);
	}

	return (val + ' ' + unit);
}

Ext.apply(Ext.form.VTypes, {
			confirmPwd : function(val, field) {
				if (field.confirmPwd) {
					var firstPwdId = field.confirmPwd.first;
					var secondPwdId = field.confirmPwd.second;
					this.firstField = Ext.getCmp(firstPwdId);
					this.secondField = Ext.getCmp(secondPwdId);
					var firstPwd = this.firstField.getValue();
					var secondPwd = this.secondField.getValue();
					if (firstPwd == secondPwd) {
						return true;
					} else {
						return false;
					}
				}
			},
			confirmPwdText : '两次输入的密码不一致!'
		});

		
/*		//自动完成类
Smart.ux.AutoComplete = Ext.extend(Ext.form.ComboBox,{
	typeAhead:false,
	loadingText:'载入中...',
	displayField:'user',
	hideTrigger:true,
	minChars:1,
	simple:false,
	valueField:'user',
	multi:false,
	store:{
		xtype:'jsonstore',
		url:'index.php/mail/auto_user',
		autoDestroy:true,
		idProperty:'id',
		totalProperty:'total',
		root:'records',
		fields:['kid','c1','c3']
	},
	tpl:new Ext.XTemplate(
		'<div class="auto-item">',
		'<h3>{kid}<a class="auto-fld1">{c1}</a>({c3})</h3>',
		'</div>'
	),
	itemSelector:'div.auto-item',

	initComponent:function()
	{
		if(this.simple){
			this.tpl = new Ext.XTemplate(
					'<div class="auto-item">',
					'<h3>{kid}<a class="auto-fld1">{c1}</a>({c3})</h3>',
					'</div>'
				);
		}
		Ext.ux.AutoComplete.superclass.initComponent.call(this);
	},
	onSelect : function(record, index)
	{
		var value = this.getElValue();
		if (this.multi) {//多个输入
			value += record.data['username']+'@'+record.data['realname'] + ';';
		}else{
			value = record.data['username']+'@'+record.data['realname'];
		}
		if(this.fireEvent('beforeselect', this, record, index) !== false){
            this.setValue(value);
            this.collapse();
            this.fireEvent('select', this, record, index);
        }
	},
	// private
	getElValue:function()
	{
		var v = this.el.dom.value;
		if(typeof(v)=='string' && v.length>0){
			var result = /\w+\@[^;,\s]+/ig.exec(v);
			if(result)
				return result.join(';')+';';
		}
		return '';
	}
});
Ext.reg('xauto',Smart.ux.AutoComplete);
*/
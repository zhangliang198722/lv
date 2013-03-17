<script type="text/javascript">
Ext.onReady(function() {
	var parentElement = Ext.getCmp("smart:sire:project:add:window");
	var tbDisabled = true;
	var props = {};
	props.ADD_URL = "window/document.html";
	props.SAVE_URL = "../project/pms/doc/saveTaskDocument.action";
	props.REMOVE_URL = "../project/pms/doc/removeDocument.action";
	props.DOWNLOAD_URL = "../project/pms/doc/download.action";
	props.LIST_DOCUMENT_URL = "../project/pms/doc/listTaskDocument.action";
	props.TASK_ID = parentElement.params.id;
    props.ACTION = "view";
  
	var cfg = {
		border : false,
		disabled : tbDisabled,
		title : '附件',
		height : 400
	}
	props = Ext.applyIf(props || {}, cfg);
	Smart.app.project.Document.ProjectGrid = function(config) {
		this.actions = new Smart.app.project.Document.Action(this);
		var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
					header : '名称',
					dataIndex : 'NAME',
					sortable : false
				}, {
					header : '文档名称',
					dataIndex : 'DOC_NAME',
					sortable : false,
					renderer : this.actions.renderForDownload
				},
				{
					header : '创建人',
					dataIndex : 'CREATER',
					sortable : false
				}, {
					header : '创建时间',
					dataIndex : 'CREATE_TIME',
					sortable : false
				}

		]);
		config.cm = cm
		Smart.app.project.Document.ProjectGrid.superclass.constructor.call(
				this, config);
	}
	Ext.extend(Smart.app.project.Document.ProjectGrid,
			Smart.app.project.Document.Grid, {

			})
	var documentGird = new Smart.app.project.Document.ProjectGrid(props);

	var edit_project_topPanel = new Ext.FormPanel({
		frame : true,
		id : "form_project_edit_form",
		autoWidth : true,
		border : false,
		height : parentElement.getInnerHeight(),
		layout : 'fit',
		defaultType : 'textfield',
		autoScroll : true,
		items : {
			xtype : 'tabpanel',
			activeTab : 0,
			plain : true,
			items : [{
				title : '繁殖记录',
				bodyStyle : 'padding:10px',
				labelAlign:'right',
				//autoHeight : true,
				layout : 'fit',
				autoScroll:true,
				defaults:{
					readOnly:(parentElement.params.operator_action == 'detail'),
					width:'96%',
					autoWidth:false
				},
				items : [{
					xtype : 'fieldset',
					autoHeight : true,
					title : "必填项",
					collapsible : true,
					items : [
							{
						width:450,
						layout : 'form',
						items : [
						         {xtype : 'hidden',name : 'id',allowBlank : true},
						         {xtype : 'textfield',name : 'breedId',allowBlank : true,fieldLabel : '母驴号',anchor : '80%'},
						         {xtype : 'datefield',format:'Y-m-d',name : 'produceDate',allowBlank : true,fieldLabel : '产驴日期',anchor : '80%'},
						         {xtype : 'radiogroup',
										fieldLabel : '性别',
										columns : 2,
										items : [{
													boxLabel : '母驴',
													checked : true,
													name : 'sex',
													inputValue : '母'
												}, {
													boxLabel : '公驴',
													name : 'sex',
													inputValue : '公'
												}]},
								 {xtype : 'datefield',format:'Y-m-d',name : 'miscarry',allowBlank : true,fieldLabel : '流产日期',anchor : '80%'},
								 {xtype : 'datefield',format:'Y-m-d',name : 'deathDate',allowBlank : true,fieldLabel : '死亡日期',anchor : '80%'},
						         {xtype : 'textfield',name : 'deliverId',allowBlank : true,fieldLabel : '接产人',anchor : '80%'}
						         ]
					}
					]
				},{
					xtype : 'fieldset',
					title : '选填项',
					collapsible : true,
					collapsed : false,
					autoHeight : true,
					items : [{
								fieldLabel : '备注',
								name : 'remark',
								xtype : 'textarea',
								height : 50,
								anchor : '97%'
							}]
				}]
			}
			]
		}
	});

	edit_project_topPanel.render(parentElement.body);
//	edit_project_topPanel.resetDocumentPanel = function(id) {
//		documentGird.setDisabled(false);
//		Ext.getCmp('projet:contant:panel').setDisabled(false);
//		props.id = id;
//		parentElement.params.id = id;
//	};
	if (parentElement.params.operator_action == 'modify'
			|| parentElement.params.operator_action == 'detail') {

		edit_project_topPanel.load({
					url : 'sire/load',
					params : {
						id : parentElement.params.id
					},
					success : function(form, action) {},
					failure : function(form, action) {
						alert(action.failureType)
					}
				});
		//edit_project_topPanel.resetDocumentPanel(parentElement.params.kid)

	}
})
</script>

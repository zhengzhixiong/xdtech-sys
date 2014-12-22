<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>

<script type="text/javascript">
   //当前菜单节点
   var currentMenuNode;
   var isLeaf;
   function loadMenuInfo(node) {
	   var pnode = $('#sysMenuTree').tree('getParent',node.target); 
 		console.log(pnode);
 		currentMenuNode = node;
		console.log(node);
		var pMenuId = '';
	    var pMenuName= '';
	    if (pnode) {
		   pMenuId = pnode.id;
		   pMenuName = pnode.text;
	    }else {
		   
	    }
		var menu = {
			pMenuId:pMenuId,
			pMenuName:pMenuName,
			id:node.id
		};
		console.log(menu);
		$.post('menuFunction.do?loadMenuInfo',{data:JSON.stringify(menu)},function(result){
       	console.log(result);
           if (result){
           	$("#menuForm").form('load', result);  
           } else {
        	   showMsg('加载失败.');
           }
       },'json');
		isLeaf = $('#sysMenuTree').tree('isLeaf',node.target);
		printLog('是否根节点'+isLeaf);
		//加载按钮列表
		loadButtonList(node.id);
   }
   //加载按钮列表
   function loadButtonList(menuId) {
	   if (isLeaf) {
		   var params = $('#buttonlist').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
		   printLog(params);
		   if (menuId) {
			 params['menuId'] = menuId;
		   }
		   
		   printLog(params);
		   $('#buttonlist').datagrid('reload'); //设置好查询参数 reload 一下就可以了  
		} else {
	
		}
	  
   }
   
   function addMenuTreeItem() {
	   $('#menuForm').form('clear'); 
	   var node =$("#sysMenuTree").tree("getSelected");
	   console.log(node);
	   if (node) {
// 		   var pnode = $('#sysMenuTree').tree('getParent',node.target); 
		   var pMenuId = '';
// 		   var pMenuName= '';
// 		   if (pnode) {
		   var pMenuId  = node.id;
		   var pMenuName = node.text;
// 		   }else {
			   
// 		   }
		   var menu = {
					pMenuId:pMenuId,
					pMenuName:pMenuName,
					nameCN:'',
					pageUrl:'',
					iconName:''
				};
		   printLog(menu)
		   $("#menuForm").form('load', menu);  
	   } else {
// 		   showMsg('请选择菜单项.');
		   var menu = {
					pMenuId:'',
					pMenuName:'',
					nameCN:'',
					pageUrl:'',
					iconName:''
				};
		   printLog(menu)
		   $("#menuForm").form('load', menu);  
	   }
   }
   
   function removeMenu() {
// 	   $('#userForm').form('clear');  
   }
   
   function saveMenu() {
	    var actionurl=$('#menuForm').attr('action');//提交路径
		var formData = new Object();
		$("#menuForm :input").each(function() {
			 formData[this.name] =$("#"+this.name ).val();
		});
		printLog(formData);
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : actionurl,// 请求的action路径
			data : formData,
			error : function() {// 请求失败处理函数
				showMsg('保存失败.');
			},
			success : function(data) {
				printLog(data);
				$('#sysMenuTree').tree('reload'); 
				showMsg('保存成功.');
			}
		});
   }
   var editIndex2 = undefined;//标示当前编辑行，提高效率，避免使用each遍历所有行来关闭正在编辑的行。
   function endEditing(){
       if (editIndex2 == undefined){return true;}
       if ($('#buttonlist').datagrid('validateRow', editIndex2)){
           var ed = $('#buttonlist').datagrid('getEditor', {index:editIndex2,field:'operationCode'});
//            var productname = $(ed.target).combobox('getText');
//            $('#buttonlist').datagrid('getRows')[editIndex2][''] = productname;
           $('#buttonlist').datagrid('endEdit', editIndex2);
           editIndex2 = undefined;
           return true;
       } else {
           return false;
       }
   }
    
   function buttonlist_onClickRow(index,data){
// 	   alert(index+'c'+editIndex2);
       if (editIndex2 != index){
           if (endEditing()){
               $('#buttonlist').datagrid('selectRow', index).datagrid('beginEdit', index);
               editIndex2 = index;
           } else {
               $('#buttonlist').datagrid('selectRow', editIndex2);
           }
       }
   }
    
   function buttonlist_append(){
       if (endEditing()){
           $('#buttonlist').datagrid('appendRow',{menuFunId:currentMenuNode.id,type:1});
           editIndex2 = $('#buttonlist').datagrid('getRows').length-1;
           $('#buttonlist').datagrid('selectRow', editIndex2).datagrid('beginEdit', editIndex2);
       }
   }
    
   function buttonlist_remove(){
       if (editIndex2 == undefined){return;}
       $('#buttonlist').datagrid('cancelEdit', editIndex2).datagrid('deleteRow', editIndex2);
       editIndex2 = undefined;
   }
    
   function buttonlist_save(){
       if (endEditing()){
//     	   alert();
           var inserted = $('#buttonlist').datagrid('getChanges', "inserted");
           var deleted = $('#buttonlist').datagrid('getChanges', "deleted");
           var updated = $('#buttonlist').datagrid('getChanges', "updated");
//            var effectRow = new Object();
//            if (inserted.length) {
//                    effectRow["inserted"] = JSON.stringify(inserted);
//            }
//            if (deleted.length) {
//                    effectRow["deleted"] = JSON.stringify(deleted);
//            }
//            if (updated.length) {
//                    effectRow["updated"] = JSON.stringify(updated);
//            }
		var effectRow = {
				inserted:inserted,
				deleted:deleted,
				updated:updated
		};
           printLog(effectRow);
           printLog(JSON.stringify(effectRow));
           $.post("operation.do?saveOrUpdate", {data:JSON.stringify(effectRow)}, function(result) {
                   if(result.success){
                	   showMsg('保存成功.');
                   	 $("#buttonlist").datagrid('acceptChanges');
                   }else {
                	   showMsg('保存失败.');
                   }
           }, "JSON");
       }
   }

  
</script>
<div class="easyui-layout" data-options="fit:true">
	 <div data-options="region:'west',split:true,title:'功能菜单',collapsible:false,border:false" style="width:200px;">
	  <ul id="sysMenuTree" class="easyui-tree" data-options="
	          url: '<%=basePath %>menuFunction.do?menuFuncTree',
	           method: 'get',
	          animate: true,
	          lines:true,
	          onClick:function(node){
	          		loadMenuInfo(node);
			  }">
	  </ul>
	 </div>
	 
	 <div data-options="region:'center',border:false">
       <div class="easyui-layout" data-options="fit:true">
       		<div data-options="region:'north'" style="height:62px;overflow: hidden;">
       			<div style="border:1px solid #ddd;">
			        <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="javascript:addMenuTreeItem()">新增</a>
			        <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="javascript:removeMenu()">删除</a>
			    	<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="javascript:saveMenu()">保存</a>
			    </div>
			    <form id="menuForm" action="menuFunction.do?saveMenuInfo">
			    	<input type="hidden" id="pMenuId" name="pMenuId"/>
			    	<input type="hidden" id="id" name="id"/>
			    	<table>
			    		<tr>
			    			<td>父菜单:</td>
			    			<td><input type="text" id="pMenuName" name="pMenuName" disabled="disabled"></input></td>
			    			<td>菜单编码:</td>
			    			<td><input type="text" id="operationCode" name="operationCode" ></input></td>
			    			<td>菜单名称:</td>
			    			<td><input type="text" id="nameCN" name="nameCN" ></input></td>
			    			<td>菜单页面:</td>
			    			<td><input type="text" id="pageUrl" name="pageUrl" ></input></td>
			    			<td>菜单图标:</td>
			    			<td><input type="text" id="iconName" name="iconName" ></input></td>
			    		</tr>
			    	</table>
			    </form>
       		</div>
       		<div data-options="region:'center',border:false">
		        <easy:datagrid id="buttonlist"  title="按钮列表" url="operation.do?loadByMenuId"  pagination="false" onClickRow="buttonlist_onClickRow(index,data);" toolbar="buttonlist_toolbar" >
		        	<easy:datagridColumn field="id"/>
		        	<easy:datagridColumn field="menuFunId" hidden="true"/>
		        	<easy:datagridColumn field="type" hidden="true"/>
		        	<easy:datagridColumn field="operationCode" fieldName="权限编码" editor="{type:'text'}" width="100"/>
		        	<easy:datagridColumn field="labelName" fieldName="显示名称" editor="{type:'text'}" width="100"/>
		        	<easy:datagridColumn field="iconName" fieldName="图标" editor="{type:'text'}" width="80"/>
		        	<easy:datagridColumn field="description" fieldName="描述" editor="{type:'text'}" width="400"/>
		        </easy:datagrid>
				<easy:toolbar id="buttonlist_toolbar">
		       		<easy:toolbarButton iconCls="icon-add" labelName="新增"  onclick="buttonlist_append()"></easy:toolbarButton>
		       		<easy:toolbarButton iconCls="icon-remove" labelName="移除"  onclick="buttonlist_remove()"></easy:toolbarButton>
		       		<easy:toolbarButton iconCls="icon-save" labelName="保存" operationCode="S010204" onclick="buttonlist_save()"></easy:toolbarButton>
		       	</easy:toolbar>
       		</div>
       </div>
     </div>
</div>

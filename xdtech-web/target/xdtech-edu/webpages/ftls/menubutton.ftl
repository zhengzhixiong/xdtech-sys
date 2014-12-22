
<#macro loadMenu menus>
<div>
<#list menus as menu>
	<#if (menu.children)?size==0>
		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'${menu.icon}'">${menu.name}</a>
	<#else>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm${menu.id}',iconCls:'${menu.icon}'">${menu.name}</a>
	</#if>
</#list>
</div>

<#list menus as menu>
	<div id="mm${menu.id}" style="width: 150px;">
	<#list menu.children as mc>
		<@loadSubMenu mc />
	</#list>
	</div>
</#list>
</#macro>


<#macro loadSubMenu menu>
	
		<#if (menu.children)?size==0>
			<div onclick="window.mainpage.clickMenuAddModelTab('${menu.id}','${menu.name}','${menu.parent.id}','${menu.url}','${menu.icon}',${menu.iframe?c})" data-options="iconCls:'${menu.icon}'">${menu.name}</div>
		<#else>
			<div data-options="iconCls:'icon-woocons-32-printer'">
				<span>${menu.name}</span>
				<#list menu.children as mc>
					<@loadSubMenu mc />
				</#list>
			</div>
		</#if>
	
</#macro>
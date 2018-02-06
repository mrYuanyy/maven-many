<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品展示</title>
<!-- 引入jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<!-- 引入皮肤包 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-easyui-1.4.5/themes/default/easyui.css">
<!-- 引入图标 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-easyui-1.4.5/themes/icon.css">
</head>
<body>
	<h1 align="center">欢迎${login_user.empName }光临</h1>
	<a  target="_blank"  href="${pageContext.request.contextPath }/goods/showShopCar.do">查看我的购物车</a>
	<table id="goods_dataGrid_table"></table>  
</body>
<script type="text/javascript">
$('#goods_dataGrid_table').datagrid({    
    url:'<%=request.getContextPath()%>/goods/queryGoodsList.do',    
    columns:[[    
        {field:'goodsId',title:'商品编号',width:100},    
        {field:'goodsName',title:'商品名称',width:100,
        	formatter: function(value,row,index){
        		console.info(row.goodsId);
        		console.info("<a href='${pageContext.request.contextPath}/goods/toGoodsInfo.do?goodsId="+row.goodsId+"'>"+value+"</a>");
				return "<a target='_blank' href='${pageContext.request.contextPath}/goods/toGoodsInfo.do?goodsId="+row.goodsId+"'>"+value+"</a>"
			}
},
        {field:'goodsPrice',title:'商品价格',width:100,align:'right'},
        {field:'goodsCount',title:'商品库存',width:100,align:'right'},    
        {field:'goodsDate',title:'出厂日期',width:100,align:'right'},    
        {field:'goodsTypeId',title:'商品类型',width:100,align:'right'}    
    ]],
    pagination:true
}); 
</script>
</html>
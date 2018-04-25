<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<style>
	div {width:100%; height:100px; border:1px solid black; margin-top: 20px; text-align: center;}
</style>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<div>
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>
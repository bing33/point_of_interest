<%@ page language="java" import="cs5530.*" %>

<html>
<head>
<script type="text/javascript">

    var w = window,
    d = document,
    e = d.documentElement,
    g = d.getElementsByTagName('body')[0],
    x = w.innerWidth || e.clientWidth || g.clientWidth,
    y = w.innerHeight|| e.clientHeight|| g.clientHeight;

    document.getElementById("top").style.height = (y*0.3)+"px";

</script>
</head>
<body>
<div id="bottom" style="position: fixed; left: 0; right: 0; bottom: 0; height:100%; background-color: #f3e6ff;">
	<img src="food.jpg" style="width:304px;height:228px; position: relative; top: 250px; left: 20px">
	<img src="dinosaur-park-and-museum.jpg" style="width:304px;height:228px; position: relative; top: 280px; left: 25px">
	<img src="bowling.jpg" style="width:304px;height:228px; position: relative; top: 250px; left: 30px">
	<img src="massage.jpg" style="width:304px;height:228px; position: relative; top: 280px; left: 35px">
	<h2 style="color:purple; font-family: chalkduster; font-size:300%; text-indent: 1em; position: absolute;
	left: 150px; bottom: 10px;">All About Your Point of Interest</h2>
</div> 

<div id="top" style="position: fixed; left: 0; right: 0; top: 0; background-color: #e6e6e6;">
	<h1 style="color:purple; font-family: chalkduster; font-size:300%; text-indent: 1em;">Welcome To UTrack!</h1>
	<button type="button" onclick="javascript:window.location='register.jsp';" style="padding: 0; border: none; background: none; color:purple; font-family: chalkduster; font-size:100%; position:absolute; top: 115px; left: 750px; z-index: 1;">Register As A New User</button>
	<button type="button" onclick="javascript:window.location='login.jsp';"style="padding: 0; border: none; background: none; color:purple; font-family: chalkduster; font-size: 100%; position:absolute; top: 115px; left: 1000px; z-index: 1;">Log In As An Existing User</button>
</div>
</body>
</html>

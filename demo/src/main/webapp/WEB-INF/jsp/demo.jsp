<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/resources/html/newMain.html" flush="true" />
<style>
h3{font-weight:bold;color: #069;}
p{font-size: 12px;line-height: 1.75;color:#666;}
.highlight{padding:9px 14px;margin-bottom:14px;background-color:#f7f7f9;border:1px solid #e1e1e8;border-radius:4px}
.highlight pre {
    background-color: transparent;
    border: 0 none;
    margin-bottom: 0;
    margin-top: 0;
    padding: 0;
    white-space: nowrap;
    word-break: normal;
}
.k {
    color: #069;
}
.nt {
    color: #2f6f9f;
}
.nd {
    color: #99f;
}
</style>
<body id="body" style="background:#fff;">
<div class="col-lg-12">
	<h3>引用的文件</h3>
	<p>头部的引文文件改为newMain.html</p>
	<div class="highlight">
		<pre>
			&lt;jsp:include page=&quot;/resources/html/newMain.html&quot; flush=&quot;true&quot; /&gt;
		</pre>
	</div>
	<h3>页面主体结构</h3>
	<p>页面主体分为标题，搜索条件区域，表单区域</p>
	<div class="highlight">
		<pre>
				&lt;body id=&quot;body&quot; style=&quot;background:#fff;&quot;&gt;<br/>
				&lt;div class=&quot;col-lg-12&quot;&gt;<br/>
				&lt;h4 class=&quot;page-header&quot;&gt;这里是标题&lt;/h4&gt;<br/>
	&lt;/div&gt;<br/>
	&lt;div class=&quot;col-lg-12&quot;&gt;<br/>
		这里是搜索条件区域,一个或多个.col-lg-12的div<br/>
	&lt;/div&gt;<br/>
	&lt;div style=&quot;margin-top: 20px;&quot;&gt;<br/>
		这里是表单区域<br/>
	&lt;/div&gt;<br/>
	&lt;/body&gt;
		</pre>
	</div>
	<h3>搜索条件区域</h3>
	<p>增加一个或者多个按钮</p>
	<div class="showDemo" style="margin-left:-15px;">
		<div class="col-lg-12" style="margin-top:10px;">
			<a class="btn btn-primary btn-sm" id="addBtn"><i class="fa fa-plus-circle"></i>  按钮1</a>
			<a class="btn btn-primary btn-sm" id="hideBtn"><i class="fa fa-plus-circle"></i>  按钮2</a>
		</div>
	</div>
	<div class="highlight" style="margin-top:10px;">
		<pre>
			&lt;a class=&quot;btn btn-primary btn-sm&quot; id=&quot;addBtn&quot;&gt;&lt;i class=&quot;fa fa-plus-circle&quot;&gt;&lt;/i&gt;  按钮1&lt;/a&gt;<br/>
			&lt;a class=&quot;btn btn-primary btn-sm&quot; id=&quot;hideBtn&quot;&gt;&lt;i class=&quot;fa fa-plus-circle&quot;&gt;&lt;/i&gt;  按钮2&lt;/a&gt;
		</pre>
	</div>
	<p>增加多种查询条件</p>
	<div class="showDemo" style="margin-left:-15px;">
		<div class="col-lg-12" style="margin-top:10px;">
	<div class="groupTime">
		<div class="form-group input-group">
	        <span class="input-group-addon">日期查询：
	        </span>
	        <input type="text" id="name" name="name" class="form-control time-input">
			<span class="input-group-btn">
				<button type="button" class="btn btn-default time-btn"><i class="fa fa-calendar"></i>
				</button>
			</span>
	    </div>
	    <div class="timeShowDv"></div>
    </div>
    <div style="width:280px;display:inline-block;">
	    <div class="form-group input-group">
	        <span class="input-group-addon">普通查询：
	        </span>
	        <input type="text" id="code" name="code" class="form-control">
	    </div>
    </div>
    <div style="width:280px;display:inline-block;">
    	<div class="form-group input-group">
    		<button id="search" name="search" class="btn btn-default" type="reset"><i class="fa fa-search"></i>   搜索</button>
		</div>
    </div>
</div>
	</div>
	<div class="highlight">
		<pre>
			<xmp >
<div class="col-lg-12" style="margin-top:10px;">
	<div class="groupTime">
		<div class="form-group input-group">
	        <span class="input-group-addon">日期查询：
	        </span>
	        <input type="text" id="name" name="name" class="form-control time-input">
			<span class="input-group-btn">
				<button type="button" class="btn btn-default time-btn"><i class="fa fa-calendar"></i>
				</button>
			</span>
	    </div>
	    <div class="timeShowDv"></div>
    </div>
    <div style="width:280px;display:inline-block;">
	    <div class="form-group input-group">
	        <span class="input-group-addon">普通查询：
	        </span>
	        <input type="text" id="code" name="code" class="form-control">
	    </div>
    </div>
    <div style="width:280px;display:inline-block;">
    	<div class="form-group input-group">
    		<button id="search" name="search" class="btn btn-default" type="reset"><i class="fa fa-search"></i>   搜索</button>
		</div>
    </div>
</div>
</xmp>
		</pre>
	</div>
</div>
<script>

</script>
</body>
</html>
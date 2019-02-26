<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="Error Page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="page-content">
    <div class="row">
      <div class="col-md-2">
        <div class="sidebar content-box" style="display: block;">
            <ul class="nav">
                <!-- Main menu -->
                <li class="current"><a href="controller?action=main">Home</a></li>
                <li><a href="controller?action=login">Login</a></li>
                <li><a href="controller?action=registration">Sign up</a></li>

            </ul>
         </div>
      </div>
      <div class="col-md-10">
        <div class="row">
            <div class="col-md-12">
                <div class="content-box-large">
                    <div class="panel-heading">
                        <div class="panel-title">New vs Returning Visitors</div>

                        <div class="panel-options">
                            <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                            <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                        </div>
                    </div>
                    <div class="panel-body">
                       Body here, can be table.
                    </div>
                </div>
            </div>
        </div>
      </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

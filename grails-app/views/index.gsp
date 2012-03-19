<html>
    <head>
        <title>Welcome to Grails</title>
        <meta name="layout" content="main" />
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;

        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        <div id="nav">
            <div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">
                    <h1>Navigation</h1>
                    <ul>
                    	<li><a href="member">Member</a></li>
                        <li><a href="committee">Committees</a></li>
                        <li><a href="event">Events</a></li>
                    </ul>
                    
                    <g:if test="${session?.user?.role == 'admin'}">
                    	<h1>Admin Menu</h1>
                    	<ul>
                    		<li><a href="apparatus">Apparatus</a></li>
                    		<li><a href="apparatusType">Apparatus Types</a></li>
                    		<li><a href="eventType">Event Types</a></li>
                    		<li><a href="clearance">Clearance</a></li>
                    		<li><a href="certification">Certification</a></li>
                    		<li><a href="certificationType">Certification Types</a></li>
                    		<li><a href="electedPosition">Elected Position</a></li>
                    		<li><a href="membershipType">Membership Types</a></li>
                    		<li><a href="positionType">Position Types</a></li>
                    		<li><a href="rank">Ranks</a></li>
                    		
                    	</ul>
                    </g:if>
                    
                    <g:if env="development">
	                    <h1>List of controllers for dev purposes:</h1>
		                <ul>
		                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
		                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
		                    </g:each>
		                </ul>
	                </g:if>
	                
                </div>
                <div class="panelBtm"></div>
            </div>
        </div>
        <div id="pageBody">
            <h1>Welcome to the AVFD Member Private Site</h1>
            <p>Blaah blah blah add some words here</p>
            <h2>Announcements</h2>
			<p>maybe add some announcements here</p>
        </div>
    </body>
</html>

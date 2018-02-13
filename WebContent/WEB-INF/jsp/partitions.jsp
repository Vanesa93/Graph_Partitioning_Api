<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:app>
    <jsp:body>
		<div class="container p4p">		
			<div class="row rowContainer">
				<form style="float: right; margin:0; padding:5px;" id="exportForm" method="POST" action="/GraphPartitioning/partitioning/export">					  
					<button type="submit" class="btn btn-primary ">Export results</button>
				</form>
				<div class="col-md-12" style="text-align:center;">
					<h4 style="text-align:center;margin-bottom:20px;font-size:25px;">Graph</h4>	
					<div style="width:100%;padding-left:20px;">					
						<div id="graph">
							<a class='downloadLink' id='download-link' href='#' download='graph.png'>Download Image</a>	
						</div>						
					</div>
				</div>
			</div>
			<div class="row rowContainer partitionsRowBorder">
				<div class="col-md-6 colRowContainer" style="text-align:center;padding-top: 5%;">
					<h4 style="text-align:center;margin-bottom:20px;font-size:25px;">Partition 1</h4>						
					<div style="width:100%;padding-left:20px;">					
						<div id="partition0">
							<a class='downloadLink' id='download-link-partition-1' href='#' download='graph.png'>Download image</a>	
						</div>
					</div>
				</div>
				<div class="col-md-6" style="text-align:center;padding-top: 5%;">
					<h4 style="text-align:center;margin-bottom:20px;font-size:25px;">Partition 2</h4>	
					<div style="width:100%;padding-left:20px;">
						<div id="partition1">
							<a class='downloadLink' id='download-link-partition-2' href='#' download='graph.png'>Download image</a>	
						</div>
					</div>
				</div>
			</div>
		</div>
		
	<script>
		 $(document).ready(function() {
			 sigma.parsers.json("files/" + "${firstPartitionFileName}", {
			    container: 'partition' + 0,
				    settings: {
	   			      defaultNodeColor: '#203a54',
	   			   		scalingMode: "inside"
				    }
				  });
			 sigma.parsers.json("files/" + "${secondPartitionFileName}", {
			    container: 'partition' + 1,
				    settings: {
				      defaultNodeColor: '#203a54',
				      scalingMode: "inside"
				    }
				  });
			 
			 sigma.parsers.json("files/" + "${filename}", {
				    container: 'graph',
					    settings: {
					      defaultNodeColor: '#203a54',
					      scalingMode: "inside"
					    }
			});
			
			setTimeout(function () {
				 sigma.renderers.def = sigma.renderers.canvas;
				 var sigmaInstance = sigma.instances()[0];
				 sigmaInstance.refresh();
				 var downloadLink = document.getElementById('download-link');
				 downloadLink.addEventListener('click', function (event) {
				     downloadLink.setAttribute('href', sigmaInstance.renderers[0].domElements.scene.toDataURL());
				 });
				}, 1000);
			
			setTimeout(function () {
				 sigma.renderers.def = sigma.renderers.canvas;
				 var sigmaInstance = sigma.instances()[1];
				 sigmaInstance.refresh();
				 var downloadLink = document.getElementById('download-link-partition-1');
				 downloadLink.addEventListener('click', function (event) {
				     downloadLink.setAttribute('href', sigmaInstance.renderers[0].domElements.scene.toDataURL());
				 });
				}, 1000);
			
			setTimeout(function () {
				 sigma.renderers.def = sigma.renderers.canvas;
				 var sigmaInstance = sigma.instances()[2];
				 sigmaInstance.refresh();
				 var downloadLink = document.getElementById('download-link-partition-2');
				 downloadLink.addEventListener('click', function (event) {
				     downloadLink.setAttribute('href', sigmaInstance.renderers[0].domElements.scene.toDataURL());
				 });
				}, 1000);
		});
	</script>
    </jsp:body>
</t:app>




<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:app>
    <jsp:body>
    	<div class="container p4p">
    		<div class="rowContainer">
			<h2 style="text-align:center;">Choose partitioning method*</h2>
				<div class="row"  style="display:inline-flex;padding-top:2%;width:100%;">
					<div class="col-md-6" style="width:80%; text-align:center;">
						<div id="graphToPartition"></div>
					</div>
					<div class="col-md-6" style="width:100%; text-align:center;">
						<form style="padding-top:15%;" id="partitioningMethodForm" method="POST" action="/GraphPartitioning/partitioning" modelAttribute="partitionsInput" >					  
						  <fieldset class="form-group" id= "approaches">
						    <legend>Methods for Graph Partitioning:</legend>
						    <div class="form-check">
						      <label class="form-check-label">
						        <input type="radio" class="form-check-input" name="approach" id="klin" value="klin">
						        Kernighan-Lin
						      </label>
						    </div>
						    <div class="form-check">
						    <label class="form-check-label">
						        <input type="radio" class="form-check-input" name="approach" id="fmat" value="fmat">
						       Fiduccia-Mattheyses
						      </label>
						    </div>
						    <div class="form-check">
						    <label class="form-check-label">
						        <input type="radio" class="form-check-input" name="approach" id="evol" value="evol" >
						        Evolutionary approach
						      </label>
						    </div>
						    <div class="form-check">
						    <label class="form-check-label">
						        <input type="radio" class="form-check-input" name="approach" id="naive" value="naive" >
						        Naive approach
						      </label>
						    </div>
						  </fieldset>
						  <input type="hidden" name="graph1" id="graph1" value="${graph}">
						  <button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	<script>
	$(document).ready(function() {
		$("#partitioningMethodForm").validate({
			 rules: {
				 approach: {
			          required: true, 
			        },
				  },
			 messages: {
				 approach: {
				       required:"Method required"
				 },
			 },
			 errorPlacement: function(error, element) {
				if (element.is(":radio")) {
	                error.appendTo(element.parents('#approaches'));
		        } else { // This is the default behavior 
	                error.insertAfter( element );
		        } 
		   },
		 });
		 sigma.parsers.json("files/" + "${filename}", {
			    container: 'graphToPartition',
				    settings: {
	   			      defaultNodeColor: '#203a54',
	   			   	  scalingMode: "inside"
				    }
				  });
	});
	</script>
    </jsp:body>
</t:app>



